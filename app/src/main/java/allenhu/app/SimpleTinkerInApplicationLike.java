package allenhu.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import java.io.File;

import allenhu.app.util.FileUtils;
import allenhu.app.util.LogUtil;

/**
 * Author：燕青 $ on 2016/8/25  10:04
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */

@DefaultLifeCycle(application = ".SimpleTinkerInApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL,
        loadVerifyFlag = false)
public class SimpleTinkerInApplicationLike extends ApplicationLike {
    public SimpleTinkerInApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        TinkerInstaller.install(this);

//        SharedPreferences preferences = getApplication().getSharedPreferences("city_info", Context.MODE_PRIVATE);
//        int i = preferences.getInt("isFrist", 0);
//        if (i == 0) {
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
////                    Message message = new Message();
////                    message.what = 5;
//                    handler.sendEmptyMessage(5);
//                }
//            });
//            thread.start();
//        } else {
////            edit.putInt("isFrist", 1);
////            edit.commit();
//        }

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

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 5) {
//                writeCity();
            }
        }
    };

//    private void writeCity() {
//        try {
//            AssetManager asset = getAssets();
//            InputStream input = asset.open("region.json");
//            int length = input.available();
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            int i;
//            while ((i = input.read()) != -1) {
//                stream.write(i);
//            }
//            String s = new String(stream.toByteArray(), "UTF-8");
//            Gson gson = new Gson();
//            ProvinceDao pdao = new ProvinceDao(this);
//            CityDao cdao = new CityDao(this);
//            AreaDao adao = new AreaDao(this);
//            List<PBean> bean = Arrays.asList(gson.fromJson(s, PBean[].class));
//            LogUtil.e(s);
//            if (bean != null && bean.size() > 0) {
//                for (PBean pb : bean) {
//                    LogUtil.e("name:" + pb.getValue());
//                    if (pb.getChildren() != null) {
//                        LogUtil.e(pb.getChildren().size() + "");
//                    }
//                }
//            } else {
//                LogUtil.e("省数组是空的");
//            }
//            LogUtil.e("size:" + bean.size());
//        } catch (IOException e) {
//            e.printStackTrace();
//            LogUtil.e(e.toString());
//        }
//    }
}
