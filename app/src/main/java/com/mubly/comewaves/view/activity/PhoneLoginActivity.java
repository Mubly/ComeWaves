package com.mubly.comewaves.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.CrossApp;
import com.mubly.comewaves.common.sharedpreference.AppConfig;
import com.mubly.comewaves.common.utils.EditViewUtil;
import com.mubly.comewaves.common.utils.TimeUtils;
import com.mubly.comewaves.model.interfaces.CallBack;
import com.mubly.comewaves.model.livedatabus.LiveDataBus;
import com.mubly.comewaves.model.model.LoginResBean;
import com.mubly.comewaves.present.PhoneLoginPresent;
import com.mubly.comewaves.view.interfaceview.PhoneLoginView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 手机登录页面
 */

public class PhoneLoginActivity extends BaseActivity<PhoneLoginPresent, PhoneLoginView> implements PhoneLoginView {
    @BindView(R.id.senCodeBtn)
    Button sandCodeBtn;
    TimeUtils mTimeUtils;
    @BindView(R.id.register_btn)
    Button registerBtn;
    @BindView(R.id.phone_no_et)
    EditText phoneEt;
    @BindView(R.id.phone_code_et)
    EditText phoneCodeEt;
    @BindView(R.id.login_by_password)
    TextView loginByPassTv;
    boolean isHasCode;


    @Override
    public void initView() {
        super.initView();
        mTimeUtils = new TimeUtils(sandCodeBtn, "重新发送");
    }

    @Override
    protected PhoneLoginPresent createPresenter() {
        return new PhoneLoginPresent();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        EditViewUtil.EditDatachangeLister(phoneEt, new CallBack() {
            @Override
            public void callBack(String data) {
                if (mPresenter.phoneCheck(data)) {
                    sandCodeBtn.setEnabled(true);
                } else {
                    sandCodeBtn.setEnabled(false);
                }
                registerBtn.setEnabled(false);
            }
        });
        EditViewUtil.EditDatachangeLister(phoneCodeEt, new CallBack() {
            @Override
            public void callBack(String data) {
                if (EditViewUtil.checkCode(data)) {
                    registerBtn.setEnabled(true);
                } else {
                    registerBtn.setEnabled(false);
                }
            }
        });
        loginByPassTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PhoneLoginActivity.this, LoginActivity.class));
                finish();
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone_login;
    }


    @OnClick({R.id.senCodeBtn, R.id.register_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.senCodeBtn:
                mPresenter.getCode(phoneEt.getText().toString());
                mTimeUtils.runTimer();
                break;
            case R.id.register_btn:
                mPresenter.login(phoneEt.getText().toString(), phoneCodeEt.getText().toString());
                break;
        }
    }

    @Override
    public void getCode() {
        isHasCode = true;
    }

    @Override
    public void loginSuccess(LoginResBean loginResBean) {
        AppConfig.token.put(loginResBean.getToken());
        AppConfig.loginInfo.put(new Gson().toJson(loginResBean));
        OkGo.getInstance().getCommonParams().put("T",loginResBean.getToken());
        startActivity(new Intent(PhoneLoginActivity.this, MainActivity.class));
        finish();
        LiveDataBus.get().with("refreshUserInfo").postValue(true);

    }

    @Override
    public void firstLogin() {
        Intent intent = new Intent(PhoneLoginActivity.this, ReigsterActivity.class);
        intent.putExtra("phone", phoneEt.getText().toString());
        startActivity(intent);
        finish();
    }
}
