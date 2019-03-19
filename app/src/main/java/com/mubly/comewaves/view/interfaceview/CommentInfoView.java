package com.mubly.comewaves.view.interfaceview;

import com.mubly.comewaves.common.base.BaseMvpView;
import com.mubly.comewaves.model.model.CommentInfo;
import com.mubly.comewaves.model.model.TopicInfoVo;

import java.util.List;

public interface CommentInfoView extends BaseMvpView {
    void showCommentInfo(List<CommentInfo> commentInfos) ;
    void replyCommentSuccess();
    void showTopicInfo(TopicInfoVo topicInfoVo);
}
