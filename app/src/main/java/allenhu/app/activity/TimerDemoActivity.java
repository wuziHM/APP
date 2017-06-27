package allenhu.app.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import allenhu.app.R;
import allenhu.app.base.BaseActivity;
import allenhu.app.util.LogUtil;

public class TimerDemoActivity extends BaseActivity implements View.OnClickListener {

    private Button btnStart;
    private Button btnOver;
    private TextView tvtime;
    private Timer timer;
    private TimerTask timerTask;
    private Message msg;
    private int f = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_timer_demo;
    }

    @Override
    protected void onMCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

    }

    private void initView() {
        btnStart = (Button) findViewById(R.id.btn_start);
        btnOver = (Button) findViewById(R.id.btn_over);
        tvtime = (TextView) findViewById(R.id.tv_time);

        btnStart.setOnClickListener(this);
        btnOver.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                start();
                break;

            case R.id.btn_over:
                stop();
                break;
        }
    }

    private void start() {
//        if (timer == null)
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                f++;
                msg = handler.obtainMessage();
                msg.arg1 = f;
                handler.sendMessage(msg);
            }
        };
        timer.schedule(timerTask, 0, 100);
    }

    private void stop() {
        timer.cancel();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LogUtil.e("arg1：" + msg.arg1);
            float t = msg.arg1 / 10.0f;
            LogUtil.e("t：" + t);
            float i = 20.0f - t;
            LogUtil.e("i:" + i);

            DecimalFormat fnum = new DecimalFormat("##0.0");
            String dd = fnum.format(i);
            tvtime.setText(dd);
            if (i < 5) {
                tvtime.setTextColor(Color.RED);
            }
            if (i <= 0) {
                tvtime.setText("0.0");
                timer.cancel();
            }

        }
    };
}
