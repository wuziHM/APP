package com.hlib.widget.autoloadview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * 自动加载更多listview
 * Created by moguangjian on 15/8/3 00:57.
 */
public class MAutoLoadMoreListView extends ListView implements MAutoLoadMoreViewAble {

    private static final String TAG = MAutoLoadMoreListView.class.getSimpleName();
    private boolean isLoadingMore = false;
    private boolean isEnableAutoLoadView = true;
    private LinearLayout loadingMoreView;
    private OnMLoadingMoreListener onMLoadingMoreListener;
    private OnScrollListener onScrollListener;
    private MLoadMoreViewAble mLoadMoreViewAble;
    private MAutoLoadMoreStatusEnum mAutoLoadMoreStatusEnum;

    public MAutoLoadMoreListView(Context context) {
        super(context);
        init();
    }

    public MAutoLoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MAutoLoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (onScrollListener != null) {
                    onScrollListener.onScrollStateChanged(view, scrollState);
                }
                scrollAutoLoadNextPage(scrollState);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (onScrollListener != null) {
                    onScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                }
            }
        });

        initLoadingView();
        mAutoLoadMoreStatusEnum = MAutoLoadMoreStatusEnum.MORE;
    }

    private void initLoadingView() {
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        loadingMoreView = new LinearLayout(getContext());
        loadingMoreView.setLayoutParams(params);
        loadingMoreView.setOrientation(LinearLayout.HORIZONTAL);
        loadingMoreView.setGravity(Gravity.CENTER);

        removeLoadMoreView();

        mLoadMoreViewAble = new MLoadMoreView(getContext());
        setLoadingMoreView(mLoadMoreViewAble);
        hideLoadingMoreView();
    }

    private void scrollAutoLoadNextPage(int scrollState) {
        if ((scrollState == 0)
                && (getLastVisiblePosition() == (getCount() - 1))
                && isShowMoreView()
                && (!isLoadingMore)
                && (isEnableAutoLoadView)
                && (onMLoadingMoreListener != null)
                ) {

            showMoreViewStatusLoading();
            onMLoadingMoreListener.onLoadingMore(this);
        }
    }

    @Override
    public void setLoadingMoreView(MLoadMoreViewAble view) {
        if (view == null) {
            return;
        }
        MAutoLoadMoreRecyclerView.removeLoadingMoreViewParent(loadingMoreView);
        loadingMoreView.removeAllViews();
        if (view instanceof View) {
            loadingMoreView.addView((View) view);
        } else {
            loadingMoreView.addView(view.getInstanceView());
        }

    }

    @Override
    public void setOnMLoadingMoreListener(OnMLoadingMoreListener onMLoadingMoreListener) {
        this.onMLoadingMoreListener = onMLoadingMoreListener;
    }

    @Override
    public void setOnMScrollListeners(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    @Override
    public void showLoadingMoreView() {
        loadingMoreView.setVisibility(VISIBLE);
    }

    @Override
    public void hideLoadingMoreView() {
        loadingMoreView.setVisibility(INVISIBLE);
        mAutoLoadMoreStatusEnum = MAutoLoadMoreStatusEnum.HIDE;
    }

    @Override
    public void showMoreViewStatusLoading() {
        isLoadingMore = true;
        showLoadingMoreView();
        mLoadMoreViewAble.showMoreViewStatusLoading();
        mAutoLoadMoreStatusEnum = MAutoLoadMoreStatusEnum.LOADING;
    }

    @Override
    public void showMoreViewStatusMore() {
        isLoadingMore = false;
        showLoadingMoreView();
        mLoadMoreViewAble.showMoreViewStatusMore();
        mAutoLoadMoreStatusEnum = MAutoLoadMoreStatusEnum.MORE;
    }

    @Override
    public void showMoreViewStatusFinish() {
        isLoadingMore = false;
        showLoadingMoreView();
        mLoadMoreViewAble.showMoreViewStatusFinish();
        mAutoLoadMoreStatusEnum = MAutoLoadMoreStatusEnum.FINISH;
    }

    @Override
    public boolean isShowMoreView() {
        return loadingMoreView.isShown();
    }

    @Override
    public void enableAutoLoadView(boolean isEnable) {
        isEnableAutoLoadView = isEnable;
        if (isEnableAutoLoadView) {
            setLoadingMoreView(mLoadMoreViewAble);
            hideLoadingMoreView();
        } else {
            removeLoadMoreView();
        }
    }


    @Override
    public MAutoLoadMoreStatusEnum getAutoLoadMoreStatus() {
        return mAutoLoadMoreStatusEnum;
    }

    private void removeLoadMoreView() {

        if (getFooterViewsCount() > 0) {
            try {
                // TODO 这里没查出为什么在模拟器会报空指针
                removeFooterView(loadingMoreView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        MAutoLoadMoreRecyclerView.removeLoadingMoreViewParent(loadingMoreView);
        loadingMoreView.removeAllViews();
        addFooterView(loadingMoreView);
    }

}
