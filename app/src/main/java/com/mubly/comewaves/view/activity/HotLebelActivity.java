package com.mubly.comewaves.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.model.adapter.SmartAdapter;
import com.mubly.comewaves.model.model.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HotLebelActivity extends BaseActivity {


    @BindView(R.id.mapView)
    MapView mMapView;

    @BindView(R.id.hot_label_rv)
    RecyclerView mRecyclerView;
    SmartAdapter smartAdapter;
    List<String> dataList = new ArrayList<>();
    List<Integer> imageList = new ArrayList<>();
    AMap aMap;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    //定位蓝点
    MyLocationStyle myLocationStyle;
    @BindView(R.id.top_tittle)
    TextView topTittle;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapView.onCreate(savedInstanceState);
        aMap = mMapView.getMap();
        iniLocal();
    }

    private void iniLocal() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);
//        myLocationStyle.strokeColor(R.color.blue);
//        myLocationStyle.radiusFillColor(R.color.blue);//设置定位蓝点精度圆圈的填充颜色的方法。
        myLocationStyle.strokeWidth(1);
        aMap.setMyLocationStyle(myLocationStyle);
//        3-19
        aMap.moveCamera(CameraUpdateFactory.zoomTo(19));
        aMap.setMyLocationEnabled(true);
//设置定位回调监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation.getErrorCode() == 0) {
                    topTittle.setText(aMapLocation.getAddress());
//                    CommUtil.ToastU.showToast(mContext, aMapLocation.getAddress());
//可在其中解析amapLocation获取相应内容。
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        });
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.Sport);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        if (null != mLocationClient) {
            mLocationClient.setLocationOption(mLocationOption);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
    }

    @Override
    public void initView() {
        super.initView();

        imageList.add(R.drawable.ishad_1);
        imageList.add(R.drawable.ishad_2);
        imageList.add(R.drawable.ishad_3);
        dataList.add("wjeoif");
        dataList.add("wjeoif");
        dataList.add("wjeoif");
        dataList.add("wjeoif");
        smartAdapter = new SmartAdapter<String>(dataList) {
            @Override
            public int getLayout(int viewType) {
                return R.layout.item_hot_label_layout;
            }

            @Override
            public void dealView(VH holder, String data, int position) {
                Banner banner = (Banner) holder.getChildView(R.id.hot_label_item_banner);
                //设置图片加载器
                banner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                banner.setImages(imageList);
                //banner设置方法全部调用完毕时最后调用
                banner.start();
            }


        };
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(smartAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hot_lebel;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLocationClient.stopLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @OnClick(R.id.top_back_btn)
    public void onViewClicked() {
        finish();
    }
}
