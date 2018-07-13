package allenhu.app.activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.hlib.widget.BottomNavigationViewEx;
import com.hlib.widget.header.MHeaderView;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.fragment.MeiCameraFragment;
import allenhu.app.fragment.MeiFindFragment;
import allenhu.app.fragment.MeiHotFragment;
import allenhu.app.fragment.MeiNewFragment;
import allenhu.app.fragment.MeiRecFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MeiziMainActivity extends BaseActivity {


    @BindView(R.id.fr_content)
    FrameLayout frContent;

    @BindView(R.id.navigation)
    BottomNavigationViewEx navigation;

    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.headerView)
    MHeaderView headerView;

    private Fragment[] fragments;
    private int lastShowFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizi_main);
        ButterKnife.bind(this);

        initView();
        initFragments();
    }


    /**
     * 切换Fragment
     *
     * @param lastIndex 上个显示Fragment的索引
     * @param index     需要显示的Fragment的索引
     */
    public void switchFragment(int lastIndex, int index) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.hide(fragments[lastIndex]);

        if (!fragments[index].isAdded()) {
            transaction.add(R.id.fr_content, fragments[index]);
        }

        transaction.show(fragments[index]).commitAllowingStateLoss();
    }


    private void initFragments() {

        MeiNewFragment one = MeiNewFragment.newInstance("");
        MeiRecFragment two = MeiRecFragment.newInstance("");
        MeiFindFragment three = MeiFindFragment.newInstance("");
        MeiHotFragment four = MeiHotFragment.newInstance("");
        MeiCameraFragment five = MeiCameraFragment.newInstance("");

        fragments = new Fragment[]{one, two, three, four, five};
        lastShowFragment = 0;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fr_content, one)
                .show(one)
                .commit();
    }


    /**
     * 初始化控件
     */
    private void initView() {

        headerView.hideLeftView();
        headerView.setTitle("最新");

        ColorStateList csl = createColorStateList();
        navigation = (BottomNavigationViewEx) findViewById(R.id.navigation);
        //开始或关闭子菜单位移模式。 如果为 true，除了当前选中项，其他项的文本将会隐藏。 当菜单数大于3时，默认为 true。
        navigation.enableItemShiftingMode(true);
        //开始或关闭导航条位移模式。如果为 true，选中项和其他项的宽度不一样。当菜单数大于3时，默认为 true。
        navigation.enableShiftingMode(false);
        navigation.setItemTextColor(csl);
        navigation.setItemIconTintList(csl);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    @NonNull
    private ColorStateList createColorStateList() {
        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        };

        int[] colors = new int[]{getResources().getColor(R.color.c_c6c6c6),
                getResources().getColor(R.color.colorPrimary)
        };
        return new ColorStateList(states, colors);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_new:
                    if (lastShowFragment != 0) {
                        switchFragment(lastShowFragment, 0);
                        headerView.setTitle("最新");
                        lastShowFragment = 0;
                    }
                    return true;
                case R.id.navigation_rec:
                    if (lastShowFragment != 1) {
                        switchFragment(lastShowFragment, 1);
                        headerView.setTitle("推荐");
                        lastShowFragment = 1;
                    }
                    return true;
                case R.id.navigation_find:
                    if (lastShowFragment != 2) {
                        switchFragment(lastShowFragment, 2);
                        headerView.setTitle("发现");
                        lastShowFragment = 2;
                    }
                    return true;
                case R.id.navigation_hot:
                    if (lastShowFragment != 3) {
                        switchFragment(lastShowFragment, 3);
                        headerView.setTitle("热门");
                        lastShowFragment = 3;
                    }
                    return true;
                case R.id.navigation_me:
                    if (lastShowFragment != 4) {
                        switchFragment(lastShowFragment, 4);
                        headerView.setTitle("自拍");
                        lastShowFragment = 4;
                    }
                    return true;
            }
            return false;
        }
    };


}
