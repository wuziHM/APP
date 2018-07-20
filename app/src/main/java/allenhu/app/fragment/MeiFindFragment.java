package allenhu.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.base.adapter.recyclerview.EmptyRecyclerView;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import allenhu.app.R;
import allenhu.app.activity.TopicActivity;
import allenhu.app.activity.TopicDetailActivity;
import allenhu.app.adapter.TopicAdapter;
import allenhu.app.bean.request.FindItemBean;
import allenhu.app.mvp.present.MeiFindPresenter;
import allenhu.app.mvp.view.MeiFindView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class MeiFindFragment extends BaseFragment implements MeiFindView {

    @BindView(R.id.rc_topic)
    EmptyRecyclerView rcTopic;
    @BindView(R.id.rc_girl)
    EmptyRecyclerView rcGirl;
    private View rootView;
    private MeiFindPresenter presenter;
    private TopicAdapter topicAdapter;
    private TopicAdapter girlAdapter;

    private ArrayList<ArrayList<ArrayList<FindItemBean>>> findBeans = new ArrayList<>();
    private List<ArrayList<FindItemBean>> topBeas = new ArrayList<>();
    private List<ArrayList<FindItemBean>> girlBeas = new ArrayList<>();


    public MeiFindFragment() {
    }

    public static MeiFindFragment newInstance(String url) {
        MeiFindFragment fragment = new MeiFindFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_mei_find, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }

    private void initView() {

        topicAdapter = new TopicAdapter(getContext(), R.layout.item_mei_topic, topBeas);
        girlAdapter = new TopicAdapter(getContext(), R.layout.item_mei_topic, girlBeas);
        rcGirl.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rcTopic.setLayoutManager(new GridLayoutManager(getContext(), 4));
        topicAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                FindItemBean bean = (FindItemBean) o;
                TopicDetailActivity.toTopicDetailActivity(getContext(), bean.getTitle(), bean.getTagid());
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        girlAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                FindItemBean bean = (FindItemBean) o;
                TopicDetailActivity.toTopicDetailActivity(getContext(), bean.getTitle(), bean.getTagid());
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });

        rcGirl.setAdapter(girlAdapter);
        rcTopic.setAdapter(topicAdapter);

        presenter = new MeiFindPresenter(this);
        presenter.getData();
    }


    @OnClick({R.id.ly_search, R.id.tv_all_topic, R.id.tv_all_girl, R.id.tv_change})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_search:
                break;
            case R.id.tv_all_topic:
                TopicActivity.toTopicActivity(findBeans.get(0), getContext());
                break;
            case R.id.tv_all_girl:
                TopicActivity.toTopicActivity(findBeans.get(1), getContext());
                break;
            case R.id.tv_change:
                presenter.getTopicAndGirl(findBeans);
                break;
        }
    }

    @Override
    public void setData(ArrayList<ArrayList<ArrayList<FindItemBean>>> list1) {
        if (list1 != null) {
            findBeans.clear();
            findBeans.addAll(list1);
        }
    }


    @Override
    public void setTopics(List<ArrayList<FindItemBean>> returnTopic) {
        if (returnTopic == null) {
            return;
        }
//        Logger.d("returnTopic:" + returnTopic.size());
//        for (ArrayList<FindItemBean> arrayList : returnTopic) {
//            Logger.d(arrayList.size());
//            Logger.d("arrayList:" + arrayList.size());
//        }
        topBeas.clear();
        topBeas.addAll(returnTopic);
        topicAdapter.notifyDataSetChanged();
    }

    @Override
    public void setGirls(List<ArrayList<FindItemBean>> returnGirl) {
        if (returnGirl == null) {
            return;
        }
        girlBeas.clear();
        girlBeas.addAll(returnGirl);
        girlAdapter.notifyDataSetChanged();
    }
}

