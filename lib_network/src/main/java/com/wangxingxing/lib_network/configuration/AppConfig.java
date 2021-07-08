package com.wangxingxing.lib_network.configuration;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppConfig implements Initializer<AppConfig> {

    private Map<Object, Object> CONFIGS;

    private Context context;

    public Context getContext() {
        return context;
    }

    public static AppConfig getInstance() {
        return Holder.INSTANCE;
    }

    public AppConfig() {
        CONFIGS = new HashMap<>();
    }

    private static class Holder {
        private static final AppConfig INSTANCE = new AppConfig();
    }

    @NonNull
    @Override
    public AppConfig create(@NonNull Context context) {
        this.context = context.getApplicationContext();
        return AppConfig.getInstance();
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.emptyList();
    }

    public final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " is null");
        }
        return (T) value;
    }

    private void checkConfiguration() {
        boolean isFinished = (boolean) CONFIGS.get(ConfigKeys.CONFIG_FINISHED);
        if (!isFinished) {
            throw new RuntimeException("Configuration is not finished!");
        }
    }

    public AppConfig baseUrl(String url) {
        CONFIGS.put(ConfigKeys.BASE_URL, url);
        return this;
    }

    public AppConfig connTimeout(long timeout) {
        CONFIGS.put(ConfigKeys.CONN_TIMEOUT, timeout);
        return this;
    }

    public AppConfig readTimeout(long timeout) {
        CONFIGS.put(ConfigKeys.READ_TIMEOUT, timeout);
        return this;
    }

    public AppConfig writeTimeout(long timeout) {
        CONFIGS.put(ConfigKeys.WRITE_TIMEOUT, timeout);
        return this;
    }

    public void configure() {
        CONFIGS.put(ConfigKeys.CONFIG_FINISHED, true);
    }



}
