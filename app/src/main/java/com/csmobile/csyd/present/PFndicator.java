package com.csmobile.csyd.present;

import com.csmobile.csyd.base.BaseResponse;
import com.csmobile.csyd.base.Consts;
import com.csmobile.csyd.model.response.Fndicator_Res;
import com.csmobile.csyd.mvp.XPresent;
import com.csmobile.csyd.net.ResponseObserver;
import com.csmobile.csyd.net.RetrofitUtil;
import com.csmobile.csyd.ui.fragments.Fragment_Fndicator;

/**
 * <p>文件描述：<p>
 * <p>作者：zuowenhua<p>
 * <p>创建时间：2019/4/2<p>
 */
public class PFndicator extends XPresent<Fragment_Fndicator> {
    public void getALLData(String phone) {
        RetrofitUtil.getInstance(getV().getActivity()).getFndicatorData(phone, new ResponseObserver<BaseResponse<Fndicator_Res>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onNext(BaseResponse<Fndicator_Res> fndicator_resBaseResponse) {
                try {
                    if (fndicator_resBaseResponse.code.equals(Consts.RESCODE_SUCCESS)) {
                        getV().setData(fndicator_resBaseResponse.data);
                    } else {
                        getV().showToast(fndicator_resBaseResponse.msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                getV().handErrorList(e);
            }
        });
    }



    public void getMONTHData(String phone) {
        RetrofitUtil.getInstance(getV().getActivity()).getMONTHData(phone, new ResponseObserver<BaseResponse<Fndicator_Res>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onNext(BaseResponse<Fndicator_Res> fndicator_resBaseResponse) {
                try {
                    if (fndicator_resBaseResponse.code.equals(Consts.RESCODE_SUCCESS)) {
                        getV().setData(fndicator_resBaseResponse.data);
                    } else {
                        getV().showToast(fndicator_resBaseResponse.msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
