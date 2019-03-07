package com.mubly.comewaves.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;

/**
 * 查询二级页面
 */
public class SearchInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_info);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_info;
    }
}
