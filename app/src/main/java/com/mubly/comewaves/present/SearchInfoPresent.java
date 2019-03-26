package com.mubly.comewaves.present;

import com.mubly.comewaves.common.base.BaseMvpView;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.base.ResponseData;
import com.mubly.comewaves.common.network.Apis;
import com.mubly.comewaves.common.network.RxObserver;
import com.mubly.comewaves.model.model.CategoryVo;
import com.mubly.comewaves.model.model.SearchVideoVo;
import com.mubly.comewaves.view.interfaceview.SearchInfoView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchInfoPresent extends BasePresenter<SearchInfoView> {
    public void getCategaryTwo(int cateId) {
        Apis.getSearchVideo(cateId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<List<SearchVideoVo>>>() {
                    @Override
                    public void _onNext(ResponseData<List<SearchVideoVo>> responseData) {
                        if (isAttachView()) {
                            getMvpView().getSearchVideoInfo(responseData.getData());
                        }
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        if (isAttachView()) {
                            getMvpView().checkNetCode(-1, errorMessage);
                        }
                    }
                });
    }
}
