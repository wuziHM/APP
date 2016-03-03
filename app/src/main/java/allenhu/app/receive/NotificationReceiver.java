package allenhu.app.receive;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;

import allenhu.app.MainActivity;
import allenhu.app.R;

/**
 * Created by AllenHu on 2016/3/2.
 */
public class NotificationReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        notifyUser(context, 1, "你好", "大家好才是真的好", "duang");

    }

    /**
     * 用户消息提示
     */
    public static void notifyUser(Context context, int id, String title, String content, String tickerText) {

        // 设置intent之间的跳转 点击通知后要跳向的页面
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // PendingIntent pi = PendingIntent.getActivity(context, 0,intent,
        // PendingIntent.FLAG_ONE_SHOT);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 获取系统的状态类通知
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification n = new Notification();
        n.icon = R.mipmap.iv_lol_icon1;
        n.defaults = Notification.DEFAULT_SOUND;
        n.tickerText = "You clicked BaseNF!";

        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = new Notification.Builder(context)
                    .setAutoCancel(true)
                    .setContentTitle("title")
                    .setContentText("describe")
                    .setContentIntent(pi)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setWhen(System.currentTimeMillis())
                    .build();
        } else {
            Notification.Builder builder = new Notification.Builder(context)
                    .setAutoCancel(true)
                    .setContentTitle("11  title")
                    .setContentText("11  describe")
                    .setContentIntent(pi)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setWhen(System.currentTimeMillis())
                    .setOngoing(true);
            notification = builder.getNotification();
        }

        notificationManager.notify(1001, notification);
    }
}
