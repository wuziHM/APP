package com.hlib.adapter;

import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by moguangjian on 16/7/13 11:39.
 */
public interface MRecyclerViewAdapterAble {

    public void addHeaderView(View headerView) ;

    public void setFooterView(RelativeLayout footerView);

    public void addFooterView(View footerView);

    public boolean isFirstItem(int position);

    public boolean isEndItem(int position);

    public boolean isValidPosition(int position) ;

    public void removeItem(Object object) ;

    public void removeItem(int position) ;

    public void addItem(Object object, int position) ;

    public void setOnItemClickListener(MRecyclerViewAdapter.OnItemClickListener onItemClickListener);

    public void setOnItemLongClickListener(MRecyclerViewAdapter.OnItemLongClickListener onItemLongClickListener);

    public int getCount() ;

}
