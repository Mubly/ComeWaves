package com.mubly.comewaves.present;

import android.text.TextUtils;

import com.mubly.comewaves.common.Constant;
import com.mubly.comewaves.common.base.BaseModel;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.base.ResponseData;
import com.mubly.comewaves.common.network.Apis;
import com.mubly.comewaves.common.network.RxObserver;
import com.mubly.comewaves.model.interfaces.CallBack;
import com.mubly.comewaves.model.model.LoginResBean;
import com.mubly.comewaves.model.model.SmartBeanVo;
import com.mubly.comewaves.model.model.UserInfoVo;
import com.mubly.comewaves.view.interfaceview.HomeView;
import com.mubly.comewaves.view.interfaceview.MineView;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MinePresent extends BasePresenter<MineView> {
    public void getUserInfo() {
        Apis.getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<UserInfoVo>>() {
                    @Override
                    public void _onNext(ResponseData<UserInfoVo> userInfoVoResponseData) {
                        if (isAttachView()) {
                            mvpView.getUserInfo(userInfoVoResponseData.getData());
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

    public void getUpLoadToken() {
        Apis.getUpLoadToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<SmartBeanVo>>() {
                    @Override
                    public void _onNext(ResponseData<SmartBeanVo> userInfoVoResponseData) {
                        if (isAttachView()) {
                            if (userInfoVoResponseData.getCode() == Constant.SuccessCode) {
                                mvpView.getUpLoadToken(userInfoVoResponseData.getData().qiniu_token);
                            } else {
                                mvpView.checkNetCode(userInfoVoResponseData.getCode(), userInfoVoResponseData.getMsg());
                            }

                        }

                    }

                    @Override
                    public void _onError(String errorMessage) {
                        if (isAttachView()) {
                            mvpView.showError(errorMessage);
                        }

                    }
                });
    }

    public void uploadImg(UploadManager mUploadManager, String path, String key, String token, final CallBack callback) {
        mUploadManager.put(path, key, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject response) {
                if (info.isOK()) {
                    try {
                        if (!response.has("key"))
                            return;
                        String keyRes = response.getString("key");
                        if (TextUtils.isEmpty(keyRes))
                            return;
                        if (null != callback)
                            callback.callBack(keyRes);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (null != callback)
                        callback.callBack(null);
                }
            }
        }, null);
    }

    public void upDateAvtar(String avatarUrl) {
        Apis.updateAvatar(avatarUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<BaseModel>>() {
                    @Override
                    public void _onNext(ResponseData<BaseModel> userInfoVoResponseData) {
                        if (isAttachView()) {
                            if (userInfoVoResponseData.getCode() != Constant.SuccessCode) {
                                mvpView.showError(userInfoVoResponseData.getMsg());
                            }
                        }

                    }

                    @Override
                    public void _onError(String errorMessage) {
                        if (null == getMvpView()) {
                            return;
                        }
                        mvpView.showError(errorMessage);
                    }
                });
    }
}
