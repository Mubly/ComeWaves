package com.mubly.comewaves.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.utils.ToastUtils;
import com.mubly.comewaves.model.adapter.SmartAdapter;
import com.mubly.comewaves.videocrop.ExtractFrameWorkThread;
import com.mubly.comewaves.videocrop.ExtractVideoInfoUtil;
import com.mubly.comewaves.videocrop.UIUtils;
import com.mubly.comewaves.videocrop.VideoEditInfo;
import com.mubly.comewaves.videocrop.VideoUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.LinearLayoutManager.*;

public class VideoScreenCropActivity extends BaseActivity {


    @BindView(R.id.video_crop_show_iv)
    ImageView videoCropShowIv;
    @BindView(R.id.video_img_rv)
    RecyclerView videoImgRv;
    @BindView(R.id.top_layout_right_tv)
    TextView ackBtn;
    @BindView(R.id.top_tittle)
    TextView titleTv;
    @BindView(R.id.top_back_btn)
    ImageButton backbtn;
    private ExtractVideoInfoUtil mExtractVideoInfoUtil;
    private ExtractFrameWorkThread mExtractFrameWorkThread;
    private String OutPutFileDirPath;
    private String mVideoPath = "/storage/emulated/0/PictureSelector/CameraImage/PictureSelector_20190318_125418.mp4";
    List<VideoEditInfo> dataList = new ArrayList<>();
    SmartAdapter smartAdapter;
    private final long MAX_CUT_DURATION = 1000L;
    private boolean hasDefaut;
    private String selectImg;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        mVideoPath = getIntent().getStringExtra("videoPath");
        return R.layout.activity_video_screen_crop;
    }

    @Override
    public void initView() {
        super.initView();
        titleTv.setText("选择封面");
        ackBtn.setText("完成");
        ackBtn.setVisibility(View.VISIBLE);
        backbtn.setVisibility(View.GONE);
        if (TextUtils.isEmpty(mVideoPath)) {
            ToastUtils.showToast("视频路径不能为空");
            finish();
            return;
        }
        mExtractVideoInfoUtil = new ExtractVideoInfoUtil(mVideoPath);
        long startPosition = 0;
        int thumbnailsCount;
        long endPosition = Long.valueOf(mExtractVideoInfoUtil.getVideoLength());
        OutPutFileDirPath = VideoUtil.getSaveEditThumbnailDir(this);
        int extractH = UIUtils.dp2Px(100);
        int extractW = extractH;
        thumbnailsCount = (int) (endPosition * 1.0f / (MAX_CUT_DURATION * 1.0f));
        mExtractFrameWorkThread = new ExtractFrameWorkThread(extractW, extractH, mUIHandler,
                mVideoPath,
                OutPutFileDirPath, startPosition, endPosition, thumbnailsCount);
        mExtractFrameWorkThread.start();
        smartAdapter = new SmartAdapter<VideoEditInfo>(dataList) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_crop_img_layout;
            }

            @Override
            public void dealView(VH holder, final VideoEditInfo data, final int position) {
                ImageView imageView = (ImageView) holder.getChildView(R.id.square_img);
                Glide.with(mContext).load(data.path).into(imageView);

                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectImg = data.path;
                        Glide.with(mContext).load(data.path).into(videoCropShowIv);
                    }
                });
            }


        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        videoImgRv.setLayoutManager(layoutManager);
        videoImgRv.setAdapter(smartAdapter);

    }

    @Override
    public void initEvent() {
        super.initEvent();
        ackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dealFinish();
            }
        });
    }

    private final MainHandler mUIHandler = new MainHandler(this);

    private static class MainHandler extends Handler {

        private final WeakReference<VideoScreenCropActivity> mActivity;

        MainHandler(VideoScreenCropActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            VideoScreenCropActivity activity = mActivity.get();
            if (activity != null) {
                if (msg.what == ExtractFrameWorkThread.MSG_SAVE_SUCCESS) {
                    VideoEditInfo info = (VideoEditInfo) msg.obj;
                    activity.dataList.add(info);
                    activity.smartAdapter.notifyItemInserted(activity.dataList.size());
                    activity.loaddefautImg();
                }
            }
        }
    }

    void loaddefautImg() {
        if (!hasDefaut) {
            if (dataList.size() > 0) {
                selectImg = dataList.get(0).path;
                Glide.with(mContext).load(dataList.get(0).path).into(videoCropShowIv);
                hasDefaut = true;
            }
        } else
            return;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dealFinish();
    }

    void dealFinish() {
        Intent intent = new Intent();
        intent.putExtra("videoimg", selectImg);
        setResult(RESULT_OK, intent);
        finish();
    }
}
