package com.mubly.comewaves.present;

import android.text.TextUtils;

import com.mubly.comewaves.common.base.BaseMvpView;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.base.ResponseData;
import com.mubly.comewaves.common.network.Apis;
import com.mubly.comewaves.common.network.RxObserver;
import com.mubly.comewaves.model.interfaces.CallBack;
import com.mubly.comewaves.model.livedatabus.LiveDataBus;
import com.mubly.comewaves.model.model.SmartBeanVo;
import com.mubly.comewaves.view.interfaceview.UpLoadView;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UpLoadPresent<T extends UpLoadView> extends BasePresenter<T> {
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

    public void uploadVideo(UploadManager mUploadManager, String path, String key, String token, final CallBack callBack) {
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
                        if (null != callBack)
                            callBack.callBack(keyRes);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    //视频上传失败
                    if (null != callBack)
                        callBack.callBack(null);
                }
            }
        }, new UploadOptions(null, null, false, new UpProgressHandler() {
            @Override
            public void progress(String key, double percent) {
                LiveDataBus.get().with("videoUpload").postValue(percent);
            }
        }, null));
    }
}
