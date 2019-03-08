package com.mubly.comewaves.common.base;

import android.app.Activity;
import android.content.Intent;

public abstract class BasePresenter<V extends BaseMvpView> {

    public V mvpView;

    public void attachView(V view) {
        this.mvpView = view;
    }

    public void detachView() {
        this.mvpView = null;
    }

    /**
     * 判断 view是否为空
     *
     * @return
     */
    public boolean isAttachView() {
        return mvpView != null;
    }

    /**
     * 返回目标view
     *
     * @return
     */
    public V getMvpView() {
        return mvpView;
    }


}
