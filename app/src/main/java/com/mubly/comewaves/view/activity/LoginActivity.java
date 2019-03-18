package com.mubly.comewaves.view.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.sharedpreference.AppConfig;

import butterknife.BindView;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.email_sign_in_button)
    Button ack;
    @BindView(R.id.register_new_amount)
    TextView registerAmount;
    //    @BindView(R.id.register_button)
//    Button registerBtn;
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
        registerAmount.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ReigsterActivity.class));
            }
        });
        ack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConfig.token.put("6ZKJ5aSfMTU1MTUxMDI2OTQz");
                finish();
            }
        });
//        registerBtn.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this, ReigsterActivity.class));
//            }
//        });
    }
}

