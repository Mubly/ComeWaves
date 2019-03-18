package com.mubly.comewaves.present;

import android.text.TextUtils;

import com.mubly.comewaves.common.Constant;
import com.mubly.comewaves.common.base.BaseModel;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.base.ResponseData;
import com.mubly.comewaves.common.network.Apis;
import com.mubly.comewaves.common.network.RxObserver;
import com.mubly.comewaves.model.model.LoginResBean;
import com.mubly.comewaves.view.interfaceview.HomeView;
import com.mubly.comewaves.view.interfaceview.PhoneLoginView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PhoneLoginPresent extends BasePresenter<PhoneLoginView> {
    public void getCode(String phone) {
        Apis.getCode(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<BaseModel>>() {
                    @Override
                    public void _onNext(ResponseData<BaseModel> baseModelResponseData) {
                        if (isAttachView()) {
                            if (baseModelResponseData.getCode() == Constant.SuccessCode)
                                getMvpView().getCode();
                            else
                                getMvpView().checkNetCode(baseModelResponseData.getCode(), baseModelResponseData.getMsg());
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

    public void login(String phone, String code) {
        Apis.login(phone, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<LoginResBean>>() {
                    @Override
                    public void _onNext(ResponseData<LoginResBean> actWeekBeanResponseData) {
                        if (isAttachView()) {
                            if (actWeekBeanResponseData.getCode() == Constant.SuccessCode) {
                                getMvpView().loginSuccess(actWeekBeanResponseData.getData());
                            } else if (actWeekBeanResponseData.getCode() == Constant.FIRST_LOGIN_CODE) {
                                getMvpView().firstLogin();
                            } else {
                                getMvpView().checkNetCode(actWeekBeanResponseData.getCode(), actWeekBeanResponseData.getMsg());
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

    public boolean phoneCheck(String phoneStr) {
        if (TextUtils.isEmpty(phoneStr)) {
            return false;
        }
        return phoneStr.length() == 11;
    }
}
