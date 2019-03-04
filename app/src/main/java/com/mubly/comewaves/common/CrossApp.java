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

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class CrossApp extends Application {
    private static CrossApp sCrossApp;

    public static CrossApp get() {
        return sCrossApp;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sCrossApp = this;
    }
    private void initOkGo() {

//        HttpHeaders headers = new HttpHeaders();
//        headers.put("clientType", "Android");
//        headers.put("T", AppConfig.token.get());
//        headers.put("device-width", String.valueOf(DisplayUtil.getScreenWidth(mContext)/2));
//        headers.put("uuid", DeviceUtils.buildDeviceUUID(mContext));
//        headers.put("device-model", DeviceUtils.getSystemModel(mContext));

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
//                .addCommonHeaders(headers)
                .addCommonParams(new HttpParams("T",AppConfig.token.get()==null?"6ZKJ5aSfMTU1MTUxMDI2OTQz":AppConfig.token.get()))
                .setOkHttpClient(builder.build()) //设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setRetryCount(3);

    }
}
