package allenhu.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import allenhu.app.R;

/**
 * 作者：燕青 $ on 16/3/14 10:47
 * 邮箱：359222347@qq.com
 * <p/>
 * use to...
 */
public class MySecondView extends View {
    private Bitmap mImage;
    private int mImageScale;
    private String mTitle;
    private int mTextSize;
    private int mColor;
    private Rect rec;
    private Paint mPaint;
    private Rect mTextBound;
    int mWidth = 0;
    int mHeight = 0;
    private static final int IMAGE_SCALE_FITXY = 0;
    private static final int IMAGE_SCALE_CENTER = 1;

    public MySecondView(Context context) {
        this(context, null);
    }

    public MySecondView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySecondView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);

    }

    private void init(Context context, AttributeSet att, int defStyleAttr) {

        TypedArray array = getContext().getTheme().obtainStyledAttributes(att,
                R.styleable.MySecondView, defStyleAttr, 0);
        mTitle = "";
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.MySecondView_secImage:
                    mImage = BitmapFactory.decodeResource(getResources(), array.getResourceId(attr, 0));
                    break;
                case R.styleable.MySecondView_secImageType:
                    mImageScale = array.getInt(attr, 0);
                    break;
                case R.styleable.MySecondView_secText:
                    mTitle = array.getString(attr);
                    break;
                case R.styleable.MySecondView_secTextSize:
                    mTextSize = array.getDimensionPixelSize(attr,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                                    16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.MySecondView_secTextColor:
                    mColor = array.getColor(attr, Color.BLACK);
                    break;
            }
        }
        array.recycle();
        rec = new Rect();
        mPaint = new Paint();
        mTextBound = new Rect();
        mPaint.setTextSize(mTextSize);
        mPaint.getTextBounds(mTitle, 0, mTitle.length(), mTextBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            mWidth = specSize;
        } else {
            //由图片决定宽度
            int desireByImg = getPaddingLeft() + getPaddingRight() + mImage.getWidth();
            //由文字决定宽度
            int desireByText = getPaddingLeft() + getPaddingRight() + mTextBound.width();
            if (specMode == MeasureSpec.AT_MOST) {
                int desire = Math.max(desireByImg, desireByText);
                mWidth = Math.min(desire, specSize);
            }
            if (specMode == MeasureSpec.UNSPECIFIED) {
                int desire = Math.max(desireByImg, desireByText);
                mWidth = Math.min(desire, specSize);
            }
        }

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            mHeight = heightMeasureSpec;
        } else {
            int desireByImg = getPaddingTop() + getPaddingBottom() + mImage.getHeight();
            int desireByText = getPaddingTop() + getPaddingBottom() + mTextBound.height();
            if (specMode == MeasureSpec.AT_MOST) {
                int desire = Math.max(desireByImg, desireByText);
                mHeight = Math.min(desire, specSize);
            } else if (specMode == MeasureSpec.UNSPECIFIED) {
                int desire = Math.max(desireByImg, desireByText);
                mHeight = desire;
            }
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        rec.left = getPaddingLeft();
        rec.right = mWidth - getPaddingRight();
        rec.top = getPaddingTop();
        rec.bottom = getHeight() - getPaddingBottom();

        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.FILL);
        /**
         * 当前的宽度小于字体需要的宽度的时候，将字体改为
         */
        if (mTextBound.width() > mWidth) {
            TextPaint textPaint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mTitle, textPaint, (float) (mWidth - getPaddingLeft() - getPaddingRight()),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);
        } else {
            canvas.drawText(mTitle, (mWidth - mTextBound.width()) / 2 - 1, mHeight - getPaddingBottom() + 10, mPaint);
        }

        rec.bottom -= mTextBound.height();
        if (mImageScale == IMAGE_SCALE_FITXY) {
            canvas.drawBitmap(mImage, null, rec, mPaint);
        } else {
            //计算居中范围
            rec.left = mWidth / 2 - mImage.getWidth() / 2;
            rec.right = mWidth / 2 + mImage.getWidth() / 2;
            rec.top = ((mHeight - mTextBound.height()) - mImage.getHeight()) / 2;
            rec.bottom = ((mHeight - mTextBound.height()) + mImage.getHeight()) / 2 - 10;
            canvas.drawBitmap(mImage, null, rec, mPaint);
        }
//        mImage.recycle();
    }
}
