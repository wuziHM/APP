package com.hlib.adapter;

import android.view.View;
import android.widget.RelativeLayout;

/**
 * Author：燕青 $ on 17/6/26 11:38
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public interface MRecyclerViewAdapterAble {

    public void addHeaderView(View headerView);

    public void setFooterView(RelativeLayout footerView);

    public void addFooterView(View footerView);

    public boolean isFirstItem(int position);

    public boolean isEndItem(int position);

    public boolean isValidPosition(int position);

    public void removeItem(Object object);

    public void removeItem(int position);

    public void addItem(Object object, int position);

    public int getCount();
}
