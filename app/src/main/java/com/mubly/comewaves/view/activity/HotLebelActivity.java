package com.mubly.comewaves.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.amap.api.maps2d.MapFragment;
import com.amap.api.maps2d.MapView;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.model.adapter.SmartAdapter;
import com.mubly.comewaves.model.model.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotLebelActivity extends BaseActivity {


    @BindView(R.id.mapView)
    MapView mMapView;

    @BindView(R.id.hot_label_rv)
    RecyclerView mRecyclerView;
    SmartAdapter smartAdapter;
    List<String> dataList = new ArrayList<>();
    List<Integer> imageList=new ArrayList<>();

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapView.onCreate(savedInstanceState);

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
                Banner banner= (Banner) holder.getChildView(R.id.hot_label_item_banner);
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
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
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

}
