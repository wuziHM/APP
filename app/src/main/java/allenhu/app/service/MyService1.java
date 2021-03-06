package allenhu.app.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;

import com.hlib.util.MLogUtil;

import java.util.Date;

import allenhu.app.receive.AlarmReceiver;
import allenhu.app.receive.NotificationReceiver;

public class MyService1 extends Service {

    private MyBinder myBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        sendBroadcast(new Intent(this,NotificationReceiver.class));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                MLogUtil.e("executed at " + new Date().toString());
            }
        }).start();

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 1 * 60 * 1000; // 这是一小时的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    public MyService1() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return myBinder;
    }


    public class MyBinder extends Binder {
        public void eat() {
            MLogUtil.e("吃吃喝喝，朱门酒肉臭,路有冻死骨");
        }

        public void sleep() {
            MLogUtil.e("悠悠生死别经年，魂魄不曾来入梦。");
        }
    }
}
