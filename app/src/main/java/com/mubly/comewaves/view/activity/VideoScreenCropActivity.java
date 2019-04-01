package com.mubly.comewaves.view.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.SeekBar;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoScreenCropActivity extends BaseActivity {
    @BindView(R.id.crop_video_surface)
    SurfaceView cropVideoSurface;
    @BindView(R.id.crop_vide_seekbar)
    SeekBar cropVideSeekbar;
    private MediaPlayer player;
    private SurfaceHolder holder;
    public static final int UPDATE_TIME = 0x0001;
    String localVideoPath = "/storage/emulated/0/PictureSelector/CameraImage/PictureSelector_20190318_125418.mp4";
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TIME:
                    updateTime();
                    mHandler.sendEmptyMessageDelayed(UPDATE_TIME, 500);
                    break;
            }
        }
    };

    private void updateTime() {

        cropVideSeekbar.setProgress(player.getCurrentPosition());
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_screen_crop;
    }

    @Override
    public void initView() {
        super.initView();
        player = new MediaPlayer();
        try {
            player.setDataSource(this, Uri.parse(localVideoPath));
            holder = cropVideoSurface.getHolder();
            holder.addCallback(new MyCallBack());
            player.prepare();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    player.start();
                    player.setLooping(true);
                    mHandler.sendEmptyMessageDelayed(UPDATE_TIME, 500);
                    cropVideSeekbar.setMax(mp.getDuration());
                    cropVideSeekbar.setProgress(mp.getCurrentPosition());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initEvent() {
        super.initEvent();
        player.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mp) {
                player.pause();
            }
        });
        cropVideSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (null != player && fromUser) {
                    player.seekTo(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private class MyCallBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            player.setDisplay(holder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }

}
