package allenhu.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import allenhu.app.R;
import allenhu.app.util.LogUtil;

/**
 * 作者：燕青 $ on 16/3/10 15:12
 * 邮箱：359222347@qq.com
 * <p/>
 * use to...
 */
public class MyFirstView extends View {

    /**
     * 1、自定义View的属性
     * 2、在View的构造方法中获得我们自定义的属性
     * 3、重写onMeasure
     * 4、重写onDraw
     *
     * @param context
     */

    private static final int TEXTFLAG = 0;
    private static final int BACKFLAG = 1;
    private String mTitle;

    private int mBackColor;
    private int mTextColor;
    private Paint textPaint;
    private Paint backPaint;

    private Rect mBound;

    public MyFirstView(Context context) {
        this(context, null);
    }

    public MyFirstView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyFirstView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyFirstView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.MyFirstView_backColor:
                    mBackColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.MyFirstView_mTextColor:
                    mTextColor = a.getColor(attr, Color.WHITE);
                    break;
                case R.styleable.MyFirstView_mText:
                    mTitle = a.getString(attr);
                    LogUtil.e("Title:" + mTitle);
                    break;
            }
        }
        a.recycle();
        textPaint = new Paint(TEXTFLAG);
        textPaint.setColor(mTextColor);
        LogUtil.e("实例化画笔");

        backPaint = new Paint(BACKFLAG);
        backPaint.setColor(mBackColor);

        mBound = new Rect();
        textPaint.getTextBounds(mTitle, 0, mTitle.length(), mBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            textPaint.getTextBounds(mTitle, 0, mTitle.length(), mBound);
            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
//            mPaint.setTextSize(mTitleTextSize);
            textPaint.getTextBounds(mTitle, 0, mTitle.length(), mBound);
            float textHeight = mBound.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }


        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), backPaint);
//        backPaint.setColor(mBackColor);
//        LogUtil.e("getMeasuredWidth:" + getMeasuredWidth() + "   getMeasuredHeight:"
//                + getMeasuredHeight() + "    getWidth:" + getWidth() + "     getHeight:" + getHeight() +
//                "   mBound.width:" + mBound.width()+"   mBound.height:"+mBound.height());
        canvas.drawText(mTitle, (getWidth() - mBound.width()) / 2, (getHeight() + mBound.height()) / 2, backPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }
}
