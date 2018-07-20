package allenhu.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.base.adapter.recyclerview.EmptyRecyclerView;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.Collection;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.adapter.TopicAdapter;
import allenhu.app.bean.request.FindItemBean;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 妹子图专题
 */
public class TopicActivity extends BaseActivity {

    @BindView(R.id.recycle)
    EmptyRecyclerView recycle;
    private ArrayList<ArrayList<FindItemBean>> topics = new ArrayList<>();

    public static void toTopicActivity(ArrayList<ArrayList<FindItemBean>> topics, Context context) {
        Intent intent = new Intent(context, TopicActivity.class);
        intent.putExtra("topics", topics);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        ButterKnife.bind(this);
        topics.addAll((Collection<? extends ArrayList<FindItemBean>>) getIntent().getExtras().get("topics"));
        TopicAdapter topicAdapter = new TopicAdapter(this, R.layout.item_mei_topic, topics);
        topicAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                FindItemBean bean = (FindItemBean) o;
                TopicDetailActivity.toTopicDetailActivity(TopicActivity.this, bean.getTitle(), bean.getTagid());
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        recycle.setLayoutManager(new GridLayoutManager(this, 4));
        recycle.setAdapter(topicAdapter);

    }
}
