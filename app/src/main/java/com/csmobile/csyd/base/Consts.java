package com.csmobile.csyd.base;


import com.csmobile.csyd.BuildConfig;

/**
 * 常量定义设置类，不允许放其他数据
 *
 * @author zuowenhua
 */

public final class Consts {
    /**
     * API地址
     */
    public static String APP_HOST = BuildConfig.host;//baseURL
    public final static boolean DEBUG = BuildConfig.DEBUG;
    public final static String APPSP = "YDAPPSP";//app存储
    public final static String RESCODE_SUCCESS = "00";//为成功，其他失败

}
