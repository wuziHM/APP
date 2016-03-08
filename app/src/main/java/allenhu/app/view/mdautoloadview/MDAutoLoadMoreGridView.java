package allenhu.app.view.mdautoloadview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import allenhu.app.R;
import allenhu.app.util.LogUtil;
import allenhu.app.view.GridViewWithHeaderAndFooter;

/**
 * 自动加载下一页
 * Created by moguangjian on 15/9/1 11:05.
 */
public class MDAutoLoadMoreGridView extends GridViewWithHeaderAndFooter implements IAutoLoadMoreView {

    private static final String TAG = MDAutoLoadMoreGridView.class.getSimpleName();

    private boolean isLoadingMore = false;
    private LinearLayout loadingMoreView;
    private OnLoadingMoreListener onLoadingMoreListener;
    private AbsListView.OnScrollListener onScrollListener;

    //底部加载
    public RelativeLayout loadView;
    public ProgressBar pbLoadView;
    public TextView tvHintLoadView;
    public View ivMoreLine;


    public MDAutoLoadMoreGridView(Context context) {
        super(context);
    }

    public MDAutoLoadMoreGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MDAutoLoadMoreGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void initLoadingMoreViewDefault() {
        setOnScrollListener(new AbsListView.OnScrollListener() {
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

        AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);
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
    public void setOnScrollListeners(AbsListView.OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    @Override
    public void setOnItemClickExceptHeaderFooterViewListener(OnItemClickExceptHeaderFooterViewListener onItemClickExceptHeaderFooterViewListener) {
        LogUtil.e("setOnItemClickExceptHeaderFooterViewListener is not implements");
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

}
