package com.hlib.widget.autoloadview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hlib.adapter.MRecyclerViewAdapter;
import com.hlib.adapter.MRecyclerViewAdapterAble;
import com.hlib.util.MLogUtil;
import com.hlib.util.MStringUtil;


/**
 * 自动加载更多RecyclerView
 * Created by moguangjian on 15/8/3 00:57.
 */
public class MAutoLoadMoreRecyclerView extends RecyclerView implements MAutoLoadMoreViewAble {


    private static final String TAG = MAutoLoadMoreRecyclerView.class.getSimpleName();
    private boolean isLoadingMore = false;
    private boolean isEnableAutoLoadView = true;
    private MRecyclerViewAdapterAble mRecyclerViewAdapterAble;
    private LinearLayout loadingMoreView;
    private OnMLoadingMoreListener onMLoadingMoreListener;
    private MLoadMoreViewAble mLoadMoreViewAble;
    private OnRecyclerViewScrollListener onRecyclerViewScrollListener;
    private MAutoLoadMoreStatusEnum mAutoLoadMoreStatusEnum;
    private OnRecyclerViewScrollListener srollListener;

    public MAutoLoadMoreRecyclerView(Context context) {
        super(context);
        init();
    }

    public MAutoLoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MAutoLoadMoreRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    boolean isMult = false;

    public void setMult(boolean mult) {
        isMult = mult;
    }

    private void init() {
        setHasFixedSize(true);
        setItemAnimator(new DefaultItemAnimator());

        addOnScrollListener(getListener());


        initLoadingView();
        mAutoLoadMoreStatusEnum = MAutoLoadMoreStatusEnum.MORE;
    }

    @NonNull
    private OnRecyclerViewScrollListener getListener() {

        return srollListener = new OnRecyclerViewScrollListener(isMult) {

            @Override
            public void onScrollUp() {
                if (onRecyclerViewScrollListener != null) {
                    onRecyclerViewScrollListener.onScrollUp();
                }
            }

            @Override
            public void onScrollDown() {
                MLogUtil.e("onScrollDown");
                if (onRecyclerViewScrollListener != null) {
                    onRecyclerViewScrollListener.onScrollDown();
                }
            }

            @Override
            public void onBottom() {

                if (onRecyclerViewScrollListener != null) {
                    onRecyclerViewScrollListener.onBottom();
                }
                MLogUtil.i(TAG, "isEnableAutoLoadView:" + isEnableAutoLoadView + " isLoadingMore:" + isLoadingMore + " onMLoadingMoreListener:" + onMLoadingMoreListener);
                if ((!isLoadingMore)
                        && isShowMoreView()
                        && (isEnableAutoLoadView)
                        && (onMLoadingMoreListener != null)
                        ) {
                    showMoreViewStatusLoading();
                    onMLoadingMoreListener.onLoadingMore(MAutoLoadMoreRecyclerView.this);
                }
            }

            @Override
            public void onMoved(int distanceX, int distanceY) {
                if (onRecyclerViewScrollListener != null) {
                    onRecyclerViewScrollListener.onMoved(distanceX, distanceY);
                }
            }
        };
    }

    private void initLoadingView() {
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        loadingMoreView = new LinearLayout(getContext());
        loadingMoreView.setLayoutParams(params);
        loadingMoreView.setOrientation(LinearLayout.VERTICAL);
        loadingMoreView.setGravity(Gravity.CENTER);

        mLoadMoreViewAble = new MLoadMoreView(getContext());
        setLoadingMoreView(mLoadMoreViewAble);
    }

    @Override
    public void setLoadingMoreView(MLoadMoreViewAble view) {
        if (view == null) {
            return;
        }

        if (isEnableAutoLoadView) {
            removeLoadingMoreViewParent(loadingMoreView);
            loadingMoreView.removeAllViews();

            if (view instanceof View) {
                loadingMoreView.addView((View) view);
            } else {
                loadingMoreView.addView(view.getInstanceView());
            }

            RelativeLayout relativeLayout = new RelativeLayout(getContext());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            relativeLayout.setLayoutParams(params);

            relativeLayout.addView(loadingMoreView);


            addLoadingView(relativeLayout);

        } else {
            removeLoadMoreView();
        }

        hideLoadingMoreView();
    }

    @Override
    public void setOnMLoadingMoreListener(OnMLoadingMoreListener onMLoadingMoreListener) {
        this.onMLoadingMoreListener = onMLoadingMoreListener;
    }

    @Override
    public void setOnMScrollListeners(AbsListView.OnScrollListener onScrollListener) {
        MLogUtil.e(TAG, "no implement method setOnMScrollListeners()");
    }

    public void setOnRecyclerViewScrollListener(OnRecyclerViewScrollListener onRecyclerViewScrollListener) {
        this.onRecyclerViewScrollListener = onRecyclerViewScrollListener;
    }

    @Override
    public void showLoadingMoreView() {
        loadingMoreView.setVisibility(VISIBLE);
    }

    @Override
    public void hideLoadingMoreView() {
        loadingMoreView.setVisibility(GONE);
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
        } else {
            removeLoadMoreView();
        }
    }


    @Override
    public MAutoLoadMoreStatusEnum getAutoLoadMoreStatus() {
        return mAutoLoadMoreStatusEnum;
    }


    @Override
    public void setAdapter(Adapter adapter) {
        setAdapter(adapter, false);
    }

    public void setAdapter(Adapter adapter, boolean isMult) {
        srollListener.setMutil(isMult);
        if (adapter instanceof MRecyclerViewAdapterAble) {
            mRecyclerViewAdapterAble = (MRecyclerViewAdapterAble) adapter;
            setLoadingMoreView(mLoadMoreViewAble);
        } else {
            MLogUtil.e(TAG, "Adapter no implement MRecyclerViewAdapterAble. You can't use AutoLoadMore");
        }
        super.setAdapter(adapter);
    }

    private void removeLoadMoreView() {
        if (MStringUtil.isObjectNull(mRecyclerViewAdapterAble)) {
            return;
        }
        removeLoadingMoreViewParent(loadingMoreView);

        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        relativeLayout.setLayoutParams(params);
        relativeLayout.addView(loadingMoreView);
        addLoadingView(relativeLayout);
    }

    public static void removeLoadingMoreViewParent(ViewGroup loadingMoreView) {
        ViewGroup parent = (ViewGroup) loadingMoreView.getParent();
        if (!MStringUtil.isObjectNull(parent)) {
            parent.removeView(loadingMoreView);
        }
        loadingMoreView.removeAllViews();
    }

    private void addLoadingView(View view) {
        if (MStringUtil.isObjectNull(mRecyclerViewAdapterAble)) {
            return;
        }

        if (mRecyclerViewAdapterAble instanceof MRecyclerViewAdapter) {
            mRecyclerViewAdapterAble.setFooterView((RelativeLayout) view);
        } else {
            mRecyclerViewAdapterAble.addFooterView(view);
        }
    }
}

