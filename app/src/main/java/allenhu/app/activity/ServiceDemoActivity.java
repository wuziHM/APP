package allenhu.app.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.service.MyService1;

public class ServiceDemoActivity extends BaseActivity implements View.OnClickListener {

    private Button btnService1;
    private Button btnService2;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService1.MyBinder myBinder = (MyService1.MyBinder) service;
            myBinder.eat();
            myBinder.sleep();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);
        btnService1 = (Button) findViewById(R.id.btn_service1);
        btnService1.setOnClickListener(this);

        btnService2 = (Button) findViewById(R.id.btnService2);
        btnService2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_service1:

                Intent intent = new Intent(ServiceDemoActivity.this, MyService1.class);
                bindService(intent, connection, BIND_AUTO_CREATE);

                break;

            case R.id.btnService2:
                unbindService(connection);
                break;
        }
    }
}
