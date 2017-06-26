package com.hlib.widget.autoloadview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hlib.R;

/**
 * Created by moguangjian on 15/12/23 13:33.
 */
public class MLoadMoreView extends LinearLayout implements MLoadMoreViewAble {

    private Context context;
    private ProgressBar progressBar;
    private TextView tvTitle;
    private View ivMoreLine;

    private String finishTitle = "已加载完全部数据";
    private String loadingTitle = "正在加载 ...";
    private String moreTitle = "更多";

    public MLoadMoreView(Context context) {
        super(context);
        this.context = context;

        init();
    }

    public MLoadMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.m_load_more_view, this, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        ivMoreLine = view.findViewById(R.id.ivMoreLine);

        addView(view);
    }

    @Override
    public void showMoreViewStatusLoading() {
        progressBar.setVisibility(VISIBLE);
        tvTitle.setText(loadingTitle);
    }

    @Override
    public void showMoreViewStatusMore() {
        progressBar.setVisibility(GONE);
        tvTitle.setText(moreTitle);
    }

    @Override
    public void showMoreViewStatusFinish() {
        progressBar.setVisibility(GONE);
        tvTitle.setText(finishTitle);
    }

    @Override
    public MLoadMoreViewAble getInstance() {
        return this;
    }

    @Override
    public View getInstanceView() {
        return this;
    }

    public void showLine() {
        ivMoreLine.setVisibility(View.VISIBLE);
    }

    public void hideLine() {
        ivMoreLine.setVisibility(View.GONE);
    }

    public void setTitle(String moreTitle, String loadingTitle, String finishTitle) {
        this.moreTitle = moreTitle;
        this.loadingTitle = loadingTitle;
        this.finishTitle = finishTitle;
    }


}
