package com.mubly.comewaves.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseActivity;
import com.mubly.comewaves.common.base.BaseMvpView;
import com.mubly.comewaves.model.model.StartBean;
import com.mubly.comewaves.present.StartPresent;
import com.mubly.comewaves.view.interfaceview.StartView;

import java.util.List;

public class StartActivity extends BaseActivity<StartPresent,StartView> implements StartView {


    @Override
    protected StartPresent createPresenter() {
        return new StartPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.getStartImage();
    }

    @Override
    public void initView() {
        super.initView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(StartActivity.this,MainActivity.class));
                finish();
            }
        },2000);
    }

    @Override
    public void showStart(List<StartBean> startBeanList) {

    }
}
