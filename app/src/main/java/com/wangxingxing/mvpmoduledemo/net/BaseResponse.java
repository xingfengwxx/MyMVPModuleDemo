package com.wangxingxing.mvpmoduledemo.net;

import com.wangxingxing.lib_network.retrofit.respone.IResponse;

/**
 * author : 王星星
 * date : 2021/7/8 11:04
 * email : 1099420259@qq.com
 * description :
 */
public class BaseResponse<T> implements IResponse<T> {

    private String success;
    private T result;

    @Override
    public T getData() {
        return result;
    }

    @Override
    public String getMsg() {
        return "";
    }

    @Override
    public String getCode() {
        return "0x00";
    }

    @Override
    public boolean isSuccess() {
        return "1".equals(success);
    }
}
