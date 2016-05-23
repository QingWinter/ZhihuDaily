package com.winter.huang.zhihudaily.network;

import com.winter.huang.zhihudaily.common.Constants;
import com.winter.huang.zhihudaily.common.network.HostType;
import com.winter.huang.zhihudaily.common.network.OkHttpClientHelper;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Winter on 2016/5/16.
 * Description
 * email:huang.wqing@qq.com
 */
public interface ZhiHuApi {

    /**
     * A factory of ZhiHuApi, you do not need to create it every time for network.
     */
    class Factory {

        private static final Retrofit.Builder builder;

        static {
            builder = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_HTTP_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        }

        public static <T extends ZhiHuApi> T create(Class<T> clazz) {
            return create(clazz, HostType.HTTP);
        }

        public static <T extends ZhiHuApi> T create(Class<T> clazz, HostType type) {
            return create(clazz, type, false);
        }

        public static <T extends ZhiHuApi> T create(Class<T> clazz, HostType type, boolean retryOnFailue) {
            Retrofit retrofit = builder
                    .client(OkHttpClientHelper.getClient(type, retryOnFailue))
                    .build();
            return retrofit.create(clazz);
        }


    }
}
