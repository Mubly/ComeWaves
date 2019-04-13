package com.mubly.comewaves.common.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.mubly.comewaves.model.model.HomeBean;
import com.mubly.comewaves.model.model.ImgItemVo;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DataService extends IntentService {
    public DataService() {
        super("");
    }

    public static void startService(Context context, List<String> datas, String subtype) {
        Intent intent = new Intent(context, DataService.class);
        intent.putExtra("subtype", subtype);
        intent.putExtra("data", (ArrayList<String>) datas);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            return;
        }

        List<String> datas = intent.getStringArrayListExtra("data");
        String subtype = intent.getStringExtra("subtype");
        try {
            handleGirlItemData(datas, subtype);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void handleGirlItemData(List<String> datas, String subtype) throws ExecutionException, InterruptedException {
        if (datas.size() == 0) {
            EventBus.getDefault().post("finish");
            return;
        }
        List<ImgItemVo> imgItemVos = new ArrayList<>();
        for (String data : datas) {
            Bitmap bitmap = Glide.with(this).asBitmap().load(data).submit().get();
            if (bitmap != null) {
                ImgItemVo imgItemVo = new ImgItemVo();
                imgItemVo.width = bitmap.getWidth();
                imgItemVo.height = bitmap.getHeight();
                imgItemVo.subtype = subtype;
                imgItemVos.add(imgItemVo);
            }
        }
        EventBus.getDefault().post(imgItemVos);
    }
}