package com.mubly.comewaves.videoplayer;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.mubly.comewaves.R;
import com.mubly.comewaves.model.interfaces.CallBack;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

public class SwitchVideo extends StandardGSYVideoPlayer {

    private TextView detailBtn;
    private OnClickCallBack onClickCallBack;

    public void setOnClickCallBack(OnClickCallBack onClickCallBack) {
        this.onClickCallBack = onClickCallBack;
    }

    public SwitchVideo(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public SwitchVideo(Context context) {
        super(context);
    }

    public SwitchVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        detailBtn = (TextView) findViewById(R.id.detail_btn);
        detailBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (isInPlayingState()) {
                    if (null != onClickCallBack) {
                        onClickCallBack.onClick(SwitchVideo.this);
                    }
//                }
            }
        });
        if (mIfCurrentIsFullscreen) {
            detailBtn.setVisibility(GONE);
        }

    }

    public void isShowDetialBtn(boolean flag) {
        if (flag) {
            detailBtn.setVisibility(VISIBLE);
        } else {
            detailBtn.setVisibility(GONE);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.switch_video;
    }


    public void setSwitchUrl(String url) {
        mUrl = url;
        mOriginUrl = url;
    }

    public void setSwitchCache(boolean cache) {
        mCache = cache;
    }

    public void setSwitchTitle(String title) {
        mTitle = title;
    }

    public void setSurfaceToPlay() {
        addTextureView();
        getGSYVideoManager().setListener(this);
        checkoutState();
    }

    public SwitchVideo saveState() {
        SwitchVideo switchVideo = new SwitchVideo(getContext());
        cloneParams(this, switchVideo);
        return switchVideo;
    }

    public void cloneState(SwitchVideo switchVideo) {
        cloneParams(switchVideo, this);
    }

    interface OnClickCallBack {
        void onClick(SwitchVideo switchVideo);
    }
}
