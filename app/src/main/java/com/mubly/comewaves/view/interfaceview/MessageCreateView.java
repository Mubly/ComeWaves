package com.mubly.comewaves.view.interfaceview;

import android.content.Context;

import com.mubly.comewaves.common.base.BaseMvpView;

import io.reactivex.disposables.Disposable;

public interface MessageCreateView extends BaseMvpView {
    void addDisposable(Disposable disposable);
    Context getContext();
    void upLoadSuccess();
}
