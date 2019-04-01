package com.mubly.comewaves.videoplayer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.utils.CommUtil;
import com.mubly.comewaves.common.utils.ToastUtils;
import com.mubly.comewaves.model.interfaces.ScrollChange;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.Random;

public class MyVideoPlayer extends StandardGSYVideoPlayer {
    MotionEvent mMotionEvent;
    boolean verListener = true;//是否监听上下滑动
    boolean isVerStatus = false;
    ScrollChange scrollChange;

    public void setScrollChangeListener(ScrollChange scrollChange) {
        this.scrollChange = scrollChange;
    }

    public MyVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public MyVideoPlayer(Context context) {
        super(context);
    }

    public MyVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void showVolumeDialog(float deltaY, int volumePercent) {
//        super.showVolumeDialog(deltaY, volumePercent);
        Log.i("myVideoPlayer", "向上滑动/向下滑动" + volumePercent);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int id = v.getId();
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                touchSurfaceDown(x, y);

                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = x - mDownX;
                float deltaY = y - mDownY;
                touchSurfaceMove(deltaX, deltaY, y);
                break;
            case MotionEvent.ACTION_UP:
                float deltaUpX = x - mDownX;
                float deltaUpY = y - mDownY;
                startDismissControlViewTimer();

                startProgressTimer();

                //不要和隐藏虚拟按键后，滑出虚拟按键冲突
                if (mHideKey && mShowVKey) {
                    return true;
                }
                touchUp(deltaUpX, deltaUpY);
                break;

        }
        mMotionEvent = event;
        gestureDetector.onTouchEvent(event);
        return super.onTouch(v, event);
    }

    private void touchUp(float deltaX, float deltaY) {
        if (isVerStatus) {
            isVerStatus = false;
            scrollChange.scrollChanged(deltaX, deltaY);
        }
    }


    @Override
    protected void touchSurfaceMove(float deltaX, float deltaY, float y) {
        float absDeltaX = Math.abs(deltaX);
        float absDeltaY = Math.abs(deltaY);
        if (absDeltaY > absDeltaX && !isVerStatus) {
            isVerStatus = true;
        }
        if (isVerStatus) {
            scrollChange.scrollChange(deltaX, deltaY);
        }
    }

    @Override
    protected void touchDoubleUp() {
        final ImageView imageView = new ImageView(mContext);
        //设置展示的位置，需要在手指触摸的位置上方，即触摸点是心形的右下角的位置
        LayoutParams params = new LayoutParams(300, 300);
        params.leftMargin = (int) mMotionEvent.getX() - 150;
        params.topMargin = (int) mMotionEvent.getY() - 300;
        //设置图片资源
        imageView.setImageDrawable(getResources().getDrawable(R.mipmap.praise_light_icon));
        imageView.setLayoutParams(params);
        //把IV添加到父布局当中
        addView(imageView);
        //设置控件的动画
        AnimatorSet animatorSet = new AnimatorSet();
        //缩放动画，X轴2倍缩小至0.9倍
        animatorSet.play(scale(imageView, "scaleX", 2f, 0.9f, 100, 0))
                //缩放动画，Y轴2倍缩放至0.9倍
                .with(scale(imageView, "scaleY", 2f, 0.9f, 100, 0))
                //旋转动画，随机旋转角
                .with(rotation(imageView, 0, 0, new float[]{1.5f, 3.6f, 2.8f}))
                //渐变透明动画，透明度从0-1
                .with(alpha(imageView, 0, 1, 100, 0))
                //缩放动画，X轴0.9倍缩小至
                .with(scale(imageView, "scaleX", 0.9f, 1, 50, 150))
                //缩放动画，Y轴0.9倍缩放至
                .with(scale(imageView, "scaleY", 0.9f, 1, 50, 150))
                //位移动画，Y轴从0上移至600
                .with(translationY(imageView, 0, -600, 800, 400))
                //透明动画，从1-0
                .with(alpha(imageView, 1, 0, 300, 400))
                //缩放动画，X轴1至3倍
                .with(scale(imageView, "scaleX", 1, 3f, 700, 400))
                //缩放动画，Y轴1至3倍
                .with(scale(imageView, "scaleY", 1, 3f, 700, 400));
        //开始动画
        animatorSet.start();
        //设置动画结束监听
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //当动画结束以后，需要把控件从父布局移除
                removeViewInLayout(imageView);
            }
        });

    }


    /**
     * 缩放动画
     *
     * @param view
     * @param propertyName
     * @param from
     * @param to
     * @param time
     * @param delayTime
     * @return
     */
    public static ObjectAnimator scale(View view, String propertyName, float from, float to, long time, long delayTime) {
        ObjectAnimator translation = ObjectAnimator.ofFloat(view
                , propertyName
                , from, to);
        translation.setInterpolator(new LinearInterpolator());
        translation.setStartDelay(delayTime);
        translation.setDuration(time);
        return translation;
    }

    /**
     * 位移动画
     *
     * @param view
     * @param from
     * @param to
     * @param time
     * @param delayTime
     * @return
     */
    public static ObjectAnimator translationX(View view, float from, float to, long time, long delayTime) {
        ObjectAnimator translation = ObjectAnimator.ofFloat(view
                , "translationX"
                , from, to);
        translation.setInterpolator(new LinearInterpolator());
        translation.setStartDelay(delayTime);
        translation.setDuration(time);
        return translation;
    }

    public static ObjectAnimator translationY(View view, float from, float to, long time, long delayTime) {
        ObjectAnimator translation = ObjectAnimator.ofFloat(view
                , "translationY"
                , from, to);
        translation.setInterpolator(new LinearInterpolator());
        translation.setStartDelay(delayTime);
        translation.setDuration(time);
        return translation;
    }

    /**
     * 透明度动画
     *
     * @param view
     * @param from
     * @param to
     * @param time
     * @param delayTime
     * @return
     */
    public static ObjectAnimator alpha(View view, float from, float to, long time, long delayTime) {
        ObjectAnimator translation = ObjectAnimator.ofFloat(view
                , "alpha"
                , from, to);
        translation.setInterpolator(new LinearInterpolator());
        translation.setStartDelay(delayTime);
        translation.setDuration(time);
        return translation;
    }

    public static ObjectAnimator rotation(View view, long time, long delayTime, float... values) {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(view, "rotation", values);
        rotation.setDuration(time);
        rotation.setStartDelay(delayTime);
        rotation.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return input;
            }
        });
        return rotation;
    }

}
