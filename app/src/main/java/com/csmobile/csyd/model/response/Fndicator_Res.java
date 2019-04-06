package com.csmobile.csyd.model.response;

import com.csmobile.csyd.model.bean.BeanFndicator;

import java.util.List;

public class Fndicator_Res {
    public String staffNm;// 经理姓名
    public double monthIn;// 当月收入
    public double monthShare;//当月份额
    public double lastMonthShare;//上月份额
    public int chlNum;// 渠道数
    public int statNum;// 基站数
    public int groupNum;//  集团数
    public int statUserNum;// 基站用户数
    public int grpUserNum;// 集团用户数
    public double lastMonthIn;// 上月收入
    public List<BeanFndicator>list;
}
