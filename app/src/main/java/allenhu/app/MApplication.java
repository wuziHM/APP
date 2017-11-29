package allenhu.app;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Author：HM $ on 17/11/29 10:32
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class MApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initLogger();
    }

    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }
}
