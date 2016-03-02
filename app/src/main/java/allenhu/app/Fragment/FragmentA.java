package allenhu.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import allenhu.app.R;
import allenhu.app.activity.ContentProviderActivity;
import allenhu.app.activity.CustomerViewActivity;
import allenhu.app.activity.DrawableActivity;
import allenhu.app.activity.GridViewDemoActivity;
import allenhu.app.activity.HongbaoActivity;
import allenhu.app.activity.ServiceDemoActivity;
import allenhu.app.activity.SpinnerActivity;
import allenhu.app.activity.WeixinActivity;
import allenhu.app.adapter.DividerItemDecoration;
import allenhu.app.adapter.HomeAdapter;
import allenhu.app.listener.OnItemClickListener;

/**
 * Created by AllenHu on 2016/2/14.
 */
public class FragmentA extends Fragment {
    private View rootView;
    private List<String> listContent;
    private List<Class> listActivity;
    private Context context;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_one, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        initData();
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycle);
        HomeAdapter homeAdapter = new HomeAdapter(listContent, context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
//        recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
//        recyclerView.addItemDecoration(new DividerGridItemDecoration(context));
        recyclerView.setAdapter(homeAdapter);
        //设置监听，监听是自己写的
        homeAdapter.setOnItemClickListener(new OnItemClickListener() {
            Intent intent = null;

            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(context, "这是第" + position + "个item", Toast.LENGTH_SHORT).show();
                intent = new Intent(context, listActivity.get(position));
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    private void initData() {
        listContent = new ArrayList<>();
        listContent.add("Drawable小结");
        listContent.add("画廊");
        listContent.add("Spinner");
        listContent.add("自定义View");
        listContent.add("微信个性分享");
        listContent.add("红包");
        listContent.add("ContentProvider");
        listContent.add("Service");

        listActivity = new ArrayList<>();
        listActivity.add(DrawableActivity.class);
        listActivity.add(GridViewDemoActivity.class);
        listActivity.add(SpinnerActivity.class);
        listActivity.add(CustomerViewActivity.class);
        listActivity.add(WeixinActivity.class);
        listActivity.add(HongbaoActivity.class);
        listActivity.add(ContentProviderActivity.class);
        listActivity.add(ServiceDemoActivity.class);
//        listContent.add("第二个");
//        for (int i = 'A'; i < 'z'; i++) {
//            listContent.add("" + (char) i);
//        }

        byte[] bytes = new byte[1024];
        OutputStream outputStream = new ByteArrayOutputStream();
        try {
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
