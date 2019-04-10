package com.mubly.comewaves.videoplayer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.utils.CommUtil;
import com.mubly.comewaves.model.model.HomeBean;

import java.util.List;

import static com.umeng.commonsdk.stateless.UMSLEnvelopeBuild.mContext;


/**
 * Created by guoshuyu on 2017/1/9.
 */

public class RecyclerAutoDetialAdapter extends RecyclerView.Adapter {
    private final static String TAG = "RecyclerBaseAdapter";

    private List<HomeBean> itemDataList = null;
    private Context context = null;
    CallHolderBack callHolderBack;

    public RecyclerAutoDetialAdapter(Context context, List<HomeBean> itemDataList) {
        this.itemDataList = itemDataList;
        this.context = context;
    }

    public void setCallHolderBack(CallHolderBack callHolderBack) {
        this.callHolderBack = callHolderBack;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_home_layout, parent, false);
        final RecyclerView.ViewHolder holder = new RecyclerItemAutoDetiallHolder(context, v);


        return holder;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final RecyclerItemAutoDetiallHolder recyclerItemViewHolder = (RecyclerItemAutoDetiallHolder) holder;
        recyclerItemViewHolder.setRecyclerBaseAdapter(this);
        recyclerItemViewHolder.onBind(position, itemDataList.get(position));
        recyclerItemViewHolder.praiseTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CommUtil.isLogin(context))
                    return;
                String count = recyclerItemViewHolder.praiseTv.getText().toString();
                Drawable drawable = null;
                if (itemDataList.get(position).getLike_status() == 0) {

                    drawable = context.getResources().getDrawable(R.mipmap.praise_light_icon);
                    recyclerItemViewHolder.praiseTv.setText(CommUtil.strLess(count, -1));
                    itemDataList.get(position).setLike_status(1);
                } else {
                    drawable = context.getResources().getDrawable(R.mipmap.praise_icon);
                    recyclerItemViewHolder.praiseTv.setText(CommUtil.strLess(count, 1));
                    itemDataList.get(position).setLike_status(0);
                }

                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                recyclerItemViewHolder.praiseTv.setCompoundDrawables(drawable, null, null, null);
                if (null != callHolderBack) {
                    callHolderBack.callBack(itemDataList.get(position));
                }
            }
        });

        //收藏
        recyclerItemViewHolder.attentTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CommUtil.isLogin(context))
                    return;
                String count = recyclerItemViewHolder.attentTv.getText().toString();
                Drawable drawable = null;
                if (itemDataList.get(position).getCollect_status() == 0) {

                    drawable = context.getResources().getDrawable(R.mipmap.attent_red_icon);
                    recyclerItemViewHolder.attentTv.setText(CommUtil.strLess(count, -1));
                    itemDataList.get(position).setCollect_status(1);
                } else {
                    drawable = context.getResources().getDrawable(R.mipmap.attent_no_icon);
                    recyclerItemViewHolder.attentTv.setText(CommUtil.strLess(count, 1));
                    itemDataList.get(position).setCollect_status(0);
                }

                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                recyclerItemViewHolder.attentTv.setCompoundDrawables(drawable, null, null, null);
                if (null != callHolderBack) {
                    callHolderBack.callAttent(itemDataList.get(position));
                }
            }
        });
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

    public interface CallHolderBack {
        void callBack(HomeBean homeBean);
        void callAttent(HomeBean homeBean);
    }

}
