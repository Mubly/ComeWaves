package com.mubly.comewaves.present;

import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.base.ResponseData;
import com.mubly.comewaves.common.network.Apis;
import com.mubly.comewaves.common.network.RxObserver;
import com.mubly.comewaves.model.model.UserInfoVo;
import com.mubly.comewaves.model.model.UserPostVo;
import com.mubly.comewaves.view.interfaceview.MineInView;
import com.mubly.comewaves.view.interfaceview.MineView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MineInPresent extends BasePresenter<MineInView> {
    public void mytopAndFocus(int type, int page) {
        Apis.mytopAndFocus(type, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<List<UserPostVo>>>() {
                    @Override
                    public void _onNext(ResponseData<List<UserPostVo>> listResponseData) {
                        if (isAttachView()) {
                            mvpView.requestSuccess(listResponseData.getData());
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
