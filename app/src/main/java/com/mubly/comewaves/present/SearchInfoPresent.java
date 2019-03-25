package com.mubly.comewaves.present;

import com.mubly.comewaves.common.base.ResponseData;
import com.mubly.comewaves.common.network.Apis;
import com.mubly.comewaves.common.network.RxObserver;
import com.mubly.comewaves.model.model.CategoryVo;
import com.mubly.comewaves.model.model.SearchVideoVo;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchInfoPresent {
    public void getCategaryTwo(int cateId) {
        Apis.getSearchVideo(cateId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<List<SearchVideoVo>>>() {
                    @Override
                    public void _onNext(ResponseData<List<SearchVideoVo>> responseData) {

                    }

                    @Override
                    public void _onError(String errorMessage) {

                    }
                });
    }
}
