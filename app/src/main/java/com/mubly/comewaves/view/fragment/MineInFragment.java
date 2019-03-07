package com.mubly.comewaves.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.model.adapter.SmartAdapter;
import com.mubly.comewaves.view.costomview.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineInFragment extends BaseFragment {
    @BindView(R.id.mine_recycleView)
    RecyclerView mRecyclerView;
    List<String> dataList = new ArrayList<>();
    SmartAdapter smartAdapter;

    public static MineInFragment newInstance(int type) {
        MineInFragment fragment = new MineInFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine_in;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        dataList.add("34535");
        dataList.add("34535");
        dataList.add("34535");
        dataList.add("34535");
        dataList.add("34535");
        dataList.add("34535");
        dataList.add("34535");
        dataList.add("34535");
        dataList.add("34535");
        dataList.add("34535");
        dataList.add("34535");
        dataList.add("34535");
        dataList.add("34535");
        dataList.add("34535");
        dataList.add("34535");
        dataList.add("34535");
        smartAdapter = new SmartAdapter<String>(dataList) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_square_img_layout;
            }

            @Override
            public void dealView(VH holder, String data, int position) {
                ImageView imageView = (ImageView) holder.getChildView(R.id.square_img);
                if (position % 2 == 0) {
                    Glide.with(mContext).load(R.drawable.ishad_2).into(imageView);
                } else {
                    Glide.with(mContext).load(R.drawable.ishad_1).into(imageView);
                }
            }


        };
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(smartAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(10);
        mRecyclerView.addItemDecoration(decoration);
    }
}
