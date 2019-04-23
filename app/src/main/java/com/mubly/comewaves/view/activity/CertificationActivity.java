package com.mubly.comewaves.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 用户认证
 */
public class CertificationActivity extends BaseActivity {

    @BindView(R.id.top_tittle)
    TextView mTitleTv;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void initView() {
        super.initView();
        mTitleTv.setText("用户认证");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_certification;
    }


    @OnClick({R.id.top_back_btn, R.id.store_enter_ack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back_btn:
                finish();
                break;
            case R.id.store_enter_ack:
                startActivity(new Intent(mContext,Certification2Activity.class));
                break;
        }
    }

}