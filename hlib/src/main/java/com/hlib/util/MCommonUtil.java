package com.hlib.util;

import android.content.Context;
import android.os.Vibrator;

/**
 * Author：燕青 $ on 17/6/26 10:04
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class MCommonUtil {


    private static final String TAG = MCommonUtil.class.getSimpleName();

    /**
     * 振动器
     *
     * @param context
     * @param duration
     */
    public static void vibrate(Context context, long duration) {
        try {
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            long[] pattern = {
                    0, duration
            };
            vibrator.vibrate(pattern, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
