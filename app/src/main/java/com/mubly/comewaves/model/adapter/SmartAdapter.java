package com.mubly.comewaves.model.adapter;

import java.util.List;

/**
 * Created by PC on 2018/12/28.
 */

public abstract class SmartAdapter<T> extends BaseRecyclerViewAdapter<T> {

    public SmartAdapter(List<T> data) {
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

    public abstract int getLayout(int viewType);

    public abstract void dealView(VH holder, T data, int position);

}
