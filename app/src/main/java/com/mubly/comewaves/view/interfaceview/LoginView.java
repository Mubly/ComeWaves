package com.mubly.comewaves.view.interfaceview;

import com.mubly.comewaves.common.base.BaseMvpView;

public interface LoginView extends BaseMvpView {
    void getCode();
    void login();
    void showMsg();
}
