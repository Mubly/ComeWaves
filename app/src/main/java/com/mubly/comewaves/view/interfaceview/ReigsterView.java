package com.mubly.comewaves.view.interfaceview;

import com.mubly.comewaves.common.base.BaseMvpView;
import com.mubly.comewaves.model.model.CategoryVo;

import java.util.List;

public interface ReigsterView extends BaseMvpView {
    void getTagSuccess(List<CategoryVo> categoryVos);

    void register();
}