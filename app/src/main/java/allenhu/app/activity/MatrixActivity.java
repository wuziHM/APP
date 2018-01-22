package allenhu.app.activity;

import android.Manifest;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.util.GlideImageLoader;
import allenhu.app.util.PhotoUtil;
import allenhu.app.util.ToastUtils;
import io.reactivex.functions.Consumer;

public class MatrixActivity extends BaseActivity implements View.OnClickListener {

    private Button btnOpenImage;
    private ImageView ivOrigin, ivAfter;
    private Button btnBig, btnSmall, btnXuanZhuan, btnBaohe, btnDuibi;
    //图片变换参数
    private float smallbig = 1.0f;   //缩放比例
    private int turnRotate = 0;       //旋转度数
    private float saturation = 0f;    //饱和度

    private Bitmap bmp; //原始图片
    private ArrayList<ImageItem> imageItems;
    private GlideImageLoader glideImageLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        initView();

    }

    private void initView() {

        glideImageLoader = new GlideImageLoader();
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
        requestPermissions();

    }

    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission
                .requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.SEND_SMS)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            Logger.d(permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            Logger.d(permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            Logger.d(permission.name + " is denied.");
                        }
                    }
                });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open_image:
                PhotoUtil.openImg(this, glideImageLoader, false,
                        true, 1, false, PhotoUtil.OPEN_IMAGE_REQUEST_CODER);
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
        Logger.d("saturation:" + saturation);
        cm.setSaturation(saturation);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        //显示图片
        Matrix matrix = new Matrix();
        Bitmap createBmp = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
        Canvas canvas = new Canvas(createBmp);
        canvas.drawBitmap(bmp, matrix, paint);
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

//    private void openImg() {
//
//        ImagePicker imagePicker = ImagePicker.getInstance();
//        imagePicker.setImageLoader(glideImageLoader);
//        imagePicker.setMultiMode(false);   //多选
//        imagePicker.setShowCamera(true);  //显示拍照按钮
//        imagePicker.setSelectLimit(1);    //最多选择9张
//        imagePicker.setCrop(false);       //不进行裁剪
//        Intent intent = new Intent(this, ImageGridActivity.class);
//        startActivityForResult(intent, 100);
//
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;    //手机屏幕水平分辨率
        int height = dm.heightPixels;  //手机屏幕垂直分辨率
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == PhotoUtil.OPEN_IMAGE_REQUEST_CODER) {
                imageItems = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (imageItems != null && imageItems.size() > 0) {

                    setImageMap(width, height);
                    Glide.with(MatrixActivity.this).load(new File(imageItems.get(0).path)).into(ivOrigin);
                    ToastUtils.ToastMessage(this, "图片加载完成");
                } else {
                    ToastUtils.ToastMessage(this, "选择图片失败");
                }
            } else {
                Toast.makeText(this, "没有选择图片", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * @param width  手机屏幕宽度
     * @param height 手机屏幕高度
     */
    private void setImageMap(float width, float height) {
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        Logger.d(imageItems.get(0).path);
        bmp = BitmapFactory.decodeFile(imageItems.get(0).path, option);
        if (bmp == null) {
            Logger.d("bmp还是空的");
        }

        int heightRatio = (int) Math.ceil(option.outHeight / height);
        int widthRatio = (int) Math.ceil(option.outWidth / width);
        if (heightRatio > 1 && widthRatio > 1) {
            if (heightRatio > widthRatio) {
                option.inSampleSize = heightRatio * 2;
            } else {
                option.inSampleSize = widthRatio * 2;
            }
        }
        option.inJustDecodeBounds = false;

        //设置好option之后再赋值一次，第一次是空的，根据屏幕大小去适应出来
        bmp = BitmapFactory.decodeFile(imageItems.get(0).path, option);
        ivAfter.setImageBitmap(bmp);
    }

}
