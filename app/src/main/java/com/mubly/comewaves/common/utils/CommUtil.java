package com.mubly.comewaves.common.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.mubly.comewaves.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * 所有的工具类集合
 */
public class CommUtil {

    /**
     * 判断是否有网络
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable() && mNetworkInfo.isConnected();
            }
        }
        return false;
    }


    /**
     * 获取mipmap图标
     *
     * @param context
     * @param mipmapId
     * @return
     */
    public static Drawable getDrawableToMipmap(Context context, int mipmapId) {
        return context.getResources().getDrawable(mipmapId);
    }

    /**
     * 获取XML中字符串String.xml
     *
     * @return
     */
    public static String getStringToStringXml(Context context, int stringId) {
        if (stringId == 0) return "";
        return context.getResources().getString(stringId);
    }

    public static int getPxToDimenXml(Context context, int dimenId) {
        return context.getResources().getDimensionPixelSize(dimenId);
    }

    public static int getPxToColorXml(Context context, int colorId) {
        return context.getResources().getColor(colorId);
    }


    /**
     * 设置TextView文本
     *
     * @param textView
     * @param tv
     */
    public static void textViewTv(TextView textView, String tv) {
        textView.setText(tv);
    }

    /**
     * 根据Xml设置TextView文本
     *
     * @param context
     * @param textView
     * @param tvToXml
     */
    public static void textTextToStringXml(Context context, TextView textView, int tvToXml) {
        textView.setText(getStringToStringXml(context, tvToXml));
    }

    /**
     * 设置ImageView图标
     *
     * @param imageView
     * @param ivToMipmapXml
     */
    public static void imageViewIcon(ImageView imageView, int ivToMipmapXml) {
        if (imageView.getVisibility() == View.GONE) {
            imageView.setVisibility(View.VISIBLE);
        }
        imageView.setImageResource(ivToMipmapXml);
    }


    /**
     * 数组、判断数组大小
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> boolean listSize(List<T> list) {
        if (list == null || list.size() == 0)
            return true;
        return false;
    }

    /**
     * Toast多次点击只显示一次
     */
    public static class ToastU {
        private static Context context = null;
        private static Toast toast = null;

        public static void showToast(Context context, String text) {
            if (toast == null) {
                toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            } else {
                toast.setText(text);
                toast.setDuration(Toast.LENGTH_SHORT);
            }
            toast.show();
        }
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        //int height = dm.heightPixels;       // 屏幕高度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        //int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        //int screenHeight = (int) (height / density);// 屏幕高度(dp)
        return screenWidth;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        //int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        //int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        //int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        int screenHeight = (int) (height / density);// 屏幕高度(dp)
        return screenHeight;
    }

