package allenhu.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.zhy.base.adapter.recyclerview.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import allenhu.app.R;
import allenhu.app.adapter.MeiziNewAdapter;
import allenhu.app.bean.request.MeiNewBean;
import allenhu.app.mvp.present.MeiRecPresenter;
import allenhu.app.mvp.view.MeiNewView;
import allenhu.app.view.EmptyView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class MeiRecFragment extends BaseFragment implements MeiNewView {

    @BindView(R.id.swipe_target)
    EmptyRecyclerView swipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    private MeiRecPresenter presenter;
    private View rootView;
    private int page = 1;
    private MeiziNewAdapter adapter;
    private List list;


    public MeiRecFragment() {
    }

    public static MeiRecFragment newInstance(String url) {
        MeiRecFragment fragment = new MeiRecFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_image_cate, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }


    private void initView() {
        presenter = new MeiRecPresenter(this);
        presenter.getData(page);

        list = new ArrayList();
        swipeToLoadLayout.setOnLoadMoreListener(() -> {
            page++;
            presenter.getData(page);
        });

        swipeToLoadLayout.setOnRefreshListener(() -> {
            page = 1;
            presenter.getData(page);
        });
        adapter = new MeiziNewAdapter(getContext(), R.layout.item_mei_new, list, MeiziNewAdapter.TYPE_REC);
        swipeTarget.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        swipeTarget.setAdapter(adapter);
        swipeTarget.setEmptyView(new EmptyView(getContext()));
    }


    @Override
    public void hideProgress() {
        super.hideProgress();
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
    }

    @Override
    public void setData(List<MeiNewBean> meiNewBeanList) {
        if (meiNewBeanList != null) {
            if (page == 1) {
                list.clear();
            }
            list.addAll(meiNewBeanList);
            adapter.notifyDataSetChanged();
        }
    }
//    @Override
//    public void setData(List<MeiNewBean> meiNewBeanList) {
//
//    }
}
