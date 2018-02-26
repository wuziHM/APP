package allenhu.app.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import allenhu.app.R;
import allenhu.app.activity.AccelerometerPlayActivity;
import allenhu.app.activity.CityPickerActivity;
import allenhu.app.activity.CoorLayoutActivity;
import allenhu.app.activity.ExcelActivity;
import allenhu.app.activity.HyperTranslateActivity;
import allenhu.app.activity.MatrixActivity;
import allenhu.app.activity.MultiDownloadActivity;
import allenhu.app.activity.OkGoActivity;
import allenhu.app.activity.RXjavaActivity;
import allenhu.app.activity.ShoppingCar2;
import allenhu.app.activity.ShoppingCarActivity;
import allenhu.app.activity.Swipe2Activity;
import allenhu.app.adapter.HomeAdapter;
import allenhu.app.listener.OnItemClickListener;
import allenhu.app.view.mdautoloadview.MDAutoLoadMoreRecyclerView;

/**
 * Created by AllenHu on 2016/2/14.
 */
public class FragmentB extends Fragment {

    private View rootView;
    private Context context;

    private SwipeRefreshLayout iRefreshView;
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
        iRefreshView = (SwipeRefreshLayout) rootView.findViewById(R.id.md_fresh);
        recyclerView = (MDAutoLoadMoreRecyclerView) rootView.findViewById(R.id.md_recycle);


        iRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fresh();
            }
        });

        recyclerView.initLoadingMoreViewDefault();
        recyclerView.showLoadingMoreView();
        recyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(context));

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

        iRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {

                iRefreshView.setRefreshing(false);
            }
        }, 1000);
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
//        list.add("高德地图");
        list.add("Material Design");
        list.add("ok go上传下载");
        list.add("Matrix 矩阵设置图片");
        list.add("多图片下载");

        classes = new ArrayList<Class>();
        classes.add(AccelerometerPlayActivity.class);
        classes.add(ShoppingCarActivity.class);
        classes.add(HyperTranslateActivity.class);
        classes.add(CityPickerActivity.class);
        classes.add(ShoppingCar2.class);
        classes.add(Swipe2Activity.class);
        classes.add(ExcelActivity.class);
        classes.add(RXjavaActivity.class);
        classes.add(CoorLayoutActivity.class);
        classes.add(OkGoActivity.class);
        classes.add(MatrixActivity.class);
        classes.add(MultiDownloadActivity.class);
    }
}
