package com.winter.huang.zhihudaily.common.network.base;

import android.support.annotation.Nullable;


import com.winter.huang.zhihudaily.common.Constants;
import com.winter.huang.zhihudaily.common.ZhiHuApplication;
import com.winter.huang.zhihudaily.common.network.HostType;
import com.winter.huang.zhihudaily.common.network.OkHttpClientHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Winter on 2016/5/20.
 * Description
 * email:huang.wqing@qq.com
 */
public interface BaseNetwork {

    class Factory {

        public static <T> T create(Class<T> clazz) {
            return create(clazz, HostType.HTTP);
        }

        public static <T > T create(Class<T> clazz, HostType type) {
            return create(clazz, type, false);
        }

        public static <T> T create(Class<T> clazz, HostType type, boolean retryOnFailure) {
            Retrofit retrofit =new Retrofit.Builder()
                    .baseUrl(Constants.BASE_HTTP_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(OkHttpClientHelper.getClient(type, retryOnFailure))
                    .build();
            return retrofit.create(clazz);
        }


    }

}
