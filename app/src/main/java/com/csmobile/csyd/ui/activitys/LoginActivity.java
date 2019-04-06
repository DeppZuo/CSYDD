package com.csmobile.csyd.ui.activitys;

import android.os.Bundle;
import android.view.View;

import com.csmobile.csyd.R;
import com.csmobile.csyd.base.BaseActivity;
import com.csmobile.csyd.present.PLogin;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <p>文件描述：<p>
 * <p>作者：zuowenhua<p>
 * <p>创建时间：2019/4/2<p>
 */
public class LoginActivity extends BaseActivity<PLogin> {
    @BindView(R.id.ll_state_bar)
    View mLlStateBar;

    @Override
    public int getLayoutId() {
        return R.layout.login_activity;
    }

    @Override
    public PLogin newP() {
        return new PLogin();
    }

    @OnClick(R.id.btn_login)
    void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                getP().login("13973198515","13973198515");
                break;
        }
    }

    @Override
    public void bindEvent() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        ImmersionBar.with(this).barColor(R.color.color_status_bar).statusBarDarkFont(false).init();
        setStatusBarRelativeLayout(mLlStateBar);
    }

    public void showToast(String msg) {
        getvDelegate().dismissLoading();
        getvDelegate().toastShort(msg);
    }
}
