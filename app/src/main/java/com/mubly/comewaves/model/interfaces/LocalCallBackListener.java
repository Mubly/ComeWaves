package com.mubly.comewaves.model.interfaces;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;

public interface LocalCallBackListener {
    void localBack(AMapLocationClient mlocationClient , AMapLocation mLocation);
}
