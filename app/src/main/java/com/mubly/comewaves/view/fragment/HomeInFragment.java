package com.mubly.comewaves.view.fragment;


import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.Constant;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.sharedpreference.AppConfig;
import com.mubly.comewaves.common.utils.CommUtil;
import com.mubly.comewaves.common.utils.GlideRoundTransform;
import com.mubly.comewaves.common.utils.TextViewUtils;
import com.mubly.comewaves.common.utils.ToastUtils;
import com.mubly.comewaves.model.adapter.SmartAdapter;
import com.mubly.comewaves.model.livedatabus.LiveDataBus;
import com.mubly.comewaves.model.model.EventBusEvent;
import com.mubly.comewaves.model.model.HomeBean;
import com.mubly.comewaves.present.HomePresent;
import com.mubly.comewaves.videoplayer.RecyclerAutoDetialAdapter;
import com.mubly.comewaves.videoplayer.RecyclerBaseAdapter;
import com.mubly.comewaves.videoplayer.ScrollCalculatorHelper;
import com.mubly.comewaves.view.activity.GoodsInfoActivity;
import com.mubly.comewaves.view.activity.PhoneLoginActivity;
import com.mubly.comewaves.view.costomview.CircleImageView;
import com.mubly.comewaves.view.costomview.PileLayout;
import com.mubly.comewaves.view.costomview.SpacesItemDecoration;
import com.mubly.comewaves.view.interfaceview.HomeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;

public class HomeInFragment extends BaseFragment<HomePresent, HomeView> implements HomeView {
    @BindView(R.id.home_in_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    List<HomeBean> dataList = new ArrayList<>();
    SmartAdapter smartAdapter;
    int type;//1视频2图片
    //自动播放辅助类
    ScrollCalculatorHelper scrollCalculatorHelper;
    LinearLayoutManager linearLayoutManager;
    RecyclerAutoDetialAdapter recyclerNormalAdapter;
    //是否全屏
    boolean mFull = false;
    int page = 0;
    private boolean isInit;

    public static HomeInFragment newInstance(int status) {
        HomeInFragment fragment = new HomeInFragment();
        Bundle args = new Bundle();
        args.putInt("status", status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData() {
        super.initData();
        //1视频 2图文
//        ImmersionBar
        mPresenter.getHomeData(getArguments().getInt("status"), page);
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        if (type == 1) {//视频
            isInit = true;
            //限定范围为屏幕一半的上下偏移180
            int playTop = CommonUtil.getScreenHeight(mContext) / 2 - CommonUtil.dip2px(mContext, 200);
            int playBottom = CommonUtil.getScreenHeight(mContext) / 2 + CommonUtil.dip2px(mContext, 100);
            //自定播放帮助类
            scrollCalculatorHelper = new ScrollCalculatorHelper(R.id.video_item_player, playTop, playBottom);


            recyclerNormalAdapter = new RecyclerAutoDetialAdapter(mContext, dataList);
            recyclerNormalAdapter.setCallHolderBack(new RecyclerAutoDetialAdapter.CallHolderBack() {
                @Override
                public void callBack(HomeBean homeBean) {
                    mPresenter.doPraise(homeBean.getPost_id());
                }

                @Override
                public void callAttent(HomeBean homeBean) {
                    mPresenter.doCollection(homeBean.getPost_id());
                }
            });
            linearLayoutManager = new LinearLayoutManager(mContext);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setAdapter(recyclerNormalAdapter);
            SpacesItemDecoration decoration = new SpacesItemDecoration(10);
            mRecyclerView.addItemDecoration(decoration);
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                int firstVisibleItem, lastVisibleItem;

                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    //导入列表的滑动状态
                    scrollCalculatorHelper.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                    //这是滑动自动播放的代码
                    if (!mFull) {
                        scrollCalculatorHelper.onScroll(recyclerView, firstVisibleItem, lastVisibleItem, lastVisibleItem - firstVisibleItem);
                    }
                }
            });
        } else {//图片
            smartAdapter = new SmartAdapter<HomeBean>(dataList) {
                @Override
                public int getLayout(int viewType) {
                    return R.layout.item_home_layout;
                }

                @Override
                public void dealView(VH holder, final HomeBean data, final int position) {
                    holder.setText(R.id.user_name, data.getUser_name());
                    holder.setText(R.id.user_location, data.getLocation());
                    holder.setText(R.id.user_distance, "1.2km");
                    holder.setText(R.id.praise_tv, data.getFabulous_num() + "");
                    holder.setText(R.id.comment_tv, data.getReport_num() + "");
//                    holder.setText(R.id.praise_man_tv, "赵子龙，张翼德，刘玄德等人觉得很赞");
                    TextView contentTv = (TextView) holder.getChildView(R.id.content_tv);
                    contentTv.setText(TextViewUtils.getWeiBoContent(mContext, data.getPost_info(), contentTv));
                    ImageView mImageView = (ImageView) holder.getChildView(R.id.user_photo_img);
                    mImageView.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(data.getFirst_url()).into(mImageView);
                    ImageView imageView = (ImageView) holder.getChildView(R.id.avatar_image);
                    Glide.with(mContext).load(data.getUser_head()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageView);
//                    PileLayout mPileLayout = (PileLayout) holder.getChildView(R.id.pileLayout);
//                    initPraises(mPileLayout);//头像悬浮堆砌
                    final TextView praiseTv = (TextView) holder.getChildView(R.id.praise_tv);
                    praiseTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!isLogin()){
                                return;
                            }
                            mPresenter.doPraise(data.getPost_id());
                            String count = praiseTv.getText().toString();
                            Drawable drawable = null;
                            if (data.getLike_status() == 0) {

                                drawable = mContext.getResources().getDrawable(R.mipmap.praise_light_icon);
                                praiseTv.setText(CommUtil.strLess(count, -1));
                                data.setLike_status(1);
                            } else {
                                drawable = mContext.getResources().getDrawable(R.mipmap.praise_icon);
                                praiseTv.setText(CommUtil.strLess(count, 1));
                                data.setLike_status(0);
                            }

                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            praiseTv.setCompoundDrawables(drawable, null, null, null);
                        }
                    });

