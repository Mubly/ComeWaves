package com.mubly.comewaves.view.interfaceview;

import com.mubly.comewaves.common.base.BaseMvpView;
import com.mubly.comewaves.model.model.UserPostVo;

import java.util.List;

public interface MineInView extends BaseMvpView {
    void requestSuccess(List<UserPostVo> data);
    void error(String msg);
}
