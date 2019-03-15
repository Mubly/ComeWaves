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
import com.mubly.comewaves.common.sharedpreference.AppConfig;
import com.mubly.comewaves.view.activity.MainActivity;
import com.mubly.comewaves.view.activity.SettingActivity;


import butterknife.BindView;

/**
 * A simple {@link } subclass.
 */
public class UserSettingFragment extends BaseFragment {
    @BindView(R.id.login_out_btn)
    TextView logionOutBtn;

    public UserSettingFragment() {
        // Required empty public constructor
    }


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
                ((SettingActivity)mContext).finishActivity();
            }
        });
    }
}
