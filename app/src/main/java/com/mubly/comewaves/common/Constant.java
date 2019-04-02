package com.mubly.comewaves.common;

/**
 * Created by waber on 2018/6/28.
 */
public class Constant {

    public static final int SuccessCode = 200; //请求数据成功
    public static final int FIRST_LOGIN_CODE = 400; //首次登录
    public static final int NASTTOKENERROR = 401; //nast-token 失效或过期
    public static final int USERFORBID = 402; // 账号禁用
    public static final int UUIDERROR = 403; // 多端登陆

    public static final int BANCLICK = 444;  // 禁止点击事件

    public static final int SERVERERROR = 500; // 服务器错误

    public static final int PULL_IMAGE_VIDEO_CODE = 0; // 拍摄发布图片/视频/音频
    public static final int VIDEO_TYPE_CODE = 1; // 视频
    public static final int IMAGE_TYPE_CODE = 2; // 图片
    public static final int PULL_IMAGE_CODE = 1; // 拍摄发布图片
    public static final int PULL_VIDEO_CODE = 2; // 拍摄发布视频

    public static final int REQUEST_VIDEO_IMG_CODE = 1010; //获取视频封面

    public static final int RESULT_CODE_TYPE = 1020; //获取选择的类型

    public static final int REQUEST_CODE_SIGN_SETTING = 1030; //获取报名设置信息

    public static final int REQUEST_CODE_FEE = 1040; // 获取票种信息

    public static final int REQUEST_ADD_CODE_FEE = 1050; // 添加票种信息

    public static final int RESULT_ADD_CODE_FEE = 1060; // 添加票种信息完成

    public static final int RESULT_ADD_CODE_FEE_MAX_MIN = 1070; // 添加票种信息完成

    public static final int REQUEST_CODE_DEIAIL = 1080; // 打开富文本链接

    public static final int RESULT_CODE_COMPLETE = 1100; // 报名项点击了完成

    public static final int RESULT_CODE_DETAIL = 1111; // 完成活动详情编辑

    public static final int RESULT_CODE_DETAIL_EDIT = 1131; // 完成活动详情编辑

    public static final int RESULT_CODE_RELEASE_DETAIL = 1122; // 发布活动进入详情
    public static final int REQUEST_CODE_EDIT_ADDRESS = 1123; // 修改收货人地址
    public static final int REQUEST_CODE_ADD_ADDRESS = 1124; // 添加收货人地址
    public static final int ADVENTURER_CODE = 1125; // 玩咖策划，events进入原生此详情CODE


    public static final String WX_APPID = "wx2b033b393329cde8";//微信appid
    public static final String WX_APPSECRET = "232d40daf0540da312a615970a6267f1";//微信APPSECRET
    public static final int CODELENgTH = 4;//验证码长度

}
