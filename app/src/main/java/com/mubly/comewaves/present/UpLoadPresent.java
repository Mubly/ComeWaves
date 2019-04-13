package com.mubly.comewaves.present;

import android.text.TextUtils;

import com.luck.picture.lib.entity.LocalMedia;
import com.mubly.comewaves.common.Constant;
import com.mubly.comewaves.common.base.BaseMvpView;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.base.ResponseData;
import com.mubly.comewaves.common.network.Apis;
import com.mubly.comewaves.common.network.RxObserver;
import com.mubly.comewaves.model.interfaces.CallBack;
import com.mubly.comewaves.model.interfaces.CallBackObject;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    //多图上传
    public void uploadImgMore(UploadManager mUploadManager, List<LocalMedia> localMediaList, String token, final CallBackObject callBack) {
        if (null == localMediaList || localMediaList.size() < 1)
            return;
        final String[] imgList = {""};
        final int[] i = {0};
        imgUpload(mUploadManager, localMediaList, token, imgList, i, new CallBackObject<String>() {
            @Override
            public void callBack(String o) {
                if (null != callBack) {
                    callBack.callBack(o);
                }
            }
        });
    }

    private void imgUpload(final UploadManager mUploadManager, final List<LocalMedia> localMediaList, final String token, final String[] imgList, final int[] index, final CallBackObject callBackObject) {
        File file = new File(localMediaList.get(index[0]).getCompressPath());
        uploadImg(mUploadManager, localMediaList.get(index[0]).getCompressPath(), file.getName(), token, new CallBack() {
            @Override
            public void callBack(String data) {
                if (TextUtils.isEmpty(imgList[0])) {
                    imgList[0] = data;
                } else {
                    imgList[0] = imgList[0] + "," + data;

                }

                index[0]++;
                if (index[0] == localMediaList.size() && null != callBackObject) {
                    callBackObject.callBack(imgList[0]);
                } else {
                    imgUpload(mUploadManager, localMediaList, token, imgList, index, callBackObject);
                }
            }
        });
    }
}
