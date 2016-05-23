package com.winter.huang.zhihudaily.common.network.base;

/**
 * Created by Winter on 2016/5/18.
 * Description use this, if the result Json data from server like this
 * {
 * "state":0,
 * "msg":"",
 * "data":{}
 * }
 * email:huang.wqing@qq.com
 */
public class BaseResult<T> {
    private int state;
    private String msg;
    private T data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
