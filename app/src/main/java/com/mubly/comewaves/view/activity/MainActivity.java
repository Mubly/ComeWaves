package com.mubly.comewaves.view.activity;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.lzy.okgo.model.Progress;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.CrossApp;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.common.sharedpreference.AppConfig;
import com.mubly.comewaves.common.utils.CommUtil;
import com.mubly.comewaves.common.utils.ToastUtils;
import com.mubly.comewaves.model.interfaces.CallBackObject;
import com.mubly.comewaves.model.livedatabus.LiveDataBus;
import com.mubly.comewaves.model.model.EventBusEvent;
import com.mubly.comewaves.view.costomview.MyViewPager;
import com.mubly.comewaves.view.fragment.HomeFragment;
import com.mubly.comewaves.view.fragment.IsHadFragment;
import com.mubly.comewaves.view.fragment.MineFragment;
import com.mubly.comewaves.view.fragment.ReleaseFragment;
import com.mubly.comewaves.view.fragment.SearchFragment;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.youth.banner.transformer.DefaultTransformer;
import com.youth.banner.transformer.ScaleInOutTransformer;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {
    /**
     * 首页Activity
     *
     * @return
     */
    @BindView(R.id.main_mypager)
    MyViewPager main_mypager;
    @BindView(R.id.ll_home)
    LinearLayout homeTab;
    @BindView(R.id.ll_live)
    LinearLayout searchTab;
    @BindView(R.id.ll_release)
    LinearLayout releaseTab;
    @BindView(R.id.ll_info)
    LinearLayout isHadTab;
    @BindView(R.id.ll_mine)
    LinearLayout mineTab;

    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.share_btn)
    Button shareBtn;
    @BindView(R.id.iv_tabbar_home_icon)
    ImageView ivTabbarHomeIcon;
    @BindView(R.id.iv_tabbar_live_icon)
    ImageView ivTabbarLiveIcon;
    @BindView(R.id.iv_tabbar_discover_icon)
    ImageView ivTabbarDiscoverIcon;
    @BindView(R.id.iv_tabbar_explore_icon)
    ImageView ivTabbarExploreIcon;
    @BindView(R.id.iv_tabbar_me_icon)
    ImageView ivTabbarMeIcon;
    @BindView(R.id.wait_img)
    ImageView waitImage;
    @BindView(R.id.video_upload_progress_pb)
    ProgressBar mProgressBar;
    HomeFragment homeFragment = new HomeFragment();
    ReleaseFragment releaseFragment = new ReleaseFragment();
    IsHadFragment isHadFragment = new IsHadFragment();
    MineFragment mineFragment = new MineFragment();
    SearchFragment searchFragment = new SearchFragment();

    private List<Fragment> fragmentList = new ArrayList<>();
    RxPermissions rxPermissions = null;
    boolean isLogining;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        rxPermissions = new RxPermissions(this);
        return R.layout.activity_main;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int type = intent.getIntExtra("type", 0);
        main_mypager.setCurrentItem(type);
        if (isLogining) {
            isLogining = !isLogining;
            LiveDataBus.get().with("onpause").setValue(false);
        }
    }

    @Override
    public void initView() {
        super.initView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                waitImage.setVisibility(View.GONE);
            }
        }, 3000);
        ivTabbarHomeIcon.setSelected(true);
        loginBtn.setVisibility(View.GONE);
        shareBtn.setVisibility(View.GONE);
        requestRxPermissions();
        fragmentList.add(homeFragment);
        fragmentList.add(searchFragment);
//        fragmentList.add(videoFragment);
        fragmentList.add(releaseFragment);
        fragmentList.add(isHadFragment);
        fragmentList.add(mineFragment);
        main_mypager.setNoScroll(true);
        main_mypager.setOffscreenPageLimit(5);
        main_mypager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        main_mypager.setCurrentItem(1);
        tabSelect(1);
        main_mypager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    LiveDataBus.get().with("onpause").setValue(false);
                } else {
                    LiveDataBus.get().with("onpause").setValue(true);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void requestRxPermissions() {
        rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (!aBoolean) {
                    finish();
                }
            }

        });
    }

    @OnClick({R.id.ll_home, R.id.ll_live, R.id.ll_release, R.id.ll_info, R.id.ll_mine, R.id.share_btn, R.id.login_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                main_mypager.setCurrentItem(0);
                tabSelect(0);
                break;
            case R.id.ll_live:
                main_mypager.setCurrentItem(1);
                tabSelect(1);
                break;
            case R.id.ll_release:
                main_mypager.setCurrentItem(2);
                tabSelect(2);

                break;
            case R.id.ll_info:
                tabSelect(3);
                main_mypager.setCurrentItem(3);
                break;
            case R.id.ll_mine:
                if (AppConfig.token.get() == null) {
                    LiveDataBus.get().with("onpause").setValue(true);
                    startActivity(new Intent(this, PhoneLoginActivity.class));
                } else {
                    tabSelect(4);
                    main_mypager.setCurrentItem(4);
                }
                break;
            case R.id.share_btn:
                new ShareAction(MainActivity.this).withText("hello")
                        .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                        .setCallback(umShareListener).open();
                break;
            case R.id.login_btn:
                CrossApp.get().getmShareAPI().getPlatformInfo(MainActivity.this, SHARE_MEDIA.WEIXIN, umAuthListener);
                break;
        }

    }

    @Override
    public void initEvent() {
        super.initEvent();
        LiveDataBus.get().with("videoUpload", Double.class).observe(this, new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double progress) {
                if (mProgressBar.getVisibility() == View.GONE) {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
                double current = (progress * 100);
                mProgressBar.setProgress((int) current);
                if (progress == 1) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }


    public class MyViewPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> list;

        public MyViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }

    }


    private UMShareListener umShareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "成功                                        了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this, "失                                            败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "取消                                          了", Toast.LENGTH_LONG).show();

        }
    };
    private UMAuthListener umAuthListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(mContext, "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(mContext, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    void tabSelect(int index) {
        ivTabbarHomeIcon.setSelected(false);
        ivTabbarLiveIcon.setSelected(false);
        ivTabbarDiscoverIcon.setSelected(false);
        ivTabbarExploreIcon.setSelected(false);
        ivTabbarMeIcon.setSelected(false);
        switch (index) {
            case 0:
                ivTabbarHomeIcon.setSelected(true);
                break;
            case 1:
                ivTabbarLiveIcon.setSelected(true);
                break;
            case 2:
                ivTabbarDiscoverIcon.setSelected(true);
                break;
            case 3:
                ivTabbarExploreIcon.setSelected(true);
                break;
            case 4:
                ivTabbarMeIcon.setSelected(true);
                break;
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            CrossApp.BackKeyCount++;
            if (CrossApp.BackKeyCount >= 2) {
//                finish();
                Intent home = new Intent(Intent.ACTION_MAIN);
                home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                home.addCategory(Intent.CATEGORY_HOME);
                startActivity(home);
            } else {
                if (GSYVideoManager.backFromWindowFull(this)) {
                    CrossApp.BackKeyCount--;
                } else {
                    ToastUtils.showToast("再按一次退出程序");
                    ScheduledThreadPoolExecutor mScheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
                    //10秒之后BackKeyCount设置为0
                    mScheduledThreadPoolExecutor.schedule(new MyTask(), 10, TimeUnit.SECONDS);
                }
            }
        }
        return false;
    }

    /**
     * 指定时间后执行task任务
     */
    class MyTask extends TimerTask {
        @Override
        public void run() {
            CrossApp.BackKeyCount = 0;
        }
    }
}
