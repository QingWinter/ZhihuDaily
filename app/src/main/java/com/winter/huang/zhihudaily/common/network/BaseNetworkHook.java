package com.winter.huang.zhihudaily.common.network;

import android.support.annotation.Nullable;


import com.winter.huang.zhihudaily.common.Constants;
import com.winter.huang.zhihudaily.common.ZhiHuApplication;

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

/**
 * Created by Winter on 2016/5/20.
 * Description
 * email:huang.wqing@qq.com
 */
public class BaseNetworkHook {
    private static final int MODE = ZhiHuApplication.ENV;

    @Nullable
    public static OkHttpClient getOkHttpClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        // 开发模式记录整个body
        switch (MODE) {
            case Constants.Http.Environment.Command.DEV_HOST:
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                break;
            case Constants.Http.Environment.Command.TEST_ENV:
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
                break;
            case Constants.Http.Environment.Command.NORMAL_ENV:
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
                break;
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                // HeadInterceptor实现了Interceptor，Request Header添加一些业务相关数据，如APP版本，token信息
                .addInterceptor(new HeadInterceptor())
                //日志Interceptor，输出日志
                .addInterceptor(loggingInterceptor)
                // 连接超时时间设置
                .connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(10, TimeUnit.SECONDS)
                // 失败重试
                .retryOnConnectionFailure(true)
                // 支持Https
                .sslSocketFactory(getSslSocketFactory())
                // 信任的主机名，返回true表示信任，可以根据主机名和SSLSession判断主机是否信任
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                // 使用host name作为cookie保存的key
                .cookieJar(new CookieJar() {
                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(HttpUrl.parse(url.host()), cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(HttpUrl.parse(url.host()));
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .build();

        return okHttpClient;
    }

    private static SSLSocketFactory getSslSocketFactory() {
        SSLSocketFactory sslSocketFactory = null;
        try {
            sslSocketFactory = SSLSocketFactoryHook.getSslSocketFactory(
                    null,
                    ZhiHuApplication.getApplication().getAssets().open("starline2.bks"),
                    "123@eascs.com"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sslSocketFactory;
    }

}
