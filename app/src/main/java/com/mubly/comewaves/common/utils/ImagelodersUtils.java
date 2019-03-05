package com.mubly.comewaves.common.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mubly.comewaves.R;

/**
 * Created by PC on 2018/11/2.
 */

public class ImagelodersUtils  {


    /**
     * Glide加载图片
     *
     * @param context
     * @param picUrl
     * @param imageView
     */
    public static void glideLoadImage(Context context, String picUrl, ImageView imageView) {
        Glide.with(context).load(picUrl).apply(new RequestOptions().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).centerCrop().transform(new GlideRoundTransform(context, 4))) //占位图//出错的占位图
                //.override(width,height)//图片显示的分辨率 ，像素值 可以转化为DP再设置
                .into(imageView); //显示在哪个控件中
    }

    /**
     * Glide加载图片
     *
     * @param context
     * @param picUrl
     * @param imageView
     */
//    public static void glideLoadImageWidthAndHeight(Context context, String picUrl, ImageView imageView,int width,int height) {
//        Glide.with(context).load(picUrl).placeholder(R.mipmap.ic_launcher) //占位图
//                .error(R.mipmap.ic_launcher) //出错的占位图
//                .transform(new CenterCrop(context), new GlideRoundTransform(context, 4))
//                .override(width,height)//图片显示的分辨率 ，像素值 可以转化为DP再设置
//                .into(imageView); //显示在哪个控件中
//    }

    /**
     * Glide加载图片
     *
     * @param context
     * @param picUrl
     * @param imageView
     * @param width
     * @param height
     * @param radius
     */
    public static void glideLoadImage(Context context, String picUrl, ImageView imageView, int width, int height, int radius) {
        Glide.with(context).load(picUrl).apply(new RequestOptions().placeholder(R.mipmap.ic_launcher) //占位图
                .error(R.mipmap.ic_launcher).centerCrop() //出错的占位图
                .transform(new GlideRoundTransform(context, radius)).override(width, height))
                //图片显示的分辨率 ，像素值 可以转化为DP再设置)
                .into(imageView); //显示在哪个控件中
    }

//    public static void glideLoadImage(Context context, String picUrl, ImageView imageView, int radius) {
//        Glide.with(context).load(picUrl).placeholder(R.mipmap.ic_launcher) //占位图
//                .error(R.mipmap.ic_launcher) //出错的占位图
//                .transform(new CenterCrop(context), new GlideRoundTransform(context, radius))
//                //.override(width, height)//图片显示的分辨率 ，像素值 可以转化为DP再设置
//                .into(imageView); //显示在哪个控件中
//    }

    public static void glideLoadImage(Context context, String picUrl, ImageView imageView, int radius, int defaultMipId) {
        Glide.with(context).load(picUrl).apply(new RequestOptions().placeholder(defaultMipId) //占位图
                .error(defaultMipId)
                .centerCrop()//出错的占位图
                .transform(new GlideRoundTransform(context, radius)))
                //.override(width, height)//图片显示的分辨率 ，像素值 可以转化为DP再设置
                .into(imageView); //显示在哪个控件中
    }

    public static void glideLoadImageCenter(Context context, String picUrl, ImageView imageView, int radius, int defaultMipId) {
        Glide.with(context).load(picUrl).apply(new RequestOptions().placeholder(defaultMipId) //占位图
                .error(defaultMipId) //出错的占位图
                .centerCrop().transform(new GlideRoundTransform(context, radius)).fitCenter())
                .into(imageView); //显示在哪个控件中
    }
    public static void glideLoadImageCenter(Context context, int picUrl, ImageView imageView, int radius, int defaultMipId) {
        Glide.with(context).load(picUrl).apply(new RequestOptions().placeholder(defaultMipId) //占位图
                .error(defaultMipId) //出错的占位图
                .centerCrop().transform(new GlideRoundTransform(context, radius)).fitCenter())
                .into(imageView); //显示在哪个控件中
    }
    public static void glideLoadImageCenter(Context context, String picUrl, ImageView imageView, int defaultMipId) {
        Glide.with(context).load(picUrl).apply(new RequestOptions().placeholder(defaultMipId) //占位图
                .error(defaultMipId)//出错的占位图
                .fitCenter())
                .into(imageView); //显示在哪个控件中
    }

    /**
     * Created by fengjie on 2016/12/27.
     * <p>
     * 将图片转化为圆角
     * <p>
     * 构造中第二个参数定义半径
     */

//    public static class GlideRoundTransform extends BitmapTransformation {
//
//        private float radius = 0f;
//
//        public GlideRoundTransform(Context context) {
//            this(context, 4);
//        }
//
//        public GlideRoundTransform(Context context, int dp) {
//            super(context);
//            radius = Resources.getSystem().getDisplayMetrics().density * dp;
//
//        }
//
//        @Override
//        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
//            return roundCrop(pool, toTransform);
//        }
//
//        private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
//            if (source == null) return null;
//
//            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
//            if (result == null) {
//                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
//
//            }
//            Canvas canvas = new Canvas(result);
//            Paint paint = new Paint();
//            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
//            paint.setAntiAlias(true);
//            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
//            canvas.drawRoundRect(rectF, radius, radius, paint);
//            return result;
//
//        }
//
//        @Override
//        public String getId() {
//            return getClass().getName() + Math.round(radius);
//        }
//    }

}
