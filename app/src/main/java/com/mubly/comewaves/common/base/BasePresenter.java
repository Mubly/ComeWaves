package com.mubly.comewaves.common.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

public abstract class BasePresenter<V extends BaseMvpView> {

    public V mvpView;

    public void attachView(V view) {
        this.mvpView = view;
    }

    public void detachView() {
        this.mvpView = null;
    }

    /**
     * 判断 view是否为空
     *
     * @return
     */
    public boolean isAttachView() {
        return mvpView != null;
    }

    /**
     * 返回目标view
     *
     * @return
     */
    public V getMvpView() {
        return mvpView;
    }

    public void getLocal(Context context, AMapLocationListener aMapLocationListener) {
        AMapLocationClient mlocationClient;
//声明mLocationOption对象
        AMapLocationClientOption mLocationOption = null;
        mlocationClient = new AMapLocationClient(context);
//初始化定位参数
        mLocationOption = new AMapLocationClientOption();
//设置定位监听
        mlocationClient.setLocationListener(aMapLocationListener);
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
//获取最近3s内精度最高的一次定位结果：
//设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
//设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();
    }

}
