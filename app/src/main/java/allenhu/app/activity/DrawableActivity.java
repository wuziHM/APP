package allenhu.app.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;

public class DrawableActivity extends BaseActivity {

    private int change = 0;
    private int[] ids = new int[]{R.mipmap.looker1, R.mipmap.looker2, R.mipmap.looker3,
            R.mipmap.looker4, R.mipmap.looker5, R.mipmap.looker6, R.mipmap.looker7, R.mipmap.looker8};
    private Drawable[] drawables;
    private ImageView img;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_drawable;
    }

    @Override
    protected void onMCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        img = (ImageView) findViewById(R.id.draw_img);
//        TransitionDrawable drawable = (TransitionDrawable) img.getDrawable();
//        drawable.startTransition(3000);


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.looker1, options);
        options.inSampleSize = computeSampleSize(options, 0, 640 * 931);

        options.inJustDecodeBounds = false;
        drawables = new Drawable[ids.length];

        try {
            for (int i = 0; i < ids.length; i++) {
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), ids[i], options);
                drawables[i] = new BitmapDrawable(bmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(new MyRunnable()).start();
    }

    //计算合适的图片大小
    public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {

        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    //计算合适的图片大小
    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h
                / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
                Math.floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    //处理transition的改变
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            int duration = msg.arg1;
            TransitionDrawable transitionDrawable = null;
            transitionDrawable = new TransitionDrawable(new Drawable[]{
                    drawables[change % ids.length],//实现从0 1 2 3 4 5 0 1 2.。。这样的不停转变
                    drawables[(change + 1) % ids.length]});
            change++;
            img.setImageDrawable(transitionDrawable);
            transitionDrawable.startTransition(duration);
            return false;
        }
    });


    private class MyRunnable implements Runnable {

        @Override
        public void run() {
            while (true) {
                int duration = 3000;//改变的间隔
                Message message = handler.obtainMessage();
                message.arg1 = duration;
                handler.sendMessage(message);
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
