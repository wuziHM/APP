package com.hlib.widget.header;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Author：燕青 $ on 17/6/26 09:56
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public interface MHeaderViewAble {


    void setOnClickLeftViewListener(View.OnClickListener onClickListener);

    void setOnClickRightViewListener(View.OnClickListener onClickListener);

    void showLeftView();

    void hideLeftView();

    void showRightView();

    void hideRightView();

    LinearLayout getLeftView();

    TextView getTitleView();

    LinearLayout getRightView();

    void setBottomLineColor(int colorId);

    void setBgColor(int colorId);

    void setRightViewTitle(String title);

    void setLeftViewTitle(String title);

    void setLeftViewImage(int resId);

    void setRightViewImage(int resId);

    void setTitle(String title);

//    void addToRootView(RelativeLayout rootView);

//    void removeFromRootView(RelativeLayout rootView);

    TextView getLeftViewTextView();

    TextView getRightViewTextView();

    ImageView getLeftViewImageView();

    ImageView getRightViewImageView();
}
