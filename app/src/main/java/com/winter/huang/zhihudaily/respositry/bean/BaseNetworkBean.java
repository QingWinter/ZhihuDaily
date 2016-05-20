package com.winter.huang.zhihudaily.respositry.bean;

/**
 * Created by Winter on 2016/5/19.
 * Description
 * email:huang.wqing@qq.com
 */
public class BaseNetworkBean<T> {
    private int state;
    private String msg;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
