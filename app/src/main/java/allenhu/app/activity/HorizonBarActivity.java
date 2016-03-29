package allenhu.app.activity;

import android.os.Bundle;

import allenhu.app.R;
import allenhu.app.base.BaseActivity;
import allenhu.app.view.HorizontalBar;

public class HorizonBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizon_bar);

        HorizontalBar bar = (HorizontalBar) findViewById(R.id.bar);
        bar.setFiveCount(10);
        bar.setFourCount(15);
        bar.setThreeCount(9);
        bar.setTwoCount(8);
        bar.setOneCount(3);
    }
}
