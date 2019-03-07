package com.mubly.comewaves.view.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;

import butterknife.BindView;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.email_sign_in_button)
    Button ack;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        ack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

