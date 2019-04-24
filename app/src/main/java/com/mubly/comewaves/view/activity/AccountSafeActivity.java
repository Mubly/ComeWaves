package com.mubly.comewaves.view.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountSafeActivity extends BaseActivity {


    @BindView(R.id.top_back_btn)
    ImageButton topBackBtn;
    @BindView(R.id.top_tittle)
    TextView topTittle;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void initView() {
        super.initView();
        topTittle.setText("账户与安全");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_safe;
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
