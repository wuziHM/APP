package allenhu.app.activity;

import android.os.Bundle;
import android.widget.ListView;

import allenhu.app.R;
import allenhu.app.adapter.ChatAdapter;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.bean.ChatMessage;

public class MultiItemListViewActivity extends BaseActivity {

    private ListView mListView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_almighty_adapter;
    }

    @Override
    protected void onMCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListView = (ListView) findViewById(R.id.id_listview_list);
        mListView.setDivider(null);
        mListView.setAdapter(new ChatAdapter(this, ChatMessage.MOCK_DATAS));

    }

}
