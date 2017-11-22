package allenhu.app.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.widget.Toast;

import com.hlib.util.MLogUtil;

/**
 * Created by AllenHu on 2016/2/24.
 */
public class OperationsManager extends IntentService {
    public static final String ACTION_EVENT = "ACTION_EVENT";
    public static final String ACTION_WARNING = "ACTION_WARNING";
    public static final String ACTION_ERROR = "ACTION_ERROR";
    public static final String EXTRA_NAME = "eventName";
    private IntentFilter matcher;

    public OperationsManager() {
        super("OperationsManager");
//创建一个过滤器来匹配收到的请求
        matcher = new IntentFilter();
        matcher.addAction(ACTION_EVENT);
        matcher.addAction(ACTION_WARNING);
        matcher.addAction(ACTION_ERROR);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
//检查是否是有效的请求
        if (!matcher.matchAction(intent.getAction())) {
            Toast.makeText(this, "OperationsManager: Invalid Request",
                    Toast.LENGTH_SHORT).show();
            return;
        }
//直接在这个方法中处理每个请求。不需要创建其他的线程
        if (TextUtils.equals(intent.getAction(), ACTION_EVENT)) {
            logEvent(intent.getStringExtra(EXTRA_NAME));
        }
        if (TextUtils.equals(intent.getAction(), ACTION_WARNING)) {
//            Android 开发范例代码大全(第2 版)
            logWarning(intent.getStringExtra(EXTRA_NAME));
        }
        if (TextUtils.equals(intent.getAction(), ACTION_ERROR)) {
            logError(intent.getStringExtra(EXTRA_NAME));
        }
    }

    private void logEvent(String name) {
        try {
//通过休眠来模拟一个长时间的网络操作
            Thread.sleep(5000);
            MLogUtil.e(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void logWarning(String name) {
        try {
//通过休眠来模拟一个长时间的网络操作
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void logError(String name) {
        try {
//通过休眠来模拟一个长时间的网络操作
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
