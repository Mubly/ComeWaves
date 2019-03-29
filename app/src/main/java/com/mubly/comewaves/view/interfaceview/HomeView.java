package com.mubly.comewaves.view.interfaceview;

import com.mubly.comewaves.common.base.BaseMvpView;
import com.mubly.comewaves.model.model.HomeBean;
import com.mubly.comewaves.model.model.SmartBeanVo;

import java.util.List;

public interface HomeView extends BaseMvpView {
    void showError(String msg);

    void shoSuccess(List<HomeBean> homeBeanList);

//    void doPraise(SmartBeanVo data);
}
