package com.mubly.comewaves.common.base;

import android.app.ProgressDialog;

public interface BaseMvpView {
    void showProgress(String msg);
    ProgressDialog getProgressDialog();
    void hideProgress();
    void checkNetCode(int code,String msg);
}
