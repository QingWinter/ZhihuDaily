package com.winter.huang.zhihudaily.common;


import com.winter.huang.zhihudaily.common.network.base.BaseResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Winter on 2016/5/17.
 * Description
 * email:huang.wqing@qq.com
 */
public interface PersonalNetwork {

    @FormUrlEncoded
    @POST("buyer/user/find.do")
    Observable<BaseResult<QueryData>> queryUser(@Field("phone") String phone);

    @FormUrlEncoded
    @POST("buyer/user/login.do")
    Observable<BaseResult<UserInfo>> login(@Field("phone") String phone, @Field("pwd") String pwd);

}
