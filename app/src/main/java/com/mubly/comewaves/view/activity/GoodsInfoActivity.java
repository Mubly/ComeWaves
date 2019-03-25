package com.mubly.comewaves.view.activity;


import android.app.MediaRouteButton;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.utils.CommUtil;
import com.mubly.comewaves.common.utils.ToastUtils;
import com.mubly.comewaves.model.model.CommentInfo;
import com.mubly.comewaves.model.model.GlideImageLoader;
import com.mubly.comewaves.model.model.SmartBeanVo;
import com.mubly.comewaves.model.model.TopicInfoVo;
import com.mubly.comewaves.model.model.VisitorInfo;
import com.mubly.comewaves.present.CommentInfoPresent;
import com.mubly.comewaves.view.interfaceview.CommentInfoView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页进入二级页面
 */
public class GoodsInfoActivity extends BaseActivity<CommentInfoPresent, CommentInfoView> implements CommentInfoView {
    @BindView(R.id.top_back_btn)
    ImageButton topBackBtn;
    @BindView(R.id.goods_info_img_banner)
    Banner goodsInfoImgBanner;
    @BindView(R.id.user_avtar_iv)
    ImageView userAvtarIv;
    @BindView(R.id.user_name_tv)
    TextView userNameTv;
    @BindView(R.id.user_address_tv)
    TextView userAddressTv;
    @BindView(R.id.to_attention_tv)
    TextView toAttentionTv;
    @BindView(R.id.video_content_tv)
    TextView videoContentTv;
    @BindView(R.id.praise_tv)
    TextView praiseTv;
    @BindView(R.id.comment_count)
    TextView commentCount;
    @BindView(R.id.attent_count)
    TextView attentCount;
    @BindView(R.id.praise_comment_attent_layout)
    LinearLayout praiseCommentAttentLayout;
    @BindView(R.id.praiser_list_tv)
    TextView praiserListTv;
    @BindView(R.id.comment_layout)
    LinearLayout commentLayout;
    @BindView(R.id.comment_open_more_tv)
    TextView commentOpenMoreTv;
    @BindView(R.id.edit_message_layout)
    FrameLayout editLayout;
    @BindView(R.id.input_et)
    EditText inputEt;
    private int type;
    private int postId;
    private List<String> imgList = new ArrayList<>();


    @Override
    protected CommentInfoPresent createPresenter() {
        return new CommentInfoPresent();
    }

    @Override
    protected int getLayoutId() {
        type = getIntent().getIntExtra("type", 0);
        postId = getIntent().getIntExtra("postId", 0);
        return R.layout.activity_goods_info;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        inputEt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    String contentStr = inputEt.getText().toString();
                    if (!TextUtils.isEmpty(contentStr)) {
                        CommUtil.hideKeyboard(inputEt);
                        editLayout.setVisibility(View.GONE);
                        mPresenter.sendReplyComment(postId, contentStr, 1);
                    } else {
                        ToastUtils.showToast("请输入内容");
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void showCommentInfo(List<CommentInfo> commentInfos) {

    }

    @Override
    public void replyCommentSuccess() {
        initData();
        inputEt.setText("");
        ToastUtils.showToast("评论添加成功");
    }

    @Override
    public void showTopicInfo(TopicInfoVo topicInfoVo) {
        commentLayout.removeAllViews();
        if (null != topicInfoVo.comment && topicInfoVo.comment.size() > 0) {
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            commentLayout.setVisibility(View.VISIBLE);
            commentOpenMoreTv.setVisibility(View.VISIBLE);
            for (VisitorInfo commentInfo : topicInfoVo.comment) {

                View view = layoutInflater.inflate(R.layout.view_comment_layout, null);
                ((TextView) view.findViewById(R.id.commenter_name_tv)).setText(commentInfo.getUser_name() + "：" + commentInfo.getContent());
                ((TextView) view.findViewById(R.id.comment_time_tv)).setText(commentInfo.getCreated_time());
                ((ImageView) view.findViewById(R.id.comment_praise_iv)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast("点赞成功");
                    }
                });
                commentLayout.addView(view);
            }
        } else {
            commentLayout.setVisibility(View.GONE);
            commentOpenMoreTv.setVisibility(View.GONE);
        }

        Glide.with(mContext).load(topicInfoVo.post.getUser_head()).apply(RequestOptions.circleCropTransform()).into(userAvtarIv);
        userNameTv.setText(topicInfoVo.post.getUser_name());
        userAddressTv.setText(topicInfoVo.post.getLocation());
        if (false) {//判断是否已关注
            toAttentionTv.setVisibility(View.GONE);
        }
        praiseTv.setText(topicInfoVo.post.getFabulous_num() + "");//喜欢
        attentCount.setText(topicInfoVo.post.getLike_status() + "");//收藏
        commentCount.setText(topicInfoVo.post.getReport_num() + "");
        imgList.clear();
        if (null != topicInfoVo.post.getPhoto_url() && topicInfoVo.post.getPhoto_url().size() > 0) {
            for (SmartBeanVo smartBeanVo : topicInfoVo.post.getPhoto_url()) {
                imgList.add(smartBeanVo.community_post_img);
            }

//            goodsInfoImgBanner.update(imgList);
            goodsInfoImgBanner.setImages(imgList);
            goodsInfoImgBanner.start();
        }

        setPraise(topicInfoVo.post.getLike_status(), false);
        setAttent(topicInfoVo.post.getCollect_status(), false);
    }

