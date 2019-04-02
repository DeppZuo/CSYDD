package com.csmobile.csyd.net;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;

import com.csmobile.csyd.BuildConfig;
import com.csmobile.csyd.api.ApiService;
import com.csmobile.csyd.base.BaseApplication;
import com.csmobile.csyd.base.BaseResponse;
import com.csmobile.csyd.base.Consts;
import com.csmobile.csyd.model.response.Login_Res;
import com.csmobile.csyd.utils.LogUtils;
import com.csmobile.csyd.utils.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitUtil {

    public static final int DEFAULT_TIMEOUT = 60;

    private Retrofit mRetrofit;
    private ApiService mApiService;
    protected SharedPreferences sp;
    private static RetrofitUtil mInstance;
    //http://www.kuaidi100.com/query?type=yuantong&postid=500379523313//测试链接

    /**
     * 私有构造方法
     */
    private RetrofitUtil() {
        sp = BaseApplication.getInstance().getSharedPreferences(Consts.APPSP, Context.MODE_PRIVATE);
        if (BuildConfig.DEBUG) {
            Consts.APP_HOST = sp.getString("APP_HOST", Consts.APP_HOST);
        }
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(genericClient(BaseApplication.getInstance()))
                .baseUrl(Consts.APP_HOST)
                .build();
        mApiService = mRetrofit.create(ApiService.class);
    }

    public static RetrofitUtil getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtil.class) {
                mInstance = new RetrofitUtil();
            }
        }
        return mInstance;
    }

    public static RetrofitUtil getInstance(Context context) {
        if (mInstance == null) {
            synchronized (RetrofitUtil.class) {
                mInstance = new RetrofitUtil();
            }
        }
        return mInstance;
    }


    public OkHttpClient genericClient(final Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (BuildConfig.DEBUG) {
                    try {
                        JSONObject oj = new JSONObject(message);
                        LogUtils.json(message);
                    } catch (Exception e) {
                        LogUtils.d(message);
                    }
                }
            }//添加请求及返回Log
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).addInterceptor(httpLoggingInterceptor);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()//添加请求头
                        .newBuilder()
                        .build();
                return chain.proceed(request);
            }
        });
//        try {
//            InputStream inputStreams = BaseApplication.getInstance().getAssets().open("rongxinwang_https.cer");
//            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(new InputStream[]{inputStreams}, null, null);
//            builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        builder.hostnameVerifier(new HttpsUtils.UnSafeHostnameVerifier());
        return builder.build();
    }

    private SSLSocketFactory getSSLSocketFactory() {
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }};

            // Install the all-trusting trust manager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();
            return sslSocketFactory;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }



    private <T> void toSubscribe(Observable<T> observable, Observer<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    //检查网络后提交
    private <T> void toSubscribeCheck(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private boolean getPermission;

    private boolean checkPermission(final Context context) {//因为请求头都需要获取deviceID所以必须检查是否有权限
        RxPermissions rxPermissions = new RxPermissions((Activity) context);
        rxPermissions
                .request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            // 用户允许权限
                        } else {
                            ToastUtils.showToast("设备信息获取失败,请开启设备信息权限");
                            // 用户拒绝了权限申请
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                            intent.setData(uri);
                            context.startActivity(intent);
                        }
                        getPermission = aBoolean;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getPermission = false;
                    }
                });
        return getPermission;
    }
    //登录
    public void toLogin(String mobile, String password, Observer<BaseResponse<Login_Res>> subscriber) {
        toSubscribeCheck(mApiService.login( mobile, password), subscriber);
    }
}

