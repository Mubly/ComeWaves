package com.mubly.comewaves.common.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.mubly.comewaves.common.CrossApp;
import com.mubly.comewaves.model.interfaces.LocalCallBackListener;

public class GPSLocal {
    private static GPSLocal instance;
    private Context mContext = CrossApp.get();
    private AMapLocation mMapLocation;
    private AMapLocationClient mlocationClient = null;
    //声明mLocationOption对象
    private AMapLocationClientOption mLocationOption = null;
//    private int status = 0;//0初始状态，1，定位中，2，定位结束
    private LocalCallBackListener callBackObject = null;
    private ProgressDialog progressDialog;
    private boolean isShow;

    public GPSLocal getInstance() {
        if (null == instance) {
            instance = new GPSLocal();
            init();
        }
        return instance;
    }

    private GPSLocal() {
    }

    private AMapLocationListener aMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (isShow) {
                hideProgress();
            }
            mMapLocation = aMapLocation;
            if (null != callBackObject && null != aMapLocation) {
                callBackObject.localBack(mlocationClient, aMapLocation);
            }
        }
    };

    public void init() {

        mlocationClient = new AMapLocationClient(mContext);
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
    }

    public void startLocation() {
        if (mlocationClient.isStarted())
            return;
        if (isShow) {
            showDialog();
        }
        mlocationClient.startLocation();

    }

    public void stopLocation() {
        mlocationClient.stopLocation();
    }

    public AMapLocation getLocation() {
//        status = 0;
        return null == mMapLocation ? null : mMapLocation;
    }

    public GPSLocal setLocalBack(LocalCallBackListener callBackObject) {
        this.callBackObject = callBackObject;
        return getInstance();
    }

    public GPSLocal showLoading(boolean isShow) {
        this.isShow = isShow;
        if (null == progressDialog) {
            progressDialog = new ProgressDialog(mContext);//实例化progressDialog
        }
        return getInstance();
    }

    private void showDialog() {
        if (null == progressDialog || progressDialog.isShowing())
            return;
        progressDialog.show();
    }

    private void hideProgress() {
        if (null != progressDialog && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if (null == callBackObject) {
            isShow = false;
        }
    }
}
