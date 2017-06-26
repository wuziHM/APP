package com.hlib.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hlib.R;

/**
 * Author：燕青 $ on 17/6/26 14:34
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class MToastUtil {


    private static final String TAG = MToastUtil.class.getSimpleName();
    private static Toast toast;
    private static Toast imgToast;
    private static TextView tvMsg;
    private static TextView imgTvMsg;

    /**
     * 显示Toast信息
     *
     * @param msg
     */
    public static void show(Context context, String msg) {
        initToastMsg(context, msg, null);
    }

    /**
     * 显示Toast R.string.**的信息
     *
     * @param stringId
     */
    public static void show(Context context, int stringId) {
        String msg = context.getString(stringId);
        initToastMsg(context, msg, null);
    }

    /**
     * 显示Toast R.string.**的信息,并显示指定icon
     *
     * @param context
     * @param stringId
     * @param drawableId
     */
    public static void show(Context context, int stringId, int drawableId) {
        String msg = context.getString(stringId);
        initToastMsg(context, msg, getDrawable(context, drawableId));
    }

    /**
     * 显示Toast R.string.**的信息,并显示指定icon
     *
     * @param context
     * @param stringId
     * @param drawable
     */
    public static void show(Context context, int stringId, Drawable drawable) {
        String msg = context.getString(stringId);
        initToastMsg(context, msg, drawable);
    }

    /**
     * 隐藏Toast
     */
    public static void hide() {
        if (toast != null) {
            toast.cancel();
        }
    }

    /**
     * id转drawable对象
     *
     * @param context
     * @param drawableId
     * @return
     */
    private static Drawable getDrawable(Context context, int drawableId) {
        Drawable drawable = null;
        try {
            drawable = context.getResources().getDrawable(drawableId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return drawable;
    }

    /**
     * 设置Toast显示信息
     *
     * @param context
     */
    private static void initToastMsg(final Context context, final String message, final Drawable drawable) {

        try {
            String msg;
            if (context == null) {
                MLogUtil.e(TAG, "context or msg is null . Toast is hide !");
                return;
            }

            if (message == null || message.trim().length() < 0) {
                msg = "加载数据失败";
            } else {
                msg = message;
            }


            if (MStringUtil.isEmpty(msg)) {
                showNoTextToast(context, drawable, msg);
            } else {
                showTextToast(context, drawable, msg);
            }
        } catch (Exception e) {
            MLogUtil.e(TAG, e.getMessage());
            e.printStackTrace();
        }
    }

    private static void showNoTextToast(Context context, Drawable drawable, String msg) {
        if (imgToast == null) {
            View view = LayoutInflater.from(context).inflate(R.layout.m_toast_icon_msg_nostring, null);
            imgTvMsg = (TextView) view.findViewById(R.id.tvMsg);
            imgTvMsg.setText(msg);

            imgToast = new Toast(context);
            imgToast.setGravity(Gravity.CENTER, 0, 0);
            imgToast.setDuration(Toast.LENGTH_SHORT);
            imgToast.setView(view);
        }

        if (drawable == null) {
            imgTvMsg.setCompoundDrawables(null, null, null, null);
        } else {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            imgTvMsg.setCompoundDrawables(drawable, null, null, null);
        }

        imgToast.show();
    }

    private static void showTextToast(Context context, Drawable drawable, String msg) {
        if (null == toast) {
            View view = LayoutInflater.from(context).inflate(R.layout.m_toast_icon_msg, null);
            tvMsg = (TextView) view.findViewById(R.id.tvMsg);
            tvMsg.setText(msg);

            toast = new Toast(context);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view);
        }

        if (drawable == null) {
            tvMsg.setCompoundDrawables(null, null, null, null);
        } else {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvMsg.setCompoundDrawables(drawable, null, null, null);
        }

        tvMsg.setText(msg);
        toast.show();
    }

    private static Toast showTextToastReturn(Context context, Drawable drawable, String msg) {
        if (null == toast) {
            View view = LayoutInflater.from(context).inflate(R.layout.m_toast_icon_msg, null);
            tvMsg = (TextView) view.findViewById(R.id.tvMsg);
            tvMsg.setText(msg);

            toast = new Toast(context);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view);
        }

        if (drawable == null) {
            tvMsg.setCompoundDrawables(null, null, null, null);
        } else {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvMsg.setCompoundDrawables(drawable, null, null, null);
        }

        tvMsg.setText(msg);
        toast.show();
        return toast;
    }

    /**
     * 显示toast，自己定义显示长短。
     * param1:activity  传入context
     * param2:word   我们需要显示的toast的内容
     * param3:time length  long类型，我们传入的时间长度（如500）
     */
    public static void showToast(final Activity activity, final String word, final long time) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                final Toast toast = MToastUtil.showTextToastReturn(activity, null, word);
//                final Toast toast = Toast.makeText(activity, word, Toast.LENGTH_LONG);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        toast.cancel();
                    }
                }, time);
            }
        });
    }
}
