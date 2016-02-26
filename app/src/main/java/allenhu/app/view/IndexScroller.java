package allenhu.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.SectionIndexer;

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
