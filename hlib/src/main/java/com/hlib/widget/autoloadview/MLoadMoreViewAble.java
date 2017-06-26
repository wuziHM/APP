package com.hlib.widget.autoloadview;

import android.view.View;

/**
 * Created by moguangjian on 15/9/1 10:38.
 */
public interface MLoadMoreViewAble {


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

    MLoadMoreViewAble getInstance();

    /**
     * 获取加载更多视图
     *
     * @return
     */
    View getInstanceView();

}
