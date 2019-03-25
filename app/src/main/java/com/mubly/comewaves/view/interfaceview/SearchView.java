package com.mubly.comewaves.view.interfaceview;

import com.mubly.comewaves.common.base.BaseMvpView;
import com.mubly.comewaves.model.model.CategoryVo;

import java.util.List;

public interface SearchView extends BaseMvpView {
    void getOneTab(List<CategoryVo> categoryVoList);
}
