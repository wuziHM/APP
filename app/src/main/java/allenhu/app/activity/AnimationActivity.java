package allenhu.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import allenhu.app.R;
import allenhu.app.base.BaseActivity;

public class AnimationActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        findViewById(R.id.btn_rotate).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_rotate:
                intent = new Intent(AnimationActivity.this, RotateAnimationActivity.class);
                startActivity(intent);
                break;
        }
    }
}
