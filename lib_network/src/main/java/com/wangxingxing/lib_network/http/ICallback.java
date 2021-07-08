package com.wangxingxing.lib_network.http;

/**
 * author : 王星星
 * date : 2021/7/7 16:21
 * email : 1099420259@qq.com
 * description :
 */
public interface ICallback {

    void onSuccess(String result);

    void onFailure(String exception);
}
