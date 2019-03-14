package com.mubly.comewaves.view.costomview;

import android.graphics.Rect;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * recycleView 间隔
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        outRect.top = space;
//        if (parent.getChildAdapterPosition(view) == 0) {
//
//        }
    }
}
