package allenhu.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.base.adapter.recyclerview.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import allenhu.app.R;
import allenhu.app.activity.CollectActivity;
import allenhu.app.activity.ImageAppActivity;
import allenhu.app.activity.LuckActivity;
import allenhu.app.adapter.HomeAdapter;
import allenhu.app.listener.OnItemClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AllenHu on 2016/2/14.
 */
public class FragmentD extends Fragment {
    @BindView(R.id.recycle)
    EmptyRecyclerView recycle;

    private View rootView;

    private HomeAdapter adapter;


    private List<String> listContent;
    private List<Class> listActivity;


    public static FragmentD newInstance() {
        Bundle args = new Bundle();
        FragmentD fragment = new FragmentD();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_four, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initAdapter();
    }

    private void initAdapter() {
        adapter = new HomeAdapter(listContent, getContext());
        recycle.setLayoutManager(new LinearLayoutManager(getContext()));
//        recycle.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getContext(), listActivity.get(position)));
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        recycle.setAdapter(adapter);
    }

    private void initData() {
        listActivity = new ArrayList<>();
        listContent = new ArrayList<>();

        listContent.add("图图大全");
        listActivity.add(ImageAppActivity.class);

        listContent.add("我喜欢");
        listActivity.add(CollectActivity.class);

        listContent.add("抽奖");
        listActivity.add(LuckActivity.class);

        listContent.add("我自己写的接口");
        listActivity.add(LuckActivity.class);

        listContent.add("抓数据");
        listActivity.add(WelfareActivity.class);


    }
}
