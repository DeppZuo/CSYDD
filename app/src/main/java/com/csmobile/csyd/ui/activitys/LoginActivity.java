package com.csmobile.csyd.ui.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.csmobile.csyd.R;
import com.csmobile.csyd.base.BaseActivity;
import com.csmobile.csyd.present.PLogin;
import com.csmobile.csyd.utils.StringUtils;
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
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.ed_password)
    EditText ed_password;
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
                if (StringUtils.isNull(ed_phone.getText().toString().trim())||ed_phone.getText().toString().length()<11||ed_phone.getText().toString().length()>11||StringUtils.isNull(ed_password.getText().toString().trim()))
                {
                    showToast("请输入正确的手机号码及密码");
                    return;
                }
                getP().login(ed_phone.getText().toString().trim(),ed_password.getText().toString().trim());
//                getP().login("13973198515","36212907");
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
