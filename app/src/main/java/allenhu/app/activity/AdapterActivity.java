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

    /**
     * 内存恢复
     * @param level
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_adapter;
    }

    @Override
    protected void onMCreate(Bundle savedInstanceState) {
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
