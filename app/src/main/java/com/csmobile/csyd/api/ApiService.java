package com.csmobile.csyd.api;


import com.csmobile.csyd.base.BaseResponse;
import com.csmobile.csyd.model.response.Login_Res;
import com.csmobile.csyd.net.NetUrl;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @author nanchen
 * @fileName RetrofitRxDemoo
 * @packageName com.nanchen.retrofitrxdemoo
 * @date 2016/12/09  17:04
 */

public interface ApiService {
    @FormUrlEncoded
    @POST(NetUrl.login)
    Observable<BaseResponse<Login_Res>> login(@Field("mobile") String mobile, @Field("password") String password);
}
