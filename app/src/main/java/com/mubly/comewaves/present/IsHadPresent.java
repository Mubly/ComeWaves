package com.mubly.comewaves.present;

import com.mubly.comewaves.common.Constant;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.base.ResponseData;
import com.mubly.comewaves.common.network.Apis;
import com.mubly.comewaves.common.network.RxObserver;
import com.mubly.comewaves.model.model.HomeBean;
import com.mubly.comewaves.model.model.LoginResBean;
import com.mubly.comewaves.view.interfaceview.HomeView;
import com.mubly.comewaves.view.interfaceview.IsHadView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class IsHadPresent extends BasePresenter<IsHadView> {
    public void getHomeData(int status, int page) {
        Apis.getHomeData(status, page)
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
                        if (isAttachView())
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
}
