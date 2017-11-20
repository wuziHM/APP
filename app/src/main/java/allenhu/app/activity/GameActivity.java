package allenhu.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;

public class GameActivity extends BaseActivity implements View.OnClickListener {

    private Button btnWu;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_game;
    }

    @Override
    protected void onMCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnWu = (Button) findViewById(R.id.btn_wu);
        btnWu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_wu:
                startActivity(new Intent(GameActivity.this, WuActivity.class));
                break;

        }
    }
}
