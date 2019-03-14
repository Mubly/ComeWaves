package com.mubly.comewaves.model.model;


import com.mubly.comewaves.model.interfaces.ItemTouchHelperAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private ItemTouchHelperAdapter mAdapter;


    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter){
        mAdapter = adapter;
    }
    @Override//这个方法用于让RecyclerView拦截向上滑动，向下滑动，想左滑动
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;    //允许上下的拖动
        int swipeFlags = ItemTouchHelper.LEFT;  //只允许从右向左侧滑
        return makeMovementFlags(dragFlags,swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        //onItemMove是接口方法进行数据交换
        mAdapter.onItemMove(viewHolder.getAdapterPosition(),viewHolder1.getAdapterPosition());
        return true;
    }
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //onItemDissmiss是接口方法进行数据删除
        mAdapter.onItemDissmiss(viewHolder.getAdapterPosition());
    }


}
