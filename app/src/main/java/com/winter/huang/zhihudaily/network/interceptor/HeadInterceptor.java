package com.winter.huang.zhihudaily.network.interceptor;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

/**
 * Created by Winter on 2016/5/18.
 * Description
 * email:huang.wqing@qq.com
 */
public class HeadInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Map<String, String> header = new HashMap<>();
        header.put("app_name", "ZhiHuDaily");
        Headers headers = Headers.of(header);
        Request originalRequest = chain.request();
        Request compressedRequest = originalRequest.newBuilder()
                .headers(headers)
//                            .method(originalRequest.method(), gzip(originalRequest.body()))
                .build();
        return chain.proceed(compressedRequest);
    }

    /**
     * Use this if we need to gzip the RequestBody .
     * @param body
     * @return
     */
    private static RequestBody gzip(final RequestBody body) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return body.contentType();
            }

            @Override
            public long contentLength() {
                try {
                    return body.contentLength();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    return -1;
                }
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
                body.writeTo(gzipSink);
                gzipSink.close();
            }
        };
    }
}
