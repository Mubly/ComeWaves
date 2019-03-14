package com.mubly.comewaves.view.activity;

import android.os.Bundle;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.view.fragment.UserInfoFragment;
import com.mubly.comewaves.view.fragment.UserSettingFragment;

import androidx.constraintlayout.widget.ConstraintLayout;
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
    @BindView(R.id.top_layout_right_tv)
    TextView topRightTv;
    boolean isSetting;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        super.initView();
        titleTv.setText("编辑个人资料");
        topRightTv.setText("设置");
        topRightTv.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().add(R.id.setting_bg, new UserInfoFragment()).addToBackStack("userInfo").commit();
    }


    @OnClick({R.id.top_back_btn, R.id.top_layout, R.id.top_layout_right_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back_btn:
                if (isSetting) {
                    getSupportFragmentManager().popBackStack();
                    isSetting = false;
                    topRightTv.setVisibility(View.VISIBLE);
                    titleTv.setText("编辑个人资料");
                } else {
                    finish();
                }
                break;
            case R.id.top_layout:
                break;
            case R.id.top_layout_right_tv:
                isSetting = true;
                topRightTv.setVisibility(View.GONE);
                titleTv.setText("设置");
                getSupportFragmentManager().beginTransaction().replace(R.id.setting_bg, new UserSettingFragment()).addToBackStack("setting").commit();
                break;
        }
    }
}
