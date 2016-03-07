package allenhu.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;

/**
 * 封装添加了右边的条目的listview
 * <p/>
 * Created by AllenHu on 2016/2/26.
 */
public class IndexableListView extends ListView {

    private boolean mIsFastScrollEnabled = false;
    private IndexScroller mScroller = null;
    private GestureDetector mGestureDetector = null;

    public IndexableListView(Context context) {
        super(context);
    }

    public IndexableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IndexableListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isFastScrollEnabled() {
        return mIsFastScrollEnabled;
    }

    @Override
    public void setFastScrollEnabled(boolean enabled) {
        mIsFastScrollEnabled = enabled;
        if (mIsFastScrollEnabled) {
            if (mScroller == null)
                mScroller = new IndexScroller(getContext(), this);
        } else {
            if (mScroller != null) {
                mScroller.hide();
                mScroller = null;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        // Overlay index bar
        if (mScroller != null)
            mScroller.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Intercept ListView's touch event
        if (mScroller != null && mScroller.onTouchEvent(ev))
            return true;

        if (mGestureDetector == null) {
            // 创建一个GestureDetector（手势探测器）
            mGestureDetector = new GestureDetector(getContext(),
                    new GestureDetector.SimpleOnGestureListener() {

                        @Override
                        public boolean onFling(MotionEvent e1, MotionEvent e2,
                                               float velocityX, float velocityY) {
                            // If fling happens, index bar shows
                            // 显示索引条
                            mScroller.show();
                            return super.onFling(e1, e2, velocityX, velocityY);
                        }

                    });
        }
        mGestureDetector.onTouchEvent(ev);

        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        if (mScroller != null)
            mScroller.setAdapter(adapter);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mScroller != null)
            mScroller.onSizeChanged(w, h, oldw, oldh);
    }

}