package com.csmobile.csyd.model.bean;

/**
 * <p>文件描述：<p>
 * <p>作者：zuowenhua<p>
 * <p>创建时间：2019/4/2<p>
 */
public class BeanFndicator {
    public String title;
    public int type_red;
    public int type_up;

    public BeanFndicator(String title, int type_red, int type_up) {
        this.title = title;
        this.type_red = type_red;
        this.type_up = type_up;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
