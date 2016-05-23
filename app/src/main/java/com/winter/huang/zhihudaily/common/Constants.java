package com.winter.huang.zhihudaily.common;

/**
 * Created by Winter on 2016/5/16.
 * Description
 * email:huang.wqing@qq.com
 */
public class Constants {

    public static final String BASE_HTTP_URL = "http://news-at.zhihu.com/api/4/news/";
    public static final String BASE_HTTPS_URL = "https://news-at.zhihu.com/api/4/news/";

    public class Http {
        public class Environment {
            public class Command {
                public static final int DEV_HOST = 1;
                public static final int TEST_ENV = 2;
                public static final int NORMAL_ENV = 3;
            }
        }
    }
}