    /**
     * 日期转换
     *
     * @param str
     * @return
     */
    public static String formatDate(String str) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sf2 = new SimpleDateFormat("MM-dd");
        String formatStr = "";
        try {
            formatStr = sf2.format(sf1.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatStr;
    }

    /**
     * 编辑对话框
     *
     * @param context
     * @param layoutId
     * @param bgColor
     * @return
     */
    public static Dialog showEditDialog(Context context, int layoutId, int bgColor) {
        View view = LayoutInflater.from(context).inflate(layoutId, null);
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawableResource(bgColor);
        //dialog.show();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.90);    //宽度设置为屏幕的0.5
        p.height = (int) (displayHeight * 0.38);    //宽度设置为屏幕的0.5
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.getWindow().setAttributes(p);     //设置生效
        return dialog;
    }

    /**
     * 对话框
     *
     * @param context
     * @param layoutId
     * @param bgColor
     * @param widthPercent
     * @param heightPercent
     * @return
     */
    public static Dialog showEditDialog(Context context, int layoutId, int bgColor, double widthPercent, double heightPercent) {
        View view = LayoutInflater.from(context).inflate(layoutId, null);
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawableResource(bgColor);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.width = (int) (displayWidth * widthPercent);    //宽度设置为屏幕的0.5
        p.height = (int) (displayHeight * heightPercent);    //宽度设置为屏幕的0.5
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.getWindow().setAttributes(p);     //设置生效
        return dialog;
    }

    public static Dialog showEditDialog(Context context, int layoutId, int bgColor, double widthPercent, double heightPercent, boolean isCancel) {
        View view = LayoutInflater.from(context).inflate(layoutId, null);
        Dialog dialog = new Dialog(context);
        dialog.setCancelable(isCancel);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawableResource(bgColor);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.width = (int) (displayWidth * widthPercent);    //宽度设置为屏幕的0.5
        p.height = (int) (displayHeight * heightPercent);    //宽度设置为屏幕的0.5
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.getWindow().setAttributes(p);     //设置生效
        return dialog;
    }

    public static Dialog showEditDialog(Context context, int layoutId, int bgColor, boolean isCancel) {
        View view = LayoutInflater.from(context).inflate(layoutId, null);
        Dialog dialog = new Dialog(context);
        dialog.setCancelable(isCancel);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawableResource(bgColor);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.getWindow().setAttributes(p);     //设置生效
        return dialog;
    }


    /**
     * 省市区对话框
     *
     * @param context
     * @param layoutId
     * @param bgColor
     * @param widthPercent
     * @param heightPercent
     * @return
     */
    public static Dialog showProvCityAreaWheelDialog(Context context, int layoutId, int bgColor, double widthPercent, double heightPercent) {
        View view = LayoutInflater.from(context).inflate(layoutId, null);
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawableResource(bgColor);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.gravity = Gravity.BOTTOM;
        p.width = (int) (displayWidth * widthPercent);    //宽度设置为屏幕的0.5
        p.height = (int) (displayHeight * heightPercent);    //宽度设置为屏幕的0.5
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.getWindow().setAttributes(p);     //设置生效
        return dialog;
    }

    /**
     * 设置TextView 左
     *
     * @param context
     * @param textView
     * @param mipmapId
     */
    public static void setLeftCompoundDrawables(Context context, TextView textView, int mipmapId) {
        Drawable drawableLeft = CommUtil.getDrawableToMipmap(context, mipmapId);
        textView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
    }

    /**
     * 存储对象
     * /data/data/<package name>/files
     *
     * @param context
     * @param ser
     * @param file
     * @return
     */
    public static boolean saveObject(Context context, Serializable ser, String file) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = context.openFileOutput(file, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ser);
            oos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                oos.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取对象
     *
     * @param context
     * @param file
     * @return
     */
    public static Serializable readObject(Context context, String file) {
        if (!isExistDataCache(context, file))
            return null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = context.openFileInput(file);
            ois = new ObjectInputStream(fis);
            return (Serializable) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            // 反序列化失败 - 删除缓存文件
            if (e instanceof InvalidClassException) {
                File data = context.getFileStreamPath(file);
                data.delete();
            }
        } finally {
            try {
                ois.close();
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 判断缓存是否存在
     *
     * @param cachefile
     * @return
     */
    public static boolean isExistDataCache(Context context, String cachefile) {
        if (context == null)
            return false;
        boolean exist = false;
        File data = context.getFileStreamPath(cachefile);
        if (data.exists())
            exist = true;
        return exist;
    }

    /**
     * 验证手机号码是否合法 * 176, 177, 178; * 180, 181, 182, 183, 184, 185, 186, 187, 188, 189; * 145, 147; * 130, 131, 132, 133, 134, 135, 136, 137, 138, 139; * 150, 151, 152, 153, 155, 156, 157, 158, 159;
     * * * "13"代表前两位为数字13, * "[0-9]"代表第二位可以为0-9中的一个, * "[^4]" 代表除了4 * "\\d{8}"代表后面是可以是0～9的数字, 有8位。
     */
    public static boolean isMobileNumber(String mobiles) {
        String telRegex = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
        return !isEmpty(mobiles) && mobiles.matches(telRegex);
    }

    /**
     * 字符串为空判断
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        return s == null || "".equals(s) || "null".equals(s) || s.trim().length() == 0;
    }

    public static String getStr(String s) {
        try {
            if (isEmpty(s)) return " ";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 手机号码用空格隔开，例如：133 2569 7536
     *
     * @return
     */
    public static String dealPhoneNumber(String phoneNum) {
        StringBuilder builder = new StringBuilder();
        if (!isEmpty(phoneNum)) {
            int phoneNumLength = phoneNum.length();
            for (int i = 0; i < phoneNumLength; i++) {
                builder.append(phoneNum.charAt(i));
                if (i == 2 || i == 6) {
                    builder.append(" ");
                }
            }
        }
        return builder.toString();
    }

    /**
     * 当前字符串是否匹配当前正则表达式
     *
     * @param matchStr
     * @param regex
     * @return
     */
    public static boolean isMatch(String matchStr, String regex) {
        return !isEmpty(matchStr) && matchStr.matches(regex);
    }

    /**
     * String类型转成Int类型
     *
     * @param s
     * @return
     */
    public static int stringToInt(String s) {
        return Integer.valueOf(s).intValue();
    }

    /**
     * String类型转成Double类型
     *
     * @param s
     * @return
     */
    public static double stringToDouble(String s) {
        return Double.parseDouble(s);
    }

    /**
     * 银行卡替换，保留后四位
     * <p>
     * 如果银行卡号为空 或者 null ,返回null ；否则，返回替换后的字符串；
     *
     * @param bankCard 银行卡号
     * @return
     */
    public static String bankCardReplaceWith(String bankCard) {

        if (bankCard.isEmpty() || bankCard == null) {
            return null;
        } else {
            return replaceAction(bankCard, "(?<=\\d{0})\\d(?=\\d{4})");
        }
    }

    /**
     * 银行卡替换
     *
     * @param username username
     * @param regular  正则
     * @return
     */
    private static String replaceAction(String username, String regular) {
        return username.replaceAll(regular, "*");
    }

    /**
     * 一种格式日期转换成另外一种格式日期字符串
     * 例如：201-10-18转成10月10日
     *
     * @param timeFormat
     * @param timeS
     * @return
     */
    public static String timeFormat(String timeFormat, String otherTimeFormat, String timeS) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(otherTimeFormat);
            Date date = new Date(dateToStamp(timeS, timeFormat));
            return format.format(date).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 日期转时间戳
     *
     * @param s
     * @return
     */
    public static long dateToStamp(String s, String timeFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat);
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 时间戳转日期
     *
     * @param s
     * @return
     */
    public static String stampTodate(int s ){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        long lt = s;
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 保留2位小数
     *
     * @param s
     * @return
     */
    public static String keepTwo(String s) {
//        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
//        String distanceString = decimalFormat.format(Double.parseDouble(s));//format 返回的是字符串
        return s;
    }

    public static String keepOne(String s) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String distanceString = decimalFormat.format(Double.parseDouble(s));//format 返回的是字符串
        return distanceString;
    }


    /**
     * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
     * 价格、每隔3位加入逗号（，号）
     *
     * @param str 需要处理的字符串
     * @return 处理完之后的字符串
     */

    public static String priceAddComma(String str) {
        DecimalFormat decimalFormat = new DecimalFormat(",###");
        return decimalFormat.format(Double.parseDouble(str));
    }


    public static void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            view.requestFocus();
            imm.showSoftInput(view, 0);
        }
    }

    public static void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void toggleSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
        }
    }

    /**
     * 获取应用版本号
     *
     * @param context
     * @return
     */
    public static int getPackageCode(Context context) {
        PackageManager manager = context.getPackageManager();
        int code = 0;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            code = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * 获取应用版本号
     *
     * @param context
     * @return
     */
    public static String getPackageName(Context context) {
        PackageManager manager = context.getPackageManager();
        String name = "";
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            name = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * 获取版本号内容
     *
     * @param content
     * @return
     */
    public static String getUpateApkContent(String content) {
        try {
            String[] s = content.split("#");
            StringBuffer sb = new StringBuffer();
            int sLength = s.length;
            for (int i = 0; i < sLength; i++) {
                sb.append("-");
                sb.append(s[i]);
                if (i < sLength - 1)
                    sb.append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * List集合大小
     *
     * @param tList
     * @param <T>
     * @return
     */
    public static <T> boolean listEmpty(List<T> tList) {
        if (tList == null || tList.size() == 0)
            return true;
        return false;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


//    public static void dealFlipperView(ViewFlipper mVp, final List<HomeIndexBean.NoticeListBean> mList, final Context mContext, final CallBackObj callBack) {
//        for (int i = 0; i < mList.size(); i++) {
//            TextView view = new TextView(mContext);
//            ViewFlipper.LayoutParams params = new ViewFlipper.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            params.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
//            view.setLayoutParams(params);
//            view.setText(mList.get(i).getNoticeTitle());
//            view.setTextSize(12);
//            view.setEllipsize(TextUtils.TruncateAt.END);
//            view.setMaxEms(20);
//            view.setSingleLine();
//            view.setTextColor(mContext.getResources().getColor(R.color.color_999999));
//            final int finalI = i;
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    callBack.callBack(mList.get(finalI));
//                }
//            });
//            mVp.addView(view);
//        }
//        //是否自动开始滚动
//        mVp.setAutoStart(true);
//        //滚动时间
//        mVp.setFlipInterval(2000);
//        if (mList.size() > 1) {
//            //开始滚动
//            mVp.startFlipping();
//        }
//        //出入动画
//        mVp.setOutAnimation(mContext, R.anim.push_up_out);
//        mVp.setInAnimation(mContext, R.anim.push_down_in);
//    }


    /**
     * 方法描述 隐藏手机号中间位置字符，显示前三后三个字符
     *
     * @param phoneNo
     * @return
     * @author yaomy
     * @date 2018年4月3日 上午10:38:51
     */
    public static String hidePhoneNo(String phoneNo) {
        /*if(StringUtils.isBlank(phoneNo)) {
            return phoneNo;
        }*/

        int length = phoneNo.length();
        int beforeLength = 3;
        int afterLength = 4;
        //替换字符串，当前使用“*”
        String replaceSymbol = "*";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            if (i < beforeLength || i >= (length - afterLength)) {
                sb.append(phoneNo.charAt(i));
            } else {
                sb.append(replaceSymbol);
            }
        }

        return sb.toString();
    }

//    // 获取渠道号
//    public static String getChannel(Context mContext) {
//        String channel = "未知";
//        if (null == mContext) {
//            mContext = App.getAppContext();
//        }
//        try {
//            if (null == mContext) {
//                return channel;
//            }
//            PackageManager packageManager = mContext.getPackageManager();
//            if (null != packageManager) {
//                ApplicationInfo info = packageManager.getApplicationInfo(mContext.getPackageName(), PackageManager.GET_META_DATA);
//                if (info != null && info.metaData != null) {
//                    String metaData = info.metaData.getString("UMENG_CHANNEL");
//                    if (null != metaData) {
//                        channel = metaData;
//                    }
//                }
//            }
//
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//            channel = "未知";
//        }
//        return channel;
//    }

    public static String getMoneyType(String string) {
        // 把string类型的货币转换为double类型。
        Double numDouble = Double.parseDouble(string);
        // 想要转换成指定国家的货币格式
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        // 把转换后的货币String类型返回
        String numString = format.format(numDouble);
        return numString;
    }

    public static String getDateFromDetailTimeAndDate(String string) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String nowDate = null;
        try {
            date = dateFormat.parse(string);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            nowDate = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
            nowDate = string;
        }

        return nowDate;

    }

    /*
     * 如果是小数，保留两位，非小数，保留整数
     * @param number
     */
    public static String getDoubleString(double number) {
        String numberStr;
        if (((int) number * 1000) == (int) (number * 1000)) {
            //如果是一个整数
            numberStr = String.valueOf((int) number);
        } else {
            DecimalFormat df = new DecimalFormat("######");
            numberStr = df.format(number);
        }
        return numberStr;
    }


}
