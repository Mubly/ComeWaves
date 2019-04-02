package com.mubly.comewaves.view.fragment;


import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelectionModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.sharedpreference.AppConfig;
import com.mubly.comewaves.model.adapter.MyViewPageAdapter;
import com.mubly.comewaves.model.livedatabus.LiveDataBus;
import com.mubly.comewaves.model.model.LoginResBean;
import com.mubly.comewaves.model.model.UserInfoVo;
import com.mubly.comewaves.present.MinePresent;
import com.mubly.comewaves.view.activity.AttentionsActivity;
import com.mubly.comewaves.view.activity.MessageCreateActivity;
import com.mubly.comewaves.view.activity.SettingActivity;
import com.mubly.comewaves.view.costomview.ScrollViewPage;
import com.mubly.comewaves.view.interfaceview.MineView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

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
    //    @BindView(R.id.attention_layout)
//    LinearLayout attentionLayout;//关注
    List<String> title = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();
    LoginResBean loginResBean = null;
    @BindView(R.id.fans_layout)
    LinearLayout fansLayout;
    @BindView(R.id.motto_of_person_lebel)
    TextView mottoOfPersonLebel;
    Unbinder unbinder;

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
    public void initEvent() {
        super.initEvent();
        LiveDataBus.get().with("refreshUserInfo", Boolean.class).observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    initData();
                }
            }
        });
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
    public void getUserInfo(UserInfoVo userInfoVo) {
        if (null == userInfoVo)
            return;
        if (!TextUtils.isEmpty(userInfoVo.getUser_head())) {
            Glide.with(this).load(userInfoVo.getUser_head()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(userAvtarImg);
        }
        attentAmountTv.setText(userInfoVo.getAttention_num() + "");
        fansAmountTv.setText(userInfoVo.getFans_num() + "");
        userNameTv.setText(userInfoVo.getUser_name());
        accountDingTv.setText(userInfoVo.getDing_num());
        if (!TextUtils.isEmpty(userInfoVo.getSignature())) {
            mottoContentTv.setText(userInfoVo.getSignature());
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.mine_avtar_img, R.id.attention_layout, R.id.openMore})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_avtar_img:
                takeAvatorPhoto();
                break;
            case R.id.attention_layout:
                startActivity(new Intent(mContext, AttentionsActivity.class));
                break;
            case R.id.openMore:
                startActivity(new Intent(mContext, SettingActivity.class));
                break;
        }
    }

    private void takeAvatorPhoto() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .previewImage(true)
                .compress(true)
                .maxSelectNum(1)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    Glide.with(this).load(selectList.get(0).getCompressPath()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(userAvtarImg);
                    updateUserAvtar(selectList.get(0).getCompressPath());
//                    dealImageOrVideo(selectList);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    break;
            }
        }
    }

    private void updateUserAvtar(String compressPath) {

    }
}
