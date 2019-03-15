package com.mubly.comewaves.videoplayer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mubly.comewaves.R;
import com.mubly.comewaves.model.model.HomeBean;

import java.util.List;



/**
 * Created by guoshuyu on 2017/1/9.
 */

public class RecyclerAutoDetialAdapter extends RecyclerView.Adapter {
    private final static String TAG = "RecyclerBaseAdapter";

    private List<HomeBean> itemDataList = null;
    private Context context = null;

    public RecyclerAutoDetialAdapter(Context context, List<HomeBean> itemDataList) {
        this.itemDataList = itemDataList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_home_layout, parent, false);
        final RecyclerView.ViewHolder holder = new RecyclerItemAutoDetiallHolder(context, v);
        return holder;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        RecyclerItemAutoDetiallHolder recyclerItemViewHolder = (RecyclerItemAutoDetiallHolder) holder;
        recyclerItemViewHolder.setRecyclerBaseAdapter(this);
        recyclerItemViewHolder.onBind(position, itemDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemDataList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    public void setListData(List<HomeBean> data) {
        itemDataList = data;
        notifyDataSetChanged();
    }
}
