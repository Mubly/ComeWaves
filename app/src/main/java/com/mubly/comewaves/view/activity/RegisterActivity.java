package com.mubly.comewaves.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.utils.TimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.senCodeBtn)
    Button sandCodeBtn;

    TimeUtils mTimeUtils;
    @BindView(R.id.register_btn)
    Button registerBtn;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void initView() {
        super.initView();
        mTimeUtils = new TimeUtils(sandCodeBtn, "重新发送");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }



    @OnClick({R.id.senCodeBtn, R.id.register_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.senCodeBtn:
                mTimeUtils.runTimer();
                break;
            case R.id.register_btn:
                mTimeUtils.cancelTimer();
                break;
        }
    }
}
