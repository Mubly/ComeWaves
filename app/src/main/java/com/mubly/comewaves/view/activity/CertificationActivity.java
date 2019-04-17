package com.mubly.comewaves.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;

/**
 * 用户认证
 */
public class CertificationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
