package com.csmobile.csyd.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.csmobile.csyd.base.BaseApplication;


/**
 * Toast相关方法
 * Created by tsy on 16/8/15.
 */
public class ToastUtils {

    /**
     * 显示short message
     *
     * @param context 全局context
     * @param resId   string string资源id
     */
    public static void showShort(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示short message
     *
     * @param context 全局context
     * @param message 显示msg
     */
    public static void showShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示long message
     *
     * @param context 全局context
     * @param resId   string string资源id
     */
    public static void showLong(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示long message
     *
     * @param context 全局context
     * @param message 显示msg
     */
    public static void showLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static View view_toast;
    public static Toast mToast;

    public static void showToast(String desc) {
        if (view_toast == null) {
            view_toast = Toast.makeText(BaseApplication.getInstance().getApplicationContext(), "", Toast.LENGTH_SHORT).getView();
        }

        if (mToast == null) {
            mToast = new Toast(BaseApplication.getInstance().getApplicationContext());
        }
        if (view_toast != null) {
            mToast.setView(view_toast);
        }
        mToast.setText(desc);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }
}
