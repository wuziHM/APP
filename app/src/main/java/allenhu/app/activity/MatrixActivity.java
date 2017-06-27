package allenhu.app.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yongchun.library.view.ImageSelectorActivity;

import java.util.ArrayList;

import allenhu.app.R;
import allenhu.app.base.BaseActivity;
import allenhu.app.util.LogUtil;

public class MatrixActivity extends BaseActivity implements View.OnClickListener {

    private Button btnOpenImage;
    private ImageView ivOrigin, ivAfter;
    private Button btnBig, btnSmall, btnXuanZhuan, btnBaohe, btnDuibi;
    //图片变换参数
    private float smallbig = 1.0f;   //缩放比例
    private int turnRotate = 0;       //旋转度数
    private float saturation = 0f;    //饱和度

    private Bitmap bmp; //原始图片

    @Override
    protected int getLayoutId() {
        return R.layout.activity_matrix;
    }

    @Override
    protected void onMCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        btnOpenImage = (Button) findViewById(R.id.btn_open_image);
        btnOpenImage.setOnClickListener(this);
        ivOrigin = (ImageView) findViewById(R.id.iv_origin);
        ivOrigin.setOnClickListener(this);
        ivAfter = (ImageView) findViewById(R.id.iv_after);
        ivAfter.setOnClickListener(this);
        btnBig = (Button) findViewById(R.id.btn_big);
        btnBig.setOnClickListener(this);
        btnSmall = (Button) findViewById(R.id.btn_small);
        btnSmall.setOnClickListener(this);
        btnXuanZhuan = (Button) findViewById(R.id.btn_xuanzhuan);
        btnXuanZhuan.setOnClickListener(this);
        btnBaohe = (Button) findViewById(R.id.btn_baohe);
        btnBaohe.setOnClickListener(this);
        btnDuibi = (Button) findViewById(R.id.btn_duibi);
        btnDuibi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open_image:
                openImg();
                break;

            case R.id.btn_big:
                bigPicture();
                break;
            case R.id.btn_small:
                smallPicture();
                break;
            case R.id.btn_xuanzhuan:
                turnPicture();
                break;
            case R.id.btn_baohe:
                saturationPicture();

                break;
            case R.id.btn_duibi:
                contrastPicture();
                break;
        }
    }

    private void contrastPicture() {
        ColorMatrix cm = new ColorMatrix();
        float brightness = -25;  //亮度
        float contrast = 2;        //对比度
        cm.set(new float[]{
                contrast, 0, 0, 0, brightness,
                0, contrast, 0, 0, brightness,
                0, 0, contrast, 0, brightness,
                0, 0, 0, contrast, 0
        });
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        //显示图片
        Matrix matrix = new Matrix();
        Bitmap createBmp = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
        Canvas canvas = new Canvas(createBmp);
        canvas.drawBitmap(bmp, matrix, paint);
        ivAfter.setImageBitmap(createBmp);
    }

    private void saturationPicture() {
        //设置饱和度 0表示灰度图像 大于1饱和度增加 0-1饱和度减小
        ColorMatrix cm = new ColorMatrix();
        if (saturation >= 1.5f) {
            saturation = 0f;
        }
        saturation = saturation + 0.1f;
        LogUtil.e("saturation:" + saturation);
        cm.setSaturation(saturation);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        //显示图片
        Matrix matrix = new Matrix();
        Bitmap createBmp = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
        Canvas canvas = new Canvas(createBmp);
        canvas.drawBitmap(createBmp, matrix, paint);
        ivAfter.setImageBitmap(createBmp);
    }

    private void turnPicture() {
        Matrix matrix = new Matrix();
        turnRotate += 15;
        matrix.setRotate(turnRotate, bmp.getWidth() / 2, bmp.getHeight() / 2);
        change(matrix);
    }

    private void smallPicture() {
        if (smallbig > 0.5) {
            smallbig -= 0.1f;
        } else
            smallbig = 0.5f;
        Matrix matrix = new Matrix();
        matrix.preSkew(0.3f, 0.3f);
        matrix.setScale(smallbig, smallbig, bmp.getWidth() / 2, bmp.getHeight() / 2);
        change(matrix);
    }

    private void change(Matrix matrix) {
        Bitmap createBmp = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
        Canvas canvas = new Canvas(createBmp);
        Paint p = new Paint();
        p.setAntiAlias(true);
        canvas.drawBitmap(bmp, matrix, p);
        ivAfter.setImageBitmap(createBmp);
    }

    private void bigPicture() {
        Matrix matrix = new Matrix();
        //缩放区间 0.5-1.0
        if (smallbig < 1.5f)
            smallbig = smallbig + 0.1f;
        else
            smallbig = 1.5f;
        //x y坐标同时缩放
        matrix.setScale(smallbig, smallbig, bmp.getWidth() / 2, bmp.getHeight() / 2);
        Bitmap createBmp = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
//        Bitmap createBmp = Bitmap.createBitmap(ivOrigin.getWidth(), ivOrigin.getHeight(), bmp.getConfig());
        Canvas canvas = new Canvas(createBmp);
        Paint paint = new Paint();
        canvas.drawBitmap(bmp, matrix, paint);
        ivAfter.setImageBitmap(createBmp);
    }

    private void openImg() {
        ImageSelectorActivity.start(MatrixActivity.this, 1, ImageSelectorActivity.MODE_MULTIPLE,
                true, true, true);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == ImageSelectorActivity.REQUEST_IMAGE) {
            ArrayList<String> images = (ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT);
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            int width = dm.widthPixels;    //手机屏幕水平分辨率
            int height = dm.heightPixels;  //手机屏幕垂直分辨率
            for (String s : images) {
//                Glide.with(MatrixActivity.this).load(new File(s)).centerCrop().into(ivOrigin);
                LogUtil.e("地址:" + s);
                BitmapFactory.Options option = new BitmapFactory.Options();
                option.inJustDecodeBounds = true;
                bmp = BitmapFactory.decodeFile(s, option);
                int heightRatio = (int) Math.ceil(option.outHeight / (float) height);
                int widthRatio = (int) Math.ceil(option.outWidth / (float) width);
                if (heightRatio > 1 && widthRatio > 1) {
                    if (heightRatio > widthRatio) {
                        option.inSampleSize = heightRatio * 2;
                    } else {
                        option.inSampleSize = widthRatio * 2;
                    }
                }
                option.inJustDecodeBounds = false;
                bmp = BitmapFactory.decodeFile(s, option);
                LogUtil.e("宽:" + bmp.getWidth() + "   高" + bmp.getHeight());
                ivOrigin.setImageBitmap(bmp);
                ivAfter.setImageBitmap(bmp);
            }
        }
    }

}
