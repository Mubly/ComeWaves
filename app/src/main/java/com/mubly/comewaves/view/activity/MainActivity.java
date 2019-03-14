package com.mubly.comewaves.view.activity;

import android.Manifest;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.mubly.comewaves.R;
import com.mubly.comewaves.common.CrossApp;
import com.mubly.comewaves.common.base.BasePresenter;
import com.mubly.comewaves.view.costomview.MyViewPager;
import com.mubly.comewaves.view.fragment.HomeFragment;
import com.mubly.comewaves.view.fragment.IsHadFragment;
import com.mubly.comewaves.view.fragment.MineFragment;
import com.mubly.comewaves.view.fragment.ReleaseFragment;
import com.mubly.comewaves.view.fragment.SearchFragment;
import com.mubly.comewaves.view.fragment.VoidesListFragment;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
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

    HomeFragment homeFragment = new HomeFragment();
    ReleaseFragment releaseFragment = new ReleaseFragment();
    IsHadFragment isHadFragment = new IsHadFragment();
    VoidesListFragment videoFragment = new VoidesListFragment();
    MineFragment mineFragment = new MineFragment();
    SearchFragment searchFragment = new SearchFragment();
    private List<Fragment> fragmentList = new ArrayList<>();
    RxPermissions rxPermissions = null;
    String[] mPermissionList = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_LOGS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.SET_DEBUG_APP,
            Manifest.permission.SYSTEM_ALERT_WINDOW,
            Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.WRITE_APN_SETTINGS};

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
    public void initView() {
        super.initView();
        loginBtn.setVisibility(View.GONE);
        shareBtn.setVisibility(View.GONE);
        requestRxPermissions();
        fragmentList.add(homeFragment);
        fragmentList.add(searchFragment);
//        fragmentList.add(videoFragment);
        fragmentList.add(releaseFragment);
        fragmentList.add(isHadFragment);
        fragmentList.add(mineFragment);
        main_mypager.setOffscreenPageLimit(5);
        main_mypager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        main_mypager.setCurrentItem(0);
        main_mypager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

//                if (TextUtils.isEmpty(AppConfig.token.get()) && (position == 2 || position == 3)) {
//                    LoginActivity.startAction(mContext);
//                } else {
//                    setTextColor(position);
//                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void requestRxPermissions() {
        rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION).subscribe(new Consumer<Boolean>() {
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
                break;
            case R.id.ll_live:
                main_mypager.setCurrentItem(1);
                break;
            case R.id.ll_release:
                startActivity(new Intent(mContext, MessageCreateActivity.class));
//                main_mypager.setCurrentItem(2);
//                rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        if (aBoolean){
//                            //申请的权限全部允许
//                            Toast.makeText(MainActivity.this, "允许了权限!", Toast.LENGTH_SHORT).show();
//                            PictureSelector.create(MainActivity.this)
//                                    .openCamera(PictureMimeType.ofImage())
//                                    .previewVideo(true)
//                                    .videoQuality(0)
//                                    .videoMaxSecond(15)
//                                    .videoMinSecond(5)
//                                    .forResult(PictureConfig.CHOOSE_REQUEST);
//                        }else{
//                            //只要有一个权限被拒绝，就会执行
//                            Toast.makeText(MainActivity.this, "未授权权限，部分功能不能使用", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

                break;
            case R.id.ll_info:
                main_mypager.setCurrentItem(3);
                break;
            case R.id.ll_mine:
//                if (AppConfig.token.get() == null) {
//                    startActivity(new Intent(this, LoginActivity.class));
//                }else
                main_mypager.setCurrentItem(4);
                break;
            case R.id.share_btn:
                new ShareAction(MainActivity.this).withText("hello")
                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                        .setCallback(umShareListener).open();
                break;
            case R.id.login_btn:
             CrossApp.get().getmShareAPI().getPlatformInfo(MainActivity.this, SHARE_MEDIA.WEIXIN, umAuthListener);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    break;
            }
        }
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
            Toast.makeText(MainActivity.this,"成功                                        了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this,"失                                            败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this,"取消                                          了",Toast.LENGTH_LONG).show();

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

            Toast.makeText(mContext, "失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
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
}
