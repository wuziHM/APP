package allenhu.app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import allenhu.app.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FlagDActivity extends AppCompatActivity {

    @Bind(R.id.btn_a)
    Button btnA;
    @Bind(R.id.btn_b)
    Button btnB;
    @Bind(R.id.btn_c)
    Button btnC;
    @Bind(R.id.btn_d)
    Button btnD;
    @Bind(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag);
        ButterKnife.bind(this);
        text.setText("D界面");
    }

    @OnClick({R.id.btn_a, R.id.btn_b, R.id.btn_c, R.id.btn_d})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_a:
                startActivity(new Intent(this, FlagAActivity.class));
                break;
            case R.id.btn_b:
                startActivity(new Intent(this, FlagBActivity.class));
                break;
            case R.id.btn_c:
                startActivity(new Intent(this, FlagCActivity.class));
                break;
            case R.id.btn_d:
                startActivity(new Intent(this, FlagDActivity.class));
                break;
        }
    }
}
