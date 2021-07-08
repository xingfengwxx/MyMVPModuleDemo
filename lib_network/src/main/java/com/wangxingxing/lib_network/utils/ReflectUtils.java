package com.wangxingxing.lib_network.utils;

import android.util.Log;

import com.blankj.utilcode.util.LogUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectUtils {

    // 通过反射获取当前类的泛型参数(类型)
    public static Class<?> analysisClassInfo(Object obj) {
        // getGenericSuperclass：获取当前运行的类的父类类型，有泛型参数
        Type genType = obj.getClass().getGenericSuperclass();
        LogUtils.d("analysisClassInfo: genType=" + genType);
        // ParameterizedType即泛型
        ParameterizedType pType = (ParameterizedType) genType;
        // 获取泛型参数
        Type[] actualTypeArguments = pType.getActualTypeArguments();
        Type paramType = actualTypeArguments[0];
        LogUtils.d("analysisClassInfo: paramType=" + paramType);
        return (Class<?>) paramType;
    }
}
