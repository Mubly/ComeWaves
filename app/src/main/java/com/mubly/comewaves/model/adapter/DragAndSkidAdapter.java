package com.mubly.comewaves.model.adapter;

import com.mubly.comewaves.model.interfaces.ItemTouchHelperAdapter;

import java.util.Collections;
import java.util.List;

/**
 * 可拖动与侧滑的Adapter
 */

public abstract class DragAndSkidAdapter<T> extends BaseRecyclerViewAdapter<T> implements ItemTouchHelperAdapter {

    public DragAndSkidAdapter(List<T> data) {
        super(data);
    }

    @Override
    public int getLayoutId(int viewType) {
        return getLayout(viewType);
    }

    @Override
    public void convert(VH holder, T data, int position) {
        dealView(holder, data, position);
    }

    @Override
    public int getItemViewTypes(int poition, List<T> data) {
        return 0;
    }

    /**
     * 监测item的移动
     * @param fromPosition
     * @param toPosition
     */
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //交换位置
        onItemDataChange(fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

    /**
     * 做item的删除处理
     * @param position
     */
    @Override
    public void onItemDissmiss(int position) {
        //移除数据
        onItemdelete(position);
        notifyItemRemoved(position);

    }

    public abstract int getLayout(int viewType);

    public abstract void dealView(VH holder, T data, int position);


}
