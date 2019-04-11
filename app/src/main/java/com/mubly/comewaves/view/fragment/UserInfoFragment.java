package com.mubly.comewaves.view.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.sharedpreference.AppConfig;
import com.mubly.comewaves.common.utils.CommUtil;
import com.mubly.comewaves.common.utils.ToastUtils;
import com.mubly.comewaves.model.model.UserInfoVo;
import com.mubly.comewaves.present.UserInfoPresent;
import com.mubly.comewaves.view.activity.MainActivity;
import com.mubly.comewaves.view.costomview.AddressPickerView;
import com.mubly.comewaves.view.interfaceview.UserInfoView;
import com.qiniu.android.storage.UploadManager;

import java.io.File;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 *
 */
public class UserInfoFragment extends BaseFragment<UserInfoPresent, UserInfoView> implements UserInfoView {
    @BindView(R.id.user_avtar_iv)
    ImageView avtarImg;
    @BindView(R.id.setting_bg)
    ImageView bottomBgImg;
    @BindView(R.id.user_info_username)
    EditText userInfoUsername;
    @BindView(R.id.user_info_account)
    EditText userInfoAccount;
    @BindView(R.id.user_info_signature)
    EditText userInfoSignature;
    @BindView(R.id.user_info_sex)
    EditText userInfoSex;
    @BindView(R.id.user_info_birthday)
    TextView userInfoBirthday;
    @BindView(R.id.user_info_address)
    EditText userInfoAddress;
    @BindView(R.id.user_info_school)
    EditText userInfoSchool;
    @BindView(R.id.user_info_edit_tv)
    TextView editTypeBtn;
    @BindView(R.id.user_info_top)
    ImageView userInfoBg;
    @BindView(R.id.address_picker_layout)
    FrameLayout addressLayout;
    @BindView(R.id.address_picker_apv)
    AddressPickerView addressSelect;
    private boolean isEditStatus;
    UserInfoVo userInfoVo;
    private String qiNToken;
    private UploadManager mUploadManager = new UploadManager();
    private final int BGCODE = 10100;//背景上传
    private final int AVTRACODE = 10101;//头像上传

    @Override

    public void initView(View rootView) {
        super.initView(rootView);
        if (null != AppConfig.userInfoVo.get()) {
            userInfoVo = new Gson().fromJson(AppConfig.userInfoVo.get(), UserInfoVo.class);
        }
        if (null != userInfoVo) {
            Glide.with(this).load(userInfoVo.getUser_head()).apply(RequestOptions.circleCropTransform()).into(avtarImg);
            Glide.with(this).load(userInfoVo.getBackground_img()).apply(RequestOptions.placeholderOf(R.drawable.ishad_1)).into(userInfoBg);
        }

    }

    @Override
    protected UserInfoPresent createPresenter() {
        return new UserInfoPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_info;
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.getUserInfo();
    }

    @Override
    public void getEditUserInfo(UserInfoVo userInfoVo) {
        if (null != userInfoVo) {
            AppConfig.userInfoVo.put(CommUtil.object2Json(userInfoVo));
            Glide.with(this).load(userInfoVo.getUser_head()).apply(RequestOptions.circleCropTransform()).into(avtarImg);
            Glide.with(this).load(userInfoVo.getBackground_img()).apply(RequestOptions.placeholderOf(R.drawable.ishad_1)).into(userInfoBg);
            userInfoUsername.setText(userInfoVo.getUser_name());
            userInfoSignature.setText(userInfoVo.getSignature());
            userInfoSex.setText(userInfoVo.getSex());
            userInfoBirthday.setText(userInfoVo.getBirthday());
            userInfoAddress.setText(userInfoVo.getLocation());
            userInfoSchool.setText(userInfoVo.getSchool());
        }
        mPresenter.getUpLoadToken();
    }

    @Override
    public void updateSuccess(String msg) {
        ToastUtils.showToast(msg);
    }

    @Override
    public void getUpLoadToken(String token) {
        qiNToken = token;

    }

    @Override
    public void upLoadImgSuccess(String imgKey, int type) {
        switch (type) {
            case BGCODE:
                mPresenter.updateBg(imgKey);
                break;
            case AVTRACODE:
                mPresenter.upDateAvtar(imgKey);
                break;
        }
    }

