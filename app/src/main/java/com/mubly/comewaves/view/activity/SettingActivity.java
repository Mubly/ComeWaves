package com.mubly.comewaves.view.activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.sharedpreference.AppConfig;
import com.mubly.comewaves.view.fragment.UserInfoFragment;
import com.mubly.comewaves.view.fragment.UserSettingFragment;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {
    @BindView(R.id.setting_bg)
    FrameLayout frameLayout;
    @BindView(R.id.top_back_btn)
    ImageButton topBackBtn;
    @BindView(R.id.top_layout)
    ConstraintLayout topLayout;
    @BindView(R.id.top_tittle)
    TextView titleTv;
    private String type;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        type = getIntent().getStringExtra("type");
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        super.initView();

        if (type.equals("edit")) {
            titleTv.setText("编辑个人资料");
            getSupportFragmentManager().beginTransaction().add(R.id.setting_bg, new UserInfoFragment()).addToBackStack(null).commit();
        } else {
            titleTv.setText("设置");
            getSupportFragmentManager().beginTransaction().add(R.id.setting_bg, new UserSettingFragment()).addToBackStack(null).commit();
        }
    }


    @OnClick({R.id.top_back_btn, R.id.top_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back_btn:
                finish();
                break;
            case R.id.top_layout:
                break;
        }
    }

    public void finishActivity() {
        AppConfig.token.put(null);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finish();
    }
}
