package com.mubly.comewaves.present;

import com.mubly.comewaves.common.Constant;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.base.ResponseData;
import com.mubly.comewaves.common.network.Apis;
import com.mubly.comewaves.common.network.RxObserver;
import com.mubly.comewaves.model.model.CommentInfo;
import com.mubly.comewaves.view.interfaceview.CommentInfoView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class CommentInfoPresent extends BasePresenter<CommentInfoView> {
    public void getCommentInfo(String post_id) {
        Apis.getCommentInfoById(post_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<List<CommentInfo>>>() {
                    @Override
                    public void _onNext(ResponseData<List<CommentInfo>> listResponseData) {
                        if (isAttachView()) {
                            if (listResponseData.getCode() == Constant.SuccessCode) {
                                getMvpView().showCommentInfo(listResponseData.getData());
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
