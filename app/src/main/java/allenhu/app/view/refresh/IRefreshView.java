package allenhu.app.view.refresh;

import android.view.View;

/**
 * Created by AllenHu on 2016/3/7.
 */
public interface IRefreshView {

    /**
     * 刷新完成
     */
    void refreshComplete();

    /**
     * 自动刷新
     */
    void autoRefresh();

    /**
     * 设置没有数据的时候的view
     *
     * @param view
     */
    void setEmptyView(View view);

    /**
     * 显示没有数据的时候的view
     */
    void showEmptyView();

    /**
     * 隐藏没有数据的时候的view
     */
    void hideEmptyView();

    /**
     * 设置滚动View. Listview/ScrollView/WebView/GridView必需设置该方法
     *
     * @param scrollView
     */
    void setScrollView(View scrollView);


    /**
     * 是否刷新状态
     */
    boolean isFreshStatus();

    /**
     * 设置刷新状态
     *
     * @param isRefresh
     */
    void setIsFreshStatus(boolean isRefresh);

    /**
     * 是否可以刷新
     *
     * @param isEnabled
     */
    void setEnableRefresh(boolean isEnabled);

    /**
     * 刷新监听器
     *
     * @param onMDRefreshListener
     */
    void setOnMDRefreshListener(OnMDRefreshListener onMDRefreshListener);


}
