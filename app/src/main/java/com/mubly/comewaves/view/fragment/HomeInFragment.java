package com.mubly.comewaves.view.fragment;


import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.mubly.comewaves.view.costomview.CircleImageView;
import com.mubly.comewaves.view.costomview.PileLayout;
import com.mubly.comewaves.view.interfaceview.HomeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;

public class HomeInFragment extends BaseFragment<HomePresent, HomeView> implements HomeView {
    @BindView(R.id.home_in_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    List<String> dataList = new ArrayList<>();
    SmartAdapter smartAdapter;

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
        dataList.add("121");
        dataList.add("121");
        dataList.add("121");
        dataList.add("121");
        smartAdapter = new SmartAdapter<String>(dataList) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_home_layout;
            }

            @Override
            public void dealView(VH holder, String data, int position) {
                holder.setText(R.id.user_name, "杰克琼斯");
                holder.setText(R.id.user_location, "清明上和城");
                holder.setText(R.id.user_distance, "1.2km");
                holder.setText(R.id.praise_tv, "1.2k");
                holder.setText(R.id.comment_tv, "889");
                holder.setText(R.id.praise_man_tv, "赵子龙，张翼德，刘玄德等人觉得很赞");
                holder.setText(R.id.content_tv, "下雨的日子，适合打秋风，看我家的小喵，是不是很好看");
                ImageView imageView = (ImageView) holder.getChildView(R.id.avatar_image);
//                RequestOptions.bitmapTransform(new CircleCrop())
//                RequestOptions requestOptions = RequestOptions.circleCropTransform();
                Glide.with(mContext).load(R.drawable.start_img).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageView);
                PileLayout mPileLayout = (PileLayout) holder.getChildView(R.id.pileLayout);
                initPraises(mPileLayout);
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
        return R.layout.fragment_home_in;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mSmartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore();
//                dataList.add("asyg");
//                smartAdapter.notifyDataSetChanged();

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                dataList.clear();
//                dataList.add("asyg");
                refreshlayout.finishRefresh();
                mPresenter.getHomeData(getArguments().getInt("status"));
//                smartAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void shoSuccess(List<HomeBean> homeBean) {
//        ToastUtils.showToast("获取成功");
    }


}
