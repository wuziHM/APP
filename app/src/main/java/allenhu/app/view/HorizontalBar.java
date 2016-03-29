package allenhu.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author：燕青 $ on 2016/3/28  17:54
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class HorizontalBar extends View {
    public HorizontalBar(Context context) {
        this(context, null);
    }

    public HorizontalBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        Rect rec1 = new Rect(20, 20, 200, 60);
        canvas.drawRect(rec1, paint);

        Rect rec2 = new Rect(20, 70, 200, 110);
        canvas.drawRect(rec2, paint);

        Rect rec3 = new Rect(20, 120, 200, 160);
        canvas.drawRect(rec3, paint);

        Rect rec4 = new Rect(20, 170, 200, 210);
        canvas.drawRect(rec4, paint);

        Rect rec5 = new Rect(20, 220, 200, 260);
        canvas.drawRect(rec5, paint);


    }
}
