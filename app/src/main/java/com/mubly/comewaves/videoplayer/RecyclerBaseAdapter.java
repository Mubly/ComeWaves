package com.mubly.comewaves.videoplayer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mubly.comewaves.R;
import com.mubly.comewaves.model.model.HomeBean;
import com.shuyu.gsyvideoplayer.utils.GSYVideoHelper;

import java.util.List;



/**
 * Created by GUO on 2015/12/3.
 */

/**
 * Created by Nelson on 15/11/9.
 */
public class RecyclerBaseAdapter extends RecyclerView.Adapter {

    private final static String TAG = "RecyclerBaseAdapter";

    private List<HomeBean> itemDataList = null;

    private Context context = null;

    private GSYVideoHelper smallVideoHelper;

    private GSYVideoHelper.GSYVideoHelperBuilder gsySmallVideoHelperBuilder;

    public RecyclerBaseAdapter(Context context, List<HomeBean> itemDataList) {
        this.itemDataList = itemDataList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_video_item, parent, false);
        final RecyclerView.ViewHolder holder = new RecyclerItemViewHolder(context, v);
        return holder;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        RecyclerItemViewHolder recyclerItemViewHolder = (RecyclerItemViewHolder) holder;
        recyclerItemViewHolder.setVideoHelper(smallVideoHelper, gsySmallVideoHelperBuilder);
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

    public GSYVideoHelper getVideoHelper() {
        return smallVideoHelper;
    }

    public void setVideoHelper(GSYVideoHelper smallVideoHelper, GSYVideoHelper.GSYVideoHelperBuilder gsySmallVideoHelperBuilder) {
        this.smallVideoHelper = smallVideoHelper;
        this.gsySmallVideoHelperBuilder = gsySmallVideoHelperBuilder;
    }
}
