package com.mubly.comewaves.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class Certification3Activity extends BaseActivity {
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
        mTitleTv.setText("填写申请");
        rightTv.setVisibility(View.VISIBLE);
        rightTv.setText("提交");
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_certification3;
    }
    @OnClick({R.id.top_back_btn, R.id.top_layout_right_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back_btn:
                finish();
                break;
            case R.id.top_layout_right_tv:
                ToastUtils.showToast("提交成功");
                break;
        }
    }
}
