package com.hlib.widget.autoloadview;

import android.widget.AbsListView;

/**
 * Created by moguangjian on 15/9/1 10:38.
 */
public interface MAutoLoadMoreViewAble {

    /**
     * 设置加载更多view，不设置使用默认，view 必须实现MLoadMoreViewAble
     *
     * @param view
     */
    void setLoadingMoreView(MLoadMoreViewAble view);

    /**
     * 加载更多监听器
     *
     * @param onMLoadingMoreListener
     */
    void setOnMLoadingMoreListener(OnMLoadingMoreListener onMLoadingMoreListener);

    /**
     * 设置滚动监听
     *
     * @param onScrollListener
     */
    void setOnMScrollListeners(AbsListView.OnScrollListener onScrollListener);

    /**
     * 显示加载更多view
     */
    void showLoadingMoreView();

    /**
     * 隐藏加载更多view
     */
    void hideLoadingMoreView();

    /**
     * 显示正在加载中view
     */
    void showMoreViewStatusLoading();

    /**
     * 显示加载更多view
     */
    void showMoreViewStatusMore();

    /**
     * 显示加载完成view
     */
    void showMoreViewStatusFinish();

    /**
     * 是否显示加载更多view
     *
     * @return
     */
    boolean isShowMoreView();

    void enableAutoLoadView(boolean isEnable);


    MAutoLoadMoreStatusEnum getAutoLoadMoreStatus();
}
