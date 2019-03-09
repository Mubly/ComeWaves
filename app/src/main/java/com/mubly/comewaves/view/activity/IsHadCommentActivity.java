package com.mubly.comewaves.view.activity;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.model.adapter.SmartAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IsHadCommentActivity extends BaseActivity {

    @BindView(R.id.ishad_comment_avtar_iv)
    ImageView avtarImage;
    @BindView(R.id.top_back_btn)
    ImageButton topBackBtn;
    @BindView(R.id.top_tittle)
    TextView topTittle;
    @BindView(R.id.top_layout_right_tv)
    TextView topRightTv;
    @BindView(R.id.ishad_comment_rv)
    RecyclerView ishadCommentRv;
    SmartAdapter smartAdapter;
    List<String> dataList = new ArrayList<>();
    @BindView(R.id.top_layout)
    ConstraintLayout topLayout;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_is_had_comment;
    }

    @Override
    public void initView() {
        super.initView();
        topRightTv.setVisibility(View.GONE);
        topTittle.setText("评论详情");
        dataList.add("6745");
        dataList.add("6745");
        Glide.with(mContext).load(R.drawable.ishad_1).apply(RequestOptions.circleCropTransform()).into(avtarImage);
        smartAdapter = new SmartAdapter<String>(dataList) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_ishad_comment_detial_layout;
            }

            @Override
            public void dealView(VH holder, String data, int position) {

            }
        };
        ishadCommentRv.setLayoutManager(new LinearLayoutManager(this));
        ishadCommentRv.setAdapter(smartAdapter);
    }


    @OnClick(R.id.top_back_btn)
    public void onViewClicked() {
        finish();
    }
}
