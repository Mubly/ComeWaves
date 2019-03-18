package com.mubly.comewaves.view.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.utils.CommUtil;

import butterknife.BindView;


/**
 * 评论页面
 */
public class CommentActivity extends BaseActivity {
    @BindView(R.id.comment_edit)
    EditText commentEdit;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void initEvent() {
        super.initEvent();
//        CommUtil.keyboardEt(this);
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment;
    }
}
