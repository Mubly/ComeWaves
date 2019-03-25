package com.mubly.comewaves.present;

import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.base.ResponseData;
import com.mubly.comewaves.common.network.Apis;
import com.mubly.comewaves.common.network.RxObserver;
import com.mubly.comewaves.model.model.CategoryVo;
import com.mubly.comewaves.model.model.LoginResBean;
import com.mubly.comewaves.view.interfaceview.HomeView;
import com.mubly.comewaves.view.interfaceview.SearchView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchPresent extends BasePresenter<SearchView> {
    public void getCategaryOne(int page) {
        Apis.getSearchTab1(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<List<CategoryVo>>>() {
                    @Override
                    public void _onNext(ResponseData<List<CategoryVo>> categorydatadata) {
                        if (null == getMvpView()) {
                            return;
                        }
                        getMvpView().getOneTab(categorydatadata.getData());
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        if (null == getMvpView()) {
                            return;
                        }

                    }
                });
    }

    public void getCategaryTwo(int cateId) {
        Apis.getSearchTab2(cateId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<List<CategoryVo>>>() {
                    @Override
                    public void _onNext(ResponseData<List<CategoryVo>> categorydatadata) {
                        if (null == getMvpView()) {
                            return;
                        }
                        getMvpView().getOneTab(categorydatadata.getData());
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
