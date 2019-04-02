package com.csmobile.csyd.utils;


import com.csmobile.csyd.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * LOG工具类 默认tag-LOGUTIL
 * Created by tsy on 16/8/15.
 */
public class LogUtils {

    private static final String TAG = "LOGUTIL";
    private static boolean LOG_ENABLE = true;
    private static boolean DETAIL_ENABLE = true;

    /**
     * 设置是否显示Log
     *
     * @param enable true-显示 false-不显示
     */
    public static void setLogEnable(boolean enable) {
        LOG_ENABLE = enable;
    }

//    /**
//     * 设置是否显示详细Log
//     * @param isdetail true-显示详细 false-不显示详细
//     */
//    public static void setLogDetail(boolean isdetail) {
//        DETAIL_ENABLE = isdetail;
//    }

    /**
     * verbose log
     *
     * @param msg log msg
     */
    public static void v(String msg) {
        if (BuildConfig.DEBUG) {
            Logger.v(msg);

        }
    }

    /**
     * verbose log
     *
     * @param tag tag
     * @param msg log msg
     */
    public static void v(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Logger.t(tag).v(msg);
        }
    }

    /**
     * debug log
     *
     * @param msg log msg
     */
    public static void d(String msg) {
        if (BuildConfig.DEBUG) {
            Logger.d(msg);
        }
    }

    /**
     * debug log
     *
     * @param tag tag
     * @param msg log msg
     */
    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Logger.t(tag).d(msg);
        }
    }

    /**
     * info log
     *
     * @param msg log msg
     */
    public static void i(String msg) {
        if (BuildConfig.DEBUG) {
            Logger.i(msg);
        }
    }

    /**
     * info log
     *
     * @param tag tag
     * @param msg log msg
     */
    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Logger.t(tag).i(msg);
        }
    }

    /**
     * warning log
     *
     * @param msg log msg
     */
    public static void w(String msg) {
        if (BuildConfig.DEBUG) {
            Logger.w(msg);
        }
    }

    /**
     * warning log
     *
     * @param msg log msg
     * @param e   exception
     */
    public static void w(String msg, Exception e) {
        if (BuildConfig.DEBUG) {
            Logger.e(e, msg);
        }
    }

    /**
     * warning log
     *
     * @param tag tag
     * @param msg log msg
     */
    public static void w(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Logger.t(tag).w(msg);
        }
    }

    /**
     * warning log
     *
     * @param tag tag
     * @param msg log msg
     * @param e   exception
     */
    public static void w(String tag, String msg, Exception e) {
        if (BuildConfig.DEBUG) {
            Logger.t(tag).e(e, msg);
        }
    }

    /**
     * error log
     *
     * @param msg log msg
     */
    public static void e(String msg) {
        if (BuildConfig.DEBUG) {
            Logger.t("返回>>>").e(msg);
        }
    }

    /**
     * error log
     *
     * @param msg log msg
     * @param e   exception
     */
    public static void e(String msg, Exception e) {
        if (BuildConfig.DEBUG) {
            Logger.e(e, msg);
        }
    }

    /**
     * error log
     *
     * @param tag tag
     * @param msg msg
     */
    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Logger.t(tag).e(msg);
        }
    }

    /**
     * error log
     *
     * @param tag tag
     * @param msg log msg
     * @param e   exception
     */
    public static void e(String tag, String msg, Exception e) {
        if (BuildConfig.DEBUG) {
            Logger.t(tag).e(e, msg);
        }
    }

    public static void json(String json) {
        if (BuildConfig.DEBUG) {
            Logger.t("返回>>>").json(json);
        }
    }

}