package com.mubly.comewaves.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.model.adapter.SmartAdapter;
import com.mubly.comewaves.view.costomview.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 发布帖子、视频，状态等等
 */
public class MessageCreateActivity extends BaseActivity {


    @BindView(R.id.top_back_btn)
    ImageButton topBackBtn;
    @BindView(R.id.top_tittle)
    TextView topTittle;
    @BindView(R.id.top_layout_right_tv)
    TextView topLayoutRightTv;
    @BindView(R.id.input_et)
    EditText inputEt;
    @BindView(R.id.img_or_video_rv)
    RecyclerView imgOrVideoRv;
    @BindView(R.id.user_current_address)
    TextView userCurrentAddress;
    @BindView(R.id.labels_tv)
    TextView labelsTv;
    SmartAdapter smartAdapter;
    List<String> voideImageList = new ArrayList<>();

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_create;
    }

    @Override
    public void initView() {
        super.initView();
        topLayoutRightTv.setText("发布");
        topTittle.setText("");
        voideImageList.add("启动拍照");
        smartAdapter = new SmartAdapter<String>(voideImageList) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_square_img_layout;
            }

            @Override
            public void dealView(VH holder, final String data, final int position) {
                final ImageView imageView = (ImageView) holder.getChildView(R.id.square_img);
                ImageView takePhoto = (ImageView) holder.getChildView(R.id.take_photo_iv);

                if (position == voideImageList.size() - 1) {
                    takePhoto.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.GONE);
                    Glide.with(mContext).load(R.drawable.add_black_icon).apply(RequestOptions.overrideOf(60, 60).centerInside()).into(imageView);
                } else {
                    imageView.setVisibility(View.VISIBLE);
                    takePhoto.setVisibility(View.GONE);
                    Glide.with(mContext).load(data).into(imageView);
                }
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position == voideImageList.size() - 1) {
                            PictureSelector.create(MessageCreateActivity.this)
                                    .openGallery(PictureMimeType.ofImage())
                                    .previewImage(true)
                                    .compress(true)
                                    .forResult(PictureConfig.CHOOSE_REQUEST);
                        } else {

                        }
                    }
                });
            }


        };
//        imgOrVideoRv.setNestedScrollingEnabled(false);
        imgOrVideoRv.setLayoutManager(new GridLayoutManager(mContext, 3));
        imgOrVideoRv.setAdapter(smartAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(10);
        imgOrVideoRv.addItemDecoration(decoration);
        LinearLayoutManager manager=new LinearLayoutManager(this);

    }

    @OnClick({R.id.top_back_btn, R.id.top_layout_right_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back_btn:
                finish();
                break;
            case R.id.top_layout_right_tv:
                break;
        }
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
            }
        }
    }

    private void dealImageOrVideo(List<LocalMedia> selectList) {
        for (LocalMedia localMedia : selectList) {
            voideImageList.add(voideImageList.size()-1,localMedia.getCompressPath());
        }
        smartAdapter.notifyDataSetChanged();
    }
}
