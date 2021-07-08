package com.wangxingxing.lib_network.http;

import com.wangxingxing.lib_network.GSON;
import com.wangxingxing.lib_network.utils.ReflectUtils;

/**
 * author : 王星星
 * date : 2021/7/8 11:35
 * email : 1099420259@qq.com
 * description :
 */
public abstract class HttpCallback<Result> implements ICallback {

    @Override
    public void onSuccess(String result) {
        // result就是返回的Json数据
        // 使用Gson对其进行转换，需要知道指定接收数据的泛型的类型
        Class<?> clz = ReflectUtils.analysisClassInfo(this);
        Result resultObj = (Result) GSON.get().fromJson(result, clz);
        onSuccess(resultObj);
    }

    public abstract void onSuccess(Result result);

    @Override
    public void onFailure(String exception) {

    }
}
