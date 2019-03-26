package com.mubly.comewaves.view.interfaceview;

import com.mubly.comewaves.common.base.BaseMvpView;
import com.mubly.comewaves.model.model.SearchVideoVo;

import java.util.List;

public interface SearchInfoView extends BaseMvpView {
void getSearchVideoInfo(List<SearchVideoVo> searchVideoVo);
}
