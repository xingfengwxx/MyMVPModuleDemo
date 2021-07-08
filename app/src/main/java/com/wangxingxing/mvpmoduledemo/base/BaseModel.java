package com.wangxingxing.mvpmoduledemo.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import io.reactivex.disposables.CompositeDisposable;

/**
 * author : 王星星
 * date : 2021/7/7 18:12
 * email : 1099420259@qq.com
 * description :
 */
public class BaseModel implements IModel, LifecycleObserver {

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    public void onDestroy() {
        mCompositeDisposable.dispose();
        mCompositeDisposable.clear();
        mCompositeDisposable = null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(LifecycleOwner owner) {
        onDestroy();
        owner.getLifecycle().removeObserver(this);
    }
}
