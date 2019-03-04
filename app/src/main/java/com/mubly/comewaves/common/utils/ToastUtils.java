package com.mubly.comewaves.common.utils;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.CrossApp;


/**
 * Created by android_ls on 2016/12/26.
 */

public final class ToastUtils {

    public static void showToast(String message) {
        if(TextUtils.isEmpty(message)) {
            return;
        }
        Toast toast = Toast.makeText(CrossApp.get().getApplicationContext(),
                message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showToast(int resId) {
        Toast toast = Toast.makeText(CrossApp.get().getApplicationContext(),
                CrossApp.get().getString(resId), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showToast(String message, int py) {
        Toast toast = Toast.makeText(CrossApp.get().getApplicationContext(),
                message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, py);
        toast.show();
    }

    public static void showLengthToast(String message) {
        if(TextUtils.isEmpty(message)) {
            return;
        }
        Toast toast = Toast.makeText(CrossApp.get().getApplicationContext(),
                message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
//
//    // 点赞Toast
//    public static void showLikeToast(String message) {
//        if(TextUtils.isEmpty(message)) {
//            return;
//        }
//        Toast toast = Toast.makeText(CrossApp.get().getApplicationContext(), message, Toast.LENGTH_SHORT);
//        View layout = View.inflate(CrossApp.get().getApplicationContext(), R.layout.layout_like_toast,null);
//        TextView content = layout.findViewById(R.id.tv_content);
//        content.setText(message);
//        toast.setView(layout);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.show();
//    }


}
