package allenhu.app.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import allenhu.app.R;
import allenhu.app.adapter.ContactAdapter;
import allenhu.app.adapter.DividerItemDecoration;
import allenhu.app.base.BaseActivity;
import allenhu.app.bean.ContactBean;
import allenhu.app.listener.OnItemClickListener;
import allenhu.app.util.ContactUtil;

public class ContentProviderActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private Button read, write;
    private Object data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        initView();
        initData();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.re_contact);
        read = (Button) findViewById(R.id.btn_readContact);
        write = (Button) findViewById(R.id.btn_writeContact);
        read.setOnClickListener(this);
        write.setOnClickListener(this);

    }

    public void initData() {
        List<ContactBean> list = ContactUtil.getPhoneContacts(this);
        ContactAdapter adpter = new ContactAdapter(this, list);
        recyclerView.setAdapter(adpter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adpter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(ContentProviderActivity.this,"RecycleView的item事件",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_readContact:

                break;

            case R.id.btn_writeContact:

                break;

            default:
                break;
        }
    }
}