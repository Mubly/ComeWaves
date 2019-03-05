package com.mubly.comewaves.present;

import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.base.ResponseData;
import com.mubly.comewaves.common.network.Apis;
import com.mubly.comewaves.common.network.RxObserver;
import com.mubly.comewaves.model.model.LoginResBean;
import com.mubly.comewaves.view.interfaceview.HomeView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ReleasePresent extends BasePresenter<HomeView> {
    public void getHomeData() {
        Apis.login("15572837654","897654")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<LoginResBean>>() {
                    @Override
                    public void _onNext(ResponseData<LoginResBean> actWeekBeanResponseData) {
                        if (null == getMvpView()) {
                            return;
                        }
                        getMvpView().showError("成功");
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        if (null == getMvpView()) {
                            return;
                        }
                        getMvpView().showError("出错");
                    }
                });
    }
}
