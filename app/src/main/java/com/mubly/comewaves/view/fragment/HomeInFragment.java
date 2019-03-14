package com.mubly.comewaves.view.fragment;


import android.content.Intent;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.utils.GlideRoundTransform;
import com.mubly.comewaves.common.utils.ToastUtils;
import com.mubly.comewaves.model.adapter.SmartAdapter;
import com.mubly.comewaves.model.model.HomeBean;
import com.mubly.comewaves.present.HomePresent;
import com.mubly.comewaves.view.activity.GoodsInfoActivity;
import com.mubly.comewaves.view.costomview.CircleImageView;
import com.mubly.comewaves.view.costomview.PileLayout;
import com.mubly.comewaves.view.interfaceview.HomeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class HomeInFragment extends BaseFragment<HomePresent, HomeView> implements HomeView {
    @BindView(R.id.home_in_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    List<HomeBean> dataList = new ArrayList<>();
    SmartAdapter smartAdapter;
    int type;

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
        mPresenter.getHomeData(getArguments().getInt("status"));
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        smartAdapter = new SmartAdapter<HomeBean>(dataList) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_home_layout;
            }

            @Override
            public void dealView(VH holder, HomeBean data, int position) {
                holder.setText(R.id.user_name, data.getUser_name());
                holder.setText(R.id.user_location, data.getLocation());
                holder.setText(R.id.user_distance, "1.2km");
                holder.setText(R.id.praise_tv, data.getFabulous_num() + "");
                holder.setText(R.id.comment_tv, data.getReport_num() + "");
                holder.setText(R.id.praise_man_tv, "赵子龙，张翼德，刘玄德等人觉得很赞");
                holder.setText(R.id.content_tv, data.getPost_info());
                ImageView mImageView = (ImageView) holder.getChildView(R.id.user_photo_img);
                Glide.with(mContext).load(data.getFirst_url()).into(mImageView);
                ImageView imageView = (ImageView) holder.getChildView(R.id.avatar_image);
                Glide.with(mContext).load(data.getUser_head()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageView);
                PileLayout mPileLayout = (PileLayout) holder.getChildView(R.id.pileLayout);
                initPraises(mPileLayout);
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                        intent.putExtra("type", type);
                        startActivity(intent);
                    }
                });
            }


        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mRecyclerView.setAdapter(smartAdapter);
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
        type=getArguments().getInt("status");
        return R.layout.fragment_home_in;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mSmartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.getHomeData(getArguments().getInt("status"));

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getHomeData(getArguments().getInt("status"));
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
        smartAdapter.notifyDataSetChanged();
    }


}
