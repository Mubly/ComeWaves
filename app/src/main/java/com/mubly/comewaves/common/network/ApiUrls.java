package com.mubly.comewaves.common.network;


import static com.mubly.comewaves.BuildConfig.BASE_H5_URL;
import static com.mubly.comewaves.BuildConfig.BASE_URL;

/**
 * Created by waber on 2018/6/26.
 */
public class ApiUrls {


    public static final String GET_CODE_URL = BASE_URL + "login/sendMsg";    //获取验证码

    public static final String loginUrl = BASE_URL + "login/loginByPhone";  //验证码登录
    public static final String ONE_PASS_login_Url = BASE_URL + "gt/check-gateway";  //密码登录
    public static final String GETCATEGORY_URL = BASE_URL + "category/getcategory";  //获取分类
    public static final String REGISTERED_URL = BASE_URL + "login/adduser";  //注册
    public static final String BACKGROUND_UPDATE_URL = BASE_URL + "user/postupdatebackground";  //修改用户背景图
    public static final String AVATAR_UPDATE_URL = BASE_URL + "user/postupdateimg";  //修改用户头像
    public static final String USERINFO_UPDATE_URL = BASE_URL + "user/postpersonal";  //修改用户信息
    public static final String IMAGE_UPLOAD_URL = BASE_URL + "post/postaddpictureweChat";  //图片上传
    public static final String MUIL_IMAGE_UPLOAD_URL = BASE_URL + "post/posttextimgs";  //多图片上传
    public static final String VIDEO_UPLOAD_URL = BASE_URL + "post/postaddvideo";  //视频上传

    public static final String GET_USERINFO_URL = BASE_URL + "user/getpersonal";  //用户信息获取

    public static final String PRAISE_URL = BASE_URL + "user/posttextlike";  //点赞

    public static final String START_IMAGE_URL = BASE_URL + "login/first";  //启动页面

    public static final String HOME_INFO_URL = BASE_URL + "index/index";  //首页信息

    public static final String TIE_INFO_URL = BASE_URL + "report/showpostbyid";//首页信息详情页

    public static final String COMMENT_INFO_URL = BASE_URL + "report/showcommentpage";//获取评论列表


    public static final String REPLY_COMMENT_URL = BASE_URL + "report/postreport";  //回复评论

    public static final String SEND_REPLY_COMMENT_URL = BASE_URL + "report/postcomment";  //发表评论

    public static final String ATTENTION_SOMEBODAY_URL = BASE_URL + "user/postattention";  //关注/取消关注

    public static final String COLLECTION_URL = BASE_URL + "user/posttextcollect";  //收藏/取消收藏


    public static final String MineDynamicUrl = BASE_URL + "me/dynamic/1";  //我的动态

    public static final String MineCommentUrl = BASE_URL + "me/comment/";  //我的点评

    public static final String HomeGet = BASE_URL + "banner/get";  //首页

    public static final String MsgList = BASE_URL + "msg/list";  //获取通知类型以及对应的最新一条通知记录

    public static final String GetCommentDetailUrl = BASE_URL + "topic/topicDetailNoToken";  //获取点评详情

    public static final String GetVersionUrl = BASE_URL + "version/last";  //检测版本更新

    public static final String EditCommentUrl = BASE_URL + "write/comment/";  //写点评

    public static final String OtherHomeUrl = BASE_URL + "othersHomepage/";  //进入他人主页

    public static final String GETCommentUrl = BASE_URL + "topic/getCommentByTopic";  //获取评论

    public static final String PostCommentUrl = BASE_URL + "topic/comment";  //评论

    public static final String MyLike = BASE_URL + "topic/myLike";  //获取点赞通知
    public static final String getMyTopicMsg = BASE_URL + "topic/myComment";  //获取点评通知
    public static final String myTopic = BASE_URL + "event/myTopic";
    public static final String systemlist = BASE_URL + "msg/systemlist";  //获取系统通知
    public static final String readAllSystem = BASE_URL + "msg/readAllSystem";  //批量比较系统消息已读

    public static final String TransactionUrl = BASE_URL + "transaction";  // 交易明细

    public static final String TransferUrl = BASE_URL + "wallet";  // 转出

    public static final String HelpUrl = BASE_URL + "help";  // 帮助

    public static final String TransferIntoUrl = BASE_URL + "into";  // 转入

    public static final String FydpUrl = BASE_URL + "fydp";  // 商户入驻

    public static final String JoinGroupUrl = BASE_URL + "group/joinGroup";  //加入群聊

    public static final String ShareUrl = BASE_URL + "event/share/";  // 活动分享页

    public static final String ShareTopicUrl_ = BASE_URL + "write/share/";  // 点评详情分享页

    public static final String LikeUrl = BASE_URL + "topic/like";  // 点赞

    public static final String RuleUrl = BASE_URL + "nastrule";  // 规则

    public static final String LoginOutUrl = BASE_URL + "user/logout";  // 登出

    public static final String ZCUrl = BASE_URL + "zc";

    public static final String AboutUsUrl = BASE_URL + "aboutus";  // 关于我们

    public static final String ShareActUrlCallBack = BASE_URL + "event/share";  // 分享活动详情回调

    public static final String ShareTopicUrlCallBack = BASE_URL + "topic/share";  // 分享点评详情回调

