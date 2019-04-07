package com.csmobile.csyd.model.response;

import com.csmobile.csyd.model.bean.BeanFndicator;

import java.util.List;

public class Fndicator_Res {
    public String gridNm;// 小区名字
    public String staffNm;// 经理姓名
    public String monthIn;// 当月收入
    public String monthShare;//当月份额
    public String lastMonthShare;//上月份额
    public String chlNum;// 渠道数
    public String statNum;// 基站数
    public String grpNum;//  集团数
    public String statUserNum;// 基站用户数
    public String grpUserNum;// 集团用户数
    public String lastMonthIn;// 上月收入
    public String commNum;// 小区数
    public String todayIn;// 当日收入
    public String today;//日期
    public List<BeanFndicator>list;
}
