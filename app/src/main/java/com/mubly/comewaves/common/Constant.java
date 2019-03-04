package com.mubly.comewaves.common;

/**
 * Created by waber on 2018/6/28.
 */
public class Constant {

    public static final int SuccessCode = 200; //请求数据成功

    public static final int NASTTOKENERROR = 401; //nast-token 失效或过期
    public static final int USERFORBID = 402; // 账号禁用
    public static final int UUIDERROR = 403; // 多端登陆

    public static final int BANCLICK = 444;  // 禁止点击事件

    public static final int SERVERERROR = 500; // 服务器错误

    public static final int ACTTIMEOUT = 30; // NAST不足

    public static final int INTENT_REQUEST_CODE_CAMERA = 105; //调用系统相机的请求码

    public static final int REQUEST_CODE_CHOOSE = 25; //获取相册

    public static final int REQUEST_CODE_CHOOSE_TYPE = 1010; //获取相册

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


}
