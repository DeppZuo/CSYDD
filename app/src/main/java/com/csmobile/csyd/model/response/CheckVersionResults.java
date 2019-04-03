package com.csmobile.csyd.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * <p>文件描述：<p>
 * <p>作者：zuowenhua<p>
 * <p>创建时间：2019/4/3<p>
 */
public class CheckVersionResults {
    @SerializedName("versionNo")
    public String versionNo;
    @SerializedName("versionName")
    public String versionName;
    @SerializedName("appDesc")
    public String appDesc;
    @SerializedName("updateType")
    public int updateType;//0 不需要更新，1建议更新，2强制更新
    @SerializedName("downloadUrl")
    public String downloadUrl;
}
