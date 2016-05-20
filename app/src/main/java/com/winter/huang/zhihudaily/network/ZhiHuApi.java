package com.winter.huang.zhihudaily.network;

import com.winter.huang.zhihudaily.common.Constants;
import com.winter.huang.zhihudaily.common.ZhiHuApplication;
import com.winter.huang.zhihudaily.network.https.HttpsUtils;
import com.winter.huang.zhihudaily.network.interceptor.HeadInterceptor;

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
 * Created by Winter on 2016/5/16.
 * Description
 * email:huang.wqing@qq.com
 */
public interface ZhiHuApi {

    /**
     * A factory of ZhiHuApi, you do not need to create it every time for network.
     */
    class Factory {

        private static final int DEV_MODE = 0x1;
        private static final int TEST_MODE = 0x2;
        private static final int NORMAL_MODE = 0x3;
        private static final int MODE = DEV_MODE;

        public static <T extends ZhiHuApi> T create(Class<T> clazz) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_HTTP_URL)
                    .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            return retrofit.create(clazz);
        }


        private static OkHttpClient getClient() {
            // log用拦截器
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

            // 开发模式记录整个body
            switch (MODE) {
                case DEV_MODE:
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                    break;
                case TEST_MODE:
                    logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
                    break;
                case NORMAL_MODE:
                    logging.setLevel(HttpLoggingInterceptor.Level.NONE);
                    break;
            }

            // 如果使用到HTTPS，需要创建SSLSocketFactory，并设置到client
            SSLSocketFactory sslSocketFactory = null;
            try {
                sslSocketFactory = HttpsUtils.getSslSocketFactory(
                        null,
                        ZhiHuApplication.getApplication().getAssets().open("starline2.bks"),
                        ""
                );
            } catch (IOException e) {
                e.printStackTrace();
            }

            return new OkHttpClient.Builder()
                    // HeadInterceptor实现了Interceptor，用来往Request Header添加一些业务相关数据，如APP版本，token信息
                    .addInterceptor(new HeadInterceptor())
                    .addInterceptor(logging)
                    // 连接超时时间设置
                    .connectTimeout(10, TimeUnit.SECONDS)
                    // 读取超时时间设置
                    .readTimeout(10, TimeUnit.SECONDS)
                    // 失败重试
                    .retryOnConnectionFailure(true)
                    // 支持Https
                    .sslSocketFactory(sslSocketFactory)
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
        }
    }
}
