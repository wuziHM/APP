package allenhu.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.SectionIndexer;

import allenhu.app.bean.PrintA;

/**
 * Created by AllenHu on 2016/2/26.
 */
public class IndexScroller {

    private float indexBarWidth;  //索引条宽度
    private float indexBarMargin; //索引条右边距
    private float indexBarPadding;  //在中心显示文本到四边的距离
    private float mDensity;         //当前屏幕密度除以160
    private float mScaledDensity;  ///当前屏幕密度除以160（设置字体大小）
    private float mAlphaRate;       // 透明度
    private int mState = STATE_HIDDEN;      //索引的状态
    private int mListViewWidth;
    private int mListViewHeight;
    private int mCurrentSection = -1;
    private boolean mIsIndexing = false;
    private ListView mListView = null;
    private SectionIndexer mIndex = null;
    private String[] mSections = null;
    private RectF mIndexBarRect;

    private static final int STATE_HIDDEN = 0;
    private static final int STATE_SHOWING = 1;
    private static final int STATE_SHOW = 2;
    private static final int STATE_HIDDING = 3;

    public IndexScroller(Context context, IndexableListView indexableListView) {
        //获取屏幕密度
        mDensity = context.getResources().getDisplayMetrics().density;
        mScaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        mListView = indexableListView;
        setAdapter(mListView.getAdapter());
        //根据屏幕密度技术索引条的宽度(单位：像素)
        indexBarWidth = 20 * mDensity;
        indexBarPadding = 5 * mDensity;
        indexBarMargin = 10 * mDensity;
    }

    public void hide() {

    }

    public void draw(Canvas canvas) {
        // 1、绘制索引条、包括索引条的背景和文本
        // 2、绘制预览文本和背景
        // 如果索引条隐藏、不进行绘制
        if (mState == STATE_HIDDEN) {
            return;
        }
        //设置索引条比诶金的绘制属性
        Paint indexbarPaint = new Paint();
        indexbarPaint.setColor(Color.BLACK);
        indexbarPaint.setAlpha((int) (64 * mAlphaRate));

        //绘制索引条(4个角都是圆角的矩形区域)
        canvas.drawRoundRect(mIndexBarRect, 5 * mDensity, 5 * mDensity, indexbarPaint);
        //绘制Sections
        if (mSections != null && mSections.length > 0) {
            //绘制预览文本和背景
            if (mCurrentSection >= 0) {
                Paint previewPaint = new Paint();
                previewPaint.setColor(Color.BLACK);
                previewPaint.setAlpha(96);

                Paint previewTextPaint = new Paint();
                previewTextPaint.setColor(Color.WHITE);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {

        return false;
    }

    public void show() {

    }

    public void setAdapter(Adapter adapter) {
        if (adapter instanceof SectionIndexer) {
            mIndex = (SectionIndexer) adapter;
            mSections = (String[]) mIndex.getSections();
        }
    }

    public void onSizeChanged(int w, int h, int oldw, int oldh) {

    }
}
