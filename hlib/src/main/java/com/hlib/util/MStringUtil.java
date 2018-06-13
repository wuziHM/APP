package com.hlib.util;

import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * Created by moguangjian on 15/10/10 15:31.
 */
public class MStringUtil {

    /**
     * 判断字符串或长度是否为空（空白也算为空）
     *
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        if (isObjectNull(string) || string.toString().trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断list是否是空的，list的size是0也返回true
     *
     * @param list
     * @return
     */
    public static boolean isEmptyList(List list) {
        if (list == null || list.size() < 1) {
            return true;
        }
        return false;
    }

    /**
     * 判断对象是否为空
     *
     * @param object
     * @return
     */
    public static boolean isObjectNull(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 格式化金额字符串
     *
     * @return 10, 333.00
     */
    public static String formatMoneyString(String money) {

        if (isEmpty(money)) {
            return money;
        }

        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(Double.parseDouble(money));


    }

    /**
     * 验证输入字符串是否是一个正确的手机号码
     *
     * @param number
     * @return
     */
    public static boolean validataPhoneNumber(String number) {
        if (isEmpty(number)) {
            return false;
        }

        String patternString = "^1[3-9][0-9]\\d{8}$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(number);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
