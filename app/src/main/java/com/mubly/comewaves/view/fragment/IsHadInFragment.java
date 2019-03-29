package com.mubly.comewaves.view.fragment;


import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

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
import com.mubly.comewaves.common.Constant;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.model.adapter.SmartAdapter;
import com.mubly.comewaves.model.model.HomeBean;
import com.mubly.comewaves.present.IsHadPresent;
import com.mubly.comewaves.view.activity.GoodsInfoActivity;
import com.mubly.comewaves.view.activity.IsHadCommentActivity;
import com.mubly.comewaves.view.costomview.SpacesItemDecoration;
import com.mubly.comewaves.view.interfaceview.IsHadView;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;

/**
 * A simple {@link } subclass.
 */
public class IsHadInFragment extends BaseFragment<IsHadPresent, IsHadView> implements IsHadView {
    @BindView(R.id.ishadin_comment_rv)
    RecyclerView mRecyclerView;
    SmartAdapter smartAdapter;
    List<String> title = new ArrayList<>();
    List<HomeBean> dataList = new ArrayList<>();
    //    @BindView(R.id.ishadin_tv)
//    TextView mText;
    private int page = 0;

    public static IsHadInFragment newInstance(int type) {
        IsHadInFragment fragment = new IsHadInFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter.getHomeData(Constant.IMAGE_TYPE_CODE, page);
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
//        mText.setText("类型：" + getArguments().getInt("type"));
        smartAdapter = new SmartAdapter<HomeBean>(dataList) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_ishad_content_layout;
            }

            @Override
            public void dealView(VH holder, final HomeBean data, int position) {
                ImageView mImageView = (ImageView) holder.getChildView(R.id.ishad_content_img);
                Glide.with(mContext).load(data.getFirst_url()).apply(RequestOptions.bitmapTransform(new RoundedCorners(40))).into(mImageView);
                ImageView avtarImg = (ImageView) holder.getChildView(R.id.ishad_avtar_img);
                Glide.with(mContext).load(data.getUser_head()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(avtarImg);
                holder.setText(R.id.ishad_content_tv, data.getPost_info());
                holder.setText(R.id.ishad_username, data.getUser_name());
                holder.setText(R.id.ishad_pushdate, data.getAdd_time());
                holder.setText(R.id.ishad_comment_amount, data.getReport_num() + "");
                holder.setText(R.id.ishad_praise_amount, data.getFabulous_num() + "");
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                        intent.putExtra("type", Constant.IMAGE_TYPE_CODE);
                        intent.putExtra("postId", data.getPost_id());
                        startActivity(intent);
//                        startActivity(new Intent(mContext, IsHadCommentActivity.class));
                    }
                });
            }


        };

//        mRecyclerView.setNestedScrollingEnabled(false);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        mRecyclerView.setAdapter(smartAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(12);
        mRecyclerView.addItemDecoration(decoration);
    }

    @Override
    protected IsHadPresent createPresenter() {
        return new IsHadPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_is_had_in;
    }

    @Override
    public void showError(String s) {

    }

    @Override
    public void shoSuccess(List<HomeBean> data) {
        dataList.clear();
        if (null != data) {
            dataList.addAll(data);
        }
        smartAdapter.notifyDataSetChanged();
    }
}
