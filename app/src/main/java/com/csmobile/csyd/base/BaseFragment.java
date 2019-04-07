package com.csmobile.csyd.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csmobile.csyd.R;
import com.csmobile.csyd.mvp.IPresent;
import com.csmobile.csyd.mvp.IView;
import com.csmobile.csyd.mvp.VDelegate;
import com.csmobile.csyd.mvp.VDelegateBase;
import com.gyf.barlibrary.ImmersionBar;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle.components.support.RxFragment;

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
public abstract class BaseFragment<P extends IPresent> extends RxFragment implements IView<P> {
    private VDelegate vDelegate;
    private P p;
    protected Activity context;
    private View rootView;
    protected LayoutInflater layoutInflater;

    private RxPermissions rxPermissions;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutInflater = inflater;
        if (rootView == null && getLayoutId() > 0) {
            rootView = inflater.inflate(getLayoutId(), null);
            bindUI(rootView);
        } else {
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        }
        setStatusBar();

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
    }

    protected void setStatusBar() {
        int color = Color.parseColor("#d53c27");
        ImmersionBar.with(this).barColor(R.color.color_status_bar).statusBarDarkFont(false).init();
    }

    @Override
    public void bindUI(View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
    }

    protected VDelegate getvDelegate() {
        if (vDelegate == null) {
            vDelegate = VDelegateBase.create(context);
        }
        return vDelegate;
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.context = (Activity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getP() != null) {
            getP().detachV();
        }
        getvDelegate().destory();

        p = null;
        vDelegate = null;
    }

    public void startActivity(Class<?> cls) {
        getActivity().startActivity(new Intent(getActivity(), cls));
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

}
