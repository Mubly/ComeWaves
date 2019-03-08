package com.mubly.comewaves.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;

/**
 * 首页进入二级页面
 */
public class GoodsInfoActivity extends BaseActivity {
    private int type;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        type = getIntent().getIntExtra("type", 0);
        return R.layout.activity_goods_info;
    }

}
