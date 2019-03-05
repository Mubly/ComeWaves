package com.mubly.comewaves.common.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.Converter;
import com.lzy.okrx2.adapter.ObservableBody;
import com.mubly.comewaves.common.base.ResponseData;
import com.mubly.comewaves.model.model.LoginResBean;
import com.mubly.comewaves.model.model.StartBean;


import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.Response;

import static com.mubly.comewaves.common.network.ApiUrls.START_IMAGE_URL;
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
    public static Observable<ResponseData<LoginResBean>> getHomeData(String phone, String verCode) {
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
//    public static Observable<ResponseData<LoginResBean>> oneLogin(String phone, String process_id, String accessscode) {
//        return OkGo.<ResponseData<LoginResBean>>post(ONE_PASS_login_Url)
//                .params("process_id", process_id)
//                .params("phone", phone)
//                .params("accesscode", accessscode)
//                .converter(new Converter<ResponseData<LoginResBean>>() {
//                    @Override
//                    public ResponseData<LoginResBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<LoginResBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<LoginResBean>>());
//
//    }
//
//    public static Observable<ResponseData<PhoneCheckVo>> checkPhone(String phone) {
//        return OkGo.<ResponseData<PhoneCheckVo>>get(CHECK_PHONE_Url)
//                .params("phone", phone)
//                .converter(new Converter<ResponseData<PhoneCheckVo>>() {
//                    @Override
//                    public ResponseData<PhoneCheckVo> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<PhoneCheckVo>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<PhoneCheckVo>>());
//
//    }
//
//    // 获取用户信息
//    public static Observable<ResponseData<InfoResBean>> getUserInfo() {
//        return OkGo.<ResponseData<InfoResBean>>get(UserInfoUrl)
////               .headers("nast-token", AppConfig.token.get())
//                .converter(new Converter<ResponseData<InfoResBean>>() {
//                    @Override
//                    public ResponseData<InfoResBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<InfoResBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<InfoResBean>>());
//
//    }
//
//    // 获取七牛云token
//    public static Observable<ResponseData<SevenCattleBean>> getSevenCattleToken(String deleteFile) {
//        return OkGo.<ResponseData<SevenCattleBean>>get(QNUrl)
//                .params("delete_file", deleteFile)
//                .converter(new Converter<ResponseData<SevenCattleBean>>() {
//                    @Override
//                    public ResponseData<SevenCattleBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<SevenCattleBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<SevenCattleBean>>());
//    }
//
//    // 获取七牛云token 用户上传头像使用
//    public static Observable<ResponseData<SevenCattleBean>> getSevenCattleTokenUser() {
//        return OkGo.<ResponseData<SevenCattleBean>>get(QNUrl)
//                .converter(new Converter<ResponseData<SevenCattleBean>>() {
//                    @Override
//                    public ResponseData<SevenCattleBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<SevenCattleBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<SevenCattleBean>>());
//    }
//
//    // 创建活动
//    public static Observable<ResponseData<CreateActivityBean>> createAct() {
//        return OkGo.<ResponseData<CreateActivityBean>>post(createActUrl)
//                .converter(new Converter<ResponseData<CreateActivityBean>>() {
//                    @Override
//                    public ResponseData<CreateActivityBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<CreateActivityBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<CreateActivityBean>>());
//    }
//
//    // 获取类型
//    public static Observable<ResponseData<TypeBean>> getType(String timestamp) {
//        return OkGo.<ResponseData<TypeBean>>get(TypeUrl)
//                .params("timestamp", timestamp)
//                .converter(new Converter<ResponseData<TypeBean>>() {
//                    @Override
//                    public ResponseData<TypeBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<TypeBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<TypeBean>>());
//
//    }
//
//    // 获取票务信息
//    public static Observable<ResponseData<TicketBean>> getTicketInfo(String eventId) {
//        return OkGo.<ResponseData<TicketBean>>get(TicketUrl)
//                .params("event_id", eventId)
//                .converter(new Converter<ResponseData<TicketBean>>() {
//                    @Override
//                    public ResponseData<TicketBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<TicketBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<TicketBean>>());
//    }
//
//    // 新增票种
//    public static Observable<ResponseData<AddTicketBean>> addTicket(String eventId, String addTicket) {
//        return OkGo.<ResponseData<AddTicketBean>>post(AddTicketUrl)
//                .params("event_id", eventId)
////                .params("nast",nast)
////                .params("name",name)
////                .params("limit",limit)
//                .params("tickets", addTicket)
//                .converter(new Converter<ResponseData<AddTicketBean>>() {
//                    @Override
//                    public ResponseData<AddTicketBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<AddTicketBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<AddTicketBean>>());
//    }
//
//    // 删除票种
//    public static Observable<ResponseData<DeleteTicketBean>> deleteTicket(String eventId, String deleteTicket) {
//        return OkGo.<ResponseData<DeleteTicketBean>>post(AddTicketUrl)
//                .params("event_id", eventId)
////                .params("ticket_id",ticketId)
////                .params("delete_flag",deleteFlag)
//                .params("tickets", deleteTicket)
//                .converter(new Converter<ResponseData<DeleteTicketBean>>() {
//                    @Override
//                    public ResponseData<DeleteTicketBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<DeleteTicketBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<DeleteTicketBean>>());
//    }
//
//    // 发布活动/预览活动
//    //TODO registrationItem 应是数组格式，但是此刻传数组会报错，之后解决
//    public static Observable<ResponseData<ReleaseBean>> releaseAct(String eventId, int isPublish, String title, String address, String begin
//            , String end, String phone, String totalFee, int type, String registrationItem, String coverName) {
//        return OkGo.<ResponseData<ReleaseBean>>post(ReleaseUrl)
//                .params("event_id", eventId)
//                .params("isPublish", isPublish)
//                .params("title", title)
//                .params("address", address)
//                .params("begin", begin)
//                .params("end", end)
//                .params("total_fee", totalFee)
//                .params("type", type)
//                .params("contact_no", phone)
//                .params("registration_item", registrationItem)
//                .params("cover_name", coverName)
//                .converter(new Converter<ResponseData<ReleaseBean>>() {
//                    @Override
//                    public ResponseData<ReleaseBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<ReleaseBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<ReleaseBean>>());
//
//    }
//
//    // 获取活动列表
//    public static Observable<ResponseData<ActListBean>> getActListInfo(int typeId, int page) {
//        return OkGo.<ResponseData<ActListBean>>get(GetActListUrl)
//                .params("type", typeId)
//                .params("page", page)
//                .converter(new Converter<ResponseData<ActListBean>>() {
//                    @Override
//                    public ResponseData<ActListBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<ActListBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<ActListBean>>());
//    }
//
//    // 获取单个活动详情数据
//    public static Observable<ResponseData<ActivityDetailBean>> getSingleActDetailInfo(int eventId) {
//        return OkGo.<ResponseData<ActivityDetailBean>>get(GetActListUrl)
//                .params("id", eventId)
//                .converter(new Converter<ResponseData<ActivityDetailBean>>() {
//                    @Override
//                    public ResponseData<ActivityDetailBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<ActivityDetailBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<ActivityDetailBean>>());
//
//    }
//
//    // 兑换
//    public static Observable<ResponseData<ExChangeBean>> getExchange(int eventId, int ticketId, int smsCode, int count, String registrationItem) {
//        return OkGo.<ResponseData<ExChangeBean>>post(PostExchangeUrl)
//                .params("event_id", eventId)
//                .params("ticket_id", ticketId)
//                .params("smsCode", smsCode)
//                .params("count", count)
//                .params("registration_info_android", registrationItem)
//                .converter(new Converter<ResponseData<ExChangeBean>>() {
//                    @Override
//                    public ResponseData<ExChangeBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<ExChangeBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<ExChangeBean>>());
//
//    }
//
//    // 获取点评详情
//    public static Observable<ResponseData<CommentDetailBean>> getCommentDetail(String userId, int topicId) {
//        return OkGo.<ResponseData<CommentDetailBean>>get(GetCommentDetailUrl)
//                .params("user_id", userId)
//                .params("topic_id", topicId)
//                .converter(new Converter<ResponseData<CommentDetailBean>>() {
//                    @Override
//                    public ResponseData<CommentDetailBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<CommentDetailBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<CommentDetailBean>>());
//    }
//
//    // 获取点评详情评论
//    public static Observable<ResponseData<CommentBean>> getComment(String userId, int topicId) {
//        return OkGo.<ResponseData<CommentBean>>get(GETCommentUrl)
//                .params("user_id", userId)
//                .params("topic_id", topicId)
//                .converter(new Converter<ResponseData<CommentBean>>() {
//                    @Override
//                    public ResponseData<CommentBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<CommentBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<CommentBean>>());
//
//    }
//
//    // 获取点评详情评论
//    public static Observable<ResponseData<AddCommentBean>> getAddComment(String userId, int topicId, String content, int toWho) {
//        return OkGo.<ResponseData<AddCommentBean>>post(PostCommentUrl)
//                .params("user_id", userId)
//                .params("topic_id", topicId)
//                .params("content", content)
//                .params("toWho", toWho)
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
//    //点赞
//    public static Observable<ResponseData<LikeBean>> like(int userId, int topicId) {
//        return OkGo.<ResponseData<LikeBean>>post(LikeUrl)
//                .params("user_id", userId)
//                .params("topic_id", topicId)
//                .converter(new Converter<ResponseData<LikeBean>>() {
//                    @Override
//                    public ResponseData<LikeBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<LikeBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<LikeBean>>());
//    }
//
//    // 登出
//    public static Observable<ResponseData<LogoutBean>> logout(String token) {
//        return OkGo.<ResponseData<LogoutBean>>get(LoginOutUrl)
//                .params("nast-token", token)
//                .converter(new Converter<ResponseData<LogoutBean>>() {
//                    @Override
//                    public ResponseData<LogoutBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<LogoutBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<LogoutBean>>());
//    }
//
//    // 分享点评回调
//    public static Observable<ResponseData<ShareBean>> shareTopicSuccess(String userId, int topicId) {
//        return OkGo.<ResponseData<ShareBean>>post(ShareTopicUrlCallBack)
//                .params("user_id", userId)
//                .params("topic_id", topicId)
//                .converter(new Converter<ResponseData<ShareBean>>() {
//                    @Override
//                    public ResponseData<ShareBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<ShareBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<ShareBean>>());
//    }
//
//    // 分享活动回调
//    public static Observable<ResponseData<ShareBean>> shareActSuccess(int eventId) {
//        return OkGo.<ResponseData<ShareBean>>post(ShareActUrlCallBack)
//                .params("event_id", eventId)
//                .converter(new Converter<ResponseData<ShareBean>>() {
//                    @Override
//                    public ResponseData<ShareBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<ShareBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<ShareBean>>());
//    }
//
//    // 活动管理列表
//    public static Observable<ResponseData<ActManagerBean>> actManager() {
//        return OkGo.<ResponseData<ActManagerBean>>get(actManagerUrl)
//                .converter(new Converter<ResponseData<ActManagerBean>>() {
//                    @Override
//                    public ResponseData<ActManagerBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<ActManagerBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<ActManagerBean>>());
//    }
//
//    //活动删除
//    public static Observable<ResponseData<DeleteMoveBean>> actDelete(int eventId, int flag) {
//        return OkGo.<ResponseData<DeleteMoveBean>>post(actDeleteUrl)
//                .params("event_id", eventId)
//                .params("flag", flag)
//                .converter(new Converter<ResponseData<DeleteMoveBean>>() {
//                    @Override
//                    public ResponseData<DeleteMoveBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<DeleteMoveBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<DeleteMoveBean>>());
//    }
//
//    // 获取我的活动报名者信息
//    public static Observable<ResponseData<MyEventSignUpBean>> getSinUpInfo(int eventId, int page) {
//        return OkGo.<ResponseData<MyEventSignUpBean>>get(getSingUpInfoUrl)
//                .params("event_id", eventId)
//                .params("page", page)
//                .converter(new Converter<ResponseData<MyEventSignUpBean>>() {
//                    @Override
//                    public ResponseData<MyEventSignUpBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<MyEventSignUpBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<MyEventSignUpBean>>());
//    }
//
//    //根据活动id获取活动内容
//    public static Observable<ResponseData<MoveEditActBean>> getMoveEditActInfo(int eventId) {
//        return OkGo.<ResponseData<MoveEditActBean>>get(getEditActInfoUrl)
//                .params("event_id", eventId)
//                .converter(new Converter<ResponseData<MoveEditActBean>>() {
//                    @Override
//                    public ResponseData<MoveEditActBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<MoveEditActBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<MoveEditActBean>>());
//    }
//
//    //修改活动 返回空对象
//    public static Observable<ResponseData<WalletBean>> getModifyAct(int eventId, String title, String address, String contactNo
//            , String begin, String end, int type, String registrationItem, String coverName) {
//        return OkGo.<ResponseData<WalletBean>>post(ModifyActUrl)
//                .params("event_id", eventId)
//                .params("title", title)
//                .params("address", address)
//                .params("contact_no", contactNo)
//                .params("begin", begin)
//                .params("end", end)
//                .params("type", type)
//                .params("registration_item", registrationItem)
//                .params("cover_name", coverName)
//                .converter(new Converter<ResponseData<WalletBean>>() {
//                    @Override
//                    public ResponseData<WalletBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<WalletBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<WalletBean>>());
//    }
//
//    //修改用户头像
//    public static Observable<ResponseData<UpdateAvatarBean>> updateAvatar(String avatar) {
//        return OkGo.<ResponseData<UpdateAvatarBean>>post(updateAvatarUrl)
//                .params("avatar", avatar)
//                .converter(new Converter<ResponseData<UpdateAvatarBean>>() {
//                    @Override
//                    public ResponseData<UpdateAvatarBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<UpdateAvatarBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<UpdateAvatarBean>>());
//    }
//
//    //判断用户类型
//    public static Observable<ResponseData<UserTypeBean>> modifyUserType() {
//        return OkGo.<ResponseData<UserTypeBean>>get(ModifyTypeUrl)
//                .converter(new Converter<ResponseData<UserTypeBean>>() {
//                    @Override
//                    public ResponseData<UserTypeBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<UserTypeBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<UserTypeBean>>());
//    }
//
//
//    //获取充值数据
//    public static Observable<ResponseData<ReChargeBean>> getSellListData() {
//        return OkGo.<ResponseData<ReChargeBean>>get(getSellListUrl)
//                .converter(new Converter<ResponseData<ReChargeBean>>() {
//                    @Override
//                    public ResponseData<ReChargeBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<ReChargeBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<ReChargeBean>>());
//    }
//
//    //创建购买订单
//    public static Observable<ResponseData<CreateOrderBean>> createOrder(int count) {
//        return OkGo.<ResponseData<CreateOrderBean>>post(createOrderUrl)
//                .params("count", count)
//                .converter(new Converter<ResponseData<CreateOrderBean>>() {
//                    @Override
//                    public ResponseData<CreateOrderBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<CreateOrderBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<CreateOrderBean>>());
//    }
//
//    //支付订单
//    public static Observable<ResponseData<PayBean>> payOrder(String orderId, String type, String confirmInfo) {
//        return OkGo.<ResponseData<PayBean>>post(payOrderUrl)
//                .params("order_id", orderId)
//                .params("type", type)
//                .params("confirm_info", confirmInfo)
//                .converter(new Converter<ResponseData<PayBean>>() {
//                    @Override
//                    public ResponseData<PayBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<PayBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<PayBean>>());
//    }
//
//    //订单详情
//    public static Observable<ResponseData<OrderDetailBean>> getOrderDetail(String orderId) {
//        return OkGo.<ResponseData<OrderDetailBean>>get(OrderDetailUrl)
//                .params("order_id", orderId)
//                .converter(new Converter<ResponseData<OrderDetailBean>>() {
//                    @Override
//                    public ResponseData<OrderDetailBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<OrderDetailBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<OrderDetailBean>>());
//    }
//
//    //订单列表
//    public static Observable<ResponseListData<OrderListBean>> getOrderList() {
//        return OkGo.<ResponseListData<OrderListBean>>get(OrderListUrl)
//                .converter(new Converter<ResponseListData<OrderListBean>>() {
//                    @Override
//                    public ResponseListData<OrderListBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseListData<OrderListBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseListData<OrderListBean>>());
//    }
//
//    //取消订单
//    public static Observable<ResponseData<WalletBean>> getCancelOrder(String orderId) {
//        return OkGo.<ResponseData<WalletBean>>post(CancelOrderUrl)
//                .params("order_id", orderId)
//                .converter(new Converter<ResponseData<WalletBean>>() {
//                    @Override
//                    public ResponseData<WalletBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<WalletBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<WalletBean>>());
//    }
//
//    //活动周banner
//    public static Observable<ResponseData<ActWeekBean>> getActWeekData() {
//        return OkGo.<ResponseData<ActWeekBean>>get(ActWeekBannerUrl)
//                .converter(new Converter<ResponseData<ActWeekBean>>() {
//                    @Override
//                    public ResponseData<ActWeekBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<ActWeekBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<ActWeekBean>>());
//    }
//
//    //首页
//    public static Observable<ResponseData<HomeBeanVo>> getActWeekData2() {
//        return OkGo.<ResponseData<HomeBeanVo>>get(ActWeekBannerUrl)
//                .converter(new Converter<ResponseData<HomeBeanVo>>() {
//                    @Override
//                    public ResponseData<HomeBeanVo> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<HomeBeanVo>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<HomeBeanVo>>());
//    }
//
//    //分享活动周成功后的回调
//    public static Observable<ResponseData<ShareBean>> getShareActWeek(int brandId) {
//        return OkGo.<ResponseData<ShareBean>>post(ShareActWeekCallBackUrl)
//                .params("brand_id", brandId)
//                .converter(new Converter<ResponseData<ShareBean>>() {
//                    @Override
//                    public ResponseData<ShareBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<ShareBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<ShareBean>>());
//    }
//
//    //单独获取活动周消费排名
//    public static Observable<ResponseData<SingRankInfoBean>> getSingleRankInfo() {
//        return OkGo.<ResponseData<SingRankInfoBean>>get(singleRankInfoUrl)
//                .converter(new Converter<ResponseData<SingRankInfoBean>>() {
//                    @Override
//                    public ResponseData<SingRankInfoBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<SingRankInfoBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<SingRankInfoBean>>());
//    }
//
//    //修改个人信息
//    public static Observable<ResponseData<EditPersonalInfoBean>> EditPersonInfo(String nickname, int gender,
//                                                                                String birthday, String realName, String address, String genderTip, String email) {
//        return OkGo.<ResponseData<EditPersonalInfoBean>>post(editPersonInfoUrl)
//                .params("nickname", nickname)
//                .params("gender", gender)
//                .params("birthday", birthday)
//                .params("real_name", realName)
//                .params("address", address)
//                .params("gender_tip", genderTip)
//                .params("email", email)
//                .converter(new Converter<ResponseData<EditPersonalInfoBean>>() {
//                    @Override
//                    public ResponseData<EditPersonalInfoBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<EditPersonalInfoBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<EditPersonalInfoBean>>());
//    }
//
//    //修改收货地址信息
//    public static Observable<ResponseData<EditPersonalInfoBean>> EditAddressInfo(String name, String address, String phone) {
//        return OkGo.<ResponseData<EditPersonalInfoBean>>post(editPersonInfoUrl)
//                .params("contact", name)
//                .params("address", address)
//                .params("contact_no", phone)
//                .converter(new Converter<ResponseData<EditPersonalInfoBean>>() {
//                    @Override
//                    public ResponseData<EditPersonalInfoBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<EditPersonalInfoBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<EditPersonalInfoBean>>());
//    }
//
//    //    解锁官网NAST
//    public static Observable<ResponseData<WalletBean>> unLockOfficial() {
//        return OkGo.<ResponseData<WalletBean>>post(MINE_UNLOCK_URL)
//                .converter(new Converter<ResponseData<WalletBean>>() {
//                    @Override
//                    public ResponseData<WalletBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<WalletBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<WalletBean>>());
//    }
//
//    // 玩咖策划数据获取
//    public static Observable<ResponseData<AdventurerData>> getAdventurerData(String playId) {
//        return OkGo.<ResponseData<AdventurerData>>get(PLAYER_PLAN_URL).
//                params("player_id", playId).
//                converter(new Converter<ResponseData<AdventurerData>>() {
//                    @Override
//                    public ResponseData<AdventurerData> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<AdventurerData>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                }).adapt(new ObservableBody<ResponseData<AdventurerData>>());
//    }
//
//    //    取消连续投注
//    public static Observable<ResponseData<TopicPkCancle>> cancleTopicPK() {
//        return OkGo.<ResponseData<TopicPkCancle>>get(TRADE_CANCLE_URL).
//                params("continuity", 1).
//                converter(new Converter<ResponseData<TopicPkCancle>>() {
//                    @Override
//                    public ResponseData<TopicPkCancle> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<TopicPkCancle>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                }).adapt(new ObservableBody<ResponseData<TopicPkCancle>>());
//    }
//
//    //    页面展示
//    public static Observable<ResponseData<TopicPkInfoBean>> getTopicPKInfo() {
//        return OkGo.<ResponseData<TopicPkInfoBean>>get(TRADE_INFO_URL).
//                converter(new Converter<ResponseData<TopicPkInfoBean>>() {
//                    @Override
//                    public ResponseData<TopicPkInfoBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<TopicPkInfoBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                }).adapt(new ObservableBody<ResponseData<TopicPkInfoBean>>());
//    }
//
//    //    结果获取(bean待定)
//    public static Observable<ResponseData<EmptyBean>> getTopicPKResult() {
//        return OkGo.<ResponseData<EmptyBean>>get(TRADE_RESULT_URL).converter(new Converter<ResponseData<EmptyBean>>() {
//            @Override
//            public ResponseData<EmptyBean> convertResponse(Response response) throws Throwable {
//                Type type = new TypeToken<ResponseData<EmptyBean>>() {
//                }.getType();
//                return gson.fromJson(response.body().string(), type);
//
//            }
//        }).adapt(new ObservableBody<ResponseData<EmptyBean>>());
//    }
//
//    //   check社群码
//    public static Observable<ResponseData<CommunitySignInVo>> checkCode(String code) {
//        return OkGo.<ResponseData<CommunitySignInVo>>get(COMMUNITY_CODE_CHECK_URL)
//                .params("trackCode", code)
//                .converter(new Converter<ResponseData<CommunitySignInVo>>() {
//                    @Override
//                    public ResponseData<CommunitySignInVo> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<CommunitySignInVo>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//
//                    }
//                }).adapt(new ObservableBody<ResponseData<CommunitySignInVo>>());
//    }
//
//    //   check社群码
//    public static Observable<ResponseData<EmptyBean>> joinByTrackCode(String code) {
//        return OkGo.<ResponseData<EmptyBean>>post(JOIN_BY_COMMUNITY_CODE_URL)
//                .params("trackCode", code)
//                .converter(new Converter<ResponseData<EmptyBean>>() {
//                    @Override
//                    public ResponseData<EmptyBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<EmptyBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//
//                    }
//                }).adapt(new ObservableBody<ResponseData<EmptyBean>>());
//    }
//
//    //获取能量森林数据
//    public static Observable<ResponseData<PowerForestBean>> getPowerForestData() {
//        return OkGo.<ResponseData<PowerForestBean>>get(POWER_BALL_URL)
//                .converter(new Converter<ResponseData<PowerForestBean>>() {
//                    @Override
//                    public ResponseData<PowerForestBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<PowerForestBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<PowerForestBean>>());
//    }
//
//    // 收集能量森林
//    public static Observable<ResponseData<TakeBallBean>> getPowerForestBallData(int bubbleId) {
//        return OkGo.<ResponseData<TakeBallBean>>post(GET_POWER_BALL_URL)
//                .params("bubble_id", bubbleId)
//                .converter(new Converter<ResponseData<TakeBallBean>>() {
//                    @Override
//                    public ResponseData<TakeBallBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<TakeBallBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                })
//                .adapt(new ObservableBody<ResponseData<TakeBallBean>>());
//    }
//
//    //    师徒信息获取
//    public static Observable<ResponseData<MentoringInfoVo>> getMentoringInfo() {
//        return OkGo.<ResponseData<MentoringInfoVo>>get(GET_MENTORING_INFO_URL).converter(new Converter<ResponseData<MentoringInfoVo>>() {
//            @Override
//            public ResponseData<MentoringInfoVo> convertResponse(Response response) throws Throwable {
//                Type type = new TypeToken<ResponseData<MentoringInfoVo>>() {
//                }.getType();
//                return gson.fromJson(response.body().string(), type);
//
//            }
//        }).adapt(new ObservableBody<ResponseData<MentoringInfoVo>>());
//    }
//
//    //    社群信息获取
//    public static Observable<ResponseData<CommunityInfoVo>> getCommunityInfo() {
//        return OkGo.<ResponseData<CommunityInfoVo>>get(GET_COMMUNITY_INFO_URL).converter(new Converter<ResponseData<CommunityInfoVo>>() {
//            @Override
//            public ResponseData<CommunityInfoVo> convertResponse(Response response) throws Throwable {
//                Type type = new TypeToken<ResponseData<CommunityInfoVo>>() {
//                }.getType();
//                return gson.fromJson(response.body().string(), type);
//
//            }
//        }).adapt(new ObservableBody<ResponseData<CommunityInfoVo>>());
//    }
//
//    //获取交易明细
//    public static Observable<ResponseData<TransactionInfo>> getTransactionData(int page, int limit) {
//        return OkGo.<ResponseData<TransactionInfo>>get(GET_TRANSACTION_INFO_URL).
//                params("page", page).
//                params("limit", limit).
//                converter(new Converter<ResponseData<TransactionInfo>>() {
//                    @Override
//                    public ResponseData<TransactionInfo> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<TransactionInfo>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//
//                    }
//                }).adapt(new ObservableBody<ResponseData<TransactionInfo>>());
//    }
//
//    //获取收益信息
//    public static Observable<ResponseData<TransactionInfo>> getBonusData(int page, int limit) {
//        return OkGo.<ResponseData<TransactionInfo>>get(GET_BONUS_URL).
//                params("page", page).
//                params("limit", limit).
//                converter(new Converter<ResponseData<TransactionInfo>>() {
//                    @Override
//                    public ResponseData<TransactionInfo> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<TransactionInfo>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//
//                    }
//                }).adapt(new ObservableBody<ResponseData<TransactionInfo>>());
//    }
//
//    //购买Vip、
//    public static Observable<ResponseData<EmptyBean>> buyVip() {
//        return OkGo.<ResponseData<EmptyBean>>post(BUY_VIP_URL).
//
//                converter(new Converter<ResponseData<EmptyBean>>() {
//                    @Override
//                    public ResponseData<EmptyBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<EmptyBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//
//                    }
//                }).adapt(new ObservableBody<ResponseData<EmptyBean>>());
//    }
//
//    //    确认转出
//    public static Observable<ResponseData<EmptyBean>> transMoneyOut(String transfer_out_addr, String transfer_out_amount, int type) {
//        return OkGo.<ResponseData<EmptyBean>>post(TRANSACTION_MONEY_OUT_URL).
//                params("transfer_out_addr", transfer_out_addr).
//                params("transfer_out_amount", transfer_out_amount).
//                params("type", type).
//                converter(new Converter<ResponseData<EmptyBean>>() {
//                    @Override
//                    public ResponseData<EmptyBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<EmptyBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//
//                    }
//                }).adapt(new ObservableBody<ResponseData<EmptyBean>>());
//    }
//
//    //    money转换费率信息
//    public static Observable<ResponseData<TransMoneyInfo>> transMoneyOutInfo() {
//        return OkGo.<ResponseData<TransMoneyInfo>>get(TRANSACTION_MONEY_OUT_INFO_URL).
//
//                converter(new Converter<ResponseData<TransMoneyInfo>>() {
//                    @Override
//                    public ResponseData<TransMoneyInfo> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<TransMoneyInfo>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//
//                    }
//                }).adapt(new ObservableBody<ResponseData<TransMoneyInfo>>());
//    }
//
//    //    nast转eth
//    public static Observable<ResponseData<MoneyTransVo>> nastToEth(String transfer_out_amount) {
//        return OkGo.<ResponseData<MoneyTransVo>>post(NAST_TO_ETH_URL).
//                params("transfer_out_nast", transfer_out_amount).
//                converter(new Converter<ResponseData<MoneyTransVo>>() {
//                    @Override
//                    public ResponseData<MoneyTransVo> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<MoneyTransVo>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//
//                    }
//                }).adapt(new ObservableBody<ResponseData<MoneyTransVo>>());
//    }
//
//    //    赚钱首页
//    public static Observable<ResponseData<EarnMoneyBean>> earnMoney() {
//        return OkGo.<ResponseData<EarnMoneyBean>>get(EARN_MONEY_URL).
//                converter(new Converter<ResponseData<EarnMoneyBean>>() {
//                    @Override
//                    public ResponseData<EarnMoneyBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<EarnMoneyBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//
//                    }
//                }).adapt(new ObservableBody<ResponseData<EarnMoneyBean>>());
//    }
//
//    //    赚钱详情
//    public static Observable<ResponseData<EarnMoneyDetialVo>> earnMoneyDetial() {
//        return OkGo.<ResponseData<EarnMoneyDetialVo>>get(EARN_MONEY_DETIAL_URL).
//                converter(new Converter<ResponseData<EarnMoneyDetialVo>>() {
//                    @Override
//                    public ResponseData<EarnMoneyDetialVo> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<EarnMoneyDetialVo>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//
//                    }
//                }).adapt(new ObservableBody<ResponseData<EarnMoneyDetialVo>>());
//    }
//
//    //    赚钱详情
//    public static Observable<ResponseData<FindBeanVo>> getFindData() {
//        return OkGo.<ResponseData<FindBeanVo>>get(FIND_DATA_URL).
//                converter(new Converter<ResponseData<FindBeanVo>>() {
//                    @Override
//                    public ResponseData<FindBeanVo> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<FindBeanVo>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//
//                    }
//                }).adapt(new ObservableBody<ResponseData<FindBeanVo>>());
//    }
//
//    // 获取支付宝订单
//    public static Observable<ResponseData<ALiPayOrderBean>> getALiPayOrder(int type) {
//        return OkGo.<ResponseData<ALiPayOrderBean>>get(GET_ALIPAY_ORDER_URL).
//                params("orderType",type).
//                converter(new Converter<ResponseData<ALiPayOrderBean>>() {
//                    @Override
//                    public ResponseData<ALiPayOrderBean> convertResponse(Response response) throws Throwable {
//                        Type type = new TypeToken<ResponseData<ALiPayOrderBean>>() {
//                        }.getType();
//                        return gson.fromJson(response.body().string(), type);
//                    }
//                }).adapt(new ObservableBody<ResponseData<ALiPayOrderBean>>());
//    }
}