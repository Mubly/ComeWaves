package com.mubly.comewaves.view.interfaceview;

import com.mubly.comewaves.common.base.BaseMvpView;
import com.mubly.comewaves.model.model.StartBean;

import java.util.List;

public interface StartView extends BaseMvpView {
    void showStart(List<StartBean> startBeanList);
}
