package com.mubly.comewaves.common.base;

import android.app.ProgressDialog;

import com.mubly.comewaves.model.model.UserPostVo;

import java.util.List;

public interface BaseMvpView {
    void showProgress(String msg);
    ProgressDialog getProgressDialog();
    void hideProgress();
    void checkNetCode(int code,String msg);


}
