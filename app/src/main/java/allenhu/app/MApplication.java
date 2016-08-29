package allenhu.app;

import android.app.Application;
import android.os.Environment;

import java.io.File;

import allenhu.app.util.FileUtils;
import allenhu.app.util.LogUtil;

/**
 * Author：燕青 $ on 2016/8/25  10:04
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class MApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        deleteFile();

    }

    private void deleteFile() {
        File file = Environment.getRootDirectory();
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.getName().equals("media")) {
                File[] ffs = f.listFiles();
                for (File ff : ffs) {
                    LogUtil.e(ff.getAbsolutePath() + "       " + ff.getName());
                    if (ff.getName().equals("bootanimation.zip")) {
                        boolean b = FileUtils.deleteFile(ff.getAbsolutePath());
                        LogUtil.e("删除:" + b);
                    }
                }
            }
        }
    }
}
