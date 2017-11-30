package com.hlib.util;


import com.orhanobut.logger.Logger;

/**
 * 日志工具类。
 * Created by moguangjian on 15/10/10 14:57.
 */
public class MLogUtil {
    private static String tag = "wuzi";


    /**
     * 打印log.i日志
     *
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {

        Logger.i(tag, msg);
    }

    public static void i(String msg) {
        Logger.i(msg);
    }

    /**
     * 打印log.w日志
     *
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg) {
        Logger.w(tag, msg);
    }

    /**
     * 打印log.e日志
     *
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        com.orhanobut.logger.Logger.e(tag, msg);
    }

    /**
     * 打印log.e日志
     *
     * @param msg
     */
    public static void e(String msg) {
        com.orhanobut.logger.Logger.e(tag, msg);
    }

    public static void out(String msg) {
        System.out.println(msg);
    }

}
