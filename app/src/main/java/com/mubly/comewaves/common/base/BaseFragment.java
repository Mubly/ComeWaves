package com.mubly.comewaves.common.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mubly.comewaves.common.utils.ToastUtils;


import butterknife.ButterKnife;

import static com.mubly.comewaves.common.Constant.NASTTOKENERROR;
import static com.mubly.comewaves.common.Constant.USERFORBID;
import static com.mubly.comewaves.common.Constant.UUIDERROR;


/**
 * Created by waber on 2018/6/25.
 */

public abstract class BaseFragment<P extends BasePresenter<V>, V extends BaseMvpView> extends Fragment implements BaseMvpView {

    protected P mPresenter;

    protected Context mContext;

    protected Handler mHandler;

    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract P createPresenter();

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int getLayoutId();

    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();

        if (mPresenter != null) {
            mPresenter.attachView((V) this);//因为之后所有的子类都要实现对应的View接口
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        mContext = getActivity();
        ButterKnife.bind(this, rootView);
        initView(rootView);
        initEvent();
        mHandler = new Handler(Looper.getMainLooper());
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public void initView(View rootView) {
    }

    public void initData() {
    }

    public void initEvent() {
    }

    @Override
    public ProgressDialog getProgressDialog() {
        if (null == progressDialog) {
            progressDialog = new ProgressDialog(mContext);//实例化progressDialog
        }
        return progressDialog;
    }

    @Override
    public void showProgress(String msg) {

        getProgressDialog().setMessage(msg);//设置进度条加载内容
        if (!progressDialog.isShowing())//如果进度条没有显示
            progressDialog.show();//显示进度条
    }

    @Override
    public void hideProgress() {
        if (getProgressDialog().isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void checkNetCode(int code, String msg) {
        switch (code) {
            case NASTTOKENERROR: //nast-token 失效或过期
                OffsiteLanding("登录过期,请重新登录");
                break;
            case USERFORBID:// 账号禁用
                OffsiteLanding("账号已被禁用");
                break;
            case UUIDERROR://多端登陆
                OffsiteLanding("账号已经在别处登录,请重新登录");
                break;
            default:
                ToastUtils.showToast(msg);
                break;
        }
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
//                            }
//                        });
//                    }
//                })
//                .setWidth(250)
//                .setOutCancel(false)
//                .setAnimStyle(R.style.EnterExitAnimation)
//                .show(getFragmentManager());
    }


}
