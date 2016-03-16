package allenhu.app.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;


import java.util.Calendar;

import allenhu.app.R;
import allenhu.app.base.BaseActivity;
import allenhu.app.service.OperationsManager;

public class CustomerViewActivity extends BaseActivity implements View.OnClickListener {

    private TextView textView;
    private MyRunnable myRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);
        findViewById(R.id.btn_v1).setOnClickListener(this);
        findViewById(R.id.btn_v2).setOnClickListener(this);
        findViewById(R.id.btn_v3).setOnClickListener(this);
        findViewById(R.id.btn_v4).setOnClickListener(this);
        findViewById(R.id.btn_v5).setOnClickListener(this);
        findViewById(R.id.btn_v6).setOnClickListener(this);
        textView = (TextView) findViewById(R.id.tv_handler);
        myRunnable = new MyRunnable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.post(myRunnable);
        logEvent("RESUME");
//        mHandler.sendEmptyMessageDelayed(1,1000);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_v1:
                intent = new Intent(CustomerViewActivity.this, View1Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_v2:
                intent = new Intent(CustomerViewActivity.this, View2Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_v3:
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:27.00025,116.2365214?q=花果山"));
                startActivity(intent);
                break;
            case R.id.btn_v4:
                intent = new Intent(CustomerViewActivity.this, CatalogActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_v5:
                intent = new Intent(CustomerViewActivity.this, FirstViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_v6:
                intent = new Intent(CustomerViewActivity.this, VoiceViewActivity.class);
                startActivity(intent);
                break;
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    private class MyRunnable implements Runnable {

        @Override
        public void run() {

            //1 秒后进行下一次更新
            mHandler.postDelayed(myRunnable, 1000);
            Calendar now = Calendar.getInstance();
            textView.setText(String.format("%02d:%02d:%02d",
                    now.get(Calendar.HOUR),
                    now.get(Calendar.MINUTE),
                    now.get(Calendar.SECOND)));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        logEvent("START");
    }

    @Override
    public void onPause() {
        super.onPause();
        logWarning("PAUSE");
    }

    @Override
    public void onStop() {
        super.onStop();
//        logWarning("STOP");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        logWarning("DESTROY");
    }

    private void logEvent(String event) {
        Intent intent = new Intent(this, OperationsManager.class);
        intent.setAction(OperationsManager.ACTION_EVENT);
        intent.putExtra(OperationsManager.EXTRA_NAME, event);
        startService(intent);
    }

    private void logWarning(String event) {
        Intent intent = new Intent(this, OperationsManager.class);
        intent.setAction(OperationsManager.ACTION_WARNING);
        intent.putExtra(OperationsManager.EXTRA_NAME, event);
        startService(intent);
    }
}
