package com.mubly.comewaves.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.gyf.barlibrary.ImmersionBar;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.base.BaseMvpView;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.utils.AdaptScreenUtils;


import butterknife.ButterKnife;


public abstract class BaseActivity<P extends BasePresenter<V>, V extends BaseMvpView>
        extends AppCompatActivity implements BaseMvpView {

    public static final int ANIMTOPANDBOTTOM = 1;// 从上至下
    public static final int ANIMLEFTANDRIGHT = 0;// 从左至右
    private ProgressDialog progressDialog;
    protected P mPresenter;

    public Context mContext;
    protected Handler mHandler;
    private int animType = ANIMLEFTANDRIGHT;// activity 进入动画默认为从左至右

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ImmersionBar.with(this).statusBarColor(R.color.black_aph80).init();
        //ImmersionBar.with(this).transparentStatusBar().statusBarDarkFont(true).init();
        //隐藏状态栏、标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //判断是否使用MVP模式
        mContext = this;
        mPresenter = createPresenter();
        if (null != mPresenter) {
            mPresenter.attachView((V) this);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        //子类不需要再设置布局ID,也不需要使用ButterKnife.bind();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        //excuteStatesBar();
        initView();
        initData();
        initEvent();
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        if (animType == ANIMLEFTANDRIGHT) {
//            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        } else if (animType == ANIMTOPANDBOTTOM) {
//            overridePendingTransition(R.anim.slide_bottom_out, 0);
        }
    }

    @Override
    public void finish() {
        super.finish();
        if (animType == BaseActivity.ANIMLEFTANDRIGHT) {
//            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
        } else if (animType == BaseActivity.ANIMTOPANDBOTTOM) {
//            overridePendingTransition(R.anim.slide_bottom_in, 0);
        }

    }

    public void initView() {
    }

    public void initData() {
    }

    public void initEvent() {
    }

    //用于创建Presenter(由子类实现)
    protected abstract P createPresenter();

    //得到当前界面的布局文件id
    protected abstract int getLayoutId();

    @Override
    public void showProgress(String msg) {
        progressDialog = new ProgressDialog(this);//实例化progressDialog
        progressDialog.setMessage(msg);//设置进度条加载内容
        if (!progressDialog.isShowing())//如果进度条没有显示
            progressDialog.show();//显示进度条
    }

    @Override
    public void hideProgress() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            //presenter与view断开连接
            mPresenter.detachView();
        }

        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }

        ImmersionBar.with(this).destroy();
    }

    /**
     * 解决4.4设置状态栏颜色之后，布局内容嵌入状态栏位置问题
     */
    private void excuteStatesBar() {
        ViewGroup mContentView = (ViewGroup) getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置 ContentView 的 FitsSystemWindows,
            // 而是设置 ContentView 的第一个子 View ，预留出系统 View 的空间.
            mChildView.setFitsSystemWindows(true);
        }
    }

    public void setAnimType(int animType) {
        this.animType = animType;
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

    @Override
    public void checkNetCode(int code, String msg) {
//        switch (code) {
//            case NASTTOKENERROR: //nast-token 失效或过期
//                OffsiteLanding("登录过期,请重新登录");
//                break;
//            case USERFORBID:// 账号禁用
//                OffsiteLanding("账号已被禁用");
//                break;
//            case UUIDERROR://多端登陆
//                OffsiteLanding("账号已经在别处登录,请重新登录");
//                break;
//            default:
//                ToastUtils.showToast(msg);
//                break;
//        }
    }

    public void OffsiteLanding(String msg) {
//        NiceDialog.init()
//                .setLayoutId(R.layout.logoutlanding_layout)
//                .setConvertListener(new ViewConvertListener() {
//                    @Override
//                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
//                        holder.setText(R.id.message, msg);
//                        holder.setOnClickListener(R.id.ok, new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                EMClient.getInstance().logout(true);
//                                AppConfig.clearAll();
//                                AppConfig.token.put("");
//                                CrossApp.finishAllActivity();
//                                android.os.Process.killProcess(android.os.Process.myPid());
//                                System.exit(0);
//                                dialog.dismiss();
////                                dealMain();
//
//                            }
//                        });
//                    }
//                })
//                .setWidth(250)
//                .setOutCancel(false)
//                .setAnimStyle(R.style.EnterExitAnimation)
//                .show(getSupportFragmentManager());
    }

    private void dealMain() {
//        Intent intents = new Intent(this, MainActivity.class);
//        startActivity(intents);
    }
    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(super.getResources(),1080);
    }
}
