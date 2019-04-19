package com.mubly.comewaves.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Certification2Activity extends BaseActivity {
    @BindView(R.id.store_type_layout)
    RadioGroup storeTypeLayout;
    @BindView(R.id.store_type_um)
    RadioButton storeTypeUm;
    @BindView(R.id.store_type_pm)
    RadioButton storeTypePm;
    @BindView(R.id.top_tittle)
    TextView mTitleTv;
    @BindView(R.id.top_layout_right_tv)
    TextView rightTv;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
    @Override
    public void initView() {
        super.initView();
        mTitleTv.setText("用户认证");
        rightTv.setVisibility(View.VISIBLE);
        rightTv.setText("—>");
        rightTv.setTextColor(getResources().getColor(R.color.color_D72D63));
    }
    @Override
    public void initEvent() {
        super.initEvent();
        storeTypeLayout.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.store_type_um:
                        storeTypeUm.setChecked(true);
                        storeTypePm.setChecked(false);
                        break;
                    case R.id.store_type_pm:
                        storeTypeUm.setChecked(false);
                        storeTypePm.setChecked(true);
                        break;
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_certification2;
    }

    @OnClick({R.id.top_back_btn, R.id.top_layout_right_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back_btn:
                finish();
                break;
            case R.id.top_layout_right_tv:
                startActivity(new Intent(mContext,Certification3Activity.class));
                break;
        }
    }
}
