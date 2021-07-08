package com.wangxingxing.lib_network.di;

import com.blankj.utilcode.util.LogUtils;
import com.wangxingxing.lib_network.configuration.AppConfig;
import com.wangxingxing.lib_network.configuration.ConfigKeys;
import com.wangxingxing.lib_network.http.IHttpProcessor;
import com.wangxingxing.lib_network.http.OkHttpProcessor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class NetworkModule {

    @Provides
    static AppConfig provideAppConfig() {
        return AppConfig.getInstance();
    }

    @Provides
    static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor,
                                            Interceptor interceptor,
                                            AppConfig appConfig) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(appConfig.getConfiguration(ConfigKeys.CONN_TIMEOUT), TimeUnit.SECONDS)
                .readTimeout(appConfig.getConfiguration(ConfigKeys.READ_TIMEOUT), TimeUnit.SECONDS)
                .writeTimeout(appConfig.getConfiguration(ConfigKeys.WRITE_TIMEOUT), TimeUnit.SECONDS)
                .build();
    }

    @Binds
    public abstract IHttpProcessor bindHttpProcessor(OkHttpProcessor httpProcessor);

    @Provides
    static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtils.d(message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    static Interceptor provideInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder builder = request.newBuilder();
                return chain.proceed(builder.build());
            }
        };
        return interceptor;
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofit(OkHttpClient okHttpClient,
                                    AppConfig appConfig) {
        return new Retrofit.Builder()
                .baseUrl(appConfig.<String>getConfiguration(ConfigKeys.BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
