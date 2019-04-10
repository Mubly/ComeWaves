package com.mubly.comewaves.view.fragment;


import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.Constant;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.utils.ToastUtils;
import com.mubly.comewaves.model.adapter.SmartAdapter;
import com.mubly.comewaves.model.livedatabus.LiveDataBus;
import com.mubly.comewaves.model.model.UserPostVo;
import com.mubly.comewaves.present.MineInPresent;
import com.mubly.comewaves.view.activity.GoodsInfoActivity;
import com.mubly.comewaves.view.activity.GoodsInfoVideoActivity;
import com.mubly.comewaves.view.costomview.SpacesItemDecoration;
import com.mubly.comewaves.view.interfaceview.MineInView;
import com.mubly.comewaves.view.interfaceview.MineView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;

/**
 * A simple {@link } subclass.
 */
public class MineInFragment extends BaseFragment<MineInPresent, MineInView> implements MineInView {
    @BindView(R.id.mine_recycleView)
    RecyclerView mRecyclerView;
    @BindView(R.id.none_data_prompt)
    TextView nonePromptTv;
    //    @BindView(R.id.mine_tab_refresh)
//    SmartRefreshLayout refreshLayout;
    List<UserPostVo> dataList = new ArrayList<>();
    SmartAdapter smartAdapter;
    private int type;//1:我的发布 2，我的收藏
    private int page = 0;

    public static MineInFragment newInstance(int type) {
        MineInFragment fragment = new MineInFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected MineInPresent createPresenter() {
        return new MineInPresent();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        LiveDataBus.get().with("refreshUserInfo", Boolean.class).observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    page = 0;
                    initData();
                }
            }
        });
        LiveDataBus.get().with("refreshUserInfoMore", Integer.class).observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer==type) {
                    page++;
                    initData();
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        type = getArguments().getInt("type", 1);
        return R.layout.fragment_mine_in;
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.mytopAndFocus(type, page);
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        if (type == 1) {
            nonePromptTv.setText("您还未发布过帖子");
        } else {
            nonePromptTv.setText("您还未添加任何收藏");
        }
        smartAdapter = new SmartAdapter<UserPostVo>(dataList) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_square_img_layout;
            }

            @Override
            public void dealView(VH holder, final UserPostVo data, int position) {
                if (data.getStatus() == Constant.VIDEO_TYPE_CODE) {
                    holder.getChildView(R.id.start_video).setVisibility(View.VISIBLE);
                } else {
                    holder.getChildView(R.id.start_video).setVisibility(View.GONE);
                }
                ImageView imageView = (ImageView) holder.getChildView(R.id.square_img);
                Glide.with(mContext).load(data.getCollection()).into(imageView);
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (data.getStatus() == Constant.VIDEO_TYPE_CODE) {
                            videoInfo(data.getPost_id(), 1);
                        } else {
                            imgInfo(data.getPost_id(), 2);
                        }
                    }
                });
            }


        };
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(smartAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(10);
        mRecyclerView.addItemDecoration(decoration);
    }

    @Override
    public void requestSuccess(List<UserPostVo> data) {

        if (((MineFragment) getParentFragment()).isLoadMore()) {
            ((MineFragment) getParentFragment()).stopLoad();
        } else {
            dataList.clear();
        }
        if (null != data) {
            dataList.addAll(data);
        }
        if (dataList.size() == 0) {
            nonePromptTv.setVisibility(View.VISIBLE);
        } else {
            nonePromptTv.setVisibility(View.GONE);
        }
        smartAdapter.notifyDataSetChanged();
    }

    @Override
    public void error(String msg) {
//        ToastUtils.showToast(msg);
        if (((MineFragment) getParentFragment()).isLoadMore()) {
            ((MineFragment) getParentFragment()).stopLoad();
        }
    }

    void videoInfo(int postId, int type) {
        Intent intent = new Intent(mContext, GoodsInfoVideoActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("postId", postId);
        startActivity(intent);
    }

    void imgInfo(int postId, int type) {
        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("postId", postId);
        startActivity(intent);
    }
}