    @Override
    public void doPraise(SmartBeanVo smartBeanVo) {

    }

    @Override
    public void doCollection(SmartBeanVo smartBeanVo) {

    }

    @Override
    public void initView() {
        super.initView();
        goodsInfoImgBanner.setImageLoader(new GlideImageLoader());
        goodsInfoImgBanner.setImages(imgList);
        goodsInfoImgBanner.start();
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.getTopicInfo(postId, 2);
    }

    @OnClick({R.id.to_attention_tv, R.id.praise_tv, R.id.comment_count, R.id.attent_count, R.id.comment_open_more_tv, R.id.top_back_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back_btn:
                finish();
                break;
            case R.id.to_attention_tv:
                break;
            case R.id.praise_tv:
                mPresenter.doPraise(postId);
                break;
            case R.id.comment_count:
                editLayout.setVisibility(View.VISIBLE);
                CommUtil.showKeyboard(inputEt);
                break;
            case R.id.attent_count:
                mPresenter.doCollection(postId);
                break;
            case R.id.comment_open_more_tv:
                Intent intent = new Intent(this, CommentActivity.class);
                intent.putExtra("postId", postId);
                startActivity(intent);
                break;
        }
    }

    private void setAttent(int b, boolean isCount) {
        Drawable drawable = null;
        String count = attentCount.getText().toString();

        if (b == 0) {
            if (isCount) {
                attentCount.setText(CommUtil.strLess(count, 1));
            }

            drawable = getResources().getDrawable(R.mipmap.attent_no_icon);
        } else {
            if (isCount) {
                attentCount.setText(CommUtil.strLess(count, -1));
            }

            drawable = getResources().getDrawable(R.mipmap.attent_red_icon);
        }
        // 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        attentCount.setCompoundDrawables(drawable, null, null, null);
    }

    private void setPraise(int b, boolean isCount) {
        Drawable drawable = null;
        String count = praiseTv.getText().toString();
        if (b == 0) {
            drawable = getResources().getDrawable(R.mipmap.praise_icon);

            if (isCount) {
                praiseTv.setText(CommUtil.strLess(count, 1));
            }

        } else {

            drawable = getResources().getDrawable(R.mipmap.praise_light_icon);
            if (isCount) {
                praiseTv.setText(CommUtil.strLess(count, -1));
            }
        }
        // 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        praiseTv.setCompoundDrawables(drawable, null, null, null);
    }
}
