package allenhu.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import allenhu.app.R;
import allenhu.app.util.LogUtil;

/**
 * Author：燕青 $ on 16/3/14 17:15
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class MyThirdView extends View {
    private final Thread otherThread;
    private int before;
    private int later;
    private int circleWidth;
    private int speed;
    private Paint mPaint;
    private int mProgress;
    private boolean isNext = false;

    public MyThirdView(Context context) {
        this(context, null);
    }

    public MyThirdView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyThirdView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyThirdView, defStyleAttr, 0);
        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.MyThirdView_thiBeforeColor:
                    before = a.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.MyThirdView_thiLaterColor:
                    later = a.getColor(attr, Color.RED);
                    break;
                case R.styleable.MyThirdView_thiCircleWidth:
                    circleWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.MyThirdView_thiSpeed:
                    speed = a.getInt(attr, 20);// 默认20
                    break;
            }
        }
        a.recycle();
        mPaint = new Paint();
        // 绘图线程
        otherThread = new Thread() {
            public void run() {
                while (true) {
                    mProgress++;
                    if (mProgress == 360) {
                        mProgress = 0;
                        if (!isNext)
                            isNext = true;
                        else
                            isNext = false;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        otherThread.start();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogUtil.e("onAttachedToWindow");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtil.e("third-->onMeasure");
    }


    @Override
    protected void onDraw(Canvas canvas) {

        int centre = getWidth() / 2; // 获取圆心的x坐标
        int radius = centre - circleWidth / 2;// 半径
        mPaint.setStrokeWidth(circleWidth); // 设置圆环的宽度
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心
        LogUtil.e("centre:" + centre + "    radius:" + radius + "     circleWidth:" + circleWidth);
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius); // 用 于定义的圆弧的形状和大小的界限
        if (!isNext) {// 第一颜色的圈完整，第二颜色跑
            mPaint.setColor(before); // 设置圆环的颜色
            canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环
            mPaint.setColor(later); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        } else {
            mPaint.setColor(later); // 设置圆环的颜色
            canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环
            mPaint.setColor(before); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        }

    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LogUtil.e("onDetachedFromWindow");
//        otherThread.stop();
    }
}
