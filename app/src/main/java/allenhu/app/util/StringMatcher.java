package allenhu.app.util;

/**
 * Created by AllenHu on 2016/2/26.
 */
public class StringMatcher {

    /**
     * 判断value中是否包含key
     *
     * @param value item的文本
     * @param key   索引中的字符
     * @return
     */
    public static boolean match(String value, String key) {
        if (value == null || key == null) {
            return false;
        }
        if (key.length() > value.length()) {
            return false;
        }
        int i = 0/*value的指针*/, j = 0 /*key的指针*/;
        do {
            if (key.charAt(j) == value.charAt(i)) {
                i++;
                j++;
            } else if (j > 0) {
                break;
            } else {
                i++;
            }
        } while (i < value.length() && j < key.length());
        return (j == key.length()) ? true : false;
    }
}
