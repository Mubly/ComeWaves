package com.mubly.comewaves.view.fragment;


import android.content.Intent;
import android.os.Bundle;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import com.google.gson.Gson;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.sharedpreference.AppConfig;
import com.mubly.comewaves.model.adapter.MyViewPageAdapter;
import com.mubly.comewaves.model.model.LoginResBean;
import com.mubly.comewaves.model.model.UserInfoVo;
import com.mubly.comewaves.present.MinePresent;
import com.mubly.comewaves.view.activity.AttentionsActivity;
import com.mubly.comewaves.view.activity.SettingActivity;
import com.mubly.comewaves.view.costomview.ScrollViewPage;
import com.mubly.comewaves.view.interfaceview.MineView;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.OnClick;

public class MineFragment extends BaseFragment<MinePresent, MineView> implements MineView {
    @BindView(R.id.top_bg_img)
    ImageView topBgImg;
    @BindView(R.id.wallet_btn)
    TextView walletBtn;
    @BindView(R.id.openMore)
    Button openMoreBtn;
    @BindView(R.id.mine_avtar_img)
    ImageView userAvtarImg;
    @BindView(R.id.attention_amount)
    TextView attentAmountTv;
    @BindView(R.id.fans_amount)
    TextView fansAmountTv;
    @BindView(R.id.mine_user_name)
    TextView userNameTv;
    @BindView(R.id.account_of_ding)
    TextView accountDingTv;
    @BindView(R.id.motto_of_person)
    TextView mottoContentTv;
    @BindView(R.id.mine_tab)
    TabLayout mTabLayout;
    @BindView(R.id.mine_viewpage)
    ScrollViewPage mViewPage;

    List<String> title = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();
    LoginResBean loginResBean = null;

    @Override

    protected MinePresent createPresenter() {
        return new MinePresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.getUserInfo();
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        if (!TextUtils.isEmpty(AppConfig.loginInfo.get())) {
            loginResBean = new Gson().fromJson(AppConfig.loginInfo.get(), LoginResBean.class);
            Glide.with(this).load(null == loginResBean.getUser_head() ? "" : loginResBean.getUser_head()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(userAvtarImg);
        } else {
            Glide.with(this).load(R.drawable.ishad_1).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(userAvtarImg);
        }


        title.add("我的帖子");
        title.add("我的收藏");
        fragmentList.add(MineInFragment.newInstance(1));
        fragmentList.add(MineInFragment.newInstance(2));
        mViewPage.setAdapter(new MyViewPageAdapter(getChildFragmentManager(), title, fragmentList));
        mViewPage.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPage);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        openMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, SettingActivity.class));
            }
        });
        userAvtarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, AttentionsActivity.class));
            }
        });
    }

    @Override
    public void getUserInfo(UserInfoVo userInfoVo) {
        if (!TextUtils.isEmpty(userInfoVo.getUser_head())) {
            Glide.with(this).load(userInfoVo.getUser_head()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(userAvtarImg);
        }
        attentAmountTv.setText(userInfoVo.getAttention_num() + "");
        fansAmountTv.setText(userInfoVo.getFans_num()+"");
        userNameTv.setText(userInfoVo.getUser_name());
        accountDingTv.setText(userInfoVo.getDing_num());
        if (!TextUtils.isEmpty(userInfoVo.getSignature())) {
            mottoContentTv.setText(userInfoVo.getSignature());
        }

    }
}
