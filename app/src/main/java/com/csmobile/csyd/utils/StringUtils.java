package com.csmobile.csyd.utils;

import android.content.res.Resources;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.TypedValue;
import android.widget.TextView;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串相关方法
 * Created by tsy on 16/8/15.
 */
public class StringUtils {

    private static String[] weakPwd = {"000000", "111111", "222222", "333333",
            "44444", "555555", "666666", "777777", "888888", "999999",
            "123123", "123456"};

    public static boolean isMobile(String mobile) {

        return Pattern.matches("^1\\d{10}$", mobile);
    }

    public static boolean isEmail(String email) {

        return email.contains("@");
    }

    // 判断格式是否为email
    public static boolean isEmails(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isNull(String str) {

        return (str == null) || (str.trim().length() == 0);
    }

    public static boolean isIdCard(String num) {

        if (isNull(num)) {
            return false;
        }

        if (num.length() == 18 || num.length() == 15) {
            return true;
        }

        return false;
    }

    public static boolean isIntStr(String str) {

        if (isNull(str)) {
            return false;
        }

        if ("null".equals(str)) {
            return false;
        }

        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean isFloatStr(String str) {

        if (isNull(str)) {
            return false;
        }

        if ("null".equalsIgnoreCase(str)) {
            return false;
        }

        try {
            Float.parseFloat(str);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean isDoubleStr(String str) {

        if (isNull(str)) {
            return false;
        }

        if ("null".equalsIgnoreCase(str)) {
            return false;
        }

        try {
            Double.parseDouble(str);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static double strtodouble(String str){
        if(isDoubleStr(str)){
            return Double.parseDouble(str);
        }
        return 0.00;
    }

    public static boolean isWeakPwd(String pwd) {

        for (int i = 0; i < weakPwd.length; i++) {
            if (pwd.equals(weakPwd[i])) {
                return true;
            }
        }

        return false;
    }

    public static boolean isValidPwd(String pwd) {
        return Pattern.matches("(?![0-9]*$)(?![a-zA-Z]*$)[a-zA-Z0-9]{6,16}",
                pwd);
    }

    public static String isValidPwdNew(String pwd) {
        if (pwd.length() > 16 || pwd.length() < 6) {
            return "密码长度错误，请设置6-16位数字与字母组合密码";
        } else if (Pattern.matches("^[0-9]*$", pwd)
                || Pattern.matches("^[A-Za-z]+$", pwd)) {
            return "密码安全性弱，请设置6-16位数字与字母组合密码";
        } else if (!Pattern.matches("^[A-Za-z0-9]+$", pwd)) {
            return "密码设置不支持字符，请您重新设置";
        } else {
            return "";
        }
    }

    public static boolean isValidCarNo(String carNo) {
        return Pattern.matches("[a-zA-Z]{1}[a-zA-Z0-9]{5}", carNo);
    }

    public static boolean isValidEngineNo(String engineNo) {
        return Pattern.matches("[a-zA-Z0-9]{6}", engineNo);
    }

    /**
     * ������������
     *
     * @param
     * @param len
     * @return
     */
    public static String randomVerify(int len) {

        String verify = "";

        for (int i = 0; i < len; i++) {
            verify += String.valueOf(new Random().nextInt(9));
        }

        return verify;
    }

    public static float parseToFloat(String str, float defValue) {

        if (isNull(str)) {
            return defValue;
        }
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return defValue;
    }

    public static int parseToInt(String str, int defValue) {

        if (isNull(str)) {
            return defValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return defValue;
    }

    public static String parseToString(String str, String defValue) {

        if (isNull(str)) {
            return defValue;
        }
        return str;
    }

    //20140202转化为2014/02/02
    public static String formatDate(String date) {

        if (isNull(date) || date.length() < 8) {
            return date;
        }

        StringBuffer sb = new StringBuffer(date);
        sb.insert(4, "/");
        sb.insert(7, "/");
        return sb.toString();

    }

    //20140202转化为2014-02-02
    public static String formatDate2(String date) {

        if (isNull(date) || date.length() < 8) {
            return date;
        }

        StringBuffer sb = new StringBuffer(date);
        sb.insert(4, "-");
        sb.insert(7, "-");
        return sb.toString();

    }

    //20140202转化为2014年02月02日
    public static String formatDate4(String date) {

        if (isNull(date) || date.length() < 8) {
            return date;
        }

        StringBuffer sb = new StringBuffer(date);
        sb.insert(4, "年");
        sb.insert(7, "月");
        sb.insert(10, "日");
        return sb.toString();

    }

    //2014/02/02转化为2014-02-02
    public static String formatDate3(String date) {

        if (isNull(date) || date.length() < 8) {
            return date;
        }

        StringBuffer sb = new StringBuffer(date);
        sb.replace(4, 5, "-");
        sb.replace(7, 8, "-");
        return sb.toString();

    }

    public static String formatDateToMunice(String date) {

        if (isNull(date) || date.length() < 10) {
            return date;
        }
        date = date.substring(0, 12);
        StringBuffer sb = new StringBuffer(date);
        sb.insert(4, "-");
        sb.insert(7, "-");
        sb.insert(10, " ");
        sb.insert(13, ":");
        return sb.toString();

    }

    public static String formatDateToSecond(String date) {

        if (isNull(date) || date.length() < 8) {
            return date;
        }
        date = date.substring(0, 6);
        StringBuffer sb = new StringBuffer(date);
        sb.insert(2, ":");
        sb.insert(5, ":");
        return sb.toString();

    }

    public static String formatDateToMonth(String date) {

        if (isNull(date) || date.length() < 6) {
            return date;
        }
        date = date.substring(0, 6);
        StringBuffer sb = new StringBuffer(date);
        sb.insert(4, "-");
        return sb.toString();

    }

    public static String formatDataToInt(String date) {

        if (isNull(date) || date.length() < 8) {
            return date;
        }
        date = date.substring(0, 7);
        StringBuffer sb = new StringBuffer(date);
        sb.replace(4, 5, "");
        return sb.toString();

    }

    public static String getValidUrl(String host, String url) {

        String uri = "";

        if (url.startsWith("http://")) {
            return url;
        } else {
            if (url.startsWith("/")) {

                uri = host + url;
            } else {
                uri = host + "/" + url;
            }
        }
        // System.out.println("uri=" + uri);
        return uri;
    }

    /**
     * λ����ʽ��
     *
     * @param num
     * @return
     */
    public static String numberFormat(int num, int count) {

        String result = String.valueOf(num);

        int m = (int) Math.pow(10, count - 1);

        if (num == 0) {

            for (int i = 0; i < count - 1; i++) {
                result += "0";
            }

        } else {

            while (num / m < 1) {
                result = "0" + result;
                num = num * 10;
            }
        }
        return result;
    }

    /**
     * �����ʽ��
     *
     * @param distance
     * @param max
     * @return
     */
    public static String distanceFormat(double distance, int max) {

        if (distance < 0) {
            return "δ֪";
        }

        if (distance < 1000) {
            return ((int) distance) + "��";
        }

        double d = distance / 1000;

        if (d > max) {
            return max + "ǧ������";
        } else {
            return String.format("%.2f", d) + "ǧ��";
        }

    }

    /**
     * 该方法用于生产一个length位的随机字母和数字
     *
     * @param length
     * @return
     */
    public static String getCharAndNumr(int length) {
        String val = "";

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

            if ("char".equalsIgnoreCase(charOrNum)) // 字符串
            {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) // 数字
            {
                val += String.valueOf(random.nextInt(10));
            }
        }

        return val;
    }

    public static String getRandomNumber(int length) {
        String val = "";
        SecureRandom random = new SecureRandom();
        byte seed[] = random.generateSeed(12);
        random.setSeed(seed);

        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

            if ("char".equalsIgnoreCase(charOrNum)) // 字符串
            {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) // 数字
            {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;

    }

    /*
     * 该方法用于运营商判段
     * @author yq
     * @param length
     * @return
     */
    public static String getOperator(String phone) {
        String[] operatorstr = new String[]{"中国移动", "中国联通", "中国电信"};
        String[][] Operator = new String[][]{
                {
                    /* 移动 */"135", "136", "137", "138", "139", "150", "151", "152",
                        "158", "159", "182", "183", "184", "157", "187", "188",
                        "147", "178"},
                {
					/* 联通 */"130", "131", "132", "155", "156", "185", "186", "145",
                        "176", "185"}, {
					/* 电信 */"133", "153", "180", "181", "189", "177",},};

        if (phone.length() > 11) {
            phone = phone.substring(phone.length() - 11, phone.length());
        }

        LogUtils.i("Tag", phone + "电话号码");

        String phone3 = phone.substring(0, 3);
        for (int i = 0; i < Operator.length; i++) {
            for (int j = 0; j < Operator[i].length; j++) {
                if (Operator[i][j].equals(phone3)) {
                    return operatorstr[i];
                }
            }
        }
        if ("134".equals(phone3)) {
            String phone4 = phone.substring(2, 3);
            if (phone4 == "9") {
                return "卫星通信";
            } else {
                return "中国移动";
            }
        }

        if ("170".equals(phone3)) {
            String phone4 = phone.substring(2, 3);
            if (phone4 == "5") {
                return "移动虚拟";
            }
            if (phone4 == "9") {
                return "联通虚拟";
            }
            if (phone4 == "0") {
                return "电信虚拟";
            }
        }
        return "未知号码";
    }

    public static String TMMoble(String phone) {//对手机号码脱敏
        String str = "";
        if (StringUtils.isNull(phone)) {
            return "";
        }
        if (phone.length() != 11) {
            return phone;
        }
        str = phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4, phone.length());
        return str;
    }

    public static String getLast4(String card) {//卡号后四位
        String str = "";
        if (StringUtils.isNull(card)) {
            return card;
        }
        if (card.length() > 5) {
            return str = card.substring(card.length() - 4, card.length());
        }
        return str;
    }

    public static String TMName(String name) {//对姓名脱敏
        String str = "";
        if (StringUtils.isNull(name)) {
            return "";
        }
        if (name.length() > 1) {
            str = name.substring(0, 1);
            for (int i = 0; i < name.length() - 1; i++) {
                str = str + "*";
            }
        }
        return str;
    }

    public static String TMCard(String card) {//对卡号脱敏
        String str = "";
        if (StringUtils.isNull(card)) {
            return "";
        }
        if (card.length() > 10) {
            str = card.substring(0, 4);
            for (int i = 0; i < card.length() - 8; i++) {
                str = str + "*";
            }
            str = str + card.substring(card.length() - 4, card.length());
        }
        return str;
    }

    public static String TMId(String id) {//对身份证脱敏
        String str = "";
        if (StringUtils.isNull(id)) {
            return "";
        }
        if (id.length() > 10) {
            str = id.substring(0, 4);
            for (int i = 0; i < id.length() - 8; i++) {
                str = str + "*";
            }
            str = str + id.substring(id.length() - 4, id.length());
        }
        return str;
    }

    public static boolean isContainAll(String str) {//必须同时包含大小写字母及数字
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLowerCase = false;//定义一个boolean值，用来表示是否包含字母
        boolean isUpperCase = false;//是否包含大写字母
        if (isNull(str)) {
            return false;
        }
        if (str.contains(" ")) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLowerCase(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLowerCase = true;
            } else if (Character.isUpperCase(str.charAt(i))) {
                isUpperCase = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isDigit && isLowerCase && isUpperCase && str.matches(regex);
        return isRight;
    }
    /**
     * empty4CardNo 对银行卡号4位空格
     *
     * @param
     * @return String
     */
    public static String empty4CardNo(String str) {
        String returnstr = "";
        try {
            if (TextUtils.isEmpty(str)) {
                return returnstr;
            }
            if (str.length() < 5) {
                return str;
            }
            int length = str.length();
            //123 456 789 1
            for (int i = 0; i < (length % 4 == 0 ? length / 4 : (length / 4 + 1)); i++) {
                if (i < (length %4 == 0 ? length / 4 : (length / 4 + 1))-1) {
                    returnstr = returnstr + str.substring(4* i, 4 * (i + 1)) + " ";
                } else {
                    returnstr = returnstr + str.substring(4 * i, length);
                }
            }
            return returnstr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 对数字字符串进行四舍五入处理,位数不够自动补零
     *
     * @param str
     * @param digit 要保留的位数
     * @return
     * @Description
     */
    public static String roundOfDecimal(String str, int digit) {
        if (!TextUtils.isEmpty(str)) {
            BigDecimal bd = new BigDecimal(str);
            String roundStr = bd.setScale(digit, BigDecimal.ROUND_HALF_UP).toString();
            return roundStr;
        } else {
            return "";
        }
    }
    /**
     * 将字符串转换成int类型
     *
     * @param str
     * @return
     * @Description
     */
    public static int stringToInt(String str) {
        int result = 0;
        if (!TextUtils.isEmpty(str)) {
            try {
                result = Integer.parseInt(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 中文字符 缩小效果
     * @param str
     * @param textView
     */
    public static void tranChar(String str, TextView textView){
       try {
           if (TextUtils.isEmpty(str)) {
               textView.setText("暂无数据");
           }else {
               SpannableString spannableString = new SpannableString(str);
               char[] chars = str.toCharArray();
               for (int i = 0; i < chars.length; i++) {
                   char c = chars[i];
                   if (!Character.isDigit(c)){
                       spannableString.setSpan(new AbsoluteSizeSpan(10, true), i, i + 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                   }
               }
               textView.setText(spannableString);
           }
       }catch (Exception e){
           e.printStackTrace();
           textView.setText(str);
       }
    }
    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }
}
