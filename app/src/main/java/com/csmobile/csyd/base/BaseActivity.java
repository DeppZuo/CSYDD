package com.csmobile.csyd.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.csmobile.csyd.R;
import com.csmobile.csyd.mvp.IPresent;
import com.csmobile.csyd.mvp.IView;
import com.csmobile.csyd.mvp.VDelegate;
import com.csmobile.csyd.mvp.VDelegateBase;
import com.csmobile.csyd.utils.LogUtils;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        bapp = (BaseApplication) getApplication();
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
}
