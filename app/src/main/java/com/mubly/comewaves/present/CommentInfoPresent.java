package com.mubly.comewaves.present;

import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.mubly.comewaves.common.Constant;
import com.mubly.comewaves.common.base.BaseModel;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.base.ResponseData;
import com.mubly.comewaves.common.network.Apis;
import com.mubly.comewaves.common.network.RxObserver;
import com.mubly.comewaves.common.utils.ToastUtils;
import com.mubly.comewaves.model.interfaces.CallBack;
import com.mubly.comewaves.model.livedatabus.LiveDataBus;
import com.mubly.comewaves.model.model.CommentInfo;
import com.mubly.comewaves.model.model.SmartBeanVo;
import com.mubly.comewaves.model.model.TopicInfoVo;
import com.mubly.comewaves.view.interfaceview.CommentInfoView;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.mubly.comewaves.common.network.ApiUrls.MUIL_IMAGE_UPLOAD_URL;
import static com.mubly.comewaves.common.network.ApiUrls.VIDEO_UPLOAD_URL;


public class CommentInfoPresent extends BasePresenter<CommentInfoView> {

    public void getCommentInfo(int post_id) {
        Apis.getCommentInfoById(post_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<List<CommentInfo>>>() {
                    @Override
                    public void _onNext(ResponseData<List<CommentInfo>> listResponseData) {
                        if (isAttachView()) {
                            if (listResponseData.getCode() == Constant.SuccessCode) {
                                getMvpView().showCommentInfo(listResponseData.getData());
                            } else {
                                getMvpView().checkNetCode(listResponseData.getCode(), listResponseData.getMsg());
                            }

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

    public void replyComment(int post_id, String content, int type, int commentId, int userId) {
        Apis.replyComment(post_id, content, type, commentId, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<BaseModel>>() {
                    @Override
                    public void _onNext(ResponseData<BaseModel> listResponseData) {
                        if (isAttachView()) {
                            if (listResponseData.getCode() == Constant.SuccessCode) {
                                getMvpView().replyCommentSuccess();
                            } else {
                                getMvpView().checkNetCode(listResponseData.getCode(), listResponseData.getMsg());
                            }

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

    public void sendReplyComment(int post_id, String content, int type) {
        Apis.sendReplyComment(post_id, content, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<BaseModel>>() {
                    @Override
                    public void _onNext(ResponseData<BaseModel> listResponseData) {
                        if (isAttachView()) {
                            if (listResponseData.getCode() == Constant.SuccessCode) {
                                getMvpView().replyCommentSuccess();
                            } else {
                                getMvpView().replyError("");
                                getMvpView().checkNetCode(listResponseData.getCode(), listResponseData.getMsg());
                            }

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

    public void getTopicInfo(int post_id, int type) {
        Apis.getTieInfo(post_id, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<TopicInfoVo>>() {
                    @Override
                    public void _onNext(ResponseData<TopicInfoVo> topicInfoVoResponseData) {

                        if (isAttachView()) {
                            if (topicInfoVoResponseData.getCode() == Constant.SuccessCode) {
                                getMvpView().showTopicInfo(topicInfoVoResponseData.getData());
                            } else {
                                getMvpView().checkNetCode(topicInfoVoResponseData.getCode(), topicInfoVoResponseData.getMsg());
                            }

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

    public void doPraise(int post_id) {
        Apis.doPraise(post_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<SmartBeanVo>>() {
                    @Override
                    public void _onNext(ResponseData<SmartBeanVo> listResponseData) {
                        if (isAttachView()) {
                            if (listResponseData.getCode() == Constant.SuccessCode) {
                                getMvpView().doPraise(listResponseData.getData());
                            } else {
                                getMvpView().checkNetCode(listResponseData.getCode(), listResponseData.getMsg());
                            }

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

    public void doAttention(int user_id) {
        Apis.doAttention(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<SmartBeanVo>>() {
                    @Override
                    public void _onNext(ResponseData<SmartBeanVo> listResponseData) {
                        if (isAttachView()) {
                            if (listResponseData.getCode() == Constant.SuccessCode) {
                                getMvpView().doAttention(listResponseData.getData());
                            } else {
                                getMvpView().checkNetCode(listResponseData.getCode(), listResponseData.getMsg());
                            }

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

    public void doCollection(int post_id) {
        Apis.doCollection(post_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<SmartBeanVo>>() {
                    @Override
                    public void _onNext(ResponseData<SmartBeanVo> listResponseData) {
                        if (isAttachView()) {
                            if (listResponseData.getCode() == Constant.SuccessCode) {
                                getMvpView().doCollection(listResponseData.getData());
                            } else {
                                getMvpView().checkNetCode(listResponseData.getCode(), listResponseData.getMsg());
                            }

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

    //    获取七牛云上传Token
    public void getUpLoadToken() {
        Apis.getUpLoadToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<SmartBeanVo>>() {
                    @Override
                    public void _onNext(ResponseData<SmartBeanVo> userInfoVoResponseData) {
                        if (isAttachView()) {
                            if (userInfoVoResponseData.getCode() == Constant.SuccessCode) {
//                                mvpView.getUpLoadToken(userInfoVoResponseData.getData().qiniu_token);
                            } else {
                                mvpView.checkNetCode(userInfoVoResponseData.getCode(), userInfoVoResponseData.getMsg());
                            }

                        }

                    }

                    @Override
                    public void _onError(String errorMessage) {
                        if (isAttachView()) {
//                            mvpView.showError(errorMessage);
                        }

                    }
                });
    }
//    七牛视频上传
    public void videodateQN(final UploadManager mUploadManager, final String qiNToken, final String post_info, final String location, final String through, final String weft, final String sign, String video, final String img) {
        final File file = new File(video);
        uploadVideo(mUploadManager, video, file.getName(), qiNToken, new CallBack() {
            @Override
            public void callBack(final String data1) {
                if (null == data1)
                    return;
                file.delete();
                final File fileImg = new File(img);
                uploadImg(mUploadManager, img, fileImg.getName(), qiNToken, new CallBack() {
                    @Override
                    public void callBack(String data2) {

                        fileImg.delete();
                        videoUpLoadTwo(post_info, location, through, weft, sign, data1, data2);
                    }
                });
            }
        });
    }
    private void videoUpLoadTwo(String post_info, String location, String through, String weft, String sign, String video, String img) {
        Apis.videoUpload2(post_info, location, through, weft, sign, video, img)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<BaseModel>>() {
                    @Override
                    public void _onNext(ResponseData<BaseModel> actWeekBeanResponseData) {
                        if (actWeekBeanResponseData.getCode() == Constant.SuccessCode) {
                            LiveDataBus.get().with("videoUpload").postValue(1.00);
                        } else {
                            ToastUtils.showToast(actWeekBeanResponseData.getMsg());
                        }
                    }

                    @Override
                    public void _onError(String errorMessage) {
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


    public void videoUpdate(final String post_info, final String location, final String through, final String weft, final String sign, final File video, File img) {
        Observable.create(new ObservableOnSubscribe<Progress>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Progress> e) throws Exception {
                OkGo.<String>post(VIDEO_UPLOAD_URL)
                        .params("post_info", post_info)//文章内容
//                .params("cate_id", cate_id)//分类Id
                        .params("location", location)//地址
                        .params("through", through)//经度
                        .params("weft", weft)//维度
                        .params("video", video)//视频
//                .params("img", img)//封面图
                        .params("sign", sign)//标签
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                e.onComplete();
                            }

                            @Override
                            public void onError(Response<String> response) {
                                e.onError(response.getException());
                            }

                            @Override
                            public void uploadProgress(Progress progress) {
                                e.onNext(progress);
                            }
                        });
            }
        })//
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
//                        btnFormUpload2.setText("正在上传中...");
                    }
                })//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe(new Observer<Progress>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
//                        if (isAttachView()) {
//                            getMvpView().addDisposable(d);
//                        }

                    }

                    @Override
                    public void onNext(@NonNull Progress progress) {
//                        String downloadLength = Formatter.formatFileSize(getMvpView().getContext(), progress.currentSize);
//                        String totalLength = Formatter.formatFileSize(getMvpView().getContext(), progress.totalSize);
//                        if (isAttachView()) {
//                            getMvpView().upLoadSuccess();
////                            getMvpView().showProgress("正在上传中……  " + downloadLength + "/" + totalLength);
//                        } else {
//                            LiveDataBus.get().with("videoUpload").postValue(progress);
//                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        if (isAttachView()) {
                            getMvpView().hideProgress();
                        }

                    }

                    @Override
                    public void onComplete() {
                        if (isAttachView()) {
//                            getMvpView().hideProgress();
//                            getMvpView().upLoadSuccess();
                        }
                    }
                });
    }

    public void upLoadeImgMore(final String post_info, final String location, final String through, final String weft, final List<File> files) {
        getMvpView().showProgress("正在上传中……");
        Observable.create(new ObservableOnSubscribe<Progress>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Progress> e) throws Exception {
                OkGo.<String>post(MUIL_IMAGE_UPLOAD_URL)
                        .params("post_info", post_info)
                        .params("location", location)
                        .params("through", through)
                        .params("weft", weft)
                        .addFileParams("img[]", files)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                e.onComplete();
                            }

                            @Override
                            public void onError(Response<String> response) {
                                e.onError(response.getException());
                            }

                            @Override
                            public void uploadProgress(Progress progress) {
                                e.onNext(progress);
                            }
                        });
            }
        })//
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
//                        btnFormUpload2.setText("正在上传中...");
                    }
                })//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe(new Observer<Progress>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        if (isAttachView()) {
//                            getMvpView().addDisposable(d);
                        }

                    }

                    @Override
                    public void onNext(@NonNull Progress progress) {
//                        String downloadLength = Formatter.formatFileSize(getMvpView().getContext(), progress.currentSize);
//                        String totalLength = Formatter.formatFileSize(getMvpView().getContext(), progress.totalSize);
//                        if (isAttachView()) {
//                            getMvpView().showProgress("正在上传中……  " + downloadLength + "/" + totalLength);
//                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        if (isAttachView()) {
                            getMvpView().hideProgress();
                        }

                    }

                    @Override
                    public void onComplete() {
                        if (isAttachView()) {
//                            getMvpView().hideProgress();
//                            getMvpView().upLoadSuccess();
                        }
                    }
                });
    }
}