    public static final String nastrule = BASE_URL + "nastrule";  // NAST规则
    public static final String nast_one = "http://nast.one";  // NAST规则
    public static final String fydp = BASE_URL + "fydp";  // 商户入住

    public static final String gmnast = "https://mp.weixin.qq.com/s/JMu1Br7jhRJsRGcjifoPpA";  // 购买NAST

    public static final String vnbig = "http://m.vnbig.com/";  // 购买NAST

    public static final String topicGet = BASE_URL + "/topic/get";  // 首页点评

    public static final String actManagerUrl = BASE_URL + "event/my";  // 活动管理列表

    public static final String actDeleteUrl = BASE_URL + "event/delete";  // 活动删除

    public static final String getSingUpInfoUrl = BASE_URL + "event/myEventSignup";  // 获取我的活动报名者信息

    public static final String getEditActInfoUrl = BASE_URL + "event/getById";  // 根据Id获取活动的基本内容

    public static final String ModifyActUrl = BASE_URL + "event/update";  // 修改活动

    //public static final String ModifyAvatarUrl = BASE_URL + "user/avatar";  // 修改头像

    public static final String ModifyTypeUrl = BASE_URL + "user/type";  // 判断用户类型

    public static final String updateAvatarUrl = BASE_URL + "user/updateBasicInfo";   // 更新头像

    public static final String getSellListUrl = BASE_URL + "order/selllist";   // 获取市商列表

    public static final String createOrderUrl = BASE_URL + "order/createOrder";   // 创建订单

    public static final String payOrderUrl = BASE_URL + "order/payOrder";   // 支付订单

    public static final String OrderDetailUrl = BASE_URL + "order/orderDetail";   // 订单详情

    public static final String OrderListUrl = BASE_URL + "order/orderList";   // 订单列表

    public static final String CancelOrderUrl = BASE_URL + "order/cancelOrder";   // 取消订单

    public static final String ActWeekBannerUrl = BASE_URL + "banner/brand";   // 活动周banner

    public static final String ShareActWeekUrl = BASE_URL + "share/brand/";   // 分享活动周

    public static final String ShareActWeekCallBackUrl = BASE_URL + "brand/share";   // 成功分享活动周

    public static final String singleRankInfoUrl = BASE_URL + "brand/rank";   // 单独获取活动周消费排名

    public static final String powerRuleUrl = BASE_URL + "powerRule";   // 能量球规则

    public static final String editPersonInfoUrl = BASE_URL + "user/updateBasicInfo";   // 修改用户个人资料

    public static final String BrokerCenterUrl = BASE_URL + "myextend";   // 经纪人中心

    public static final String MentoringUrl = BASE_URL + "reward";

    public static final String MINE_UNLOCK_URL = BASE_URL + "account/unlockOfficial";   // 解锁官网

    public static final String TRADE_VOTE_URL = BASE_URL + "trade/vote";   // 投票接口

    public static final String TRADE_CANCLE_URL = BASE_URL + "trade/cancelVote";   // 取消连续投注

    public static final String TRADE_INFO_URL = BASE_URL + "trade/show";   // 页面展示

    public static final String TRADE_RESULT_URL = BASE_URL + "trade/match";   // 结果获取

    public static final String COMMUNITY_CODE_CHECK_URL = BASE_URL + "community/getByTrackCode";   // 社群签到Code check

    public static final String JOIN_BY_COMMUNITY_CODE_URL = BASE_URL + "community/joinByTrackCode";   // 社群签到Code check

    public static final String POWER_BALL_URL = BASE_URL + "bubbles/get"; // 获取能量森林数据

    public static final String GET_POWER_BALL_URL = BASE_URL + "bubbles/take"; // 收集能量

    public static final String GET_MENTORING_INFO_URL = BASE_URL + "agent/myMemberNumbers";//获取师徒信息
    public static final String GET_COMMUNITY_INFO_URL = BASE_URL + "community/myCommunity";//获取社群信息
    public static final String GET_TRANSACTION_INFO_URL = BASE_URL + "order";//获取社群信息
    public static final String GET_BONUS_URL = BASE_URL + "account/myBonus";//获取收益信息
    public static final String BUY_VIP_URL = BASE_URL + "account/buyVip";//购买VIP
    public static final String PLAYER_PLAN_URL = BASE_URL + "player/getByPlayerId";//玩咖策划
    public static final String TRANSACTION_MONEY_OUT_URL = BASE_URL + "account/transfer/out";//确认提现
    public static final String TRANSACTION_MONEY_OUT_INFO_URL = BASE_URL + "account/transfer/config";//提现费率规则
    public static final String NAST_TO_ETH_URL = BASE_URL + "account/transfer/nastToEth";//NAST转ETH
    public static final String QIANLIAO_URL = "http://www.xmypage.top/model6_75418.html";
    public static final String EARN_MONEY_URL = BASE_URL + "banner/earnBanner";//赚钱首页
    public static final String EARN_MONEY_DETIAL_URL = BASE_URL + "account/myBonusCollection";//赚钱详情
    public static final String FIND_DATA_URL = BASE_URL + "discovery/get";//发现获取
    public static final String GET_ALIPAY_ORDER_URL = BASE_URL + "pay/createAliOrder";//获取支付宝订单
    public static final String SERVICE_ONLINE_URL = "https://www.sobot.com/chat/h5/index.html?sysNum=fd4590b9bcb042ecb94833aed9c57491&source=2";//客服在线
}
