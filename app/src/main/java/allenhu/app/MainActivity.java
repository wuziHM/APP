package allenhu.app;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import allenhu.app.base.BaseActivity;
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onMCreate(Bundle savedInstanceState) {
        setSwipeBackEnable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        initFragment();
        initView();
        textView1.performClick();       //模拟点击一次  新学的方法
    }

    private void initFragment() {
        fragmentA = FragmentA.newInstance();
        fragmentB = FragmentB.newInstance();
        fragmentC = FragmentC.newInstance();
        fragmentD = FragmentD.newInstance();
    }


    private void initView() {
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
