package com.mubly.comewaves.view.interfaceview;

import com.mubly.comewaves.common.base.BaseMvpView;
import com.mubly.comewaves.model.model.LoginResBean;

public interface PhoneLoginView extends BaseMvpView {
    void getCode();
    void loginSuccess(LoginResBean loginResBean);
    void firstLogin();
}
