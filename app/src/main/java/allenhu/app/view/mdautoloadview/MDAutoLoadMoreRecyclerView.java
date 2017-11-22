package allenhu.app.view.mdautoloadview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hlib.util.MLogUtil;

import allenhu.app.R;


/**
 * Created by moguangjian on 15/10/23 15:13.
 */
public class MDAutoLoadMoreRecyclerView extends RecyclerView implements IAutoLoadMoreView {

    private static final String TAG = MDAutoLoadMoreRecyclerView.class.getSimpleName();

    private View headerView;
    private View footerView;

    private boolean isLoadingMore = false;
    private LinearLayout loadingMoreView;
    private OnLoadingMoreListener onLoadingMoreListener;
    private AbsListView.OnScrollListener onScrollListener;

    //底部加载
    public RelativeLayout loadView;
    public ProgressBar pbLoadView;
    public TextView tvHintLoadView;
    public View ivMoreLine;
    private boolean isAutoLoad = true;

    public MDAutoLoadMoreRecyclerView(Context context) {
        super(context);
    }

    public MDAutoLoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MDAutoLoadMoreRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setIsAutoLoad(boolean isAutoLoad) {
        this.isAutoLoad = isAutoLoad;
    }

    @Override
    public void initLoadingMoreViewDefault() {

        addOnScrollListener(new OnRecyclerViewScrollListener() {

            @Override
            public void onScrollUp() {
            }

            @Override
            public void onScrollDown() {
            }

            @Override
            public void onBottom() {

                if (isAutoLoad && !isLoadingMore) {
                    showMoreViewStatusLoading();
                    if (onLoadingMoreListener != null) {
                        onLoadingMoreListener.onLoadingMore(MDAutoLoadMoreRecyclerView.this);
                    }
                }
            }

            @Override
            public void onMoved(int distanceX, int distanceY) {
            }
        });


        AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);
        loadingMoreView = new LinearLayout(getContext());
        loadingMoreView.setBackgroundColor(Color.TRANSPARENT);
        loadingMoreView.setLayoutParams(params);
        loadingMoreView.setOrientation(LinearLayout.HORIZONTAL);
        loadingMoreView.setGravity(Gravity.CENTER);

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
        MLogUtil.e("is no implement");
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

    public View getLoadMoreView() {
        return loadingMoreView;
    }

    public View getFooterView() {
        return footerView;
    }

    public void setFooterView(View footerView) {
        this.footerView = footerView;
    }

    public View getHeaderView() {
        return headerView;
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
    }
}
