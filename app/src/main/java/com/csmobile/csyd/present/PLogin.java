package com.csmobile.csyd.present;

import com.csmobile.csyd.base.BaseResponse;
import com.csmobile.csyd.base.Consts;
import com.csmobile.csyd.model.response.Login_Res;
import com.csmobile.csyd.mvp.XPresent;
import com.csmobile.csyd.net.ResponseObserver;
import com.csmobile.csyd.net.RetrofitUtil;
import com.csmobile.csyd.ui.activitys.LoginActivity;
import com.csmobile.csyd.ui.activitys.MainActivity;

/**
 * <p>文件描述：<p>
 * <p>作者：zuowenhua<p>
 * <p>创建时间：2019/4/2<p>
 */
public class PLogin extends XPresent<LoginActivity> {
    public void login(String mobile, String password) {
        getV().getvDelegate().showLoading();
        RetrofitUtil.getInstance(getV()).toLogin(mobile, password, new ResponseObserver<BaseResponse<Login_Res>>() {
            @Override
            public void onNext(BaseResponse<Login_Res> login_resBaseResponse) {
                try {
                    if (login_resBaseResponse.code.equals(Consts.RESCODE_SUCCESS)) {
                            getV().startActivity(MainActivity.class);
                            getV().finish();
                    }else {
                            getV().showToast(login_resBaseResponse.msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
