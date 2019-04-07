package com.csmobile.csyd.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.csmobile.csyd.R;
import com.csmobile.csyd.mvp.IPresent;
import com.csmobile.csyd.mvp.IView;
import com.csmobile.csyd.mvp.VDelegate;
import com.csmobile.csyd.mvp.VDelegateBase;
import com.gyf.barlibrary.ImmersionBar;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.csmobile.csyd.utils.ToastUtils.showToast;

/**
 * <p>文件描述：<p>
 * <p>作者：zuowenhua<p>
 * <p>创建时间：2019/4/2<p>
 */
public abstract class BaseActivity<P extends IPresent> extends RxAppCompatActivity implements IView<P> {
    protected Activity context;
    private Unbinder unbinder;
    private VDelegate vDelegate;
    private P p;
    public BaseApplication bapp;
    public int mWidth;
    protected int mHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        bapp = (BaseApplication) getApplication();
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        mWidth = metric.widthPixels;
        mHeight = metric.heightPixels;
        bapp.setWith(mWidth);
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            bindUI(null);
        }
        setStatusBar();
        initData(savedInstanceState);
    }

    protected P getP() {
        if (p == null) {
            p = newP();
            if (p != null) {
                p.attachV(this);
            }
        }
        return p;
    }

    public void handError(Throwable e) {//处理返回的异常信息
        getvDelegate().dismissLoading();
        if (e instanceof SocketTimeoutException) {
            showToast("网络请求超时，请重新加载");
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            showToast("网络连接断开,请检查您的网络设置");
        } else {
            showToast("网络繁忙，请稍后再试");
        }
    }
    public void startActivity(Class<?> cls) {
//        if (!NetworkUtils.checkNetworkConnect(this)) {
//        showToast("网络无法连接，请检查网络！");
//            return;
//        }
        super.startActivity(new Intent(this, cls));
    }
    /**
     * 通过设置一个高度来完成沉浸式状态栏
     *
     * @param ll_top
     */
    public void setStatusBarRelativeLayout(View ll_top) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, bapp.getTopDp());
        ll_top.setLayoutParams(layoutParams);

    }
    /**
     * 通过设置一个高度来完成沉浸式状态栏
     *
     * @param ll_top
     */
    public void setStatusBarLinearLayout(View ll_top) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, bapp.getTopDp());
        ll_top.setLayoutParams(layoutParams);

    }
    protected void setStatusBar() {
        ImmersionBar.with(this).barColor(R.color.color_status_bar).statusBarDarkFont(false).init();
    }

    @Override
    public void bindUI(View rootView) {
        unbinder = ButterKnife.bind(this);
    }

    public VDelegate getvDelegate() {
        if (vDelegate == null) {
            vDelegate = VDelegateBase.create(context);
        }
        return vDelegate;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getP() != null) {
            getP().detachV();
        }
        getvDelegate().destory();
        p = null;
        vDelegate = null;
    }
    /**
     * 显示双按钮对话框
     *
     * @param msg
     * @param callback
     */
    public void showNoticeDialog(String msg, MaterialDialog.SingleButtonCallback callback) {
        getvDelegate().showNoticeDialog(msg, callback);
    }
    /**
     * 跳转到系统设置界面
     */
    public void gotoSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }
}
