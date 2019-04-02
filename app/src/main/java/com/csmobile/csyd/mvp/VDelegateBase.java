package com.csmobile.csyd.mvp;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.csmobile.csyd.cache.SharedPref;
import com.csmobile.csyd.net.NetError;
import com.csmobile.csyd.utils.ToastUtils;
import com.tapadoo.alerter.Alerter;


/**
 * ClaseName：${NAME}
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2016/12/29 10:32
 * Modified By：
 * Fixtime：2016/12/29 10:32
 * FixDescription：
 */

public class VDelegateBase implements VDelegate {

    private Context context;
    private MaterialDialog.Builder builder;
    private MaterialDialog dialog;
    private static Toast toast;

    private VDelegateBase(Context context) {
        this.context = context;
    }

    public static VDelegate create(Context context) {
        return new VDelegateBase(context);
    }


    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destory() {

    }

    @Override
    public void visible(boolean flag, View view) {
        if (flag) view.setVisibility(View.VISIBLE);
    }

    @Override
    public void gone(boolean flag, View view) {
        if (flag) view.setVisibility(View.GONE);
    }

    @Override
    public void inVisible(View view) {
        view.setVisibility(View.INVISIBLE);
    }

    @Override
    public void toastShort(String msg) {
        dismissLoading();
        ToastUtils.showToast(msg);
    }

    @Override
    public void toastLong(String msg) {
        dismissLoading();
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(),
                    msg,
                    Toast.LENGTH_LONG);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    @Override
    public void showSnackbar(View view, String msg, String tip, View.OnClickListener listener) {
        dismissLoading();
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
                .setAction(tip, listener)
                .show();
    }

    @Override
    public void showNoticeDialog(String msg, MaterialDialog.SingleButtonCallback callback) {
        /*android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(msg);
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", listener);
        builder.show();*/
        dismissLoading();
        new MaterialDialog.Builder(context)
                .title("提示")
                .content(msg)
                .positiveText("确定")
                .negativeText("取消")
                .onAny(callback)
                .show();
    }

    @Override
    public void showErrorDialog(String msg) {
        dismissLoading();
        new MaterialDialog.Builder(context)
                .title("提示")
                .content(msg)
                .positiveText("确定")
                .show();
    }

    @Override
    public void showErrorDialog(String msg, MaterialDialog.SingleButtonCallback callback) {
        dismissLoading();
        new MaterialDialog.Builder(context)
                .title("提示")
                .content(msg)
                .positiveText("确定")
                .onAny(callback)
                .show();
    }

    @Override
    public void showLoading(String msg) {
        dismissLoading();
        builder = new MaterialDialog.Builder(context)
                .content(msg)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .canceledOnTouchOutside(false);
        dialog = builder.build();
        dialog.show();
    }

    @Override
    public void showLoading() {
        dismissLoading();
        builder = new MaterialDialog.Builder(context)
                .content("加载中...")
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .canceledOnTouchOutside(false);
        dialog = builder.build();
        dialog.show();
    }

    @Override
    public void dismissLoading() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    private MaterialDialog.Builder getDialog() {
        if (builder == null) {
            builder = new MaterialDialog.Builder(context);
            dialog = builder.build();
        }
        return builder;
    }

    @Override
    public void showError(NetError error) {
        dismissLoading();
        showErrorDialog(getErrorType(error));
    }

    @Override
    public String getErrorType(NetError error) {
        String msg = "";
        if (error != null) {
            switch (error.getType()) {
                case NetError.ParseError:
                    msg = "数据解析失败";
                    break;

                case NetError.AuthError:
                    msg = "身份验证失败";
                    break;

                case NetError.BusinessError:
                    msg = "业务异常";
                    break;

                case NetError.NoConnectError:
                    msg = "网络无连接";
                    break;

                case NetError.NoDataError:
                    msg = "数据为空";
                    break;

                case NetError.TimeOutError:
                    msg = "连接超时";
                    break;

                case NetError.OtherError:
                    msg = "连接失败";
                    break;

                case NetError.ConnectError:
                    msg = "连接服务器失败";
                    break;
            }

        }
        return msg;
    }

    @Override
    public void showAlerter(String title, final String msg) {
        if (context != null) {
            SharedPref.getInstance(context).putBoolean("showMsg", false);
            if (title == null || title != null && title.trim().length() == 0) {
                Alerter.create((Activity) context)
                        .setText(msg)
                        .enableSwipeToDismiss()
                        .setDuration(4000)
                        .show();
            } else {
                Alerter.create((Activity) context)
                        .setTitle(title)
                        .setText(msg)
                        .enableSwipeToDismiss()
                        .setDuration(4000)
                        .show();
            }
        }
    }

    @Override
    public void showAlerter(String title, String msg, View.OnClickListener listener) {
        if (context != null) {
            SharedPref.getInstance(context).putBoolean("showMsg", false);
            if (title == null || title != null && title.trim().length() == 0) {
                Alerter.create((Activity) context)
                        .setText(msg)
                        .enableSwipeToDismiss()
                        .setDuration(4000)
                        .setOnClickListener(listener)
                        .show();
            } else {
                Alerter.create((Activity) context)
                        .setTitle(title)
                        .setText(msg)
                        .enableSwipeToDismiss()
                        .setDuration(4000)
                        .setOnClickListener(listener)
                        .show();
            }
        }
    }

    @Override
    public void showPushAlerter(String title, String msg, View.OnClickListener listener) {
        if (context != null) {
            if (title == null || title != null && title.trim().length() == 0) {
                Alerter.create((Activity) context)
                        .setText(msg)
                        .enableSwipeToDismiss()
                        .setDuration(4000)
                        .setOnClickListener(listener)
                        .show();
            } else {
                Alerter.create((Activity) context)
                        .setTitle(title)
                        .setText(msg)
                        .enableSwipeToDismiss()
                        .setDuration(4000)
                        .setOnClickListener(listener)
                        .show();
            }
        }
    }
}
