package com.mubly.comewaves.videoplayer;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
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
import com.gyf.barlibrary.ImmersionBar;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseMvpView;
import com.mubly.comewaves.common.utils.CommUtil;
import com.mubly.comewaves.common.utils.ToastUtils;
import com.mubly.comewaves.model.model.CommentInfo;
import com.mubly.comewaves.model.model.SmartBeanVo;
import com.mubly.comewaves.model.model.TopicInfoVo;
import com.mubly.comewaves.model.model.VisitorInfo;
import com.mubly.comewaves.present.CommentInfoPresent;
import com.mubly.comewaves.view.activity.BaseActivity;
import com.mubly.comewaves.view.activity.CommentActivity;
import com.mubly.comewaves.view.activity.StartActivity;
import com.mubly.comewaves.view.interfaceview.CommentInfoView;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;

import java.util.IllegalFormatCodePointException;
import java.util.List;


public class SwitchDetailActivity extends BaseActivity<CommentInfoPresent, CommentInfoView> implements View.OnClickListener, CommentInfoView {

    public static final String URL = "url";
    private static final String OPTION_VIEW = "view";

    SwitchVideo detailPlayer;

    private boolean isPlay = true;
    private boolean isPause;
    ImageView userHeadIv;
    TextView openMoreComment;
    TextView praiseCountTv, commentCount, attentCount;
    ImageButton back;
    private OrientationUtils orientationUtils;
    int postId;
    int userId;
    CommentInfoPresent commentInfoPresent;
    LinearLayout commentLayout;
    FrameLayout editLayout;
    EditText inputEt;
    private TextView userName;
    private TextView userAddress;
    private TextView facousTv;

    public static void startTActivity(Activity activity, View transitionView, int postId) {
        Intent intent = new Intent(activity, SwitchDetailActivity.class);
        intent.putExtra("postId", postId);
        // 这里指定了共享的视图元素
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionView, OPTION_VIEW);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }


    @Override
    public void initView() {
        super.initView();
        postId = getIntent().getIntExtra("postId", 0);
        findViewById();
        ImmersionBar.with(this).statusBarColor(R.color.black_aph80).init();
        detailPlayer = (SwitchVideo) findViewById(R.id.detail_player);


        //增加title
        detailPlayer.getTitleTextView().setVisibility(View.GONE);
        detailPlayer.getBackButton().setVisibility(View.GONE);
        detailPlayer.isShowDetialBtn(false);

        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, detailPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);

        SwitchUtil.optionPlayer(detailPlayer, getIntent().getStringExtra(URL), true, "");

        SwitchUtil.clonePlayState(detailPlayer);

        detailPlayer.setIsTouchWiget(true);

        detailPlayer.setVideoAllCallBack(new GSYSampleCallBack() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //开始播放了才能旋转和全屏
                orientationUtils.setEnable(true);
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }
        });

        detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();
                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                detailPlayer.startWindowFullscreen(SwitchDetailActivity.this, true, true);
            }
        });

        detailPlayer.setSurfaceToPlay();


        // 这里指定了被共享的视图元素
        ViewCompat.setTransitionName(detailPlayer, OPTION_VIEW);
        Glide.with(this).load(R.drawable.ishad_1).apply(RequestOptions.circleCropTransform()).into(userHeadIv);

    }


    private void findViewById() {
        userHeadIv = findViewById(R.id.user_avtar_iv);
        openMoreComment = findViewById(R.id.comment_open_more_tv);
        praiseCountTv = findViewById(R.id.praise_tv);
        commentCount = findViewById(R.id.comment_count);
        attentCount = findViewById(R.id.attent_count);
        back = findViewById(R.id.top_back_btn);
        commentLayout = findViewById(R.id.comment_layout);
        editLayout = findViewById(R.id.edit_message_layout);
        inputEt = findViewById(R.id.input_et);
        userName = findViewById(R.id.user_name_tv);
        userAddress = findViewById(R.id.user_address_tv);
        facousTv = findViewById(R.id.to_attention_tv);

    }

    @Override
    public void onBackPressed() {
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onPause() {

        detailPlayer.getCurrentPlayer().onVideoPause();
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        detailPlayer.getCurrentPlayer().onVideoResume(false);
        super.onResume();
        isPause = false;
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.getTopicInfo(postId, 1);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailPlayer.getGSYVideoManager().setListener(detailPlayer.getGSYVideoManager().lastListener());
        detailPlayer.getGSYVideoManager().setLastListener(null);
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();

        SwitchUtil.release();
        ImmersionBar.with(this).destroy();
    }

    @Override
    public void initEvent() {
        attentCount.setOnClickListener(this);
        back.setOnClickListener(this);
        openMoreComment.setOnClickListener(this);
        commentCount.setOnClickListener(this);
        praiseCountTv.setOnClickListener(this);
        facousTv.setOnClickListener(this);
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
    protected CommentInfoPresent createPresenter() {
        return new CommentInfoPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_switch_detail;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            detailPlayer.onConfigurationChanged(this, newConfig, orientationUtils, true, true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comment_open_more_tv:
                Intent intent = new Intent(this, CommentActivity.class);
                intent.putExtra("postId", postId);
                startActivity(intent);
                break;
            case R.id.praise_tv://点赞
                mPresenter.doPraise(postId);
                break;
            case R.id.comment_count://评论
                editLayout.setVisibility(View.VISIBLE);
                CommUtil.showKeyboard(inputEt);
                break;
            case R.id.attent_count://收藏
                mPresenter.doCollection(postId);
                break;
            case R.id.top_back_btn:
                onBackPressed();
                break;
            case R.id.to_attention_tv://关注
                ToastUtils.showToast("暂未开放，敬请期待");
//                mPresenter.doAttention(userId);
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
        String count = praiseCountTv.getText().toString();
        if (b == 0) {
            drawable = getResources().getDrawable(R.mipmap.praise_icon);

            if (isCount) {
                praiseCountTv.setText(CommUtil.strLess(count, 1));
            }

        } else {

            drawable = getResources().getDrawable(R.mipmap.praise_light_icon);
            if (isCount) {
                praiseCountTv.setText(CommUtil.strLess(count, -1));
            }
        }
        // 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        praiseCountTv.setCompoundDrawables(drawable, null, null, null);
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
            openMoreComment.setVisibility(View.VISIBLE);
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
            openMoreComment.setVisibility(View.GONE);
        }
        userId=topicInfoVo.post.getPost_id();
        Glide.with(mContext).load(topicInfoVo.post.getUser_head()).apply(RequestOptions.circleCropTransform()).into(userHeadIv);
        userName.setText(topicInfoVo.post.getUser_name());

        userAddress.setText(topicInfoVo.post.getLocation());
        if (false) {//判断是否已关注
            facousTv.setVisibility(View.GONE);
        }
        praiseCountTv.setText(topicInfoVo.post.getFabulous_num() + "");//喜欢
        attentCount.setText(topicInfoVo.post.getLike_status() + "");//收藏
        commentCount.setText(topicInfoVo.post.getReport_num() + "");
        setPraise(topicInfoVo.post.getLike_status(), false);
        setAttent(topicInfoVo.post.getCollect_status(), false);
    }

    @Override
    public void doPraise(SmartBeanVo smartBeanVo) {
        setPraise(smartBeanVo.status, true);
    }

    @Override
    public void doCollection(SmartBeanVo smartBeanVo) {
        setAttent(smartBeanVo.status, true);
    }

    @Override
    public void doAttention(SmartBeanVo smartBeanVo) {

    }


}
