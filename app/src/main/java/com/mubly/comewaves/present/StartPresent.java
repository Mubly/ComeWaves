package com.mubly.comewaves.present;

import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.base.ResponseData;
import com.mubly.comewaves.common.network.Apis;
import com.mubly.comewaves.common.network.RxObserver;
import com.mubly.comewaves.model.model.StartBean;
import com.mubly.comewaves.view.interfaceview.StartView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StartPresent extends BasePresenter<StartView> {
    public void getStartImage() {
        Apis.getStartImage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<List<StartBean>>>() {
                    @Override
                    public void _onNext(ResponseData<List<StartBean>> listResponseData) {
                        if (isAttachView()) {
                            getMvpView().showStart(listResponseData.getData());
                        }

                    }

                    @Override
                    public void _onError(String errorMessage) {
                        if (isAttachView()) {

                        }

                    }
                });
    }
}
