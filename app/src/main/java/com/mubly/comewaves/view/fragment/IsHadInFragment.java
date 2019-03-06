package com.mubly.comewaves.view.fragment;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.model.adapter.SmartAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class IsHadInFragment extends BaseFragment {
    @BindView(R.id.ishadin_comment_rv)
    RecyclerView mRecyclerView;
    SmartAdapter smartAdapter;
    List<String> title = new ArrayList<>();
    List<Integer> imgList = new ArrayList<>();
    @BindView(R.id.ishadin_tv)
    TextView mText;

    public static IsHadInFragment newInstance(int type) {
        IsHadInFragment fragment = new IsHadInFragment();
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
    public void initData() {
        super.initData();
        title.add("wiueu");
        title.add("wiueu");
        title.add("wiueu");
        title.add("wiueu");
        title.add("wiueu");
        title.add("wiueu");
        title.add("wiueu");
        title.add("wiueu");
        title.add("wiueu");
        title.add("wiueu");
        title.add("wiueu");
        title.add("wiueu");
        title.add("wiueu");
        title.add("wiueu");
        title.add("wiueu");
        title.add("wiueu");
        imgList.add(R.drawable.ishad_1);
        imgList.add(R.drawable.ishad_2);
        imgList.add(R.drawable.ishad_3);
        imgList.add(R.drawable.ishad_1);
        imgList.add(R.drawable.ishad_2);
        imgList.add(R.drawable.ishad_3);
        imgList.add(R.drawable.ishad_1);
        imgList.add(R.drawable.ishad_2);
        imgList.add(R.drawable.ishad_3);
        imgList.add(R.drawable.ishad_1);
        imgList.add(R.drawable.ishad_2);
        imgList.add(R.drawable.ishad_3);
        imgList.add(R.drawable.ishad_1);
        imgList.add(R.drawable.ishad_2);
        imgList.add(R.drawable.ishad_3);
        imgList.add(R.drawable.ishad_1);
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        mText.setText("类型：" + getArguments().getInt("type"));
        smartAdapter = new SmartAdapter<String>(title) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_ishad_content_layout;
            }

            @Override
            public void dealView(VH holder, String data, int position) {
                ImageView mImageView = (ImageView) holder.getChildView(R.id.ishad_content_img);
                Glide.with(mContext).load(imgList.get(position)).apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(mImageView);
                ImageView avtarImg = (ImageView) holder.getChildView(R.id.ishad_avtar_img);
                Glide.with(mContext).load(R.drawable.start_img).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(avtarImg);
            }


        };

//        mRecyclerView.setNestedScrollingEnabled(false);
//        new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(smartAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(12);
        mRecyclerView.addItemDecoration(decoration);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_is_had_in;
    }

    private class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }
}