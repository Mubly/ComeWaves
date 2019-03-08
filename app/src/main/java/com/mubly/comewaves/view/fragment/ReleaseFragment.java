package com.mubly.comewaves.view.fragment;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;


import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseFragment;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.utils.CommUtil;

import butterknife.BindView;


public class ReleaseFragment extends BaseFragment implements SurfaceHolder.Callback,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener,
        MediaPlayer.OnErrorListener,
        MediaPlayer.OnInfoListener, View.OnClickListener,
        MediaPlayer.OnSeekCompleteListener,
        MediaPlayer.OnVideoSizeChangedListener,
        SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.playOrPause)
    ImageView playOrPauseIv;
    @BindView(R.id.surfaceView)
    SurfaceView videoSuf;
    private MediaPlayer mPlayer;
    @BindView(R.id.tv_progess)
    SeekBar mSeekBar;

    @BindView(R.id.root_rl)
    RelativeLayout rootViewRl;
    @BindView(R.id.control_ll)
    LinearLayout controlLl;
    @BindView(R.id.tv_start_time)
    TextView startTime;
    @BindView(R.id.tv_end_time)
    TextView endTime;
    @BindView(R.id.tv_forward)
    ImageView forwardButton;
    @BindView(R.id.tv_backward)
    ImageView backwardButton;
    private boolean isShow = false;
    private String path="http://gslb.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4";
    public static final int UPDATE_TIME = 0x0001;
    public static final int HIDE_CONTROL = 0x0002;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TIME:
                    updateTime();
                    mHandler.sendEmptyMessageDelayed(UPDATE_TIME, 500);
                    break;
                case HIDE_CONTROL:
                    hideControl();
                    break;
            }
        }
    };


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_release;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
//        initSurfaceView();
//        initPlayer();
//        initEvents();
    }



    private void initEvents() {
        playOrPauseIv.setOnClickListener(this);
        rootViewRl.setOnClickListener(this);
//        rootViewRl.setOnTouchListener(this);
        forwardButton.setOnClickListener(this);
        backwardButton.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    private void initSurfaceView() {
        videoSuf.setZOrderOnTop(false);
        videoSuf.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        videoSuf.getHolder().addCallback(this);
    }

    private void initPlayer() {
        mPlayer = new MediaPlayer();
        mPlayer.setOnCompletionListener(this);
        mPlayer.setOnErrorListener(this);
        mPlayer.setOnInfoListener(this);
        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnSeekCompleteListener(this);
        mPlayer.setOnVideoSizeChangedListener(this);
        try {
            //使用手机本地视频
            mPlayer.setDataSource(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mPlayer.setDisplay(holder);//将所播放的视频图像输出到指定的SurfaceView组件。
        mPlayer.prepareAsync();//装载流媒体文件
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {

        startTime.setText(CommUtil.stampTodate(mp.getCurrentPosition()));
        endTime.setText(CommUtil.stampTodate(mp.getDuration()));
        mSeekBar.setMax(mp.getDuration());
        mSeekBar.setProgress(mp.getCurrentPosition());
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }

    private void play() {
        if (mPlayer == null) {
            return;
        }
        Log.i("playPath", path);
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
            mHandler.removeMessages(UPDATE_TIME);
            mHandler.removeMessages(HIDE_CONTROL);
            playOrPauseIv.setVisibility(View.VISIBLE);
            playOrPauseIv.setImageResource(android.R.drawable.ic_media_play);
        } else {
            mPlayer.start();
            mHandler.sendEmptyMessageDelayed(UPDATE_TIME, 500);
            mHandler.sendEmptyMessageDelayed(HIDE_CONTROL, 5000);
            playOrPauseIv.setVisibility(View.INVISIBLE);
            playOrPauseIv.setImageResource(android.R.drawable.ic_media_pause);
        }
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {
        //TODO
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_backward:
                backWard();
                break;
            case R.id.tv_forward:
                forWard();
                break;
            case R.id.playOrPause:
                play();
                break;
            case R.id.root_rl:
                showControl();
                break;
        }
    }

    /**
     * 更新播放时间
     */
    private void updateTime() {
        startTime.setText(CommUtil.stampTodate(mPlayer.getCurrentPosition()));
        mSeekBar.setProgress(mPlayer.getCurrentPosition());
    }

    /**
     * 隐藏进度条
     */
    private void hideControl() {
        isShow = false;
        mHandler.removeMessages(UPDATE_TIME);
        controlLl.animate().setDuration(300).translationY(controlLl.getHeight());
    }

    /**
     * 显示进度条
     */
    private void showControl() {
        if (isShow) {
            play();
        }
        isShow = true;
        mHandler.removeMessages(HIDE_CONTROL);
        mHandler.sendEmptyMessage(UPDATE_TIME);
        mHandler.sendEmptyMessageDelayed(HIDE_CONTROL, 5000);
        controlLl.animate().setDuration(300).translationY(0);
    }

    /**
     * 设置快进10秒方法
     */
    private void forWard() {
        if (mPlayer != null) {
            int position = mPlayer.getCurrentPosition();
            mPlayer.seekTo(position + 10000);
        }
    }

    /**
     * 设置快退10秒的方法
     */
    public void backWard() {
        if (mPlayer != null) {
            int position = mPlayer.getCurrentPosition();
            if (position > 10000) {
                position -= 10000;
            } else {
                position = 0;
            }
            mPlayer.seekTo(position);
        }
    }

    //OnSeekBarChangeListener
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        if (mPlayer != null && b) {
            mPlayer.seekTo(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
