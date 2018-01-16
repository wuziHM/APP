package allenhu.app.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.fragment.DownLoadFragment;
import allenhu.app.fragment.UpLoadFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OkGoActivity extends BaseActivity {

//    @BindView(R.id.fileDownload)
//    Button btnDown;
//    @BindView(R.id.downloadSize)
//    TextView tvDownloadSize;
//    @BindView(R.id.netSpeed)
//    TextView tvNetSpeed;
//    @BindView(R.id.tvProgress)
//    TextView tvProgress;
//    @BindView(R.id.pbProgress)
//    NumberProgressBar pbProgress;
//    @BindView(R.id.requestState)
//    TextView requestState;
//    @BindView(R.id.requestHeaders)
//    TextView requestHeaders;
//    @BindView(R.id.responseData)
//    TextView responseData;
//    @BindView(R.id.responseHeader)
//    TextView responseHeader;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    String[] titles = {"下载", "上传"};
    Fragment[] fragments = new Fragment[2];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_go_acitivity);
        ButterKnife.bind(this);


        initView();

    }

    private void initView() {
        fragments[0] = new DownLoadFragment();
        fragments[1] = new UpLoadFragment();
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }



    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

    }
}
