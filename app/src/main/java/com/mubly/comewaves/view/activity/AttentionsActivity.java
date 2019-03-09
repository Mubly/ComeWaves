package com.mubly.comewaves.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.view.fragment.AttentionsFragment;

/**
 * 我的关注页面
 */
public class AttentionsActivity extends BaseActivity {


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
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new AttentionsFragment()).commit();
    }
}
