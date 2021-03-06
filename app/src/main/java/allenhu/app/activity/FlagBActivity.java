package allenhu.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hlib.util.MLogUtil;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FlagBActivity extends BaseActivity {

    @BindView(R.id.btn_a)
    Button btnA;
    @BindView(R.id.btn_b)
    Button btnB;
    @BindView(R.id.btn_c)
    Button btnC;
    @BindView(R.id.btn_d)
    Button btnD;
    @BindView(R.id.text)
    TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_flag);
        text.setText("B界面");
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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        MLogUtil.e("========onNewIntent========");
    }
}
