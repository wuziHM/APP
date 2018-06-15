package allenhu.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.hlib.util.MStringUtil;
import com.hlib.util.MToastUtil;
import com.zhy.base.adapter.recyclerview.EmptyRecyclerView;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import allenhu.app.R;
import allenhu.app.activity.MeiziImgDetailActivity;
import allenhu.app.adapter.ImageShowAdapter;
import allenhu.app.bean.ImageBean;
import allenhu.app.bean.enumBean.ImageTypeEnum;
import allenhu.app.mvp.present.ImageListPresent;
import allenhu.app.mvp.view.ImageListView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 妹子图 图片列表
 */
public class ImageListFragment extends Fragment implements ImageListView {
    private static final String PARAM_VALUE = "url";
    @BindView(R.id.swipe_target)
    EmptyRecyclerView swipeTarget;

    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    private List<ImageBean> list;
    private String url;
    private int page;

    private ImageListPresent present;
    private ImageShowAdapter adapter;


    public static ImageListFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString(PARAM_VALUE, url);
        ImageListFragment fragment = new ImageListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(PARAM_VALUE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wel_img_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        present = new ImageListPresent(getContext(), this);
        present.getData(url, page);

        list = new ArrayList<>();
        adapter = new ImageShowAdapter(getContext(), R.layout.item_img_staggered, list, ImageTypeEnum.MEIZI.getCode());
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                ImageBean bean = (ImageBean) o;
                MeiziImgDetailActivity.toMeiziImgDetailActivity(getContext(), bean.getMeiziDetailUrl());
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setItemPrefetchEnabled(false);
        swipeTarget.setAdapter(adapter);
        swipeTarget.setEmptyView(LayoutInflater.from(getContext()).inflate(R.layout.layout_empty, null));
        swipeTarget.setLayoutManager(staggeredGridLayoutManager);
        swipeTarget.setItemAnimator(new DefaultItemAnimator());

        swipeToLoadLayout.setOnRefreshListener(() -> {
            page = 1;
            present.getData(url, page);
        });
        swipeToLoadLayout.setOnLoadMoreListener(() -> {
            page += 1;
            present.getData(url, page);
        });
    }

    @Override
    public void complete() {
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }

    public void showData(List<ImageBean> imageBeans, boolean isRefresh) {
        if (MStringUtil.isEmptyList(imageBeans)) {
            MToastUtil.show(getContext(), "showData-->数据为空");
            return;
        }
        if (isRefresh) {
            list.clear();
        }
        list.addAll(imageBeans);
        adapter.notifyDataSetChanged();

    }
}
