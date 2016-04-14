package allenhu.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import allenhu.app.R;
import allenhu.app.base.BaseActivity;

public class ControlsActivity extends BaseActivity implements View.OnClickListener {

    private static int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls);
        findViewById(R.id.btn_gallery).setOnClickListener(this);
        findViewById(R.id.btn_spinner).setOnClickListener(this);
        findViewById(R.id.btn_recycle).setOnClickListener(this);
        findViewById(R.id.btn_listView).setOnClickListener(this);
        final EditText editText = (EditText) findViewById(R.id.edt_test);
        editText.setText("是不是设置成了final就不能改变值了？");

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_gallery:
                intent = new Intent(ControlsActivity.this, GridViewDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_spinner:
                intent = new Intent(ControlsActivity.this, SpinnerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycle:
                intent = new Intent(ControlsActivity.this, RecycleActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_listView:
                intent = new Intent(ControlsActivity.this, ListViewActivity.class);
                startActivity(intent);
                break;

        }
    }
}
