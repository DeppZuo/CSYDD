package com.csmobile.csyd.present;

import com.csmobile.csyd.mvp.XPresent;
import com.csmobile.csyd.ui.activitys.MainActivity;

/**
 * <p>文件描述：<p>
 * <p>作者：zuowenhua<p>
 * <p>创建时间：2019/4/2<p>
 */
public class PMainActivity extends XPresent<MainActivity> {
    public void showMessage() {
        getV().showToast("手机号格式不正确");
    }
}
