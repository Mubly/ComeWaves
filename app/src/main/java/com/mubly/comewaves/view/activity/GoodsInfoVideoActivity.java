package com.mubly.comewaves.view.activity;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.utils.CommUtil;
import com.mubly.comewaves.common.utils.TextViewUtils;
import com.mubly.comewaves.common.utils.ToastUtils;
import com.mubly.comewaves.model.model.CommentInfo;
import com.mubly.comewaves.model.model.GlideImageLoader;
import com.mubly.comewaves.model.model.SmartBeanVo;
import com.mubly.comewaves.model.model.TopicInfoVo;
import com.mubly.comewaves.model.model.VisitorInfo;
import com.mubly.comewaves.present.CommentInfoPresent;
import com.mubly.comewaves.view.interfaceview.CommentInfoView;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的（视频贴）进入二级页面
 */
public class GoodsInfoVideoActivity extends BaseActivity<CommentInfoPresent, CommentInfoView> implements CommentInfoView {
    @BindView(R.id.top_back_btn)
    ImageButton topBackBtn;
    @BindView(R.id.goods_info_video_player)
    StandardGSYVideoPlayer videoPlayer;
    @BindView(R.id.user_avtar_iv)
    ImageView userAvtarIv;
    @BindView(R.id.user_name_tv)
    TextView userNameTv;
    @BindView(R.id.user_address_tv)
    TextView userAddressTv;
    @BindView(R.id.to_attention_tv)
    TextView toAttentionTv;
    @BindView(R.id.praise_tv)
    TextView praiseTv;
    @BindView(R.id.comment_count)
    TextView commentCount;
    @BindView(R.id.attent_count)
    TextView attentCount;
    @BindView(R.id.praise_comment_attent_layout)
    LinearLayout praiseCommentAttentLayout;
    @BindView(R.id.praiser_list_tv)
    TextView praiserListTv;
    @BindView(R.id.comment_layout)
    LinearLayout commentLayout;
    @BindView(R.id.comment_open_more_tv)
    TextView commentOpenMoreTv;
    @BindView(R.id.edit_message_layout)
    FrameLayout editLayout;
    @BindView(R.id.input_et)
    EditText inputEt;
    @BindView(R.id.video_content_tv)
    TextView contentTv;
    private int type;
    private int postId;
    //    private List<String> imgList = new ArrayList<>();
    OrientationUtils orientationUtils;


    @Override
    protected CommentInfoPresent createPresenter() {
        return new CommentInfoPresent();
    }

    @Override
    protected int getLayoutId() {
        type = getIntent().getIntExtra("type", 0);
        postId = getIntent().getIntExtra("postId", 0);
        return R.layout.activity_goods_video_info;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        inputEt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    String contentStr = inputEt.getText().toString();
                    if (!TextUtils.isEmpty(contentStr)) {
                        CommUtil.hideKeyboard(inputEt);
                        editLayout.setVisibility(View.GONE);
                        mPresenter.sendReplyComment(postId, contentStr, 1);
                    } else {
                        ToastUtils.showToast("请输入内容");
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void showCommentInfo(List<CommentInfo> commentInfos) {

    }

    @Override
    public void replyCommentSuccess() {
        initData();
        inputEt.setText("");
        ToastUtils.showToast("评论添加成功");
    }

    @Override
    public void showTopicInfo(TopicInfoVo topicInfoVo) {
        commentLayout.removeAllViews();
        if (null != topicInfoVo.comment && topicInfoVo.comment.size() > 0) {
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            commentLayout.setVisibility(View.VISIBLE);
            commentOpenMoreTv.setVisibility(View.VISIBLE);
            for (VisitorInfo commentInfo : topicInfoVo.comment) {

                View view = layoutInflater.inflate(R.layout.view_comment_layout, null);
                ((TextView) view.findViewById(R.id.commenter_name_tv)).setText(commentInfo.getUser_name() + "：" + commentInfo.getContent());
                ((TextView) view.findViewById(R.id.comment_time_tv)).setText(commentInfo.getCreated_time());
                ((ImageView) view.findViewById(R.id.comment_praise_iv)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast("点赞成功");
                    }
                });
                commentLayout.addView(view);
            }
        } else {
            commentLayout.setVisibility(View.GONE);
            commentOpenMoreTv.setVisibility(View.GONE);
        }

        Glide.with(mContext).load(topicInfoVo.post.getUser_head()).apply(RequestOptions.circleCropTransform()).into(userAvtarIv);
        userNameTv.setText(topicInfoVo.post.getUser_name());
        userAddressTv.setText(topicInfoVo.post.getLocation());
        if (false) {//判断是否已关注
            toAttentionTv.setVisibility(View.GONE);
        }
        contentTv.setText(TextViewUtils.getWeiBoContent(mContext, topicInfoVo.post.getPost_info(), contentTv));
        praiseTv.setText(topicInfoVo.post.getFabulous_num() + "");//喜欢
        attentCount.setText(topicInfoVo.post.getCollection_num() + "");//收藏
        commentCount.setText(topicInfoVo.post.getReport_num() + "");
        setPraise(topicInfoVo.post.getLike_status(), false);
        setAttent(topicInfoVo.post.getCollect_status(), false);
        initPlayer(topicInfoVo);
    }