                    final TextView attentTv = (TextView) holder.getChildView(R.id.attent_tv);
                    attentTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           if (!isLogin()){
                               return;
                           }
                            mPresenter.doCollection(data.getPost_id());
                            String count = attentTv.getText().toString();
                            Drawable drawable = null;
                            if (data.getCollect_status() == 0) {

                                drawable = mContext.getResources().getDrawable(R.mipmap.attent_red_icon);
                                attentTv.setText(CommUtil.strLess(count, -1));
                                data.setCollect_status(1);
                            } else {
                                drawable = mContext.getResources().getDrawable(R.mipmap.attent_no_icon);
                                attentTv.setText(CommUtil.strLess(count, 1));
                                data.setCollect_status(0);
                            }

                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            attentTv.setCompoundDrawables(drawable, null, null, null);
                        }
                    });
                    holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                            intent.putExtra("type", type);
                            intent.putExtra("postId", data.getPost_id());
                            startActivity(intent);
                        }
                    });


                }


            };
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            mRecyclerView.setAdapter(smartAdapter);
        }

    }

    public void initPraises(PileLayout pileLayout) {
        pileLayout.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        for (int i = 0; i < 6; i++) {
            CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_praise, pileLayout, false);
            Glide.with(mContext).load(R.drawable.start_img).into(imageView);
            pileLayout.addView(imageView);
        }

    }

    @Override
    protected HomePresent createPresenter() {
        return new HomePresent();
    }

    @Override
    protected int getLayoutId() {
        type = getArguments().getInt("status");
        return R.layout.fragment_home_in;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mSmartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                mPresenter.getHomeData(getArguments().getInt("status"), page);

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 0;
                mPresenter.getHomeData(getArguments().getInt("status"), page);
            }
        });


        LiveDataBus.get().with("onpause", Boolean.class)
                .observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {

                        if (aBoolean && isInit && type == Constant.VIDEO_TYPE_CODE) {
                            GSYVideoManager.onPause();
                        } else if (isInit && type == Constant.VIDEO_TYPE_CODE) {
                            GSYVideoManager.onResume();
                        }
                    }
                });
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void shoSuccess(List<HomeBean> homeBean) {
        if (mSmartRefreshLayout.isRefreshing()) {
            dataList.clear();
            mSmartRefreshLayout.finishRefresh();
        }
        if (mSmartRefreshLayout.isLoading()) {
            mSmartRefreshLayout.finishLoadmore();
        }
        dataList.addAll(homeBean);
        if (type == 1) {
            recyclerNormalAdapter.notifyDataSetChanged();
        } else {
            smartAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (newConfig.orientation != ActivityInfo.SCREEN_ORIENTATION_USER) {
            mFull = false;
        } else {
            mFull = true;
        }

    }

//    @Override
//    public void onBackPressed() {
//        if (GSYVideoManager.backFromWindowFull(mContext)) {
//            return;
//        }
//        super.onBackPressed();
//    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isInit && type == Constant.VIDEO_TYPE_CODE) {
            if (getUserVisibleHint()) {
                GSYVideoManager.onResume();
            } else {
                GSYVideoManager.onPause();
            }
        }
    }

    @Override
    public boolean getUserVisibleHint() {
        return super.getUserVisibleHint();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void homeInEvent(EventBusEvent messageEvent) {
//        if (messageEvent.type == 1) {
//            if (isInit && type == Constant.VIDEO_TYPE_CODE) {
//                if (getUserVisibleHint()) {
//                    GSYVideoManager.onResume();
//                } else {
//                    GSYVideoManager.onPause();
//                }
//            }
//        }
//
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }

    private boolean isLogin() {
        if (TextUtils.isEmpty(AppConfig.token.get())) {
            startActivity(new Intent(mContext, PhoneLoginActivity.class));
            return false;
        }
        return true;
    }
}
