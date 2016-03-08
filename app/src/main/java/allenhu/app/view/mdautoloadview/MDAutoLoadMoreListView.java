package allenhu.app.view.mdautoloadview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import allenhu.app.R;


/**
 * 自动加载更多listview
 * Created by moguangjian on 15/8/3 00:57.
 */
public class MDAutoLoadMoreListView extends ListView implements IAutoLoadMoreView {

    private boolean isLoadingMore = false;
    private LinearLayout loadingMoreView;
    private OnLoadingMoreListener onLoadingMoreListener;
    private OnScrollListener onScrollListener;
    private OnItemClickExceptHeaderFooterViewListener onItemClickExceptHeaderFooterViewListener;

    //底部加载
    public RelativeLayout loadView;
    public ProgressBar pbLoadView;
    public TextView tvHintLoadView;
    public View ivMoreLine;

    public MDAutoLoadMoreListView(Context context) {
        super(context);
    }

    public MDAutoLoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MDAutoLoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void initLoadingMoreViewDefault() {
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

        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (getHeaderViewsCount() > 0) {
                    if (position < getHeaderViewsCount()) {
                        return;
                    }
                }

                if (getFooterViewsCount() > 0) {
                    if (position >= getAdapter().getCount() - 1) {
                        if (onItemClickExceptHeaderFooterViewListener != null) {
                            onItemClickExceptHeaderFooterViewListener.onClickMore(parent, view, position, id);
                        }
                    }
                }

                if (onItemClickExceptHeaderFooterViewListener != null) {
                    onItemClickExceptHeaderFooterViewListener.onItemClick(parent, view, position, id);
                }
            }
        });

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        loadingMoreView = new LinearLayout(getContext());
        loadingMoreView.setLayoutParams(params);
        loadingMoreView.setOrientation(LinearLayout.HORIZONTAL);
        loadingMoreView.setGravity(Gravity.CENTER);
        removeFooterView(loadingMoreView);
        addFooterView(loadingMoreView);

        try {
            loadView = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.listview_load_view, loadingMoreView, false);
            pbLoadView = (ProgressBar) loadView.findViewById(R.id.pbLoadView);
            tvHintLoadView = (TextView) loadView.findViewById(R.id.tvHintLoadView);
            ivMoreLine = loadView.findViewById(R.id.ivMoreLine);
            setLoadingMoreView(loadView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        hideLoadingMoreView();
    }

    private void scrollAutoLoadNextPage(int scrollState) {
        if (scrollState == 0) { //SCROLL_STATE_IDLE
            if (getLastVisiblePosition() == (getCount() - 1)) {
                if (!isLoadingMore) {
                    showMoreViewStatusLoading();
                    if (onLoadingMoreListener != null) {
                        onLoadingMoreListener.onLoadingMore(this);
                    }
                }
            }
        }
    }

    @Override
    public void setOnLoadingMoreListener(OnLoadingMoreListener onLoadingMoreListener) {
        this.onLoadingMoreListener = onLoadingMoreListener;
    }

    @Override
    public void setOnScrollListeners(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    @Override
    public void setLoadingMoreView(View view) {
        if (view == null) {
            return;
        }
        loadingMoreView.removeAllViews();
        loadingMoreView.addView(view);
    }

    @Override
    public void showLoadingMoreView() {
        if (loadingMoreView != null) {
            loadingMoreView.setVisibility(VISIBLE);
        }
    }

    @Override
    public void hideLoadingMoreView() {
        if (loadingMoreView != null) {
            loadingMoreView.setVisibility(INVISIBLE);
        }
    }

    @Override
    public void showMoreViewStatusLoading() {
        isLoadingMore = true;
        try {
            pbLoadView.setVisibility(View.VISIBLE);
            tvHintLoadView.setVisibility(View.VISIBLE);
            tvHintLoadView.setText("正在加载 ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMoreViewStatusMore() {
        isLoadingMore = false;
        try {
            pbLoadView.setVisibility(View.GONE);
            tvHintLoadView.setVisibility(View.VISIBLE);
            tvHintLoadView.setText("更多");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMoreViewStatusFinish() {
        isLoadingMore = false;
        try {
            pbLoadView.setVisibility(View.GONE);
            tvHintLoadView.setVisibility(View.VISIBLE);
            tvHintLoadView.setText("已经加载完所有数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isShowMoreView() {
        return loadingMoreView.isShown();
    }

    @Override
    public void setOnItemClickExceptHeaderFooterViewListener(OnItemClickExceptHeaderFooterViewListener onItemClickExceptHeaderFooterViewListener) {
        this.onItemClickExceptHeaderFooterViewListener = onItemClickExceptHeaderFooterViewListener;
    }
}
