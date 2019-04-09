package com.mubly.comewaves.view.interfaceview;

import com.mubly.comewaves.common.base.BaseMvpView;
import com.mubly.comewaves.model.model.UserInfoVo;

public interface UserInfoView extends BaseMvpView {
    void getEditUserInfo(UserInfoVo userInfoVo);

    void updateSuccess(String msg);

    void getUpLoadToken(String token);

    void upLoadImgSuccess(String imgKey, int type);

    void upLoadImgFail(String error, int type);

    void errorStr(String error);
}
