package allenhu.app.net;

import allenhu.app.BuildConfig;

/**
 * Author：HM $ on 18/7/13 10:48
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class MeiziURL {


    public static final String MEI_NEW = "posts";   //最新
    public static final String MEI_HOT = "hot";     //热门
    public static final String MEI_REC = "rand";     //推荐
    public static final String MEI_SPECIAL = "posts";     //专题

    public static final String MEI_NUM = "http://adr.meizitu.net/json/x.json";     //专题编号
    public static final String MEI_COMBO = "i";     //套图详情


    public static String getMeiNew() {
        return BuildConfig.MEIZI_API_URL.concat(MEI_NEW);
    }

    public static String getMeiHot() {
        return BuildConfig.MEIZI_API_URL.concat(MEI_HOT);
    }

    public static String getMeiRec() {
        return BuildConfig.MEIZI_API_URL.concat(MEI_REC);
    }

    public static String getMeiSpecial() {
        return BuildConfig.MEIZI_API_URL.concat(MEI_SPECIAL);
    }

    public static String getMeiNum() {
        return BuildConfig.MEIZI_API_URL.concat(MEI_NUM);
    }

    public static String getMeiCombo() {
        return BuildConfig.MEIZI_API_URL.concat(MEI_COMBO);
    }
}
