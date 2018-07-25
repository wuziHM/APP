package allenhu.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.zhy.base.adapter.recyclerview.EmptyRecyclerView;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import allenhu.app.R;
import allenhu.app.activity.MeiBrowser1Activity;
import allenhu.app.adapter.MeiziNewAdapter;
import allenhu.app.bean.request.MeiNewBean;
import allenhu.app.mvp.present.MeiRecPresenter;
import allenhu.app.mvp.view.MeiNewView;
import allenhu.app.view.EmptyView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 热门图片
 */
public class MeiHotFragment extends BaseFragment implements MeiNewView {

    @BindView(R.id.swipe_target)
    EmptyRecyclerView swipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    private View rootView;
    private MeiRecPresenter presenter;
    private int page;
    private ArrayList list;
    private MeiziNewAdapter adapter;


    public MeiHotFragment() {
    }

    public static MeiHotFragment newInstance(String url) {
        MeiHotFragment fragment = new MeiHotFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        presenter.getHotData(page);

        list = new ArrayList();
        swipeToLoadLayout.setLoadMoreEnabled(false);

        swipeToLoadLayout.setOnRefreshListener(() -> {
            page = 1;
            presenter.getHotData(page);
        });
        adapter = new MeiziNewAdapter(getContext(), R.layout.item_mei_new, list, MeiziNewAdapter.TYPE_REC);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                MeiNewBean bean = (MeiNewBean) o;
                MeiBrowser1Activity.toMeiBrowser(getContext(), bean.getId());
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        swipeTarget.setLayoutManager(new GridLayoutManager(getContext(), 2));
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

    @Override
    public void finishRequest() {
        swipeToLoadLayout.setRefreshing(false);
    }
}
