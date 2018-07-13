package allenhu.app.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.hlib.util.MToastUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadListener;
import com.orhanobut.logger.Logger;

import java.io.File;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MultiDownloadActivity extends BaseActivity {

    private static final int REQUEST_PERMISSION_STORAGE = 0x01;

    private static final String[] JSON_STR = {
            "http://image.tianjimedia.com/uploadImages/2014/090/49/48NJD223D503_680x500.jpg",
            "http://image.tianjimedia.com/uploadImages/2014/090/50/5FL76PO6519X_680x500.jpg",
            "http://image.tianjimedia.com/uploadImages/2014/090/50/X4W0465U1S5D_680x500.jpg",
            "http://i2.meizitu.net/2018/07/06a04.jpg",
            "http://i2.meizitu.net/2018/07/06a01.jpg",
            "http://i2.meizitu.net/2018/07/06a02.jpg",
            "http://i2.meizitu.net/2018/07/06a03.jpg",
            "http://image.tianjimedia.com/uploadImages/2014/090/50/3X9943E0WIZ5_680x500.jpg",
            "http://image.tianjimedia.com/uploadImages/2014/090/50/61IJ21I0QK79_680x500.jpg",
            "http://image.tianjimedia.com/uploadImages/2014/090/51/3NU5DPYCIN8A_680x500.jpg",
            "http://image.tianjimedia.com/uploadImages/2014/090/51/U7RL64VA7W0O_680x500.jpg"};


    @BindView(R.id.btn_download)
    Button btnDownload;

    @BindView(R.id.line)
    LinearLayout line;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_download);
        ButterKnife.bind(this);

        OkDownload.getInstance().setFolder(Environment.getExternalStorageDirectory().getAbsolutePath() + "/wuzi/");
        OkDownload.getInstance().getThreadPool().setCorePoolSize(3);

        checkSDCardPermission();
    }


    @OnClick(R.id.btn_download)
    public void onClick() {
        int i = 1;


        for (String url : JSON_STR) {

            //这里只是演示，表示请求可以传参，怎么传都行，和okgo使用方法一样
            GetRequest<File> request = OkGo.<File>get(url)//
                    .headers("aaa", "111")//
                    .params("bbb", "222");

            //这里第一个参数是tag，代表下载任务的唯一标识，传任意字符串都行，需要保证唯一,我这里用url作为了tag
            OkDownload.request(url, request)//
                    .priority(i)//
                    .save()//
                    .register(listener)//
                    .start();
            i++;
        }


    }

    private LogDownloadListener listener = new LogDownloadListener();

    private class LogDownloadListener extends DownloadListener {
        public LogDownloadListener() {
            super("wuzi");
        }

        @Override
        public void onStart(Progress progress) {
            Logger.i("开始下载:" + progress.toString());
        }

        @Override
        public void onProgress(Progress progress) {

            Logger.i("下载中:" + progress.toString());
//            Logger.i("正在下载:" + progress.totalSize / progress.currentSize + "   " + progress.speed);

        }

        @Override
        public void onError(Progress progress) {
            Logger.i("异常了");

        }

        @Override
        public void onFinish(File file, Progress progress) {
            Logger.i("下载完了");
            ImageView imageView = new ImageView(MultiDownloadActivity.this);
            Glide.with(MultiDownloadActivity.this)
                    .load(file)
                    .into(imageView);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            line.addView(imageView, params);

        }

        @Override
        public void onRemove(Progress progress) {
            Logger.i("取消了监听了");


        }
    }


    /**
     * 检查SD卡权限
     */
    protected void checkSDCardPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //获取权限
            } else {
                MToastUtil.show(this, "权限被禁止，无法下载文件！");
            }
        }
    }
}
