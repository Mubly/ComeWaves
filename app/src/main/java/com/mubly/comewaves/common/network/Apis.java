package com.mubly.comewaves.common.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.Converter;
import com.lzy.okrx2.adapter.ObservableBody;
import com.mubly.comewaves.common.base.BaseModel;
import com.mubly.comewaves.common.base.ResponseData;
import com.mubly.comewaves.model.model.CategoryVo;
import com.mubly.comewaves.model.model.CommentInfo;
import com.mubly.comewaves.model.model.HomeBean;
import com.mubly.comewaves.model.model.LoginResBean;
import com.mubly.comewaves.model.model.SearchVideoVo;
import com.mubly.comewaves.model.model.SmartBeanVo;
import com.mubly.comewaves.model.model.StartBean;
import com.mubly.comewaves.model.model.TopicInfoVo;
import com.mubly.comewaves.model.model.UserInfoVo;
import com.mubly.comewaves.model.model.UserPostVo;


import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.Response;

import static com.mubly.comewaves.common.network.ApiUrls.ACK_EDIT_USER_INFO_URL;
import static com.mubly.comewaves.common.network.ApiUrls.ATTENTION_SOMEBODAY_URL;
import static com.mubly.comewaves.common.network.ApiUrls.CHANGE_AVTAR_IMG_URL;
import static com.mubly.comewaves.common.network.ApiUrls.CHANGE_USERINFO_BG_URL;
import static com.mubly.comewaves.common.network.ApiUrls.COLLECTION_URL;
import static com.mubly.comewaves.common.network.ApiUrls.COMMENT_INFO_URL;
import static com.mubly.comewaves.common.network.ApiUrls.EDIT_USER_INFO_URL;
import static com.mubly.comewaves.common.network.ApiUrls.GETCATEGORY_URL;
import static com.mubly.comewaves.common.network.ApiUrls.GET_CODE_URL;
import static com.mubly.comewaves.common.network.ApiUrls.GET_USER_INFO_URL;
import static com.mubly.comewaves.common.network.ApiUrls.HOME_INFO_URL;
import static com.mubly.comewaves.common.network.ApiUrls.IMAGE_UPLOAD_URL;
import static com.mubly.comewaves.common.network.ApiUrls.IMG_VIDEO_UPLOAD_URL;
import static com.mubly.comewaves.common.network.ApiUrls.MY_TOPIC_AND_FOCUS_URL;
import static com.mubly.comewaves.common.network.ApiUrls.ONE_PASS_login_Url;
import static com.mubly.comewaves.common.network.ApiUrls.PRAISE_URL;
import static com.mubly.comewaves.common.network.ApiUrls.REGISTERED_URL;
import static com.mubly.comewaves.common.network.ApiUrls.REPLY_COMMENT_URL;
import static com.mubly.comewaves.common.network.ApiUrls.SEARCH_ONE_CATEGARY_URL;
import static com.mubly.comewaves.common.network.ApiUrls.SEARCH_TWO_CATEGARY_URL;
import static com.mubly.comewaves.common.network.ApiUrls.SEARCH_VIDEO_LIST_URL;
import static com.mubly.comewaves.common.network.ApiUrls.SEND_REPLY_COMMENT_URL;
import static com.mubly.comewaves.common.network.ApiUrls.START_IMAGE_URL;
import static com.mubly.comewaves.common.network.ApiUrls.TIE_INFO_URL;
import static com.mubly.comewaves.common.network.ApiUrls.VIDEO_UPLOAD_URL;
import static com.mubly.comewaves.common.network.ApiUrls.loginUrl;


public class Apis {

    private static final String TAG = "Apis";

    private static Gson gson = new Gson();

