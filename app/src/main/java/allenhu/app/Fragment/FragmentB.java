package allenhu.app.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.base.adapter.recyclerview.DividerItemDecoration;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import allenhu.app.R;
import allenhu.app.activity.AccelerometerPlayActivity;
import allenhu.app.activity.CityPickerActivity;
import allenhu.app.activity.ExcelActivity;
import allenhu.app.activity.HyperTranslateActivity;
import allenhu.app.activity.RXjavaActivity;
import allenhu.app.activity.ShoppingCar2;
import allenhu.app.activity.ShoppingCarActivity;
import allenhu.app.activity.Swipe2Activity;
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
    private ArrayList<Class> classes;
    private HomeAdapter adapter;

    public static FragmentB newInstance() {
        Bundle args = new Bundle();
        FragmentB fragment = new FragmentB();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_two, container, false);
        this.context = getActivity();
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
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        initData();
        adapter = new HomeAdapter(list, context);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), classes.get(position));
                startActivity(intent);
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
        list.add("购物车");
        list.add("长按复制");
        list.add("选择省市区");
        list.add("购物车效果");
        list.add("recycleView滑动");
        list.add("excel表格");
        list.add("RXJava");

        classes = new ArrayList<Class>();
        classes.add(AccelerometerPlayActivity.class);
        classes.add(ShoppingCarActivity.class);
        classes.add(HyperTranslateActivity.class);
        classes.add(CityPickerActivity.class);
        classes.add(ShoppingCar2.class);
        classes.add(Swipe2Activity.class);
        classes.add(ExcelActivity.class);
        classes.add(RXjavaActivity.class);
    }
}
