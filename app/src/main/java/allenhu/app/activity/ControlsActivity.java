package allenhu.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import allenhu.app.R;
import allenhu.app.base.BaseActivity;

public class ControlsActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls);
        findViewById(R.id.btn_gallery).setOnClickListener(this);
        findViewById(R.id.btn_spinner).setOnClickListener(this);
        findViewById(R.id.btn_recycle).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btn_gallery:
                intent = new Intent(ControlsActivity.this,GridViewDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_spinner:
                intent = new Intent(ControlsActivity.this,SpinnerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycle:
                intent = new Intent(ControlsActivity.this,RecycleActivity.class);
                startActivity(intent);
                break;

        }
    }
}
