package com.mubly.comewaves.present;

import com.mubly.comewaves.common.Constant;
import com.mubly.comewaves.common.base.BaseModel;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.base.ResponseData;
import com.mubly.comewaves.common.network.Apis;
import com.mubly.comewaves.common.network.RxObserver;
import com.mubly.comewaves.view.interfaceview.MessageCreateView;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MessageCreatePresent extends BasePresenter<MessageCreateView> {
    public void videodate(String post_info, String cate_id, String location, String through, String weft, String sign, File video, File img) {
        Apis.videoUpload(post_info, cate_id, location, through, weft, sign, video, img)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<BaseModel>>() {
                    @Override
                    public void _onNext(ResponseData<BaseModel> actWeekBeanResponseData) {
                        if (isAttachView()) {
                            if (actWeekBeanResponseData.getCode() == Constant.SuccessCode) {
//                                getMvpView().register();
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

}
