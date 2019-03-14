package com.mubly.comewaves.videoplayer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mubly.comewaves.R;
import com.mubly.comewaves.model.model.HomeBean;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;


/**
 * Created by guoshuyu on 2017/1/9.
 */

public class RecyclerItemAutoDetiallHolder extends RecyclerItemBaseHolder {

    public final static String TAG = "RecyclerView2List";

    protected Context context = null;


    SwitchVideo gsyVideoPlayer;

    ImageView imageView;

    GSYVideoOptionBuilder gsyVideoOptionBuilder;

    public RecyclerItemAutoDetiallHolder(Context context, View v) {
        super(v);
        this.context = context;
        imageView = new ImageView(context);
        gsyVideoOptionBuilder = new GSYVideoOptionBuilder();
        gsyVideoPlayer = v.findViewById(R.id.video_item_player);
    }

    public void onBind(final int position, HomeBean homeBean) {

        //增加封面
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context).load(homeBean.getFirst_url()).into(imageView);

        if (imageView.getParent() != null) {
            ViewGroup viewGroup = (ViewGroup) imageView.getParent();
            viewGroup.removeView(imageView);
        }
        String url;
        String title;
        if (position % 2 == 0) {
            url = "https://res.exexm.com/cw_145225549855002";
            title = "这是title";
        } else {
            url = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
            title = "哦？Title？";
        }


//        Map<String, String> header = new HashMap<>();
//        header.put("ee", "33");
//
//        //防止错位，离开释放
//        //gsyVideoPlayer.initUIState();
//        gsyVideoOptionBuilder
//                .setIsTouchWiget(false)
//                .setThumbImageView(imageView)
//                .setUrl(url)
//                .setVideoTitle(title)
//                .setCacheWithPlay(false)
//                .setRotateViewAuto(true)
//                .setLockLand(true)
//                .setPlayTag(TAG)
//                .setMapHeadData(header)
//                .setShowFullAnimation(true)
//                .setNeedLockFull(true)
//                .setPlayPosition(position)
//                .setVideoAllCallBack(new GSYSampleCallBack() {
//                    @Override
//                    public void onPrepared(String url, Object... objects) {
//                        super.onPrepared(url, objects);
//                        if (!gsyVideoPlayer.isIfCurrentIsFullscreen()) {
//                            //静音
//                            GSYVideoManager.instance().setNeedMute(true);
//                        }
//
//                    }
//
//                    @Override
//                    public void onQuitFullscreen(String url, Object... objects) {
//                        super.onQuitFullscreen(url, objects);
//                        //全屏不静音
//                        GSYVideoManager.instance().setNeedMute(true);
//                    }
//
//                    @Override
//                    public void onEnterFullscreen(String url, Object... objects) {
//                        super.onEnterFullscreen(url, objects);
//                        GSYVideoManager.instance().setNeedMute(false);
//                        gsyVideoPlayer.getCurrentPlayer().getTitleTextView().setText((String)objects[0]);
//                    }
//                }).build(gsyVideoPlayer);

        gsyVideoPlayer.setPlayPosition(position);
        SwitchUtil.optionPlayer(gsyVideoPlayer, url, true, "这是title");
        gsyVideoPlayer.setUpLazy(url, true, null, null, "这是title");
        gsyVideoPlayer.setThumbImageView(imageView);
        if (position == GSYVideoManager.instance().getPlayPosition()){
           gsyVideoPlayer.getThumbImageViewLayout().setVisibility(View.GONE);
        } else {
          gsyVideoPlayer.getThumbImageViewLayout().setVisibility(View.VISIBLE);
        }
//        //增加title
//        gsyVideoPlayer.getTitleTextView().setVisibility(View.GONE);
//
//        //设置返回键
//        gsyVideoPlayer.getBackButton().setVisibility(View.GONE);
//
//        //设置全屏按键功能
//        gsyVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                resolveFullBtn(gsyVideoPlayer);
//            }
//        });
    }

    /**
     * 全屏幕按键处理
     */
    private void resolveFullBtn(final StandardGSYVideoPlayer standardGSYVideoPlayer) {
        standardGSYVideoPlayer.startWindowFullscreen(context, true, true);
    }

}