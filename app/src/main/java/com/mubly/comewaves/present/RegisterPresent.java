package com.mubly.comewaves.present;

import com.mubly.comewaves.common.Constant;
import com.mubly.comewaves.common.base.BaseModel;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.base.ResponseData;
import com.mubly.comewaves.common.network.Apis;
import com.mubly.comewaves.common.network.RxObserver;
import com.mubly.comewaves.model.model.CategoryVo;
import com.mubly.comewaves.model.model.LoginResBean;
import com.mubly.comewaves.view.interfaceview.ReigsterView;

import java.io.File;
import java.util.List;
import java.util.Set;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresent extends BasePresenter<ReigsterView> {
    public void register(String phone, String userName, String tagArr, String sex, File imgFile) {
        Apis.register(phone, userName, tagArr, sex, imgFile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<LoginResBean>>() {
                    @Override
                    public void _onNext(ResponseData<LoginResBean> actWeekBeanResponseData) {
                        if (isAttachView()) {
                            if (actWeekBeanResponseData.getCode() == Constant.SuccessCode) {
                                getMvpView().register(actWeekBeanResponseData.getData());
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

    public void getTagData() {
        Apis.getTagData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<List<CategoryVo>>>() {
                    @Override
                    public void _onNext(ResponseData<List<CategoryVo>> listResponseData) {
                        if (isAttachView()) {
                            if (listResponseData.getCode() == Constant.SuccessCode) {
                                getMvpView().getTagSuccess(listResponseData.getData());
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

    public String getCategary(Set<Integer> categary) {
        String categaryIdStr = "";
        for (Integer cagid : categary) {
            categaryIdStr = categaryIdStr+(cagid + ",");
        }
        return categaryIdStr;
    }
}
