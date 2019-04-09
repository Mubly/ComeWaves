package com.mubly.comewaves.present;

import android.text.TextUtils;
import android.util.Log;

import com.mubly.comewaves.common.Constant;
import com.mubly.comewaves.common.base.BaseModel;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.base.ResponseData;
import com.mubly.comewaves.common.network.Apis;
import com.mubly.comewaves.common.network.RxObserver;
import com.mubly.comewaves.model.model.SmartBeanVo;
import com.mubly.comewaves.model.model.UserInfoVo;
import com.mubly.comewaves.view.interfaceview.UserInfoView;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserInfoPresent extends BasePresenter<UserInfoView> {
    public void getUserInfo() {
        Apis.getEditUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<UserInfoVo>>() {
                    @Override
                    public void _onNext(ResponseData<UserInfoVo> userInfoVoResponseData) {
                        if (isAttachView()) {
                            mvpView.getEditUserInfo(userInfoVoResponseData.getData());
                        }

                    }

                    @Override
                    public void _onError(String errorMessage) {
                        if (null == getMvpView()) {
                            return;
                        }

                    }
                });
    }

    public void upDateUserInfo(UserInfoVo userInfoVo) {
        mvpView.showProgress("正在提交");
        Apis.ackEditUserInfo(userInfoVo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<BaseModel>>() {
                    @Override
                    public void _onNext(ResponseData<BaseModel> userInfoVoResponseData) {
                        if (isAttachView()) {
                            mvpView.updateSuccess("修改成功");
                            mvpView.hideProgress();

                        }

                    }

                    @Override
                    public void _onError(String errorMessage) {
                        if (null == getMvpView()) {
                            return;
                        }
                        mvpView.hideProgress();

                    }
                });
    }

    public void getUpLoadToken() {
        Apis.getUpLoadToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<SmartBeanVo>>() {
                    @Override
                    public void _onNext(ResponseData<SmartBeanVo> userInfoVoResponseData) {
                        if (isAttachView()) {
                            mvpView.getUpLoadToken(userInfoVoResponseData.getData().qiniu_token);
                        }

                    }

                    @Override
                    public void _onError(String errorMessage) {
                        if (null == getMvpView()) {
                            return;
                        }

                    }
                });
    }

    // 图片上传七牛
    public void upload(UploadManager mUploadManager, String path, String key, String token, final int type) {
        mUploadManager.put(path, key, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject response) {
                if (info.isOK()) {
                    if (isAttachView()) {
                        try {
                            if (!response.has("key"))
                                return;
                            String keyRes = response.getString("key");
                            if (!TextUtils.isEmpty(keyRes))
                                mvpView.upLoadImgSuccess(keyRes, type);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    mvpView.upLoadImgFail(info.error, type);
                }
            }
        }, null);
    }

    //更新用户头像
    public void upDateAvtar(String avatarUrl) {
        Apis.updateAvatar(avatarUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<BaseModel>>() {
                    @Override
                    public void _onNext(ResponseData<BaseModel> userInfoVoResponseData) {
                        if (isAttachView()) {
                            if (userInfoVoResponseData.getCode() != Constant.SuccessCode) {
                                mvpView.errorStr(userInfoVoResponseData.getMsg());
                            }
                        }

                    }

                    @Override
                    public void _onError(String errorMessage) {
                        if (null == getMvpView()) {
                            return;
                        }
                        mvpView.errorStr(errorMessage);
                    }
                });
    }
    //更新用户背景
    public void updateBg(String bgUrl) {
        Apis.userInfoBg(bgUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<BaseModel>>() {
                    @Override
                    public void _onNext(ResponseData<BaseModel> userInfoVoResponseData) {
                        if (isAttachView()) {
                            if (userInfoVoResponseData.getCode() != Constant.SuccessCode) {
                                mvpView.errorStr(userInfoVoResponseData.getMsg());
                            }
                        }

                    }

                    @Override
                    public void _onError(String errorMessage) {
                        if (null == getMvpView()) {
                            return;
                        }
                        mvpView.errorStr(errorMessage);
                    }
                });
    }
}
