package allenhu.app.util;

/**
 * Author：燕青 $ on 2016/3/29  10:46
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class CalculateUtil {

    /**
     * 获取数组里最大的值
     *
     * @param a
     * @return
     */
    public static int getMax(int a[]) {
        int max = 0;

        for (int i = 0; i < a.length; i++) {
            if (i == 0) {
                max = a[i];
            } else {
                if (max < a[i]) {
                    max = a[i];
                }
            }

        }
        return max;
    }



}
