package com.mubly.comewaves.view.fragment;


import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.Constant;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.services.DataService;
import com.mubly.comewaves.model.adapter.SmartAdapter;
import com.mubly.comewaves.model.model.HomeBean;
import com.mubly.comewaves.model.model.ImgItemVo;
import com.mubly.comewaves.present.IsHadPresent;
import com.mubly.comewaves.view.activity.GoodsInfoActivity;
import com.mubly.comewaves.view.activity.MainActivity;
import com.mubly.comewaves.view.costomview.ScaleImageView;
import com.mubly.comewaves.view.costomview.SpacesItemDecoration;
import com.mubly.comewaves.view.interfaceview.IsHadView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;

/**
 * A simple {@link } subclass.
 */
public class IsHadInFragment extends BaseFragment<IsHadPresent, IsHadView> implements IsHadView {
    @BindView(R.id.ishadin_comment_rv)
    RecyclerView mRecyclerView;
    SmartAdapter smartAdapter;
    @BindView(R.id.ishad_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    List<HomeBean> dataList = new ArrayList<>();
    private List<String> imgSizeList = new ArrayList<>();
    private int page = 0;

    public static IsHadInFragment newInstance(int type) {
        IsHadInFragment fragment = new IsHadInFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter.getHomeData(Constant.IMAGE_TYPE_CODE, page);
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        smartAdapter = new SmartAdapter<HomeBean>(dataList) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_ishad_content_layout;
            }

            @Override
            public void dealView(VH holder, final HomeBean data, int position) {
                ImageView mImageView = (ImageView) holder.getChildView(R.id.ishad_content_img);
                mImageView.getLayoutParams().width = data.width;
                mImageView.getLayoutParams().height = data.height;
                Glide.with(mContext).load(data.getFirst_url()).apply(new RequestOptions().errorOf(R.drawable.ishad_1).override(data.width, data.height).bitmapTransform(new RoundedCorners(10))).into(mImageView);
                ImageView avtarImg = (ImageView) holder.getChildView(R.id.ishad_avtar_img);
                Glide.with(mContext).load(data.getUser_head()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(avtarImg);
                holder.setText(R.id.ishad_content_tv, data.getPost_info());
                holder.setText(R.id.ishad_username, data.getUser_name());
                holder.setText(R.id.ishad_pushdate, data.getAdd_time());
                holder.setText(R.id.ishad_comment_amount, data.getReport_num() + "");
                holder.setText(R.id.ishad_praise_amount, data.getFabulous_num() + "");
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                        intent.putExtra("type", Constant.IMAGE_TYPE_CODE);
                        intent.putExtra("postId", data.getPost_id());
                        startActivity(intent);
//                        startActivity(new Intent(mContext, IsHadCommentActivity.class));
                    }
                });
            }


        };
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        SpacesItemDecoration decoration = new SpacesItemDecoration(12);
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setAdapter(smartAdapter);
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                manager.invalidateSpanAssignments();
//            }
//        });
    }

    @Override
    protected IsHadPresent createPresenter() {
        return new IsHadPresent();
    }

    @Override
    protected int getLayoutId() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        return R.layout.fragment_is_had_in;
    }

    @Override
    public void showError(String s) {

    }

    @Override
    public void initEvent() {
        super.initEvent();
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                initData();
                smartRefreshLayout.finishLoadmore(3000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 0;
                initData();
                smartRefreshLayout.finishRefresh(3000);
            }
        });
    }

    @Override
    public void shoSuccess(List<HomeBean> data) {
        if (smartRefreshLayout.isRefreshing()) {
            smartRefreshLayout.finishRefresh();
            dataList.clear();
        } else if (smartRefreshLayout.isLoading()) {
            smartRefreshLayout.finishLoadmore();
        } else {
            dataList.clear();
        }

        if (null != data) {
            imgSizeList.clear();
            dataList.addAll(data);
            for (HomeBean homeBean : dataList) {
                if (homeBean.getFirst_url().equals("http://abc.lailang.wang/")) {
                    homeBean.setFirst_url("http://01imgmini.eastday.com/mobile/20190415/20190415092800_4fcbfcaef1c30e445e6fb780e56f6060_1.jpeg");
                }
                imgSizeList.add(homeBean.getFirst_url());
            }
        }
        DataService.startService(mContext, imgSizeList, "4");
//        smartAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dataEvent(List<ImgItemVo> data) {
        if (data.size() == dataList.size()) {
            for (int i = 0; i < data.size(); i++) {
                dataList.get(i).height = data.get(i).height;
                dataList.get(i).width = data.get(i).width;
            }
            smartAdapter.notifyDataSetChanged();
        } else {
            dataList.clear();
            smartAdapter.notifyDataSetChanged();
        }
    }
}
