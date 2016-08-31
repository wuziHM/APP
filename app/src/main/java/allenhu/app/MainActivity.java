package allenhu.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import java.io.DataOutputStream;
import java.io.File;

import allenhu.app.base.BaseActivity;
import allenhu.app.fragment.FragmentA;
import allenhu.app.fragment.FragmentB;
import allenhu.app.fragment.FragmentC;
import allenhu.app.fragment.FragmentD;
import allenhu.app.service.MyService1;
import allenhu.app.util.FileUtils;
import allenhu.app.util.LogUtil;
import allenhu.app.util.ToastUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private FragmentA fragmentA;
    private FragmentB fragmentB;
    private FragmentC fragmentC;
    private FragmentD fragmentD;

    private FrameLayout frameLayout;
    private TextView textView1, textView2, textView3, textView4;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        initView();
        textView1.performClick();       //模拟点击一次  新学的方法

//        startService(new Intent(this, MyService1.class));
    }

    private boolean getPermission(String pkgCodePath) {
        Process process = null;
        DataOutputStream os = null;
        try {
            String cmd = "chmod 777 " + pkgCodePath;
            process = Runtime.getRuntime().exec("su"); //切换到root帐号
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }
        return true;
    }

    private void initView() {
        frameLayout = (FrameLayout) findViewById(R.id.fy_content);

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

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideAllFragment(transaction);
        switch (v.getId()) {
            case R.id.txt_channel:
                setSelected();
                textView1.setSelected(true);
                if (fragmentA == null) {
                    fragmentA = new FragmentA();
                    transaction.add(R.id.fy_content, fragmentA);
                } else {
                    transaction.show(fragmentA);
                }
                break;
            case R.id.txt_channe2:
                setSelected();
                textView2.setSelected(true);
                if (fragmentB == null) {
                    fragmentB = new FragmentB();
                    transaction.add(R.id.fy_content, fragmentB);
                } else {
                    transaction.show(fragmentB);
                }
//                transaction.replace(R.id.fy_content,fragmentB);
                break;
            case R.id.txt_channe3:
                setSelected();
                textView3.setSelected(true);
                if (fragmentC == null) {
                    fragmentC = new FragmentC();
                    transaction.add(R.id.fy_content, fragmentC);
                } else {
                    transaction.show(fragmentC);
                }
                break;
            case R.id.txt_channe4:
                setSelected();
                textView4.setSelected(true);
                if (fragmentD == null) {
                    fragmentD = new FragmentD();
                    transaction.add(R.id.fy_content, fragmentD);
                } else {
                    transaction.show(fragmentD);
                }
//                transaction.replace(R.id.fy_content,fragmentD);
                break;
        }
        transaction.commit();
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
}
