package com.mubly.comewaves.present;

import android.content.Context;
import android.text.format.Formatter;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
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
import com.mubly.comewaves.model.interfaces.CallBack;
import com.mubly.comewaves.model.interfaces.CallPoiListBack;
import com.mubly.comewaves.model.livedatabus.LiveDataBus;
import com.mubly.comewaves.view.interfaceview.MessageCreateView;
import com.qiniu.android.storage.UploadManager;

import java.io.File;
import java.util.ArrayList;
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

import static com.mubly.comewaves.common.network.ApiUrls.IMAGE_UPLOAD_URL;
import static com.mubly.comewaves.common.network.ApiUrls.MUIL_IMAGE_UPLOAD_URL;
import static com.mubly.comewaves.common.network.ApiUrls.VIDEO_UPLOAD_URL;

public class MessageCreatePresent extends UpLoadPresent<MessageCreateView> {
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
                        }
                    }

                    @Override
                    public void _onError(String errorMessage) {
                    }
                });
    }

    public void videoUpdate(final String post_info, final String location, final String through, final String weft, final String sign, final File video, File img) {
//        getMvpView().showProgress("正在上传中……");
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
                        if (isAttachView()) {
                            getMvpView().addDisposable(d);
                        }

                    }

                    @Override
                    public void onNext(@NonNull Progress progress) {
//                        String downloadLength = Formatter.formatFileSize(getMvpView().getContext(), progress.currentSize);
//                        String totalLength = Formatter.formatFileSize(getMvpView().getContext(), progress.totalSize);
                        if (isAttachView()) {
                            getMvpView().upLoadSuccess();
//                            getMvpView().showProgress("正在上传中……  " + downloadLength + "/" + totalLength);
                        } else {
                            LiveDataBus.get().with("videoUpload").postValue(progress);
                        }

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
                            getMvpView().hideProgress();
                            getMvpView().upLoadSuccess();
                        }
                    }
                });
    }

    public void upLoadeImgMore(final String post_info, final String location, final String through, final String weft, final List<File> files) {
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
                            getMvpView().addDisposable(d);
                        }

                    }

                    @Override
                    public void onNext(@NonNull Progress progress) {
                        if (isAttachView()) {
                            getMvpView().upLoadSuccess();
                        } else {

                            LiveDataBus.get().with("videoUpload").postValue((double) progress.fraction);
                        }

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
                            getMvpView().hideProgress();
                            getMvpView().upLoadSuccess();
                        }
                        LiveDataBus.get().with("videoUpload").postValue(1.0);
                    }
                });
    }

    public void gainPoiList(Context context, String mLatitude, String mLongitude,String adCode, final CallPoiListBack callPoiListBack) {
        PoiSearch.Query query = new PoiSearch.Query("", "", adCode);
        query.setPageSize(20);
        PoiSearch poiSearch = new PoiSearch(context, query);
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(Double.valueOf(mLatitude), Double.valueOf(mLongitude)), 1000));
        poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
            @Override
            public void onPoiSearched(PoiResult poiResult, int i) {
                if (null != callPoiListBack) {
                    callPoiListBack.callBack(poiResult.getPois());
                }
            }

            @Override
            public void onPoiItemSearched(PoiItem poiItem, int i) {

            }
        });
        poiSearch.searchPOIAsyn();
    }
}
