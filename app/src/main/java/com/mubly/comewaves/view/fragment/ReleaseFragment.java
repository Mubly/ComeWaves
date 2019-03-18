package com.mubly.comewaves.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.Constant;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.view.activity.MessageCreateActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class ReleaseFragment extends BaseFragment {


    @BindView(R.id.make_video_message)
    ImageView makeVideoMessage;
    @BindView(R.id.make_photo_message)
    ImageView makePhotoMessage;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_release;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
    }


    @OnClick({R.id.make_video_message, R.id.make_photo_message})
    public void onViewClicked(View view) {
        Intent intent = new Intent(mContext, MessageCreateActivity.class);
        switch (view.getId()) {
            case R.id.make_video_message:
                intent.putExtra("type", Constant.PULL_VIDEO_CODE);
                break;
            case R.id.make_photo_message:
                intent.putExtra("type", Constant.PULL_IMAGE_CODE);
                break;
        }
        startActivity(intent);
    }
}