package com.csmobile.csyd.base;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 获取json数据基类
 *
 * @author nanchen
 * @fileName RetrofitRxDemoo
 * @packageName com.nanchen.retrofitrxdemoo
 * @date 2016/12/09  17:05
 */

public class BaseResponse<T> implements Serializable{
    @SerializedName("code")
    public String code;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public T data;
}
