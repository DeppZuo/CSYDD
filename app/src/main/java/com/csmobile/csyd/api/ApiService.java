package com.csmobile.csyd.api;


import com.csmobile.csyd.base.BaseResponse;
import com.csmobile.csyd.model.response.Fndicator_Res;
import com.csmobile.csyd.model.response.Login_Res;
import com.csmobile.csyd.net.NetUrl;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author nanchen
 * @fileName RetrofitRxDemoo
 * @packageName com.nanchen.retrofitrxdemoo
 * @date 2016/12/09  17:04
 */

public interface ApiService {
    @FormUrlEncoded
    @POST(NetUrl.login)
    Observable<BaseResponse<Login_Res>> login(@Field("phone") String phone, @Field("password") String password);

    @FormUrlEncoded
    @POST(NetUrl.login)
    Observable<BaseResponse<Fndicator_Res>> getFndicatorData(@Field("phone") String phone);
}
