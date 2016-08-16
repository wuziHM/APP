package com.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author：燕青 $ on 16/8/8 17:47
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class Utils {

    public static boolean isIdCardNum(String id_card) {
        String pattern = "^\\d{15}|^\\d{17}([0-9]|X|x)$";
        return patternMatcher(pattern, id_card);
    }

    /**
     * 通过正则表达式判断是否匹配内容
     *
     * @param pattern 表达式
     * @param content
     * @return
     */
    public static boolean patternMatcher(String pattern, String content) {
        boolean flag = false;
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(content);
            flag = m.matches();

        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

}
