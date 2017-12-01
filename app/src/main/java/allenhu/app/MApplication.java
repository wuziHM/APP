package allenhu.app;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Author：HM $ on 17/11/29 10:32
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class MApplication extends Application {
    private static MApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initLogger();
    }

    private void initLogger() {

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag("wuzi")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    public static MApplication getIntstance() {
        return application;
    }
}
