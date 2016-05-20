package com.winter.huang.zhihudaily.util;

import android.util.Log;

import com.winter.huang.zhihudaily.BuildConfig;

/**
 * Created by Winter on 2016/5/18.
 * Description
 * email:huang.wqing@qq.com
 */
public class Logger {

    public static boolean DEBUG = BuildConfig.DEBUG;

    public static void i(String tag,String msg)
    {
        if(DEBUG){
            Log.i(tag,msg);
        }

    }

    public static void i(String tag, String msg,Throwable th)
    {
        if(DEBUG){
            Log.i(tag,msg,th);
        }

    }


    public static void d(String tag,String msg)
    {
        if(DEBUG){
            Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg,Throwable th)
    {
        if(DEBUG){
            Log.d(tag, msg, th);
        }
    }

    public static void v(String tag,String msg)
    {
        if(DEBUG){
            Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg,Throwable th)
    {
        if(DEBUG){
            Log.v(tag, msg, th);
        }
    }


    public static void w(String tag,String msg)
    {
        if(DEBUG){
            Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg,Throwable th)
    {
        if(DEBUG){
            Log.w(tag, msg, th);
        }
    }

    public static void e(String tag,String msg)
    {
        if(DEBUG){
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg,Throwable th)
    {
        if(DEBUG){
            Log.w(tag, msg, th);
        }
    }
}
