package allenhu.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
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


    private int width;
    private int height;
    private int oneCount;
    private int twoCount;
    private int threeCount;
    private int fourCount;
    private int fiveCount;
    private boolean isFirst = true;
    private Rect rect;
    private int bottom = 10;
    private int mWidth;
    private int mHeight;
    private int left;

    public HorizontalBar(Context context) {
        this(context, null);
    }

    public HorizontalBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);




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
//                    bottom = array.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
//                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
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

        if (isFirst) {

            WindowManager wm = (WindowManager) getContext()
                    .getSystemService(Context.WINDOW_SERVICE);
            int measureWidth = wm.getDefaultDisplay().getWidth();
            int measureHeight = wm.getDefaultDisplay().getHeight();
            /**
             * 在view里获取有效的屏幕的宽高
             */
//            int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
//            int measureHeight = MeasureSpec.getSize(heightMeasureSpec);

            LogUtil.e("measureWidth：" + measureWidth + "");
            LogUtil.e("measureHeight：" + measureHeight + "");

            width = measureWidth / 3;

//            setMeasuredDimension(mWidth, mHeight);
            isFirst = false;
            setMeasuredDimension(width, 500);
        }


//        int specMode = MeasureSpec.getMode(widthMeasureSpec);
//        int specSize = MeasureSpec.getSize(widthMeasureSpec);
//
//        LogUtil.e("measureWidth：" + specSize + "");
//        if (specMode == MeasureSpec.EXACTLY) {
//            mWidth = specSize;
//        } else {
//            if (specMode == MeasureSpec.AT_MOST) {
//                mWidth = specSize;
//            }
//            if (specMode == MeasureSpec.UNSPECIFIED) {
//                mWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics());
////                mWidth = Math.min(desire, specSize);
//            }
//        }
//
//        specMode = MeasureSpec.getMode(heightMeasureSpec);
//        specSize = MeasureSpec.getSize(heightMeasureSpec);
//
//        LogUtil.e("measureHeight：" + specSize + "");
//        if (specMode == MeasureSpec.EXACTLY) {
//            mHeight = heightMeasureSpec;
//        } else {
////            int desireByImg = getPaddingTop() + getPaddingBottom() + mImage.getHeight();
////            int desireByText = getPaddingTop() + getPaddingBottom() + mTextBound.height();
//            if (specMode == MeasureSpec.AT_MOST) {
////                int desire = Math.max(desireByImg, desireByText);
////                mHeight = Math.min(desire, specSize);
//                mHeight = specSize;
//            } else if (specMode == MeasureSpec.UNSPECIFIED) {
////                int desire = Math.max(desireByImg, desireByText);
////                mHeight = desire;
//                mHeight = bottom * 5 + left * 4;
//            }
//        }
//        LogUtil.e("mWidth:"+mWidth+"    mHeight:"+mHeight);
//        setMeasuredDimension(mWidth, mHeight);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int[] arr = {oneCount, twoCount, threeCount, fourCount, fiveCount};

        int max = CalculateUtil.getMax(arr);
        if (max <= 0) {
            max = 1;
        }


        left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        bottom = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7, getResources().getDisplayMetrics());
//        circleWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
//                TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));


        rect = new Rect();
        LogUtil.e("left:" + left);
        rect.left = left;

        LogUtil.e("bottom:" + bottom);
        rect.bottom = bottom;
        rect.top = 0;
        rect.right = (int) (fiveCount * 1.0f / max * width);

        Paint paint1 = new Paint();
        int myColor = getResources().getColor(R.color.fiveStart);
        paint1.setColor(myColor);
        canvas.drawRect(rect, paint1);
        paint1.setColor(getResources().getColor(R.color.gray));
//        canvas.drawText(fiveCount + "", rect.right + 10, 15, paint1);


        Paint paint2 = new Paint();
        paint2.setColor(getResources().getColor(R.color.fourStart));
        rect.top = rect.bottom + left;
        rect.right = (int) (fourCount * 1.0f / max * width);
        rect.bottom = rect.top + bottom;
        canvas.drawRect(rect, paint2);
//        canvas.drawText(fourCount + "", rect.right + 10, 37, paint1);


        Paint paint3 = new Paint();
        paint3.setColor(getResources().getColor(R.color.threeStart));
        rect.top = rect.bottom + left;
        rect.right = (int) (threeCount * 1.0f / max * width);
        rect.bottom = rect.top + bottom;
        canvas.drawRect(rect, paint3);
//        canvas.drawText(threeCount + "", right + 10, 59, paint1);


        Paint paint4 = new Paint();
        paint4.setColor(getResources().getColor(R.color.twoStart));
        rect.top = rect.bottom + left;
        rect.right = (int) (twoCount * 1.0f / max * width);
        rect.bottom = rect.top + bottom;
        canvas.drawRect(rect, paint4);


        Paint paint5 = new Paint();
        paint5.setColor(getResources().getColor(R.color.oneStart));
        rect.top = rect.bottom + left;
        rect.right = (int) (oneCount * 1.0f / max * width);
        rect.bottom = rect.top + bottom;
        canvas.drawRect(rect, paint5);
//        canvas.drawText(oneCount + "", right + 10, 103, paint1);


    }
}
