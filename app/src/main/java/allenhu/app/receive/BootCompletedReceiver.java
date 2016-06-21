package allenhu.app.receive;


import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import allenhu.app.service.ListenClipboardService;

public class BootCompletedReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ListenClipboardService.startForWeakLock(context, intent);
    }
}
