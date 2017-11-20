package allenhu.app;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import allenhu.app.activity.base.BaseActivity;
import allenhu.app.fragment.FragmentA;
import allenhu.app.fragment.FragmentB;
import allenhu.app.fragment.FragmentC;
import allenhu.app.fragment.FragmentD;
import allenhu.app.util.HomeWatcher;
import allenhu.app.util.LogUtil;
import allenhu.app.util.ToastUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private FragmentA fragmentA;
    private FragmentB fragmentB;
    private FragmentC fragmentC;
    private FragmentD fragmentD;
    public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000; //需要自己定义标志

    private TextView textView1, textView2, textView3, textView4;
    private FragmentManager fragmentManager;
    private HomeWatcher mHomeWatcher;

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

        this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);//关键代码
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        initFragment();
        initView();
        textView1.performClick();       //模拟点击一次  新学的方法


        initHomeEvent();

//        getWindow().setExitTransition(TransitionInflater.from(this).inflateTransition(R.transition.slide));
    }

    private void initHomeEvent() {


        showProgress();


        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(final ObservableEmitter e) throws Exception {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                            e.onNext(1);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                hideProgress();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                //按了HOME键
                ToastUtils.ToastMessage(MainActivity.this, "按了home键");
            }

            @Override
            public void onHomeLongPressed() {
                //长按HOME键
                ToastUtils.ToastMessage(MainActivity.this, "长按home键");
            }
        });
        mHomeWatcher.startWatch();
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_HOME) {
            LogUtil.e("==KEYCODE_HOME==");
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
        if (mHomeWatcher != null)
            mHomeWatcher.stopWatch();// 在销毁时停止监听，不然会报错的。
    }
}
