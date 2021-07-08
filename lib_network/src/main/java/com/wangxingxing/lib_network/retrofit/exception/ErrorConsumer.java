package com.wangxingxing.lib_network.retrofit.exception;

import io.reactivex.functions.Consumer;

/**
 * author : 王星星
 * date : 2021/7/7 17:18
 * email : 1099420259@qq.com
 * description :
 */
public abstract class ErrorConsumer implements Consumer<Throwable> {

    @Override
    public void accept(Throwable throwable) throws Exception {
        ApiException ex;
        if (throwable instanceof  ApiException) {
            ex = (ApiException) throwable;
        } else {
            ex = ApiException.handleException(throwable);
        }

        error(ex);
    }

    protected abstract void error(ApiException ex);
}
