package com.mubly.comewaves.view.interfaceview;

import com.mubly.comewaves.common.base.BaseMvpView;

public interface HomeView extends BaseMvpView {
    void showError(String msg);

    void shoSuccess();
}
