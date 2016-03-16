package allenhu.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import allenhu.app.R;

/**
 * Author：燕青 $ on 16/3/15 15:50
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class FourthView extends View {
    private Rect mRect;
    private Paint mPaint;
    private int mSplitSize;
    private int mSecondColor;
    private int mFirstColor;
    private int mCount;
    private int mCircleWidth;
    private Bitmap mImage = null;
    private int mCurrentCount = 3;

    public FourthView(Context context) {
        this(context, null);
    }

    public FourthView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FourthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs,
                R.styleable.MyFourthView, defStyleAttr, 0);

        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.MyFourthView_bg:
                    mImage = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
                    break;
                case R.styleable.MyFourthView_circleWidth:
                    mCircleWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                            20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.MyFourthView_dotCount:
                    mCount = a.getInt(attr, 20);
                    break;
                case R.styleable.MyFourthView_firstColor:
                    mFirstColor = a.getColor(attr, Color.CYAN);
                    break;
                case R.styleable.MyFourthView_secondColor:
                    mSecondColor = a.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.MyFourthView_splitSize:
                    mSplitSize = a.getInt(attr, 20);
                    break;
            }
        }

        a.recycle();
        mPaint = new Paint();
        mRect = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int width = 0;
        int height = 0;
        if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);  //消除锯齿
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);   //定义线段断电形状为圆头
        mPaint.setStyle(Paint.Style.STROKE);    //设置空心
        int centre = getWidth() / 2;  //获取圆心的x坐标
        int radius = centre - mCircleWidth / 2;//半径

        /**
         * 画块
         */
        drawOval(canvas, centre, radius);
        //计算内切正方形的位置
        int relRadius = radius - mCircleWidth / 2;
        /**
         * 计算内切正方形的距离顶部 = mCircleWidth + relRadius - √2/2
         */
        mRect.left = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
        mRect.top = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
        mRect.right = (int) (mRect.left + Math.sqrt(2) * relRadius);
        mRect.bottom = (int) (mRect.left + Math.sqrt(2) * relRadius);
        /**
         * 如果图片比较小，那么根据图片的尺寸放置到中心
         */
        if (mImage.getWidth() < Math.sqrt(2) * relRadius) {
            mRect.left = (int) (mRect.left + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getWidth() * 1.0f / 2);
            mRect.top = (int) (mRect.top + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getHeight() * 1.0f / 2);
            mRect.right = (int) (mRect.left + mImage.getWidth());
            mRect.bottom = (int) (mRect.top + mImage.getHeight());
        }
        canvas.drawBitmap(mImage, null, mRect, mPaint);
    }

    private void drawOval(Canvas canvas, int centre, int radius) {
        /**
         * 根据需要画的个数以及间隔计算每个块块所占的比例*360
         */
        float itemSize = (360 * 1.0f - mCount * mSplitSize) / mCount;

        //用于定义的圆弧的形状和大小的界限
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);

        mPaint.setColor(mFirstColor); //设置圆环的颜色
        for (int i = 0; i < mCount; i++) {
            //根据进度画圆弧
            canvas.drawArc(oval, i * (itemSize + mSplitSize), itemSize, false, mPaint);

        }
        mPaint.setColor(mSecondColor);
        for (int i = 0; i < mCurrentCount; i++) {
            canvas.drawArc(oval, i * (itemSize + mSplitSize), itemSize, false, mPaint);
        }
    }
}
