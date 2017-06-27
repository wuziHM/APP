package allenhu.app.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import allenhu.app.R;

public class CaClothes extends Activity implements View.OnTouchListener {

    private ImageView imgbefore;
    private ImageView imgafter;
    int[] imageIds1 = new int[]
            {
                    R.mipmap.pre1, R.mipmap.pre2, R.mipmap.pre3, R.mipmap.pre4,
                    R.mipmap.pre5, R.mipmap.pre6, R.mipmap.pre7, R.mipmap.pre8,
                    R.mipmap.pre9, R.mipmap.pre10, R.mipmap.pre11, R.mipmap.pre12,
                    R.mipmap.pre13, R.mipmap.pre14, R.mipmap.pre15, R.mipmap.pre16,
                    R.mipmap.pre17, R.mipmap.pre18, R.mipmap.pre19, R.mipmap.pre20,
                    R.mipmap.pre21
            };


    int[] imageIds2 = new int[]
            {
                    R.mipmap.after1, R.mipmap.after2, R.mipmap.after3, R.mipmap.after4,
                    R.mipmap.after5, R.mipmap.after6, R.mipmap.after7, R.mipmap.after8,
                    R.mipmap.after9, R.mipmap.after10, R.mipmap.after11, R.mipmap.after12,
                    R.mipmap.after13, R.mipmap.after14, R.mipmap.after15, R.mipmap.after16,
                    R.mipmap.after17, R.mipmap.after18, R.mipmap.after19, R.mipmap.after20,
                    R.mipmap.after21
            };
    private int position;
    private Bitmap before;
    private Bitmap after;
    private Bitmap alterBitmap;
    private Canvas canvas;
    private Paint paint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca_clothes);

        Bundle bd = getIntent().getExtras();
        position = Integer.parseInt(bd.getString("num"));
        bindViews();
    }

    private void bindViews() {
        imgafter = (ImageView) findViewById(R.id.img_after);
        imgbefore = (ImageView) findViewById(R.id.img_before);

        BitmapFactory.Options options = new BitmapFactory.Options();
        after = BitmapFactory.decodeResource(getResources(), imageIds2[position], options);
        before = BitmapFactory.decodeResource(getResources(), imageIds1[position], options);

        alterBitmap = Bitmap.createBitmap(before.getWidth(), before.getHeight(), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(alterBitmap);
        paint = new Paint();
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(20);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        canvas.drawBitmap(before, new Matrix(), paint);
        imgafter.setImageBitmap(after);
        imgbefore.setImageBitmap(before);
        imgbefore.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                int newX = (int) event.getX();
                int newY = (int) event.getY();
                for (int i = -20; i < 20; i++) {
//                    for (int j = -20; j < 20; j++) {
                    for (int j = -(int) (Math.sin(Math.acos(Math.abs(i) / 20.0)) * 20); j < (int) (Math.sin(Math.acos(Math.abs(i) / 20.0)) * 20); j++) {
                        if (i + newX >= 0 && j + newY >= 0 && i + newX < before.getWidth() && j + newY < before.getHeight())
                            alterBitmap.setPixel(i + newX, j + newY, Color.TRANSPARENT);
                    }
                }
                imgbefore.setImageBitmap(alterBitmap);
                break;


        }
        return true;
    }
}
