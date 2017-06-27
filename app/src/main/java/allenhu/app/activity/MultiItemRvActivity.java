package allenhu.app.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import allenhu.app.R;
import allenhu.app.adapter.ChatAdapterForRv;
import allenhu.app.base.BaseActivity;
import allenhu.app.bean.ChatMessage;


public class MultiItemRvActivity extends BaseActivity
{
    private RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void onMCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ChatAdapterForRv adapter = new ChatAdapterForRv(this, ChatMessage.MOCK_DATAS);
        adapter.setOnItemClickListener(new OnItemClickListener<ChatMessage>()
        {
            @Override
            public void onItemClick(ViewGroup parent, View view, ChatMessage o, int position)
            {
                Toast.makeText(MultiItemRvActivity.this, "Click:" + position + " => " + o.getContent(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, ChatMessage o, int position)
            {
                Toast.makeText(MultiItemRvActivity.this, "LongClick:" + position + " => " + o.getContent(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mRecyclerView.setAdapter(adapter);
    }


}
