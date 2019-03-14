package com.mubly.comewaves.view.fragment;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.model.adapter.DragAndSkidAdapter;
import com.mubly.comewaves.model.adapter.RecAdapter;
import com.mubly.comewaves.model.model.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * A simple {@link } subclass.
 */
public class AttentionsFragment extends BaseFragment {
    @BindView(R.id.drap_skip_rv)
    RecyclerView mRecyclerView;
    List<String> dataList = new ArrayList<>();
    DragAndSkidAdapter adapter;
    public static AttentionsFragment newInstance(int type) {
        AttentionsFragment fragment = new AttentionsFragment();
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
        return R.layout.fragment_attentions;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        RecAdapter adapter = new RecAdapter();
        mRecyclerView.setAdapter(adapter);
//        dataList.add("wqer8w");
//        dataList.add("wqer8w");
//        dataList.add("wqer8w");
//        dataList.add("wqer8w");
//        adapter = new DragAndSkidAdapter<String>(dataList) {
//            @Override
//            public int getLayout(int viewType) {
//                return R.layout.item_attentions_layout;
//            }
//
//            @Override
//            public void dealView(VH holder, String data, int position) {
//                holder.setText(R.id.item, "嘿嘿" + position);
//            }
//
//        };
//        //先实例化Callback
//        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
////用Callback构造ItemtouchHelper
//        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
////调用ItemTouchHelper的attachToRecyclerView方法建立联系
//        touchHelper.attachToRecyclerView(mRecyclerView);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        mRecyclerView.setAdapter(adapter);
    }
}
