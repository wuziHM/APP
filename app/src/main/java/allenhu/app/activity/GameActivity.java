package allenhu.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import allenhu.app.R;
import allenhu.app.base.BaseActivity;
import allenhu.app.view.WuziqiPanel;

public class GameActivity extends BaseActivity implements View.OnClickListener {

    private Button btnWu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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
