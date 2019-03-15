package com.mubly.comewaves.view.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;


/**
 * 评论页面
 */
public class CommentActivity extends BaseActivity {



    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment;
    }
}
