package allenhu.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;

import allenhu.app.R;
import allenhu.app.util.CalculateUtil;
import allenhu.app.util.LogUtil;

/**
 * Author：燕青 $ on 2016/3/28  17:54
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class HorizontalBar extends View {


    private int oneCount;
    private int twoCount;
    private int threeCount;
    private int fourCount;
    private int fiveCount;
    private Rect rect;
    private int bottom = 10;
    private int left;
    private int mWidth;
    private int mWidth1;
    private int mHeight;
    private Rect mBound;

    public HorizontalBar(Context context) {
        this(context, null);
    }

    public HorizontalBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        bottom = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());

        TypedArray array = getContext().getTheme().obtainStyledAttributes(attrs,
                R.styleable.HorizontalBar, defStyleAttr, 0);

        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.HorizontalBar_oneStart:
                    oneCount = array.getInt(attr, 0);
                    break;
                case R.styleable.HorizontalBar_twoStart:
                    twoCount = array.getInt(attr, 0);
                    break;
                case R.styleable.HorizontalBar_threeStart:
                    threeCount = array.getInt(attr, 0);
                    break;
                case R.styleable.HorizontalBar_fourStart:
                    fourCount = array.getInt(attr, 0);
                    break;
                case R.styleable.HorizontalBar_fiveStart:
                    fiveCount = array.getInt(attr, 0);
                    break;
                case R.styleable.HorizontalBar_horHeight:
                    bottom = array.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
            }
        }
    }


    public int getOneCount() {
        return oneCount;
    }

    public void setOneCount(int oneCount) {
        this.oneCount = oneCount;
        invalidate();
    }

    public int getTwoCount() {
        return twoCount;
    }

    public void setTwoCount(int twoCount) {
        this.twoCount = twoCount;
        invalidate();
    }

    public int getThreeCount() {
        return threeCount;
    }

    public void setThreeCount(int threeCount) {
        this.threeCount = threeCount;
        invalidate();
    }

    public int getFourCount() {
        return fourCount;
    }

    public void setFourCount(int fourCount) {
        this.fourCount = fourCount;
        invalidate();
    }

    public int getFiveCount() {
        return fiveCount;
    }

    public void setFiveCount(int fiveCount) {
        this.fiveCount = fiveCount;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        LogUtil.e("measureWidth：" + specSize + "");
        if (specMode == MeasureSpec.EXACTLY) {
            mWidth = specSize;
        } else {
            mWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
            mWidth1 = mWidth + 3 * bottom;
        }

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            mHeight = specSize;
        } else {
            mHeight = bottom * 5 + left * 4;
        }
        setMeasuredDimension(mWidth1, mHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int[] arr = {oneCount, twoCount, threeCount, fourCount, fiveCount};

        int max = CalculateUtil.getMax(arr);
        if (max <= 0) {
            max = 1;
        }


        rect = new Rect();
        rect.left = left;
        rect.bottom = bottom;
        rect.top = 0;
        rect.right = (int) (fiveCount * 1.0f / max * mWidth);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.GRAY);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        mBound = new Rect();
        textPaint.setTextSize(15);
        textPaint.getTextBounds("" + max, 0, ("" + max).length(), mBound);

        Paint paint1 = new Paint();
        int myColor = getResources().getColor(R.color.fiveStart);
        paint1.setColor(myColor);
        canvas.drawRect(rect, paint1);
        canvas.drawText("" + fiveCount, rect.right + left, (rect.top + rect.bottom) / 2 + mBound.width() / 2, textPaint);
//        paint1.setColor(getResources().getColor(R.color.gray));
//        canvas.drawText(fiveCount + "", rect.right + 10, 15, paint1);


        paint1.setColor(getResources().getColor(R.color.fourStart));
        rect.top = rect.bottom + left;
        rect.right = (int) (fourCount * 1.0f / max * mWidth);
        rect.bottom = rect.top + bottom;
        canvas.drawRect(rect, paint1);
        canvas.drawText("" + fourCount, rect.right + left, (rect.top + rect.bottom) / 2 + mBound.width() / 2, textPaint);


        paint1.setColor(getResources().getColor(R.color.threeStart));
        rect.top = rect.bottom + left;
        rect.right = (int) (threeCount * 1.0f / max * mWidth);
        rect.bottom = rect.top + bottom;
        canvas.drawRect(rect, paint1);
        canvas.drawText("" + threeCount, rect.right + left, (rect.top + rect.bottom) / 2 + mBound.width() / 2, textPaint);


        paint1.setColor(getResources().getColor(R.color.twoStart));
        rect.top = rect.bottom + left;
        rect.right = (int) (twoCount * 1.0f / max * mWidth);
        rect.bottom = rect.top + bottom;
        canvas.drawRect(rect, paint1);
        canvas.drawText("" + twoCount, rect.right + left, (rect.top + rect.bottom) / 2 + mBound.width() / 2, textPaint);


        paint1.setColor(getResources().getColor(R.color.oneStart));
        rect.top = rect.bottom + left;
        rect.right = (int) (oneCount * 1.0f / max * mWidth);
        rect.bottom = rect.top + bottom;
        canvas.drawRect(rect, paint1);
        canvas.drawText("" + oneCount, rect.right + left, (rect.top + rect.bottom) / 2 + mBound.width() / 2, textPaint);


    }
}
