package allenhu.app.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import allenhu.app.R;

/**
 * Author：HM $ on 18/7/13 14:45
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class EmptyView extends LinearLayout {
    public EmptyView(Context context) {
        this(context, null);
    }

    public EmptyView(Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public EmptyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.layout_empty, this);
    }

}
