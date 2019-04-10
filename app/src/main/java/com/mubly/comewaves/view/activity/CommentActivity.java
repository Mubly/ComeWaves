package com.mubly.comewaves.view.activity;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.utils.CommUtil;
import com.mubly.comewaves.model.adapter.BaseRecyclerViewAdapter;
import com.mubly.comewaves.model.adapter.SmartAdapter;
import com.mubly.comewaves.model.model.CommentInfo;
import com.mubly.comewaves.model.model.SmartBeanVo;
import com.mubly.comewaves.model.model.TopicInfoVo;
import com.mubly.comewaves.present.CommentInfoPresent;
import com.mubly.comewaves.view.costomview.SpacesItemDecoration;
import com.mubly.comewaves.view.interfaceview.CommentInfoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 评论页面
 */
public class CommentActivity extends BaseActivity<CommentInfoPresent, CommentInfoView> implements CommentInfoView {
    @BindView(R.id.comment_edit)
    EditText commentEdit;
    @BindView(R.id.one_coment_rv)
    RecyclerView mRecycleView;
    @BindView(R.id.comment_send_btn)
    Button sendBtn;
    @BindView(R.id.top_back_btn)
    ImageButton backBtn;
    int topicId;
    int selectId;
    SmartAdapter smartAdapter;
    int userId;
    int selectCommendId;
    int type = 1;//回复的类型1:文章 2:文章图片 3:文章视频

    List<CommentInfo> commentInfoList = new ArrayList<>();

    @Override
    public void initEvent() {
        super.initEvent();
    }

    @Override
    protected CommentInfoPresent createPresenter() {
        return new CommentInfoPresent();
    }

    @Override
    public void initView() {
        super.initView();
        smartAdapter = new SmartAdapter<CommentInfo>(commentInfoList) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_comment_view;
            }

            @Override
            public void dealView(VH holder, CommentInfo data, int position) {
                initCommentView(holder, data, position);
            }


        };
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setAdapter(smartAdapter);
    }

    private void initCommentView(BaseRecyclerViewAdapter.VH holder, final CommentInfo data, int position) {
        SmartAdapter imgAdapter = null;
        RecyclerView imgRv;
        SmartAdapter innAdapter = null;
        RecyclerView innRv;
        List<CommentInfo.CombineBean> imageUrlList;
        ImageView avtarImg = (ImageView) holder.getChildView(R.id.user_avtar_img);
        Glide.with(mContext).load(data.getUser_head()).apply(RequestOptions.circleCropTransform().placeholder(R.mipmap.ic_launcher)).into(avtarImg);
        holder.setText(R.id.user_name_tv, data.getUser_name());
        holder.setText(R.id.comment_reply_time_tv, data.getCreated_time());
        holder.setText(R.id.comment_reply_content, data.getContent());
        imgRv = (RecyclerView) holder.getChildView(R.id.comment_img_video_rv);
        innRv = (RecyclerView) holder.getChildView(R.id.comment_child_rv);
        if (null != data.getCombine() && data.getCombine().size() > 0) {
            imageUrlList = new ArrayList<>();
            imageUrlList.addAll(data.getCombine());
            initImgView(imgAdapter, imgRv, imageUrlList, data.getCombine_status());
        }
        if (null != data.getReport_combine() && data.getReport_combine().size() > 0) {
            initInnReply(innAdapter, innRv, data.getReport_combine());
        }
        holder.getView(R.id.user_name_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCommendId = data.getComment_id();
                userId = data.getUser_id();
                CommUtil.showKeyboard(commentEdit);
                commentEdit.setHint("回复:" + data.getUser_name());
            }
        });
    }

    private void initInnReply(SmartAdapter innAdapter, RecyclerView innRv, List<CommentInfo.ReportCombine> reply) {
        innAdapter = new SmartAdapter<CommentInfo.ReportCombine>(reply) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_comment_inn_view;
            }

            @Override
            public void dealView(VH holder, final CommentInfo.ReportCombine data, int position) {
                ImageView avtarImg = (ImageView) holder.getChildView(R.id.user_inn_avtar_img);
                Glide.with(mContext).load(data.getForm_user().getUser_head()).apply(RequestOptions.circleCropTransform().placeholder(R.mipmap.ic_launcher)).into(avtarImg);
                if (null != data.getUser()) {
                    holder.setText(R.id.user_inn_name_tv, data.getForm_user().getUser_name() + " 回复 " + data.getUser().getUser_name());
                } else {
                    holder.setText(R.id.user_inn_name_tv, data.getForm_user().getUser_name());
                }

                holder.setText(R.id.comment_inn_reply_time_tv, data.getCreated_time());
                holder.setText(R.id.comment_inn_reply_content, data.getContent());
                holder.getView(R.id.user_inn_name_tv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectCommendId = data.getCommon_id();
                        userId = data.getForm_user_id();
                        CommUtil.showKeyboard(commentEdit);
                        commentEdit.setHint("回复:" + data.getForm_user().getUser_name());
                    }
                });
            }


        };
        innRv.setLayoutManager(new LinearLayoutManager(mContext));
        innRv.setAdapter(innAdapter);
    }

    private void initImgView(SmartAdapter imgAdapter, RecyclerView imgRv, List<CommentInfo.CombineBean> imageUrlList, final int type) {
        imgAdapter = new SmartAdapter<CommentInfo.CombineBean>(imageUrlList) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_square_img_layout;
            }

            @Override
            public void dealView(VH holder, CommentInfo.CombineBean data, int position) {
                if (type == 2) {
                    holder.setNetImage(mContext, R.id.square_img, data.getImg());
                } else if (type == 3) {
                    holder.setNetImage(mContext, R.id.square_img, data.getVideo());
                }

            }


        };
        imgRv.setLayoutManager(new GridLayoutManager(mContext, 3));
        imgRv.setAdapter(imgAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(10);
        imgRv.addItemDecoration(decoration);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.getCommentInfo(topicId);
    }

    @Override
    protected int getLayoutId() {
        topicId = getIntent().getIntExtra("postId", 0);
//        userId = getIntent().getIntExtra("userId", 0);
        return R.layout.activity_comment;
    }

    @Override
    public void showCommentInfo(List<CommentInfo> commentInfos) {
        commentInfoList.clear();
        commentInfoList.addAll(commentInfos);
        smartAdapter.notifyDataSetChanged();
    }

    @Override
    public void replyCommentSuccess() {
        mPresenter.getCommentInfo(topicId);
        commentEdit.setText("");
        selectCommendId = 0;
        CommUtil.hideKeyboard(commentEdit);
    }

    @Override
    public void showTopicInfo(TopicInfoVo topicInfoVo) {

    }

    @Override
    public void doPraise(SmartBeanVo smartBeanVo) {

    }

    @Override
    public void doCollection(SmartBeanVo smartBeanVo) {

    }

    @Override
    public void doAttention(SmartBeanVo smartBeanVo) {

    }

    @Override
    public void replyError(String msg) {
        CommUtil.hideKeyboard(commentEdit);
    }


    @OnClick({R.id.top_back_btn, R.id.comment_send_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back_btn:
                finish();
                break;
            case R.id.comment_send_btn:

                selectId = topicId;

                String contentStr = commentEdit.getText().toString();
                if (TextUtils.isEmpty(contentStr)) {
                    return;
                }
                if (selectCommendId == 0) {
                    mPresenter.sendReplyComment(topicId, contentStr, type);
                } else {
                    mPresenter.replyComment(topicId, contentStr, type, selectCommendId, userId);
                }


                break;
        }
    }
}
