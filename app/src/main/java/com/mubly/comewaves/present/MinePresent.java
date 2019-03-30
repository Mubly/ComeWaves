package com.mubly.comewaves.present;

import com.mubly.comewaves.common.base.BaseModel;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.base.ResponseData;
import com.mubly.comewaves.common.network.Apis;
import com.mubly.comewaves.common.network.RxObserver;
import com.mubly.comewaves.model.model.LoginResBean;
import com.mubly.comewaves.model.model.UserInfoVo;
import com.mubly.comewaves.view.interfaceview.HomeView;
import com.mubly.comewaves.view.interfaceview.MineView;

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
}
