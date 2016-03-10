package allenhu.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import allenhu.app.R;
import allenhu.app.adapter.HomeAdapter;
import allenhu.app.listener.OnItemClickListener;
import allenhu.app.view.impl.OnMDRefreshListener;
import allenhu.app.view.mdautoloadview.MDAutoLoadMoreRecyclerView;
import allenhu.app.view.refresh.MDRefreshView;

/**
 * Created by AllenHu on 2016/2/14.
 */
public class FragmentB extends Fragment {

    private View rootView;
    private Context context;

    private MDRefreshView iRefreshView;
    private MDAutoLoadMoreRecyclerView recyclerView;
    private ArrayList list;
    private HomeAdapter adpter;

    public FragmentB(String name) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_two, container, false);
        this.context = getContext();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        iRefreshView = (MDRefreshView) rootView.findViewById(R.id.md_fresh);
        recyclerView = (MDAutoLoadMoreRecyclerView) rootView.findViewById(R.id.md_recycle);

        iRefreshView.setOnMDRefreshListener(new OnMDRefreshListener() {
            @Override
            public void onRefresh(View view) {
                fresh();
            }
        });

        recyclerView.initLoadingMoreViewDefault();
        recyclerView.showLoadingMoreView();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        initData();
        adpter = new HomeAdapter(list, context);
        recyclerView.setAdapter(adpter);
        adpter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    private void fresh() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                iRefreshView.refreshComplete();
            }
        };
        timer.schedule(timerTask, 1000);
    }

    private void initData() {
        list = new ArrayList<String>();
        list.add("重力感应");
    }
}
