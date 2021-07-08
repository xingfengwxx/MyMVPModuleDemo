package com.wangxingxing.mvpmoduledemo.base;

/**
 * author : 王星星
 * date : 2021/7/7 18:06
 * email : 1099420259@qq.com
 * description :
 */
public interface IView {

    void showLoading();

    void dismissLoading();

    void showToast(String msg);
}
