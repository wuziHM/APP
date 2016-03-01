package allenhu.app.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import allenhu.app.R;
import allenhu.app.base.BaseActivity;

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
