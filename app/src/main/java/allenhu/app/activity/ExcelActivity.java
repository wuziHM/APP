package allenhu.app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import allenhu.app.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zhouchaoyuan.excelpanel.ExcelPanel;

public class ExcelActivity extends AppCompatActivity {

    @BindView(R.id.content_container)
    ExcelPanel contentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel);
        ButterKnife.bind(this);
    }

}
