package com.mubly.comewaves.videoplayer;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.utils.CommUtil;
import com.mubly.comewaves.common.utils.TextViewUtils;
import com.mubly.comewaves.model.model.HomeBean;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;


/**
 * Created by guoshuyu on 2017/1/9.
 */

public class RecyclerItemAutoDetiallHolder extends RecyclerItemBaseHolder {

    public final static String TAG = "RecyclerView2List";

    public Context context = null;

    SwitchVideo gsyVideoPlayer;

    ImageView imageView, avtarImg;

    TextView userNameTv, addressTv, distanceTv, praiseTv, attentTv, contentTv, commentTv, praiseMan;

    GSYVideoOptionBuilder gsyVideoOptionBuilder;

    public RecyclerItemAutoDetiallHolder(Context context, View v) {
        super(v);
        this.context = context;
        imageView = new ImageView(context);
        gsyVideoOptionBuilder = new GSYVideoOptionBuilder();
        gsyVideoPlayer = v.findViewById(R.id.video_item_player);
        userNameTv = v.findViewById(R.id.user_name);
        addressTv = v.findViewById(R.id.user_location);
        distanceTv = v.findViewById(R.id.user_distance);
        praiseMan = v.findViewById(R.id.praise_man_tv);
        praiseTv = v.findViewById(R.id.praise_tv);
        attentTv = v.findViewById(R.id.attent_tv);
        commentTv = v.findViewById(R.id.comment_tv);
        contentTv = v.findViewById(R.id.content_tv);
        avtarImg = v.findViewById(R.id.avatar_image);
        gsyVideoPlayer.setVisibility(View.VISIBLE);
    }

    public void onBind(final int position, final HomeBean homeBean) {

        //增加封面
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context).load(homeBean.getFirst_url()).into(imageView);
        userNameTv.setText(homeBean.getUser_name());
        addressTv.setText(homeBean.getLocation());
        distanceTv.setText("1.2km");
        praiseTv.setText(homeBean.getFabulous_num() + "");
        Drawable drawable = null;
        if (homeBean.getLike_status() == 0) {
            drawable = context.getResources().getDrawable(R.mipmap.praise_icon);
        } else {
            drawable = context.getResources().getDrawable(R.mipmap.praise_light_icon);
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        praiseTv.setCompoundDrawables(drawable, null, null, null);

        attentTv.setText(homeBean.getCollection_num()+"");

        Drawable drawable2 = null;
        if (homeBean.getCollect_status() == 0) {
            drawable2 = context.getResources().getDrawable(R.mipmap.attent_no_icon);
        } else {
            drawable2 = context.getResources().getDrawable(R.mipmap.attent_red_icon);
        }
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        attentTv.setCompoundDrawables(drawable2, null, null, null);

        commentTv.setText(homeBean.getReport_num() + "");
//        praiseMan.setText("赵子龙，张翼德，刘玄德等人觉得很赞");
        contentTv.setText(TextViewUtils.getWeiBoContent(context, homeBean.getPost_info(), contentTv));
        Glide.with(context).load(homeBean.getUser_head()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(avtarImg);

        if (imageView.getParent() != null) {
            ViewGroup viewGroup = (ViewGroup) imageView.getParent();
            viewGroup.removeView(imageView);
        }
        gsyVideoPlayer.setPlayPosition(position);
        SwitchUtil.optionPlayer(gsyVideoPlayer, homeBean.getVideo_url(), true, "这是title");
        gsyVideoPlayer.setUpLazy(homeBean.getVideo_url(), true, null, null, "这是title");
        gsyVideoPlayer.setThumbImageView(imageView);
        gsyVideoPlayer.setNeedShowWifiTip(false);
        if (position == GSYVideoManager.instance().getPlayPosition()) {
            gsyVideoPlayer.getThumbImageViewLayout().setVisibility(View.GONE);
        } else {
            gsyVideoPlayer.getThumbImageViewLayout().setVisibility(View.VISIBLE);
        }
        gsyVideoPlayer.setOnClickCallBack(new SwitchVideo.OnClickCallBack() {
            @Override
            public void onClick(SwitchVideo switchVideo) {
                SwitchUtil.savePlayState(switchVideo);
                switchVideo.getGSYVideoManager().setLastListener(switchVideo);
                SwitchDetailActivity.startTActivity((Activity) context, switchVideo, homeBean.getPost_id());
            }
        });
        commentTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchUtil.savePlayState(gsyVideoPlayer);
                gsyVideoPlayer.getGSYVideoManager().setLastListener(gsyVideoPlayer);
                SwitchDetailActivity.startTActivity((Activity) context, gsyVideoPlayer, homeBean.getPost_id());
            }
        });
    }

    /**
     * 全屏幕按键处理
     */
    private void resolveFullBtn(final StandardGSYVideoPlayer standardGSYVideoPlayer) {
        standardGSYVideoPlayer.startWindowFullscreen(context, true, true);
    }

}