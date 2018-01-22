package allenhu.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.fragment.DownLoadFragment;
import allenhu.app.fragment.UpLoadFragment;
import allenhu.app.util.PhotoUtil;
import allenhu.app.util.ToastUtils;
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
    private ArrayList<ImageItem> imageItems;


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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == PhotoUtil.OPEN_IMAGE_REQUEST_CODER) {
                imageItems = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (imageItems != null && imageItems.size() > 0) {
                    ((UpLoadFragment)fragments[1]).setImageItems(imageItems);
//                    com.orhanobut.logger.Logger.d("imageItems:"+imageItems.size());
                } else {
                    ToastUtils.ToastMessage(this, "选择图片失败");
                }
            } else {
                Toast.makeText(this, "没有选择图片", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
