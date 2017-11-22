package allenhu.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.hlib.util.MLogUtil;

/**
 * Author：燕青 $ on 17/1/13 18:32
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class DeleteView extends LinearLayout {
    public DeleteView(Context context) {
        super(context);
    }

    public DeleteView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DeleteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = 0, y;
        int shiftX, shiftY;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = (int) event.getX();
                y = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_MOVE:
                shiftX = (int) (x - event.getX());
                MLogUtil.e("x:" + x + "   shiftX:" + shiftX);
                if (shiftX > 100) {
                    scrollBy(shiftX, 0);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

}
