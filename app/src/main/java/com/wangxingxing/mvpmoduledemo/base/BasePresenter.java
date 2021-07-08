package com.wangxingxing.mvpmoduledemo.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * author : 王星星
 * date : 2021/7/7 18:07
 * email : 1099420259@qq.com
 * description :
 */
public class BasePresenter<M extends IModel, V extends IView> implements IPresenter, LifecycleObserver {

    protected V mView;

    protected M mModel;

    public BasePresenter(V view, M model) {
        mView = view;
        mModel = model;
        attach();
    }

    @Override
    public void attach() {
        if (mView != null && mView instanceof LifecycleOwner) {
            Lifecycle lifecycle = ((LifecycleOwner) mView).getLifecycle();
            lifecycle.addObserver(this);
            if (mModel != null && mModel instanceof LifecycleOwner) {
                lifecycle.addObserver((LifecycleObserver) mModel);
            }
        }
    }

    @Override
    public void onDestroy() {
        mView = null;
        mModel = null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(LifecycleOwner owner) {
        onDestroy();
        owner.getLifecycle().removeObserver(this);
    }
}
