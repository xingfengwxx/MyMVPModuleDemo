package com.wangxingxing.lib_network.http;

import java.util.Map;

/**
 * author : 王星星
 * date : 2021/7/7 16:20
 * email : 1099420259@qq.com
 * description : 网络层代理接口
 */
public interface IHttpProcessor {
    void post(String url, Map<String, Object> params, ICallback callback);
}
