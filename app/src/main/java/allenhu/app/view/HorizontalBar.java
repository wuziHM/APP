package allenhu.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.concurrent.ForkJoinPool;

import allenhu.app.R;
import allenhu.app.util.CalculateUtil;

/**
 * Author：燕青 $ on 2016/3/28  17:54
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class HorizontalBar extends View {

    private boolean isShowStar;
    private Bitmap mImage;
    private int oneCount;
    private int twoCount;
    private int threeCount;
    private int fourCount;
    private int fiveCount;
    private boolean isShowText;
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

        mImage = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_star);


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
                case R.styleable.HorizontalBar_showCount:
                    isShowText = array.getBoolean(attr, false);
                    break;
                case R.styleable.HorizontalBar_showStar:
                    isShowStar = array.getBoolean(attr, false);
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

    public void setIsShowText(boolean isShowText) {
        this.isShowText = isShowText;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            mWidth = specSize;
        } else {
            mWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
            //如果显示后面的字就增加长度
            mWidth1 = mWidth;
            if (isShowText) {
                mWidth1 = mWidth1 + 3 * bottom;
            }
            if (isShowStar) {
                mWidth1 = mWidth1 + mImage.getWidth() * 5;
            }
        }

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            mHeight = specSize;
        } else {
            mHeight = bottom * 5 + left * 4 + 5;
            int imgheight = mImage.getHeight() * 5;
            mHeight = Math.max(mHeight, imgheight);
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
        rect.left = isShowStar ? left + mImage.getWidth() * 5 : left;
        rect.top = left;
        rect.bottom = rect.top + bottom;
        rect.right = (int) (fiveCount * 1.0f / max * mWidth) + rect.left;

        Paint textPaint = new Paint();
        textPaint.setColor(Color.GRAY);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        mBound = new Rect();
        int textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics());
        textPaint.setTextSize(textSize);
        textPaint.getTextBounds("" + max, 0, ("" + max).length(), mBound);

        Paint paint1 = new Paint();
        int myColor = getResources().getColor(R.color.fiveStart);
        paint1.setColor(myColor);
        canvas.drawRect(rect, paint1);
        if (isShowText)
            canvas.drawText("" + fiveCount, rect.right + left, (rect.top + rect.bottom) / 2 + mBound.width() / 2, textPaint);

        paint1.setColor(getResources().getColor(R.color.fourStart));
        rect.top = rect.bottom + left;
        rect.right = (int) (fourCount * 1.0f / max * mWidth) + rect.left;
        rect.bottom = rect.top + bottom;
        canvas.drawRect(rect, paint1);
        if (isShowText)
            canvas.drawText("" + fourCount, rect.right + left, (rect.top + rect.bottom) / 2 + mBound.width() / 2, textPaint);


        paint1.setColor(getResources().getColor(R.color.threeStart));
        rect.top = rect.bottom + left;
        rect.right = (int) (threeCount * 1.0f / max * mWidth) + rect.left;
        rect.bottom = rect.top + bottom;
        canvas.drawRect(rect, paint1);
        if (isShowText)
            canvas.drawText("" + threeCount, rect.right + left, (rect.top + rect.bottom) / 2 + mBound.width() / 2, textPaint);


        paint1.setColor(getResources().getColor(R.color.twoStart));
        rect.top = rect.bottom + left;
        rect.right = (int) (twoCount * 1.0f / max * mWidth) + rect.left;
        rect.bottom = rect.top + bottom;
        canvas.drawRect(rect, paint1);
        if (isShowText)
            canvas.drawText("" + twoCount, rect.right + left, (rect.top + rect.bottom) / 2 + mBound.width() / 2, textPaint);


        paint1.setColor(getResources().getColor(R.color.oneStart));
        rect.top = rect.bottom + left;
        rect.right = (int) (oneCount * 1.0f / max * mWidth) + rect.left;
        rect.bottom = rect.top + bottom;
        canvas.drawRect(rect, paint1);
        if (isShowText)
            canvas.drawText("" + oneCount, rect.right + left, (rect.top + rect.bottom) / 2 + mBound.width() / 2, textPaint);

        /**
         * 如果要画星星
         */
        if (isShowStar) {
            Rect imgRect = new Rect();
            for (int i = 0; i < 5; i++) {
                for (int j = i; j < 5; j++) {
                    imgRect.left = j * mImage.getWidth();
                    imgRect.right = (j + 1) * mImage.getWidth();
                    imgRect.top = i * mImage.getHeight();
                    imgRect.bottom = (i + 1) * mImage.getHeight();
                    canvas.drawBitmap(mImage, null, imgRect, paint1);
                }
            }
        }
//        imgRect.left = 0;
//        imgRect.right = mImage.getWidth();
//        imgRect.bottom = mImage.getHeight();
//        imgRect.top = 5;


    }
}
