package allenhu.app.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import allenhu.app.service.MyService1;

/**
 * Created by AllenHu on 2016/3/2.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, MyService1.class));
    }
}
