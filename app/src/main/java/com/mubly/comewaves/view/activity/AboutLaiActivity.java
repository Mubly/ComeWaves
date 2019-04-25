package com.mubly.comewaves.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutLaiActivity extends BaseActivity {
    @BindView(R.id.top_tittle)
    TextView titleTv;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_lai;
    }

    @Override
    public void initView() {
        super.initView();
        titleTv.setText("关于我们");
    }


    @OnClick({R.id.top_back_btn, R.id.about_app_feek_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back_btn:
                finish();
                break;
            case R.id.about_app_feek_layout:
                startActivity(new Intent(AboutLaiActivity.this,FeedbackActivity.class));
                break;
        }
    }
}
