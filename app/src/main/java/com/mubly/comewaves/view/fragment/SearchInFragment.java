package com.mubly.comewaves.view.fragment;


import android.content.Intent;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.Constant;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.network.ApiUrls;
import com.mubly.comewaves.common.utils.CommUtil;
import com.mubly.comewaves.model.adapter.AGVRecyclerViewAdapter;
import com.mubly.comewaves.model.adapter.AsymmetricRecyclerViewAdapter;
import com.mubly.comewaves.model.adapter.SmartAdapter;
import com.mubly.comewaves.model.model.CategoryVo;
import com.mubly.comewaves.model.model.DemoItem;
import com.mubly.comewaves.present.SearchPresent;
import com.mubly.comewaves.view.activity.SearchInfoActivity;
import com.mubly.comewaves.view.costomview.AsymmetricItem;
import com.mubly.comewaves.view.costomview.AsymmetricRecyclerView;
import com.mubly.comewaves.view.costomview.SpacesItemDecoration;
import com.mubly.comewaves.view.interfaceview.SearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link } subclass.
 */
public class SearchInFragment extends BaseFragment<SearchPresent, SearchView> implements SearchView {
    @BindView(R.id.search_content_rv)
    AsymmetricRecyclerView mRecyclerView;
    List<DemoItem> dataList = new ArrayList<>();
    private int type;

    RecyclerViewAdapter adapter = null;

    public static SearchInFragment newInstance(int type) {
        SearchInFragment fragment = new SearchInFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected SearchPresent createPresenter() {
        return new SearchPresent();
    }

    @Override
    protected int getLayoutId() {
        type = getArguments().getInt("type", 0);
        return R.layout.fragment_search_in;
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.getCategaryTwo(type);
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        adapter = new RecyclerViewAdapter(dataList);
        mRecyclerView.setRequestedColumnCount(3);//设置三行
        mRecyclerView.setDebugging(true);//Debug模式
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setRequestedHorizontalSpacing(CommUtil.dip2px(mContext, 3));//横向间隔
        mRecyclerView.addItemDecoration(
                new SpacesItemDecoration(getResources().getDimensionPixelSize(R.dimen.size_1dp)));//间隔
        mRecyclerView.setAdapter(new AsymmetricRecyclerViewAdapter<>(mContext, mRecyclerView, adapter));
        mRecyclerView.setOnItemClickListener(new AsymmetricRecyclerView.ItemOnClickListener() {
            @Override
            public void onItemClick(int index, View v) {
                Intent intent=new Intent(mContext, SearchInfoActivity.class);
                intent.putExtra("categoryId",dataList.get(index).getCate_id());
                startActivity(intent);
            }
        });
    }

    @Override
    public void initEvent() {
        super.initEvent();

    }

    @Override
    public void getOneTab(List<CategoryVo> categoryVoList) {
        dataList.clear();
        dataList.addAll(CommUtil.creatItems(categoryVoList));
        adapter.notifyDataSetChanged();
    }

    class RecyclerViewAdapter extends AGVRecyclerViewAdapter<ViewHolder> {
        private final List<DemoItem> items;

        RecyclerViewAdapter(List<DemoItem> items) {
            this.items = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.bind(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public AsymmetricItem getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2 == 0 ? 1 : 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView textView;

        ViewHolder(ViewGroup parent, int viewType) {
            super(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.adapter_item_odd, parent, false));
            textView = (ImageView) itemView.findViewById(R.id.textview_odd);
        }

        void bind(final DemoItem item) {
            Glide.with(mContext).load(item.getImg_url()).into(textView);
        }
    }
}
