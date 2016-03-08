package allenhu.app.view.mdautoloadview;

import android.view.View;
import android.widget.AbsListView;

/**
 * Created by moguangjian on 15/9/1 10:38.
 */
public interface IAutoLoadMoreView {

    /**
     * 初始化默认加载视图
     */
    void initLoadingMoreViewDefault();


    /**
     * 加载更多处理
     *
     * @param onLoadingMoreListener
     */
    void setOnLoadingMoreListener(OnLoadingMoreListener onLoadingMoreListener);

    /**
     * 设置滚动监听
     *
     * @param onScrollListener
     */
    void setOnScrollListeners(AbsListView.OnScrollListener onScrollListener);


    /**
     * 除去头部与底部点击事件处理
     *
     * @param onItemClickExceptHeaderFooterViewListener
     */
    void setOnItemClickExceptHeaderFooterViewListener(OnItemClickExceptHeaderFooterViewListener onItemClickExceptHeaderFooterViewListener);

    /**
     * 设置加载更多view
     *
     * @param view
     */
    void setLoadingMoreView(View view);

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
}
