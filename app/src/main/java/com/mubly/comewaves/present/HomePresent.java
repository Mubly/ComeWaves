package com.mubly.comewaves.present;

import com.mubly.comewaves.common.Constant;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.base.ResponseData;
import com.mubly.comewaves.common.network.Apis;
import com.mubly.comewaves.common.network.RxObserver;
import com.mubly.comewaves.model.model.HomeBean;
import com.mubly.comewaves.model.model.LoginResBean;
import com.mubly.comewaves.model.model.SmartBeanVo;
import com.mubly.comewaves.view.interfaceview.HomeView;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomePresent extends BasePresenter<HomeView> {
    public void getHomeData(int status,int page) {
        Apis.getHomeData(status,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<List<HomeBean>>>() {
                    @Override
                    public void _onNext(ResponseData<List<HomeBean>> listResponseData) {
                        if (isAttachView()) {
                            if (listResponseData.getCode() == Constant.SuccessCode) {
                                getMvpView().shoSuccess(listResponseData.getData());
                            } else {
                                getMvpView().checkNetCode(listResponseData.getCode(), listResponseData.getMsg());
                            }
                        }


                    }

                    @Override
                    public void _onComplete() {
                        super._onComplete();
                        getMvpView().showError("完成");
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        if (isAttachView()) {
                            getMvpView().showError(errorMessage);
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

                            } else {
                                getMvpView().checkNetCode(listResponseData.getCode(), listResponseData.getMsg());
                            }

                        }
                    }

                    @Override
                    public void _onError(String errorMessage) {
//                        if (null == getMvpView()) {
//                            return;
//                        }
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
}
