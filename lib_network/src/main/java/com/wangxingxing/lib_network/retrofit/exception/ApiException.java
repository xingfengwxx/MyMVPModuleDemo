package com.wangxingxing.lib_network.retrofit.exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * author : 王星星
 * date : 2021/7/7 17:13
 * email : 1099420259@qq.com
 * description : 异常解析
 */
public class ApiException extends Exception {

    private static final int UNKNOWN_ERROR = 1000;
    private static final int PARSE_ERROR = 1001;
    private static final int NETWORK_ERROR = 1002;

    private String code;

    private String errorMsg;

    public ApiException(String code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    // 处理非业务逻辑错误的系统异常
    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ApiException(String.valueOf(PARSE_ERROR), "数据解析异常");
            return ex;
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException
                || e instanceof SocketTimeoutException) {
            ex = new ApiException(String.valueOf(NETWORK_ERROR), "网络请求异常");
            return ex;
        } else {
            ex = new ApiException(String.valueOf(UNKNOWN_ERROR), "未知异常");
            return ex;
        }
    }
}
