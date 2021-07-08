package com.wangxingxing.mvpmoduledemo.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

/**
 * author : 王星星
 * date : 2021/7/7 18:18
 * email : 1099420259@qq.com
 * description : 基类Activity
 */
public abstract class BaseActivity<P extends IPresenter, B extends ViewBinding> extends AppCompatActivity implements IView {

    @Inject
    protected B mBinding;

    @Inject
    protected P mPresenter;

    protected Context context;

    protected WeakReference<Dialog> mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mBinding.getRoot());
        context = this;
        initView();
        initData();
    }

    protected void initView() {

    }

    protected void initData() {

    }

    @Override
    public void showLoading() {
        if (mLoadingDialog == null) {
            Dialog dialog = ProgressDialog.show(context, "", "正在加载...");
            mLoadingDialog = new WeakReference<>(dialog);
        }
    }

    @Override
    public void dismissLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.get().dismiss();
            mLoadingDialog = null;
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
