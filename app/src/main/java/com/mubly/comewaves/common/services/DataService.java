package com.mubly.comewaves.common.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.utils.CommUtil;
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

    private int screenWith = CommUtil.getScreenWidth();

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
            Bitmap bitmap = null;
            try {
                bitmap = Glide.with(this).asBitmap().load(data).submit().get();
            } catch (ExecutionException e) {
                bitmap = Glide.with(this).asBitmap().load(R.drawable.ishad_1).submit().get();
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
                bitmap = Glide.with(this).asBitmap().load(R.drawable.ishad_1).submit().get();
            }

            if (bitmap != null) {
                ImgItemVo imgItemVo = new ImgItemVo();
                imgItemVo.width = (screenWith / 2) - CommUtil.dip2px(this, 12f);
                imgItemVo.height = (bitmap.getHeight() * imgItemVo.width) / bitmap.getWidth();
                imgItemVo.subtype = subtype;
                imgItemVos.add(imgItemVo);
            }
        }
        EventBus.getDefault().post(imgItemVos);
    }
}