    private void initPlayer(TopicInfoVo topicInfoVo) {
        videoPlayer.setUp(topicInfoVo.post.getVideo_url(), true, "测试视频");

        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(this).load(topicInfoVo.post.getFirst_url()).into(imageView);
        videoPlayer.setThumbImageView(imageView);
        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.VISIBLE);
        //设置旋转
        orientationUtils = new OrientationUtils(this, videoPlayer);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        videoPlayer.getBackButton().setVisibility(View.GONE);
        videoPlayer.startPlayLogic();
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
    public void initData() {
        super.initData();
        mPresenter.getTopicInfo(postId, type);
    }

    @OnClick({R.id.to_attention_tv, R.id.praise_tv, R.id.comment_count, R.id.attent_count, R.id.comment_open_more_tv, R.id.top_back_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back_btn:
                finish();
                break;
            case R.id.to_attention_tv:
                ToastUtils.showToast("暂未开放，敬请期待");
                break;
            case R.id.praise_tv:
                mPresenter.doPraise(postId);
                break;
            case R.id.comment_count:
                editLayout.setVisibility(View.VISIBLE);
                CommUtil.showKeyboard(inputEt);
                break;
            case R.id.attent_count:
                mPresenter.doCollection(postId);
                break;
            case R.id.comment_open_more_tv:
                Intent intent = new Intent(this, CommentActivity.class);
                intent.putExtra("postId", postId);
                startActivity(intent);
                break;
        }
    }

    private void setAttent(int b, boolean isCount) {
        Drawable drawable = null;
        String count = attentCount.getText().toString();

        if (b == 0) {
            if (isCount) {
                attentCount.setText(CommUtil.strLess(count, 1));
            }

            drawable = getResources().getDrawable(R.mipmap.attent_no_icon);
        } else {
            if (isCount) {
                attentCount.setText(CommUtil.strLess(count, -1));
            }

            drawable = getResources().getDrawable(R.mipmap.attent_red_icon);
        }
        // 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        attentCount.setCompoundDrawables(drawable, null, null, null);
    }

    private void setPraise(int b, boolean isCount) {
        Drawable drawable = null;
        String count = praiseTv.getText().toString();
        if (b == 0) {
            drawable = getResources().getDrawable(R.mipmap.praise_icon);

            if (isCount) {
                praiseTv.setText(CommUtil.strLess(count, 1));
            }

        } else {

            drawable = getResources().getDrawable(R.mipmap.praise_light_icon);
            if (isCount) {
                praiseTv.setText(CommUtil.strLess(count, -1));
            }
        }
        // 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        praiseTv.setCompoundDrawables(drawable, null, null, null);
    }


    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onBackPressed() {
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            videoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        videoPlayer.setVideoAllCallBack(null);
        super.onBackPressed();
    }
}
