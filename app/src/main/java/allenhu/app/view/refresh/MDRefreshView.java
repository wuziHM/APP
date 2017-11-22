package allenhu.app.view.refresh;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.hlib.util.MLogUtil;

import allenhu.app.R;
import allenhu.app.view.impl.IRefreshView;
import allenhu.app.view.impl.OnMDRefreshListener;
import allenhu.app.view.mdautoloadview.MDAutoLoadMoreListView;
import in.srain.cube.views.ptr.OnRefreshStatusListener;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by AllenHu on 2016/3/7.
 */
public class MDRefreshView extends RelativeLayout implements IRefreshView {

    private PtrClassicFrameLayout ptrClassicFrameLayout;
    private RelativeLayout emptyView;
    private RelativeLayout contentView;
    private View scrollView;
    private OnMDRefreshListener onMDRefreshListener;
    private boolean isFreshStatus = false;

    public MDRefreshView(Context context) {
        super(context);
        initView();
    }

    public MDRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MDRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        ptrClassicFrameLayout = (PtrClassicFrameLayout) LayoutInflater.from(getContext())
                .inflate(R.layout.widget_refresh_view, null, false);
        contentView = (RelativeLayout) ptrClassicFrameLayout.findViewById(R.id.contentView);
        emptyView = (RelativeLayout) ptrClassicFrameLayout.findViewById(R.id.emptyView);
        hideEmptyView();

        ptrClassicFrameLayout.disableWhenHorizontalMove(true);
        ptrClassicFrameLayout.setLastUpdateTimeRelateObject(getContext());
        ptrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                if (scrollView == null)
                    scrollView = content;
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, scrollView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                setIsFreshStatus(true);
                if (onMDRefreshListener != null)
                    onMDRefreshListener.onRefresh(frame);
            }
        });

        //the following are default settings
        ptrClassicFrameLayout.setResistance(1.7f);//阻尼系数
        ptrClassicFrameLayout.setRatioOfHeaderHeightToRefresh(1.2f);//触发刷新时移动的位置比例
        ptrClassicFrameLayout.setDurationToClose(500);//回弹时限
        ptrClassicFrameLayout.setDurationToCloseHeader(1000);//回弹到头部的时间
        ptrClassicFrameLayout.setPullToRefresh(false);//default is false
        ptrClassicFrameLayout.setKeepHeaderWhenRefresh(true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        moveChildViewToContentView();
    }

    public PtrClassicFrameLayout getInstance() {
        return ptrClassicFrameLayout;
    }


    private void moveChildViewToContentView() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            removeView(view);
            if ((view instanceof RecyclerView) || (view instanceof MDAutoLoadMoreListView) ||
                    (view instanceof ListView) || (view instanceof ScrollView) || (view instanceof GridView)
                    || (view instanceof WebView)) {
                scrollView = view;
            }
            contentView.addView(view);
        }
        removeAllViews();
        addView(ptrClassicFrameLayout);
    }

    @Override
    public void refreshComplete() {
        ptrClassicFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrClassicFrameLayout.refreshComplete();
                setIsFreshStatus(false);
            }
        }, 1000);
    }

    @Override
    public void autoRefresh() {
        ptrClassicFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrClassicFrameLayout.autoRefresh();
            }
        }, 500);
    }

    @Override
    public void setEmptyView(View view) {
        emptyView.removeAllViews();
        emptyView.addView(view);
    }

    @Override
    public void showEmptyView() {
        emptyView.setVisibility(VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        emptyView.setVisibility(GONE);
    }

    @Override
    public void setScrollView(View scrollView) {
        this.scrollView = scrollView;
    }

    @Override
    public boolean isFreshStatus() {
        return isFreshStatus;
    }

    @Override
    public void setIsFreshStatus(boolean isRefresh) {
        this.isFreshStatus = isRefresh;
    }

    @Override
    public void setEnableRefresh(boolean isEnabled) {
        ptrClassicFrameLayout.setEnabled(isEnabled);
    }

    @Override
    public void setOnMDRefreshListener(OnMDRefreshListener onMDRefreshListener) {
        this.onMDRefreshListener = onMDRefreshListener;
        if (onMDRefreshListener == null) {
            MLogUtil.e("onMDRefreshListener is null");
        }
    }

    public void setOnRefreshStatusListener(OnRefreshStatusListener onRefreshStatusListener) {
        if (ptrClassicFrameLayout != null) {
            ptrClassicFrameLayout.setOnRefreshStatusListener(onRefreshStatusListener);
        }
    }

    public boolean checkCanDoRefresh(RecyclerView view) {

        if (view.getChildCount() == 0) {
            return true;
        }
        int top = view.getChildAt(0).getTop();
        if (top != 0) {
            return false;
        }
        final RecyclerView recyclerView = view;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            int position = ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
            if (position == 0) {
                return true;
            } else if (position == -1) {
                position = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                return position == 0;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            boolean allViewAreOverScreen = true;
            int[] positions = ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(null);
            for (int i = 0; i < positions.length; i++) {
                if (positions[i] == 0) {
                    return true;
                }
                if (positions[i] != -1) {
                    allViewAreOverScreen = false;
                }
            }
            if (allViewAreOverScreen) {
                positions = ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(null);
                for (int i = 0; i < positions.length; i++) {
                    if (positions[i] == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
