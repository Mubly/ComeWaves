package com.mubly.comewaves.common;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.mubly.comewaves.common.sharedpreference.AppConfig;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;


import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class CrossApp extends Application {
    /**
     * 首页按返回键的次数
     */
    public static int BackKeyCount = 0;
    private static CrossApp sCrossApp;
    private static UMShareAPI mShareAPI;

    public static CrossApp get() {
        return sCrossApp;
    }

    public UMShareAPI getmShareAPI() {
        return mShareAPI;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sCrossApp = this;
        iniyoumen();
        initOkGo();
//        initPictureSelect();
    }

    private void iniyoumen() {
        UMConfigure.init(getApplicationContext(), UMConfigure.DEVICE_TYPE_PHONE, null);
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
//        PlatformConfig.setWeixin(Constant.WX_APPID, Constant.WX_APPSECRET);//微信平台的appkey与appsecret
        mShareAPI = UMShareAPI.get(this);

    }

    public void initOkGo() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
        builder.readTimeout(120000, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(120000, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(120000, TimeUnit.MILLISECONDS);   //全局的连接超时时间
        OkGo.getInstance()
                .init(this)
                .addCommonParams(new HttpParams("T", AppConfig.token.get()))
                .setOkHttpClient(builder.build()) //设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setRetryCount(3);

    }
}
