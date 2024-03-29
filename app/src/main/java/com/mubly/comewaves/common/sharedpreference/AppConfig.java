package com.mubly.comewaves.common.sharedpreference;

import android.content.Context;

import com.mubly.comewaves.model.model.UserInfoVo;


/**
 * 新增字段，若需要清除缓存的，切记的要清缓存
 * <p>
 * 示例代码：
 * public static SharedPreference<String> userName = sPrefs.value("userName", (String) null);
 * // 写入、替换
 * AppConfig.userName.put("小宝");
 * // 读取
 * String userName = AppConfig.userName.get();
 * // 删除
 * AppConfig.userName.remove();
 * // 获取Key
 * AppConfig.userName.getKey();
 * <p>
 * Created by android_ls on 2017/1/6.
 */
public final class AppConfig {

    private static SPHelper sPrefs = new SPHelper("HDating", Context.MODE_PRIVATE);
    public static SharedPreference<String> deviceId = sPrefs.value("deviceIdKey", (String) null);
    public static SharedPreference<String> token = sPrefs.value("token", (String) null);
    public static SharedPreference<String> loginInfo = sPrefs.value("loginInfo", (String) null);
    public static SharedPreference<String> userInfoVo = sPrefs.value("userInfo", (String) null);

    public static void clearAll() {
        sPrefs.clearAll();
    }

}
