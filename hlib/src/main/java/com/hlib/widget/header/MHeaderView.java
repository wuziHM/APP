package com.hlib.widget.header;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hlib.R;
import com.hlib.app.HActivity;
import com.hlib.util.MKeyboardUtil;

/**
 * Author：燕青 $ on 17/6/26 10:13
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class MHeaderView extends RelativeLayout implements MHeaderViewAble {

    private RelativeLayout rootView;
    private View headerView;

    private LinearLayout headerViewLeftView;
    private ImageView ivHeaderViewLeft;
    private TextView tvHeaderViewLeft;
    private LinearLayout headerViewRightView;
    private ImageView ivHeaderViewRight;
    private TextView tvHeaderViewRight;
    private TextView tvHeaderViewTitle;
    private View headerViewLineAtBottom;

    public MHeaderView(Context context, RelativeLayout rootView) {
        super(context);
        addToRootView(rootView);
    }

    public MHeaderView(Context context, AttributeSet attrs, RelativeLayout rootView) {
        super(context, attrs);
        addToRootView(rootView);
    }

    public MHeaderView(Context context, AttributeSet attrs, int defStyleAttr, RelativeLayout rootView) {
        super(context, attrs, defStyleAttr);
        addToRootView(rootView);
    }

    private void init(RelativeLayout parentView) {
        headerView = LayoutInflater.from(getContext()).inflate(R.layout.m_header_view, parentView, false);
        headerViewLeftView = (LinearLayout) headerView.findViewById(R.id.headerViewLeftView);
        ivHeaderViewLeft = (ImageView) headerView.findViewById(R.id.ivHeaderViewLeft);
        tvHeaderViewLeft = (TextView) headerView.findViewById(R.id.tvHeaderViewLeft);

        headerViewRightView = (LinearLayout) headerView.findViewById(R.id.headerViewRightView);
        ivHeaderViewRight = (ImageView) headerView.findViewById(R.id.ivHeaderViewRight);
        tvHeaderViewRight = (TextView) headerView.findViewById(R.id.tvHeaderViewRight);

        tvHeaderViewTitle = (TextView) headerView.findViewById(R.id.tvHeaderViewTitle);
        headerViewLineAtBottom = headerView.findViewById(R.id.headerViewLineAtBottom);

        headerViewLeftView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MKeyboardUtil.hideKeyboard(v);
                HActivity.finishActivity(getContext());
            }
        });
    }

    @Override
    public void setOnClickLeftViewListener(OnClickListener onClickListener) {
        headerViewLeftView.setOnClickListener(onClickListener);
    }

    @Override
    public void setOnClickRightViewListener(OnClickListener onClickListener) {
        headerViewRightView.setOnClickListener(onClickListener);
    }

    @Override
    public void showLeftView() {
        headerViewLeftView.setVisibility(VISIBLE);
    }

    @Override
    public void hideLeftView() {
        headerViewLeftView.setVisibility(INVISIBLE);
    }

    @Override
    public void showRightView() {
        headerViewRightView.setVisibility(VISIBLE);
    }

    @Override
    public void hideRightView() {
        headerViewRightView.setVisibility(INVISIBLE);
    }

    @Override
    public LinearLayout getLeftView() {
        return headerViewLeftView;
    }

    @Override
    public TextView getTitleView() {
        return tvHeaderViewTitle;
    }

    @Override
    public LinearLayout getRightView() {
        return headerViewRightView;
    }

    @Override
    public void setBottomLineColor(int colorId) {
        try {
            headerViewLineAtBottom.setBackgroundColor(colorId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setBgColor(int colorId) {
        try {
            headerView.setBackgroundColor(colorId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setRightViewTitle(String title) {
        tvHeaderViewRight.setText(title);
    }

    @Override
    public void setLeftViewTitle(String title) {
        tvHeaderViewLeft.setText(title);
    }

    @Override
    public void setLeftViewImage(int resId) {
        ivHeaderViewLeft.setImageResource(resId);
    }

    @Override
    public void setRightViewImage(int resId) {
        ivHeaderViewRight.setImageResource(resId);
    }

    @Override
    public void setTitle(String title) {
        tvHeaderViewTitle.setText(title);
    }

    @Override
    public void addToRootView(RelativeLayout rootView) {
        if (rootView != null) {
            this.rootView = rootView;
            init(rootView);
            View childView = rootView.getChildAt(0);
            rootView.addView(headerView, 0);

            LayoutParams params = (LayoutParams) childView.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, headerView.getId());
            childView.setLayoutParams(params);
        }
    }

    @Override
    public void removeFromRootView(RelativeLayout rootView) {
        if (rootView != null) {
            rootView.removeView(headerView);
        }
    }

    @Override
    public TextView getLeftViewTextView() {
        return tvHeaderViewLeft;
    }

    @Override
    public TextView getRightViewTextView() {
        return tvHeaderViewRight;
    }

    @Override
    public ImageView getLeftViewImageView() {
        return ivHeaderViewLeft;
    }

    @Override
    public ImageView getRightViewImageView() {
        return ivHeaderViewRight;
    }
}
