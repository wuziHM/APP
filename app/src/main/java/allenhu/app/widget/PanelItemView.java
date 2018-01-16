package allenhu.app.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import allenhu.app.R;

/**
 * Author：HM $ on 18/1/12 14:04
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class PanelItemView extends FrameLayout implements ItemView {

    private View overlay;
    private TextView tvName;

    public PanelItemView(Context context) {
        this(context, null);
    }

    public PanelItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PanelItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.view_panel_item, this);
        overlay = findViewById(R.id.overlay);
        tvName = (TextView) findViewById(R.id.tv_name);
    }

    @Override
    public void setFocus(boolean isFocused) {
        if (overlay != null) {
            overlay.setVisibility(isFocused ? INVISIBLE : VISIBLE);
        }
    }

    @Override
    public void setText(String name) {
        tvName.setText(name);
    }

    @Override
    public String getText() {
        return tvName.getText().toString();
    }


}
