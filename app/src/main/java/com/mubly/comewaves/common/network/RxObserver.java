package com.mubly.comewaves.common.network;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 *
 */

public  abstract class RxObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
        _onSubscribe(d);
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        _onError(e.getMessage());
    }

    @Override
    public void onComplete() {
        _onComplete();
    }

    public void _onSubscribe(Disposable d) {

    }
    public void _onComplete() {

    }
    //抽象方法，必须实现
    public abstract void _onNext(T t);
    public abstract void _onError(String errorMessage);
}
