package com.wangxingxing.lib_network.retrofit.respone;

import com.wangxingxing.lib_network.retrofit.exception.ApiException;
import com.wangxingxing.lib_network.utils.ReflectUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * author : 王星星
 * date : 2021/7/7 17:22
 * email : 1099420259@qq.com
 * description : 获取到IResponse后，判断请求结果，并获取data返回
 *               Observable<IResponse<T>> ->> Observable<T>
 */
public class ResponseTransformer<T> implements ObservableTransformer<IResponse<T>, T> {

    private final CompositeDisposable mCompositeDisposable;

    public ResponseTransformer(CompositeDisposable compositeDisposable) {
        mCompositeDisposable = compositeDisposable;
    }

    @NonNull
    @Override
    public ObservableSource<T> apply(@NonNull Observable<IResponse<T>> upstream) {
        return upstream
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mCompositeDisposable.add(disposable);
                    }
                })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends IResponse<T>>>() {
                    @Override
                    public ObservableSource<? extends IResponse<T>> apply(@NonNull Throwable throwable) throws Exception {
                        // 出现系统异常后统一处理
                        return Observable.error(ApiException.handleException(throwable));
                    }
                })
                .flatMap(new Function<IResponse<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(@NonNull IResponse<T> response) throws Exception {
                        // 业务逻辑判断
                        if (response.isSuccess()) {
                            // 接口请求，有时候业务成功了但是没有具体的Data
                            if (response.getData() != null) {
                                return Observable.just(response.getData());
                            } else {
                                try {
                                    // IResponse<User> -> User.Class
                                    // 创建的这个Data肯定是没有实际作用的，但是要继续发射的情况下就不能为空
                                    Class<?> clz = ReflectUtils.analysisClassInfo(response);
                                    T obj = (T) clz.newInstance();
                                    return Observable.just(obj);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        return Observable.error(new ApiException(response.getCode(), response.getMsg()));
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <U> ResponseTransformer<U> obtain(CompositeDisposable compositeDisposable) {
        return new ResponseTransformer<>(compositeDisposable);
    }
}
