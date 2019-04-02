package com.csmobile.csyd.present;

import com.csmobile.csyd.base.BaseResponse;
import com.csmobile.csyd.model.response.Login_Res;
import com.csmobile.csyd.mvp.XPresent;
import com.csmobile.csyd.net.ResponseObserver;
import com.csmobile.csyd.net.RetrofitUtil;
import com.csmobile.csyd.ui.activitys.LoginActivity;
import com.csmobile.csyd.utils.LogUtils;

/**
 * <p>文件描述：<p>
 * <p>作者：zuowenhua<p>
 * <p>创建时间：2019/4/2<p>
 */
public class PLogin extends XPresent<LoginActivity> {
    public void login(String mobile, String password) {
        RetrofitUtil.getInstance(getV()).toLogin(mobile, password, new ResponseObserver<BaseResponse<Login_Res>>() {
            @Override
            public void onNext(BaseResponse<Login_Res> login_resBaseResponse) {
                getV().showToast(login_resBaseResponse.message);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                getV().handError(e);
            }

            @Override
            public void onCompleted() {
                getV().getvDelegate().dismissLoading();
            }
        });
    }
}
