package allenhu.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import allenhu.app.R;
import allenhu.app.base.BaseActivity;

/**
 * 各种适配器的页面
 */
public class AdapterActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        initView();

    }

    private void initView() {
        findViewById(R.id.btn1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                startActivity(new Intent(AdapterActivity.this,AlmightyAdapterActivity.class));
                break;
        }
    }
}
