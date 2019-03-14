package com.mubly.comewaves.videoplayer;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mubly.comewaves.R;
import com.mubly.comewaves.model.model.HomeBean;
import com.shuyu.gsyvideoplayer.utils.GSYVideoHelper;


/**
 * Created by GUO on 2015/12/3.
 */
public class RecyclerItemViewHolder extends RecyclerItemBaseHolder {

    public final static String TAG = "RecyclerView2List";

    protected Context context = null;


    FrameLayout listItemContainer;

    ImageView listItemBtn;

    ImageView imageView;

    private GSYVideoHelper smallVideoHelper;

    private GSYVideoHelper.GSYVideoHelperBuilder gsySmallVideoHelperBuilder;

    public RecyclerItemViewHolder(Context context, View v) {
        super(v);
        this.context = context;
        imageView = new ImageView(context);
        listItemContainer=v.findViewById(R.id.list_item_container);
        listItemBtn=v.findViewById(R.id.list_item_btn);
    }

    public void onBind(final int position, HomeBean homeBean) {

        //增加封面
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context).load(homeBean.getFirst_url()).into(imageView);
        smallVideoHelper.addVideoPlayer(position, imageView, TAG, listItemContainer, listItemBtn);

        listItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smallVideoHelper.setPlayPositionAndTag(position, TAG);
                getRecyclerBaseAdapter().notifyDataSetChanged();
                //listVideoUtil.setLoop(true);
                String url;
                if (position % 2 == 0) {
                    url = "https://res.exexm.com/cw_145225549855002";
                } else {
                    url = "http://wdquan-space.b0.upaiyun.com/VIDEO/2018/11/22/ae0645396048_hls_time10.m3u8";
                }
                //listVideoUtil.setCachePath(new File(FileUtils.getPath()));

                gsySmallVideoHelperBuilder.setVideoTitle("title " + position).setUrl(url);

                smallVideoHelper.startPlay();

                //必须在startPlay之后设置才能生效
                //listVideoUtil.getGsyVideoPlayer().getTitleTextView().setVisibility(View.VISIBLE);
            }
        });
    }


    public void setVideoHelper(GSYVideoHelper smallVideoHelper, GSYVideoHelper.GSYVideoHelperBuilder gsySmallVideoHelperBuilder) {
        this.smallVideoHelper = smallVideoHelper;
        this.gsySmallVideoHelperBuilder = gsySmallVideoHelperBuilder;
    }
}





