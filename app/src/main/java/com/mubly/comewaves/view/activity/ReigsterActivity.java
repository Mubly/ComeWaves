package com.mubly.comewaves.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.OkGo;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.sharedpreference.AppConfig;
import com.mubly.comewaves.common.utils.CommUtil;
import com.mubly.comewaves.common.utils.ToastUtils;
import com.mubly.comewaves.model.livedatabus.LiveDataBus;
import com.mubly.comewaves.model.model.CategoryVo;
import com.mubly.comewaves.model.model.LoginResBean;
import com.mubly.comewaves.present.RegisterPresent;
import com.mubly.comewaves.view.interfaceview.ReigsterView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ReigsterActivity extends BaseActivity<RegisterPresent, ReigsterView> implements ReigsterView {
    @BindView(R.id.tag_flow_layout)
    TagFlowLayout mTagFlowLayout;
    @BindView(R.id.register_avtar_img)
    ImageView avtarTv;
    @BindView(R.id.register_avtar_img_logo)
    ImageView avtarLogoTv;
    @BindView(R.id.register_over_btn)
    TextView registerBtn;
    @BindView(R.id.register_user_name)
    EditText userNameEt;
    @BindView(R.id.sex_select_layout)
    RadioGroup sexLayout;
    String headImgUrl;
    LayoutInflater mInflater;
    String phoneNo;
    String sexStr = "男";

    @Override
    protected RegisterPresent createPresenter() {
        return new RegisterPresent();
    }

    @Override
    protected int getLayoutId() {
        phoneNo = getIntent().getStringExtra("phone");
        return R.layout.activity_reigster;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        avtarTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(ReigsterActivity.this).openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)
                        .enableCrop(true)
                        .freeStyleCropEnabled(false)// 裁剪框是否可拖拽 true or false
                        .circleDimmedLayer(true)//圆形
                        .compress(true)
                        .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                        .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                        .isDragFrame(false)// 是否可拖动裁剪框(固定)
                        .forResult(PictureConfig.CHOOSE_REQUEST);

            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameStr = userNameEt.getText().toString();
                if (TextUtils.isEmpty(userNameStr)) {
                    ToastUtils.showToast("请输入您的昵称");
                    return;
                }
                if (TextUtils.isEmpty(headImgUrl)) {
                    ToastUtils.showToast("请添加您的头像");
                    return;
                }
                mPresenter.register(phoneNo, userNameStr, mPresenter.getCategary(mTagFlowLayout.getSelectedList()), sexStr, new File(headImgUrl));
            }
        });
        sexLayout.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.sex_of_man_rb:
                        sexStr = "男";
                        break;
                    case R.id.sex_of_weman_rb:
                        sexStr = "女";
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.getTagData();
    }

    @Override
    public void initView() {
        super.initView();
        mInflater = LayoutInflater.from(mContext);
        Glide.with(this).load(R.drawable.ishad_1).apply(RequestOptions.circleCropTransform()).into(avtarTv);
    }


    @Override
    public void getTagSuccess(List<CategoryVo> categoryVos) {
        initTagView(categoryVos);
    }

    private void initTagView(List<CategoryVo> categoryVos) {
        mTagFlowLayout.setAdapter(new TagAdapter<CategoryVo>(categoryVos) {
            @Override
            public View getView(FlowLayout parent, int position, CategoryVo s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.view_text_view,
                        mTagFlowLayout, false);
                tv.setText(s.cate_name);
                return tv;
            }

            @Override
            public void onSelected(int position, View view) {
                super.onSelected(position, view);
                view.setBackgroundResource(R.drawable.shape_gray_square_radious_solid4);
            }

            @Override
            public void unSelected(int position, View view) {
                super.unSelected(position, view);
                view.setBackgroundResource(R.drawable.shape_gray_square_radious_strok_10);
            }
        });
    }

    @Override
    public void register(LoginResBean loginResBean) {
        AppConfig.token.put(loginResBean.getToken());
        AppConfig.loginInfo.put(new Gson().toJson(loginResBean));
        OkGo.getInstance().getCommonParams().put("T", loginResBean.getToken());
        startActivity(new Intent(ReigsterActivity.this, MainActivity.class));
        finish();
        LiveDataBus.get().with("refreshUserInfo").postValue(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    headImgUrl = selectList.get(0).getCutPath();
                    avtarLogoTv.setVisibility(View.GONE);
                    Glide.with(mContext).load(selectList.get(0).getCutPath()).apply(RequestOptions.circleCropTransform()).into(avtarTv);
                    break;
            }
        }
    }
}
