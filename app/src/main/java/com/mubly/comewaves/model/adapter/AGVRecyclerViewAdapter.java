package com.mubly.comewaves.model.adapter;

import android.support.v7.widget.RecyclerView;

import com.mubly.comewaves.view.costomview.AsymmetricItem;

public abstract class AGVRecyclerViewAdapter<VH extends RecyclerView.ViewHolder>
    extends RecyclerView.Adapter<VH> {
  public abstract AsymmetricItem getItem(int position);
}