    @Override
    public void upLoadImgFail(String error, int type) {
        ToastUtils.showToast(error);
    }

    @Override
    public void errorStr(String error) {
        ToastUtils.showToast(error);
    }

    void openEdit() {
        userInfoUsername.setEnabled(true);
        userInfoSignature.setEnabled(true);
        userInfoSex.setEnabled(true);
        userInfoBirthday.setClickable(true);
        userInfoAddress.setEnabled(true);
        userInfoSchool.setEnabled(true);
    }

    void closeEdit() {
        userInfoUsername.setEnabled(false);
        userInfoSignature.setEnabled(false);
        userInfoSex.setEnabled(false);
        userInfoBirthday.setClickable(false);
        userInfoAddress.setEnabled(false);
        userInfoSchool.setEnabled(false);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        addressSelect.setOnAddressPickerSure(new AddressPickerView.OnAddressPickerSureListener() {
            @Override
            public void onSureClick(String address, String provinceCode, String cityCode, String districtCode) {
                addressLayout.setVisibility(View.GONE);
                addressSelect.setVisibility(View.GONE);
            }
        });
        editTypeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditStatus) {
                    updateUserInfo();
                    closeEdit();
                    isEditStatus = false;
                    editTypeBtn.setText("编辑");

                } else {
                    openEdit();
                    isEditStatus = true;
                    editTypeBtn.setText("保存");
                }
            }
        });
        userInfoBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBg();

            }
        });
        avtarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAvtar();
            }
        });
        userInfoBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choseTime();
            }
        });
    }

    private void choseTime() {
        TimePickerView pvTime = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                userInfoBirthday.setText(CommUtil.dateToStr(date));
            }
        }).build();
        pvTime.show();
    }

    private void updateAvtar() {
        PictureSelector.create(UserInfoFragment.this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .compress(true)
                .enableCrop(true)
                .showCropFrame(true)
                .circleDimmedLayer(true)
                .scaleEnabled(true)
                .isDragFrame(true)
                .showCropGrid(false)
                .forResult(1011);
    }

    private void updateBg() {
        final Dialog dialog = CommUtil.showEditDialog(mContext, R.layout.dialog_continuous_layout, 0, 0.7, 0.5);
        dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(UserInfoFragment.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)
                        .compress(true)
                        .enableCrop(true)
                        .showCropFrame(true)
                        .withAspectRatio(375, 160)
                        .scaleEnabled(true)
                        .isDragFrame(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void updateUserInfo() {
        if (null == userInfoVo) {
            userInfoVo = new UserInfoVo();
        }
        if (TextUtils.isEmpty(userInfoBirthday.getText().toString())) {
            return;
        }
        if (TextUtils.isEmpty(userInfoAddress.getText().toString())) {
            return;
        }
        if (TextUtils.isEmpty(userInfoUsername.getText().toString())) {
            return;
        }
        if (TextUtils.isEmpty(userInfoSignature.getText().toString())) {
            return;
        }
        if (TextUtils.isEmpty(userInfoSex.getText().toString())) {
            return;
        }
        userInfoVo.setBirthday(userInfoBirthday.getText().toString());
        userInfoVo.setLocation(userInfoAddress.getText().toString());
        userInfoVo.setUser_name(userInfoUsername.getText().toString());
        userInfoVo.setSignature(userInfoSignature.getText().toString());
        userInfoVo.setSex(userInfoSex.getText().toString());
        userInfoVo.setSchool(userInfoSchool.getText().toString());
        mPresenter.upDateUserInfo(userInfoVo);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    Glide.with(this).load(selectList.get(0).getCompressPath()).apply(RequestOptions.placeholderOf(R.drawable.ishad_1)).into(userInfoBg);
                    File file2 = new File(selectList.get(0).getCompressPath());
                    String key2 = file2.getName();
                    mPresenter.upload(mUploadManager, selectList.get(0).getCompressPath(), key2, qiNToken, BGCODE);
                    break;
                case 1011:
                    Glide.with(this).load(selectList.get(0).getCompressPath()).apply(RequestOptions.circleCropTransform()).into(avtarImg);
                    File file = new File(selectList.get(0).getCompressPath());
                    String key = file.getName();
                    mPresenter.upload(mUploadManager, selectList.get(0).getCompressPath(), key, qiNToken, AVTRACODE);
                    break;
            }
        }
    }
}
