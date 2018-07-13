package allenhu.app;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.hlib.util.MLogUtil;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
//import com.umeng.analytics.MobclickAgent;

import allenhu.app.activity.base.BaseActivity;
import allenhu.app.fragment.FragmentA;
import allenhu.app.fragment.FragmentB;
import allenhu.app.fragment.FragmentC;
import allenhu.app.fragment.FragmentD;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private FragmentA fragmentA;
    private FragmentB fragmentB;
    private FragmentC fragmentC;
    private FragmentD fragmentD;

    private TextView textView1, textView2, textView3, textView4;
    private FragmentManager fragmentManager;

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSwipeBackEnable(false);
        fragmentManager = getSupportFragmentManager();

        initFragment();
        initView();
        textView1.performClick();       //模拟点击一次  新学的方法


        initHomeEvent();
        requestPermissions();

    }


    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission
                .requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_CONTACTS,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.SEND_SMS)
                .subscribe(permission -> {
                    if (permission.granted) {
                        // 用户已经同意该权限
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                        Logger.d(permission.name + " is denied. More info should be provided.");
                    } else {
                        // 用户拒绝了该权限，并且选中『不再询问』
                        Logger.d(permission.name + " is denied.");
                    }
                });


    }

    private void initHomeEvent() {


    }

    private void initFragment() {
        fragmentA = FragmentA.newInstance();
        fragmentB = FragmentB.newInstance();
        fragmentC = FragmentC.newInstance();
        fragmentD = FragmentD.newInstance();
    }


    private void initView() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView1 = (TextView) findViewById(R.id.txt_channel);
        textView2 = (TextView) findViewById(R.id.txt_channe2);
        textView3 = (TextView) findViewById(R.id.txt_channe3);
        textView4 = (TextView) findViewById(R.id.txt_channe4);

        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);

    }

    private void setSelected() {
        textView1.setSelected(false);
        textView2.setSelected(false);
        textView3.setSelected(false);
        textView4.setSelected(false);
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fragmentA != null) fragmentTransaction.hide(fragmentA);
        if (fragmentB != null) fragmentTransaction.hide(fragmentB);
        if (fragmentC != null) fragmentTransaction.hide(fragmentC);
        if (fragmentD != null) fragmentTransaction.hide(fragmentD);
    }

    @Override
    public void onClick(View v) {
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch (v.getId()) {
            case R.id.txt_channel:
                setSelected();
                textView1.setSelected(true);
                transaction.replace(R.id.fy_content, fragmentA);
                break;
            case R.id.txt_channe2:
                setSelected();
                textView2.setSelected(true);
                transaction.replace(R.id.fy_content, fragmentB);
                break;
            case R.id.txt_channe3:
                setSelected();
                textView3.setSelected(true);
                transaction.replace(R.id.fy_content, fragmentC);
                break;
            case R.id.txt_channe4:
                setSelected();
                textView4.setSelected(true);
                transaction.replace(R.id.fy_content, fragmentD);
                break;
        }
        transaction.commit();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_HOME) {
            MLogUtil.e("==KEYCODE_HOME==");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
