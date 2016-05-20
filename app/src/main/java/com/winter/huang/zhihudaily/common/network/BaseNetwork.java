package com.winter.huang.zhihudaily.common.network;

import com.hele.buyer.common.constant.Constants;
import com.hele.buyer.http.HttpConnection;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Winter on 2016/5/17.
 * Description
 * email:huang.wqing@qq.com
 */
public class BaseNetwork {

    static Retrofit.Builder builder;

    static {
        HttpConnection.initUrl();
        builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(BaseNetworkHook.getOkHttpClient());
    }

    public static <T> T create(Class<T> clazz) {
        return create(clazz, false);
    }

    public static <T> T create(Class<T> clazz, boolean isHttps) {
        Retrofit retrofit = builder
                .baseUrl(isHttps ? Constants.HOST_HTTPS : Constants.HOST_HTTP)
                .build();
        return retrofit.create(clazz);

    }


}
