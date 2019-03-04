package com.mubly.comewaves.common.base;

public interface BaseMvpView {
    void showProgress(String msg);
    void hideProgress();
    void checkNetCode(int code,String msg);
}
