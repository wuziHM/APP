package allenhu.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ExpandableListView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import allenhu.app.bean.AreaBean;
import allenhu.app.bean.CityBean;
import allenhu.app.bean.PBean;
import allenhu.app.bean.ProvinceBean;
import allenhu.app.db.AreaDao;
import allenhu.app.db.CityDao;
import allenhu.app.db.ProvinceDao;
import allenhu.app.util.FileUtils;
import allenhu.app.util.LogUtil;

/**
 * Author：燕青 $ on 2016/8/25  10:04
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class MApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences preferences = getSharedPreferences("city_info", Context.MODE_PRIVATE);
        int i = preferences.getInt("isFrist", 0);
        if (i == 0) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
//                    Message message = new Message();
//                    message.what = 5;
                    handler.sendEmptyMessage(5);
                }
            });
            thread.start();
        } else {
//            edit.putInt("isFrist", 1);
//            edit.commit();
        }

//        SharedPreferences
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
                writeCity();
            }
        }
    };

    private void writeCity() {
        try {
            AssetManager asset = getAssets();
            InputStream input = asset.open("region.json");
            int length = input.available();
//            byte[] buffer = new byte[length];
//            input.read(buffer);
//            input.close();
//            String s = new String(buffer, "UTF-8");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            int i;
            while ((i = input.read()) != -1) {
                stream.write(i);
            }
            String s = new String(stream.toByteArray(), "UTF-8");
//            ToastUtils.ToastMessage(this, s);
            Gson gson = new Gson();
            ProvinceDao pdao = new ProvinceDao(this);
            CityDao cdao = new CityDao(this);
            AreaDao adao = new AreaDao(this);
            List<PBean> bean = Arrays.asList(gson.fromJson(s, PBean[].class));
            LogUtil.e(s);
            if (bean != null && bean.size() > 0) {
                for (PBean pb : bean) {
                    LogUtil.e("name:" + pb.getValue());
                    if (pb.getChildren() != null) {
                        LogUtil.e(pb.getChildren().size() + "");
                    }
//                    LogUtil.e("size:" + pb.getCitys().size());
//                    pdao.add(pb);
//                    for (CityBean cb : pb.getCitys()) {
//                        cdao.add(cb);
//                        for (AreaBean ab : cb.getAreas()) {
//                            adao.add(ab);
//                        }
//                    }
                }

//                SharedPreferences preferences = getSharedPreferences("city_info", Context.MODE_PRIVATE);
//                SharedPreferences.Editor edit = preferences.edit();
//                edit.putInt("isFrist", 1);
//                edit.commit();
            } else {
                LogUtil.e("省数组是空的");
            }
//            List<PBean> bean = (List<PBean>) gson.fromJson(s, PBean.class);
            LogUtil.e("size:" + bean.size());
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.e(e.toString());
        }
    }
}
