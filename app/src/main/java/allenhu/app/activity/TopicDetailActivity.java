package allenhu.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.hlib.widget.header.MHeaderView;
import com.zhy.base.adapter.recyclerview.EmptyRecyclerView;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.adapter.MeiziNewAdapter;
import allenhu.app.bean.request.MeiNewBean;
import allenhu.app.mvp.present.MeiTopicPresenter;
import allenhu.app.mvp.view.MeiNewView;
import allenhu.app.view.EmptyView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：HM $ on 18/7/19 15:44
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class TopicDetailActivity extends BaseActivity implements MeiNewView {
    @BindView(R.id.swipe_target)
    EmptyRecyclerView swipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    @BindView(R.id.headerView)
    MHeaderView headerView;
    private String tags;
    private MeiTopicPresenter presenter;
    private ArrayList list;
    private MeiziNewAdapter adapter;
    private int page = 1;


    public static void toTopicDetailActivity(Context context, String title, String tags) {
        Intent intent = new Intent(context, TopicDetailActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("tags", tags);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        presenter = new MeiTopicPresenter(this);
        headerView.setTitle(getIntent().getStringExtra("title"));
        tags = getIntent().getStringExtra("tags");
        presenter.getData(tags, page);

        list = new ArrayList();
        swipeToLoadLayout.setOnLoadMoreListener(() -> {
            page++;
            presenter.getData(tags, page);
        });

        swipeToLoadLayout.setOnRefreshListener(() -> {
            page = 1;
            presenter.getData(tags, page);
        });
        adapter = new MeiziNewAdapter(this, R.layout.item_mei_new, list, MeiziNewAdapter.TYPE_REC);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                MeiNewBean bean = (MeiNewBean) o;
                MeiBrowser1Activity.toMeiBrowser(TopicDetailActivity.this, bean.getId());
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        swipeTarget.setLayoutManager(new GridLayoutManager(this, 2));
        swipeTarget.setAdapter(adapter);
        swipeTarget.setEmptyView(new EmptyView(this));

    }

    @Override
    public void setData(List<MeiNewBean> meiNewBeanList) {

        if (meiNewBeanList == null) {
            return;
        }
        if (page == 1)
            list.clear();
        list.addAll(meiNewBeanList);
        adapter.notifyDataSetChanged();
        if (meiNewBeanList.size() < 10) {
            swipeToLoadLayout.setLoadMoreEnabled(false);
        } else {
            swipeToLoadLayout.setLoadMoreEnabled(true);
        }


    }

    @Override
    public void finishRequest() {
        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
    }
}
