package com.mubly.comewaves.view.activity;

import android.content.Context;
import android.content.Intent;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.PoiItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.Constant;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.utils.CommUtil;
import com.mubly.comewaves.common.utils.EditViewUtil;
import com.mubly.comewaves.common.utils.ToastUtils;
import com.mubly.comewaves.model.adapter.SmartAdapter;
import com.mubly.comewaves.model.interfaces.CallBackObject;
import com.mubly.comewaves.model.interfaces.CallPoiListBack;
import com.mubly.comewaves.model.livedatabus.LiveDataBus;
import com.mubly.comewaves.present.MessageCreatePresent;
import com.mubly.comewaves.view.costomview.SpacesItemDecoration;
import com.mubly.comewaves.view.costomview.TagInputEditView;
import com.mubly.comewaves.view.interfaceview.MessageCreateView;
import com.mubly.comewaves.view.interfaceview.UpLoadView;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

import static com.mubly.comewaves.common.Constant.REQUEST_VIDEO_IMG_CODE;

/**
 * 发布帖子、视频，状态等等
 */
public class MessageCreateActivity extends BaseActivity<MessageCreatePresent, MessageCreateView> implements MessageCreateView {
    @BindView(R.id.top_back_btn)
    ImageButton topBackBtn;
    @BindView(R.id.top_tittle)
    TextView topTittle;
    @BindView(R.id.top_layout_right_tv)
    TextView topLayoutRightTv;
    @BindView(R.id.img_or_video_rv)
    RecyclerView imgOrVideoRv;
    @BindView(R.id.user_current_address)
    TextView userCurrentAddress;
    @BindView(R.id.labels_tv)
    TextView labelsTv;
    @BindView(R.id.input_et)
    TagInputEditView input_et;
    SmartAdapter smartAdapter;
    List<LocalMedia> voideImageList = new ArrayList<>();
    private String videoImg;//视频封面
    private final static int REQUEST_CODE = 0110;
    private int type;
    private String mLatitude, mLongitude;
    private String addressStr;
    private String poiName, adCode;
    int selectNum = 1;
    int index = 0;
    List<LocalMedia> selectList;
    UploadManager uploadManager = new UploadManager();
    String qiNToken;
    boolean hassearchPoi;
    List<String> localList = new ArrayList<>();
    private List<PoiItem> poiList;

