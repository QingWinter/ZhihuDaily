package com.winter.huang.zhihudaily.network;

import com.winter.huang.zhihudaily.respositry.bean.SplashBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Winter on 2016/5/19.
 * Description
 * email:huang.wqing@qq.com
 */
public interface Foo extends ZhiHuApi {
    /**
     * Network method for app, use RxJava Observable
     */

    @GET("start-image/{size}")
    Observable<SplashBean> getSplashImage(@Path("size") String size);

}