    // 登录
    public static Observable<ResponseData<LoginResBean>> login(String phone, String verCode) {
        return OkGo.<ResponseData<LoginResBean>>post(loginUrl)
                .params("phone", phone)
                .params("code", verCode)
                .converter(new Converter<ResponseData<LoginResBean>>() {
                    @Override
                    public ResponseData<LoginResBean> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<LoginResBean>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<LoginResBean>>());

    }

    //发送验证码
    public static Observable<ResponseData<BaseModel>> getCode(String phone) {
        return OkGo.<ResponseData<BaseModel>>post(GET_CODE_URL)
                .params("phone", phone)
                .converter(new Converter<ResponseData<BaseModel>>() {
                    @Override
                    public ResponseData<BaseModel> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<BaseModel>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<BaseModel>>());

    }

    //首页信息获取
    public static Observable<ResponseData<List<HomeBean>>> getHomeData(int status, int page) {
        return OkGo.<ResponseData<List<HomeBean>>>post(HOME_INFO_URL)
                .params("status", status)
                .params("page", page)
                .converter(new Converter<ResponseData<List<HomeBean>>>() {
                    @Override
                    public ResponseData<List<HomeBean>> convertResponse(Response response) throws Throwable {

                        Type type = new TypeToken<ResponseData<List<HomeBean>>>() {
                        }.getType();
//                        Log.i("asef",type.getTypeName()+"^^^");
//                        return gson.fromJson(response.body().string(), type);
                        ResponseData<List<HomeBean>> data = gson.fromJson(response.body().string(), type);

                        return data;
                    }
                })
                .adapt(new ObservableBody<ResponseData<List<HomeBean>>>());

    }

    // 启动页
    public static Observable<ResponseData<List<StartBean>>> getStartImage() {
        return OkGo.<ResponseData<List<StartBean>>>post(START_IMAGE_URL)
                .converter(new Converter<ResponseData<List<StartBean>>>() {
                    @Override
                    public ResponseData<List<StartBean>> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<List<StartBean>>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<List<StartBean>>>());

    }

    //用户注册
    public static Observable<ResponseData<LoginResBean>> register(String phone, String userName, String tagArr, String sex, File imgFile) {
        return OkGo.<ResponseData<LoginResBean>>post(REGISTERED_URL)
                .params("user_name", userName)
                .params("phone", phone)
                .params("cate_id", tagArr)
                .params("sex", sex)
                .params("header_img", imgFile)
                .converter(new Converter<ResponseData<LoginResBean>>() {
                    @Override
                    public ResponseData<LoginResBean> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<LoginResBean>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<LoginResBean>>());

    }

    //注册页面标签获取
    public static Observable<ResponseData<List<CategoryVo>>> getTagData() {
        return OkGo.<ResponseData<List<CategoryVo>>>get(GETCATEGORY_URL)
                .converter(new Converter<ResponseData<List<CategoryVo>>>() {
                    @Override
                    public ResponseData<List<CategoryVo>> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<List<CategoryVo>>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<List<CategoryVo>>>());

    }

    //    帖子详情
    public static Observable<ResponseData<TopicInfoVo>> getTieInfo(int post_id, int type) {
        return OkGo.<ResponseData<TopicInfoVo>>post(TIE_INFO_URL)
                .params("post_id", post_id)
                .params("type", type)
                .converter(new Converter<ResponseData<TopicInfoVo>>() {
                    @Override
                    public ResponseData<TopicInfoVo> convertResponse(Response response) throws Throwable {

                        Type type = new TypeToken<ResponseData<TopicInfoVo>>() {
                        }.getType();
                        ResponseData<TopicInfoVo> data = gson.fromJson(response.body().string(), type);

                        return data;
                    }
                })
                .adapt(new ObservableBody<ResponseData<TopicInfoVo>>());

    }

    // 视频上传
    public static Observable<ResponseData<BaseModel>> videoUpload(String post_info, String location, String through, String weft, String sign, File video, File img) {
        return OkGo.<ResponseData<BaseModel>>post(VIDEO_UPLOAD_URL)
                .params("post_info", post_info)//文章内容
//                .params("cate_id", cate_id)//分类Id
                .params("location", location)//地址
                .params("through", through)//经度
                .params("weft", weft)//维度
                .params("video", video)//视频
//                .params("img", img)//封面图
                .params("sign", sign)//标签
                .converter(new Converter<ResponseData<BaseModel>>() {
                    @Override
                    public ResponseData<BaseModel> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<BaseModel>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<BaseModel>>());

    }

    // 图片上传
    public static Observable<ResponseData<BaseModel>> imageUpload(String post_info, String cate_id, String location, String through, String weft, String sign, File video, File img) {
        return OkGo.<ResponseData<BaseModel>>post(IMAGE_UPLOAD_URL)
                .params("post_info", post_info)//文章内容
                .params("cate_id", cate_id)//分类Id
                .params("location", location)//地址
                .params("through", through)//经度
                .params("weft", weft)//维度
                .params("video", video)//视频
//                .params("img", img)//封面图
                .params("sign", sign)//标签
                .converter(new Converter<ResponseData<BaseModel>>() {
                    @Override
                    public ResponseData<BaseModel> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<BaseModel>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<BaseModel>>());

    }


    public static Observable<ResponseData<BaseModel>> imageVideoUpload(String common_id, String cate_id, String location, String through, String weft, String sign, File video, File img) {
        return OkGo.<ResponseData<BaseModel>>post(IMG_VIDEO_UPLOAD_URL)
                .params("common_id", common_id)//回复或者是评论的id(文章上传成功后返回此id)
                .params("universal", cate_id)//图片或者视频
                .params("type", location)//1:图片 2:视频
                .params("status", sign)//1:评论 2:回复
                .converter(new Converter<ResponseData<BaseModel>>() {
                    @Override
                    public ResponseData<BaseModel> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<BaseModel>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<BaseModel>>());

    }

    public static Observable<ResponseData<BaseModel>> imageUpload2(String id, ArrayList<File> files) {
        return OkGo.<ResponseData<BaseModel>>post(IMAGE_UPLOAD_URL)
                .params("id", id)//文章Id
                .addFileParams("img", files)//图片
                .converter(new Converter<ResponseData<BaseModel>>() {
                    @Override
                    public ResponseData<BaseModel> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<BaseModel>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<BaseModel>>());

    }

    // 获取评论列表
    public static Observable<ResponseData<List<CommentInfo>>> getCommentInfoById(int post_id) {
        return OkGo.<ResponseData<List<CommentInfo>>>post(COMMENT_INFO_URL)
                .params("post_id", post_id)
                .converter(new Converter<ResponseData<List<CommentInfo>>>() {
                    @Override
                    public ResponseData<List<CommentInfo>> convertResponse(Response response) throws Throwable {

                        Type type = new TypeToken<ResponseData<List<CommentInfo>>>() {
                        }.getType();

                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<List<CommentInfo>>>());
    }

    // 回复评论
    public static Observable<ResponseData<BaseModel>> replyComment(int post_id, String content, int type, int commentId, int userId) {
        return OkGo.<ResponseData<BaseModel>>post(REPLY_COMMENT_URL)
                .params("post_id", post_id)
                .params("content", content)
                .params("type", type)
                .params("common_id", commentId)
                .params("user_id", userId)
                .converter(new Converter<ResponseData<BaseModel>>() {
                    @Override
                    public ResponseData<BaseModel> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<BaseModel>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<BaseModel>>());
    }

    // 发表评论
    public static Observable<ResponseData<BaseModel>> sendReplyComment(int post_id, String content, int type) {
        return OkGo.<ResponseData<BaseModel>>post(SEND_REPLY_COMMENT_URL)
                .params("post_id", post_id)
                .params("content", content)
                .params("type", type)
                .converter(new Converter<ResponseData<BaseModel>>() {
                    @Override
                    public ResponseData<BaseModel> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<BaseModel>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<BaseModel>>());
    }

    // 发表评论
    public static Observable<ResponseData<BaseModel>> attentionSomeOne(int user_id) {
        return OkGo.<ResponseData<BaseModel>>post(SEND_REPLY_COMMENT_URL)
                .params("user_id", user_id)
                .converter(new Converter<ResponseData<BaseModel>>() {
                    @Override
                    public ResponseData<BaseModel> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<BaseModel>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<BaseModel>>());
    }

    // 点赞或取消点赞
    public static Observable<ResponseData<SmartBeanVo>> doPraise(int post_id) {
        return OkGo.<ResponseData<SmartBeanVo>>post(PRAISE_URL)
                .params("post_id", post_id)
                .converter(new Converter<ResponseData<SmartBeanVo>>() {
                    @Override
                    public ResponseData<SmartBeanVo> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<SmartBeanVo>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<SmartBeanVo>>());
    }

    // 点赞或取消点赞
    public static Observable<ResponseData<SmartBeanVo>> doCollection(int post_id) {
        return OkGo.<ResponseData<SmartBeanVo>>post(COLLECTION_URL)
                .params("post_id", post_id)
                .converter(new Converter<ResponseData<SmartBeanVo>>() {
                    @Override
                    public ResponseData<SmartBeanVo> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<SmartBeanVo>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<SmartBeanVo>>());
    }

    // 关注/取关
    public static Observable<ResponseData<SmartBeanVo>> doAttention(int user_id) {
        return OkGo.<ResponseData<SmartBeanVo>>post(ATTENTION_SOMEBODAY_URL)
                .params("user_id", user_id)
                .converter(new Converter<ResponseData<SmartBeanVo>>() {
                    @Override
                    public ResponseData<SmartBeanVo> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<SmartBeanVo>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<SmartBeanVo>>());
    }

    // 搜索页一级分类
    public static Observable<ResponseData<List<CategoryVo>>> getSearchTab1(int page) {
        return OkGo.<ResponseData<List<CategoryVo>>>post(SEARCH_ONE_CATEGARY_URL)
                .params("page", page)
                .converter(new Converter<ResponseData<List<CategoryVo>>>() {
                    @Override
                    public ResponseData<List<CategoryVo>> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<List<CategoryVo>>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<List<CategoryVo>>>());
    }

    // 搜索页二级分类（列表内容）
    public static Observable<ResponseData<List<CategoryVo>>> getSearchTab2(int cate_id) {
        return OkGo.<ResponseData<List<CategoryVo>>>post(SEARCH_TWO_CATEGARY_URL)
                .params("cate_id", cate_id)
                .converter(new Converter<ResponseData<List<CategoryVo>>>() {
                    @Override
                    public ResponseData<List<CategoryVo>> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<List<CategoryVo>>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<List<CategoryVo>>>());
    }

    //搜索模块视频列表
    public static Observable<ResponseData<List<SearchVideoVo>>> getSearchVideo(int cate_id) {
        return OkGo.<ResponseData<List<SearchVideoVo>>>post(SEARCH_VIDEO_LIST_URL)
                .params("cate_id", cate_id)
                .converter(new Converter<ResponseData<List<SearchVideoVo>>>() {
                    @Override
                    public ResponseData<List<SearchVideoVo>> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<List<SearchVideoVo>>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<List<SearchVideoVo>>>());
    }

    //
//    // 版本更新
//    public static Observable<ResponseData<VersionBean>> getVersion() {
//        return OkGo.<ResponseData<VersionBean>>get(GetVersionUrl)
//                .converter(new Converter<ResponseData<VersionBean>>() {
//                    @Override
//                    public ResponseData<VersionBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<VersionBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<VersionBean>>());
//    }
//
//    // 加入群聊
//    // todo AddCommentBean为空返回
//    public static Observable<ResponseData<AddCommentBean>> joinGroup(String tid) {
//        return OkGo.<ResponseData<AddCommentBean>>post(JoinGroupUrl)
//                .params("tid", tid)
//                .converter(new Converter<ResponseData<AddCommentBean>>() {
//                    @Override
//                    public ResponseData<AddCommentBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<AddCommentBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<AddCommentBean>>());
//    }
//
    //修改用户信息
    public static Observable<ResponseData<BaseModel>> editUserInfo(String user_name, String signature, String birthday, String location, String sex, String school) {
        return OkGo.<ResponseData<BaseModel>>post(EDIT_USER_INFO_URL)
                .params("user_name", user_name)
                .params("signature", signature)
                .params("birthday", birthday)
                .params("location", location)
                .params("sex", sex)
                .params("school", school)
                .converter(new Converter<ResponseData<BaseModel>>() {
                    @Override
                    public ResponseData<BaseModel> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<BaseModel>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<BaseModel>>());
    }
//

    //修改用户头像
    public static Observable<ResponseData<BaseModel>> updateAvatar(String avatar) {
        return OkGo.<ResponseData<BaseModel>>post(CHANGE_AVTAR_IMG_URL)
                .params("avatar", avatar)
                .converter(new Converter<ResponseData<BaseModel>>() {
                    @Override
                    public ResponseData<BaseModel> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<BaseModel>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<BaseModel>>());
    }

    //修改用户背景图
    public static Observable<ResponseData<BaseModel>> userInfoBg(String avatar) {
        return OkGo.<ResponseData<BaseModel>>post(CHANGE_USERINFO_BG_URL)
                .params("avatar", avatar)
                .converter(new Converter<ResponseData<BaseModel>>() {
                    @Override
                    public ResponseData<BaseModel> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<BaseModel>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<BaseModel>>());
    }

    //我的帖子，关注
    public static Observable<ResponseData<List<UserPostVo>>> mytopAndFocus(int type, int page) {
        return OkGo.<ResponseData<List<UserPostVo>>>post(MY_TOPIC_AND_FOCUS_URL)
                .params("type", type)
                .params("page", page)
                .converter(new Converter<ResponseData<List<UserPostVo>>>() {
                    @Override
                    public ResponseData<List<UserPostVo>> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<List<UserPostVo>>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<List<UserPostVo>>>());
    }

    //获取用户信息
    public static Observable<ResponseData<UserInfoVo>> getUserInfo() {
        return OkGo.<ResponseData<UserInfoVo>>post(GET_USER_INFO_URL)
                .converter(new Converter<ResponseData<UserInfoVo>>() {
                    @Override
                    public ResponseData<UserInfoVo> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<UserInfoVo>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<UserInfoVo>>());
    }

    //确认修改用户信息
    public static Observable<ResponseData<BaseModel>> ackEditUserInfo(String avatar) {
        return OkGo.<ResponseData<BaseModel>>post(ACK_EDIT_USER_INFO_URL)
                .params("avatar", avatar)
                .converter(new Converter<ResponseData<BaseModel>>() {
                    @Override
                    public ResponseData<BaseModel> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<BaseModel>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<BaseModel>>());
    }
}