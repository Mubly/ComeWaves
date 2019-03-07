package com.mubly.comewaves.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;

/**
 * 商家信息页面
 */
public class MerchantActivity extends BaseActivity {



    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_merchant;
    }
}
