package com.mubly.comewaves.view.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;

import butterknife.BindView;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.email_sign_in_button)
    Button ack;
    String[] autoString = new String[]
            {"690317264@qq.com", "218734619", "54876239", "b235235b", "5345", "bcdvSvf", "wetwet",
                    "Google Map", "Google Android"};

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        super.initView();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, autoString);
        // AutoCompleteTextView
        AutoCompleteTextView autoCompleteTextView =
                (AutoCompleteTextView) findViewById(R.id.email);
        autoCompleteTextView.setAdapter(adapter);     // 绑定adapter
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

