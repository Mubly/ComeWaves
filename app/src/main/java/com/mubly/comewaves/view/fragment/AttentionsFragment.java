package com.mubly.comewaves.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.model.adapter.DragAndSkidAdapter;
import com.mubly.comewaves.model.model.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttentionsFragment extends BaseFragment {
    @BindView(R.id.drap_skip_rv)
    RecyclerView mRecyclerView;
    List<String> dataList = new ArrayList<>();
    DragAndSkidAdapter adapter;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_attentions;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        dataList.add("wqer8w");
        dataList.add("wqer8w");
        dataList.add("wqer8w");
        dataList.add("wqer8w");
        adapter = new DragAndSkidAdapter<String>(dataList) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_attentions_layout;
            }

            @Override
            public void dealView(VH holder, String data, int position) {
                holder.setText(R.id.item, "嘿嘿" + position);
            }

        };
        //先实例化Callback
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
//用Callback构造ItemtouchHelper
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
//调用ItemTouchHelper的attachToRecyclerView方法建立联系
        touchHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(adapter);
    }
}
