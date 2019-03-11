package com.mubly.comewaves.view.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.model.adapter.MyViewPageAdapter;
import com.mubly.comewaves.view.fragment.AttentionsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的关注页面
 */
public class AttentionsActivity extends BaseActivity {


    @BindView(R.id.top_back_btn)
    ImageButton topBackBtn;
    @BindView(R.id.top_tittle)
    TextView topTittle;
    @BindView(R.id.attention_tb)
    TabLayout attentionTb;
    @BindView(R.id.attention_viewpage)
    ViewPager attentionViewpage;
    List<String> title = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();
    @BindView(R.id.framelayout)
    ConstraintLayout framelayout;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_attentions;
    }

    @Override
    public void initView() {
        super.initView();
        title.add("店铺");
        title.add("达人");
        topTittle.setText("我的关注");
        fragmentList.add(AttentionsFragment.newInstance(0));
        fragmentList.add(AttentionsFragment.newInstance(1));
        attentionViewpage.setAdapter(new MyViewPageAdapter(getSupportFragmentManager(), title, fragmentList));
        attentionViewpage.setOffscreenPageLimit(2);
        attentionTb.setupWithViewPager(attentionViewpage);
    }



    @OnClick(R.id.top_back_btn)
    public void onViewClicked() {
        finish();
    }
}
