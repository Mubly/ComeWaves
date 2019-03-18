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
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.mubly.comewaves.R;
import com.mubly.comewaves.view.activity.CommentActivity;
import com.mubly.comewaves.view.activity.StartActivity;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;


public class SwitchDetailActivity extends AppCompatActivity implements View.OnClickListener {

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

    public static void startTActivity(Activity activity, View transitionView) {
        Intent intent = new Intent(activity, SwitchDetailActivity.class);
        // 这里指定了共享的视图元素
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionView, OPTION_VIEW);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_detail);
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
        initView();
    }

    private void initView() {
        Glide.with(this).load(R.drawable.ishad_1).apply(RequestOptions.circleCropTransform()).into(userHeadIv);
    }

    private void findViewById() {
        userHeadIv = findViewById(R.id.user_avtar_iv);
        openMoreComment = findViewById(R.id.comment_open_more_tv);
        praiseCountTv = findViewById(R.id.praise_tv);
        commentCount = findViewById(R.id.comment_count);
        attentCount = findViewById(R.id.attent_count);
        back = findViewById(R.id.top_back_btn);
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
        initEvent();
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

    private void initEvent() {
        attentCount.setOnClickListener(this);
        back.setOnClickListener(this);
        openMoreComment.setOnClickListener(this);
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
                SwitchDetailActivity.this.startActivity(new Intent(this, CommentActivity.class));
                break;
            case R.id.praise_tv:

                break;
            case R.id.comment_count:
                break;
            case R.id.attent_count:
                Drawable drawable = getResources().getDrawable(R.mipmap.attent_red_icon);
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                attentCount.setCompoundDrawables(drawable, null, null, null);
                break;
            case R.id.top_back_btn:
                onBackPressed();
                break;

        }
    }
}
