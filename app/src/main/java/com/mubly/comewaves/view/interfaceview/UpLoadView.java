package com.mubly.comewaves.view.interfaceview;

import com.mubly.comewaves.common.base.BaseMvpView;

public interface UpLoadView extends BaseMvpView {
    void getUpLoadToken(String token);
    void showError(String msg);

    void upLoadImgSuccess(String keyRes, int type);

    void upLoadImgFail(String error, int type);
}
