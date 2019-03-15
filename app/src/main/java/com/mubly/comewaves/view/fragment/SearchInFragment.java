package com.mubly.comewaves.view.fragment;


import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;


import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.model.adapter.SmartAdapter;
import com.mubly.comewaves.view.costomview.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link } subclass.
 */
public class SearchInFragment extends BaseFragment {
    @BindView(R.id.search_content_rv)
    RecyclerView mRecyclerView;
    SmartAdapter smartAdapter;
    List<String> contentList = new ArrayList<>();
    private int type;

    public static SearchInFragment newInstance(int type) {
        SearchInFragment fragment = new SearchInFragment();
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
        return R.layout.fragment_search_in;
    }

    @Override
    public void initData() {
        super.initData();
        contentList.add("984");
        contentList.add("984");
        contentList.add("984");
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        type = getArguments().getInt("type");
        smartAdapter = new SmartAdapter<String>(contentList) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_search_img_layout;
            }

            @Override
            public void dealView(VH holder, String data, int position) {
//                holder.getChildView()
            }

        };
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(smartAdapter);

    }
}
