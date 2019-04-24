package com.mubly.comewaves.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class OtherSettingActivity extends BaseActivity {
    @BindView(R.id.top_tittle)
    TextView topTittle;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void initView() {
        super.initView();
        topTittle.setText("其他设置");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other_setting;
    }

    @OnClick({R.id.top_back_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back_btn:
                finish();
                break;

        }
    }
}
