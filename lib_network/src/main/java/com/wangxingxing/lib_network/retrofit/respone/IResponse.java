package com.wangxingxing.lib_network.retrofit.respone;

/**
 * author : 王星星
 * date : 2021/7/7 17:20
 * email : 1099420259@qq.com
 * description :
 */
public interface IResponse<T> {

    T getData();

    String getMsg();

    String getCode();

    boolean isSuccess();
}
