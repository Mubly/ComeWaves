package com.mubly.comewaves.view.fragment;



import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.base.BasePresenter;

import butterknife.BindView;

/**
 *
 */
public class UserInfoFragment extends BaseFragment {
    @BindView(R.id.user_avtar_iv)
    ImageView avtarImg;
    @BindView(R.id.setting_bg)
    ImageView bottomBgImg;

    public UserInfoFragment() {
        // Required empty public constructor
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        Glide.with(this).load(R.drawable.ishad_1).apply(RequestOptions.circleCropTransform()).into(avtarImg);
        Glide.with(this).load(R.drawable.ishad_1).apply(RequestOptions.placeholderOf(R.drawable.ishad_1)).into(bottomBgImg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_info;
    }


}
