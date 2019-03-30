package com.mubly.comewaves.view.interfaceview;

import com.mubly.comewaves.common.base.BaseMvpView;
import com.mubly.comewaves.model.model.UserInfoVo;

public interface MineView extends BaseMvpView {
    void getUserInfo(UserInfoVo userInfoVo);
}
