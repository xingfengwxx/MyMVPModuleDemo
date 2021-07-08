package com.wangxingxing.mvpmoduledemo.base;

/**
 * author : 王星星
 * date : 2021/7/7 18:05
 * email : 1099420259@qq.com
 * description :
 */
public interface IModel {

    // RxJava 的 Disposable
    // 防止网络/耗时操作导致的内存泄漏
    void onDestroy();
}
