package com.mubly.comewaves.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.utils.ToastUtils;
import com.mubly.comewaves.view.activity.AccountSafeActivity;
import com.mubly.comewaves.view.activity.CertificationActivity;
import com.mubly.comewaves.view.activity.OtherSettingActivity;
import com.mubly.comewaves.view.activity.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link } subclass.
 */
public class UserSettingFragment extends BaseFragment {
    @BindView(R.id.login_out_btn)
    TextView logionOutBtn;
    @BindView(R.id.user_certification_tv)
    TextView userCertification;
    @BindView(R.id.user_other_setting_tv)
    TextView userOtherSettingTv;
    @BindView(R.id.user_help_or_response_tv)
    TextView userHelpOrResponseTv;
    @BindView(R.id.setting_about_lai_tv)
    TextView settingAboutLaiTv;
    Unbinder unbinder;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_setting;
    }


    @Override
    public void initEvent() {
        super.initEvent();
        logionOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SettingActivity) mContext).finishActivity();
            }
        });
    }


    @OnClick({R.id.user_certification_tv, R.id.item_layout,R.id.user_other_setting_tv, R.id.user_help_or_response_tv, R.id.setting_about_lai_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_certification_tv:
                startActivity(new Intent(mContext, CertificationActivity.class));
                break;
            case R.id.item_layout:
                startActivity(new Intent(mContext, AccountSafeActivity.class));
                break;
            case R.id.user_other_setting_tv:
                startActivity(new Intent(mContext, OtherSettingActivity.class));
                break;
            case R.id.user_help_or_response_tv:
                ToastUtils.showToast("暂未开放");
                break;
            case R.id.setting_about_lai_tv:
                ToastUtils.showToast("暂未开放");
                break;
        }
    }





}
