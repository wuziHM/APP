package allenhu.app.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import allenhu.app.R;

/**
 * Created by AllenHu on 2016/2/16.
 */
public class MyView extends View {

    private Paint mPaint;  //绘制线条的Path
    private Path mPath;      //记录用户绘制的Path
    private Canvas mCanvas;  //内存中创建的Canvas
    private Bitmap mBitmap;  //缓存绘制的内容


    private int mLastX;
    private int mLastY;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.yellow));
        mPaint.setAntiAlias(true);  //抗锯齿
        mPaint.setDither(true);     //设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);     //结合处为圆角
        mPaint.setStrokeCap(Paint.Cap.ROUND);       // 设置转弯处为圆角
        mPaint.setStrokeWidth(18.0f);
        mPath = new Path();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         *  int widthMode = MeasureSpec.getMode(widthMeasureSpec);
         int widthSize = MeasureSpec.getSize(widthMeasureSpec);
         int heightMode = MeasureSpec.getMode(heightMeasureSpec);
         int heightSize = MeasureSpec.getSize(heightMeasureSpec);
         int width;
         int height ;
         if (widthMode == MeasureSpec.EXACTLY)
         {
         width = widthSize;
         } else
         {
         mPaint.setTextSize(mTitleTextSize);
         mPaint.getTextBounds(mTitle, 0, mTitle.length(), mBounds);
         float textWidth = mBounds.width();
         int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
         width = desired;
         }

         if (heightMode == MeasureSpec.EXACTLY)
         {
         height = heightSize;
         } else
         {
         mPaint.setTextSize(mTitleTextSize);
         mPaint.getTextBounds(mTitle, 0, mTitle.length(), mBounds);
         float textHeight = mBounds.height();
         int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
         height = desired;
         }
         */

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        // 初始化bitmap,Canvas
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPath();
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    //绘制线条
    private void drawPath() {
        mCanvas.drawPath(mPath, mPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                mPath.moveTo(mLastX, mLastY);
                break;

            case MotionEvent.ACTION_MOVE:
                int dx = Math.abs(x - mLastX);
                int dy = Math.abs(y - mLastY);
                if (dx > 3 || dy > 3) {
                    mPath.lineTo(x, y);
                }
                mLastY = y;
                mLastX = x;
                break;
        }

        invalidate();
        return true;
    }
}
