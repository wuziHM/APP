package allenhu.app.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Author：HM $ on 17/11/24 09:25
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class MyViewPager extends ViewPager {
    private float preX;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean res = super.onInterceptTouchEvent(ev);
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            preX = ev.getX();
        } else {
            if (Math.abs(ev.getX() - preX) > 4) {
                return true;
            } else {
                preX = ev.getX();
            }
        }
        return res;
    }

}
