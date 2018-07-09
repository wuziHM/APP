package allenhu.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.stetho.Stetho;
import com.lzy.okgo.OkGo;
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

    public static final String BASE_URL = "http://www.mzitu.com/";
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        OkGo.getInstance().init(this);
        initLogger();
        Stetho.initializeWithDefaults(this);

//        BFConfig.INSTANCE.init(new BaseConfig.Builder()
//                .setBaseUrl(BASE_URL)
//                .setPageSize(24)
//                .setApiQueryCacheMode(BaseConfig.CacheMode.CACHE_ELSE_NETWORK)
//                .setConverter(DocumentConverter.create())
//                .setDebug(true)
//                .build()
//        );
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
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

    public static MApplication getInstance() {
        return application;
    }
}