    @Override
    protected int getLayoutId() {
        type = getIntent().getIntExtra("type", 0);
        if (type == Constant.PULL_IMAGE_CODE) {
            selectNum = 9;
        } else {
            selectNum = 1;
        }
        return R.layout.activity_message_create;
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.getLocal(getApplicationContext(), new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                mLatitude = String.valueOf(aMapLocation.getLatitude());//获取纬度
                mLongitude = String.valueOf(aMapLocation.getLongitude());//获取经度
                poiName = aMapLocation.getPoiName();
                adCode = aMapLocation.getAdCode();
                userCurrentAddress.setText(poiName);
            }
        });
        mPresenter.getUpLoadToken();
    }

    @Override
    public void initEvent() {
        super.initEvent();

    }

    @Override
    public void initView() {
        super.initView();
        topLayoutRightTv.setText("发布");
        topLayoutRightTv.setVisibility(View.VISIBLE);
        topTittle.setText("");
        voideImageList.add(new LocalMedia());
        smartAdapter = new SmartAdapter<LocalMedia>(voideImageList) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_square_img_layout;
            }

            @Override
            public void dealView(VH holder, final LocalMedia data, final int position) {
                final ImageView imageView = (ImageView) holder.getChildView(R.id.square_img);
                ImageView takePhoto = (ImageView) holder.getChildView(R.id.take_photo_iv);
                ImageView startVideo = (ImageView) holder.getChildView(R.id.start_video);

                if (position == voideImageList.size() - 1) {
                    takePhoto.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.GONE);
                    startVideo.setVisibility(View.GONE);
                    Glide.with(mContext).load(R.drawable.add_black_icon).apply(RequestOptions.overrideOf(60, 60).centerInside()).into(imageView);
                } else {
                    imageView.setVisibility(View.VISIBLE);
                    takePhoto.setVisibility(View.GONE);
                    if (data.getPictureType().contains("mp4")) {
                        startVideo.setVisibility(View.VISIBLE);
                    } else {
                        startVideo.setVisibility(View.GONE);
                    }
                    Glide.with(mContext).load(data.getPath()).into(imageView);
                }
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position == voideImageList.size() - 1) {

                            PictureSelector.create(MessageCreateActivity.this)
                                    .openGallery(type)
                                    .previewImage(true)
                                    .previewVideo(true)
                                    .compress(true)
                                    .maxSelectNum(selectNum)
//                                    .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
                                    .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
                                    .recordVideoSecond(100)//视频秒数录制 默认60s int
                                    .selectionMedia(selectList)
                                    .isCamera(true)
                                    .forResult(PictureConfig.CHOOSE_REQUEST);
                        } else {
                            if (data.getPictureType().contains("mp4")) {
                                PictureSelector.create(MessageCreateActivity.this).externalPictureVideo(data.getPath());
                            } else {
                                PictureSelector.create(MessageCreateActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(position, voideImageList);
                            }

                        }
                    }
                });
            }


        };
        imgOrVideoRv.setNestedScrollingEnabled(false);
        imgOrVideoRv.setLayoutManager(new GridLayoutManager(mContext, 3));
        imgOrVideoRv.setAdapter(smartAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(10);
        imgOrVideoRv.addItemDecoration(decoration);
    }

    @Override
    protected MessageCreatePresent createPresenter() {
        return new MessageCreatePresent();
    }

    @OnClick({R.id.top_back_btn, R.id.top_layout_right_tv, R.id.labels_tv, R.id.user_current_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back_btn:
                finish();
                break;
            case R.id.top_layout_right_tv:

                if (type == Constant.PULL_VIDEO_CODE) {
                    uploadData();
                } else if (type == Constant.PULL_IMAGE_CODE) {
                    uploadImage();
                }

                break;
            case R.id.labels_tv://插入标签
                input_et.getText().append("#");
                break;
            case R.id.user_current_address:
                if (TextUtils.isEmpty(poiName)) {
                    ToastUtils.showToast("正在定位您当前的位置");
                    return;
                }

                if (!hassearchPoi) {
                    showProgress("正在加载……");
                    mPresenter.gainPoiList(mContext, mLatitude, mLongitude, adCode, new CallPoiListBack() {
                        @Override
                        public void callBack(final List<PoiItem> poiItemList) {
                            hassearchPoi = true;
                            poiList = poiItemList;
                            for (PoiItem poiItem : poiItemList) {
                                localList.add(poiItem.getTitle());
                            }
                            hideProgress();
                            showLocalDilog();
                        }
                    });
                } else {
                    showLocalDilog();
                }

                break;
        }

    }

    private void showLocalDilog() {
        CommUtil.showSingleListDialog(mContext, index, "选择坐标", localList, new CallBackObject<Integer>() {

            @Override
            public void callBack(Integer integer) {
                index = integer;
                poiName = poiList.get(integer).getTitle();
                mLatitude = String.valueOf(poiList.get(integer).getLatLonPoint().getLatitude());
                mLongitude = String.valueOf(poiList.get(integer).getLatLonPoint().getLongitude());
                userCurrentAddress.setText(poiName);
            }
        });
    }

    private void uploadData() {
        String sing = "";
        for (int i = 0; i < input_et.getTagList().size(); i++) {
            sing = sing + "," + input_et.getTagList().get(i);
        }
        final String contentInfo = input_et.getText().toString();

//        mPresenter.videoUpdate(contentInfo, addressStr, mLongitude, mLatitude, sing, new File(voideImageList.get(0).getPath()), new File(videoImg));
        mPresenter.videodateQN(uploadManager, qiNToken, contentInfo, poiName, mLongitude, mLatitude, sing, voideImageList.get(0).getPath(), videoImg);
        finish();
    }

    private void uploadImage() {
        final String contentInfo = input_et.getText().toString();
        List<File> fileList = new ArrayList<>();
        if (voideImageList.size() > 1) {
            fileList.clear();
            for (int i = 0; i < voideImageList.size() - 1; i++) {
                fileList.add(new File(voideImageList.get(i).getCompressPath()));
            }
        }
//        mPresenter.upLoadeImgMore(contentInfo, addressStr, mLongitude, mLatitude, fileList);
        voideImageList.remove(voideImageList.size() - 1);
        showProgress("发布中……");
        mPresenter.uploadImgMore(uploadManager, voideImageList, qiNToken, new CallBackObject<String>() {
            @Override
            public void callBack(String o) {

                mPresenter.upLoadeImgMore(contentInfo, poiName, mLongitude, mLatitude, getSignStr(), o);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    dealImageOrVideo(selectList);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    break;
                case REQUEST_VIDEO_IMG_CODE:
                    videoImg = data.getStringExtra("videoimg");
                    break;
            }
        }
    }

    @Override
    public void addDisposable(Disposable disposable) {
        super.addDisposable(disposable);
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    private void dealImageOrVideo(List<LocalMedia> selectList) {
        this.selectList = selectList;
        voideImageList.clear();
        voideImageList.add(new LocalMedia());
        for (LocalMedia localMedia : selectList) {
            voideImageList.add(voideImageList.size() - 1, localMedia);
        }
        smartAdapter.notifyDataSetChanged();
        if (type == Constant.PULL_VIDEO_CODE) {
            Intent intent = new Intent(MessageCreateActivity.this, VideoScreenCropActivity.class);
            intent.putExtra("videoPath", selectList.get(0).getPath());
            startActivityForResult(intent, REQUEST_VIDEO_IMG_CODE);
        }
    }

    @Override
    public void upLoadSuccess() {
        ToastUtils.showToast("开始上传");
        finish();
    }

    @Override
    public void getUpLoadToken(String token) {
        qiNToken = token;
    }

    @Override
    public void showError(String msg) {
        ToastUtils.showToast(msg);
    }

    @Override
    public void upLoadImgSuccess(String keyRes, int type) {
        hideProgress();
        finish();
    }

    @Override
    public void upLoadImgFail(String error, int type) {

    }

    private String getSignStr() {
        String sign = "";
        for (String str : input_et.getTagList()) {
            if (TextUtils.isEmpty(sign)) {
                sign = str.substring(1,str.length()-1);
            } else {
                sign = sign + "," + str.substring(1,str.length()-1);
            }
        }

        return sign;
    }
}
