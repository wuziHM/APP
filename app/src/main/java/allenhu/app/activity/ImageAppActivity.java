package allenhu.app.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.google.gson.Gson;
import com.hlib.util.MLogUtil;
import com.hlib.util.MToastUtil;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.bean.request.ImgListBean;
import allenhu.app.fragment.ImgListFragment;
import allenhu.app.net.retrofit2.NetWork;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ImageAppActivity extends BaseActivity {


    @BindView(R.id.smartTabLayout)
    SmartTabLayout smartTabLayout;

    @BindView(R.id.viewPage)
    ViewPager viewPage;

    @BindView(R.id.emptyView)
    View emptyView;


//    @BindView(R.id.swipe)
//    SwipeRefreshLayout swipe;

    private ArrayList<ImgListBean.ShowapiResBodyEntity.ListEntity> titles;
    private MyAdapter adapter;


    Observer<ImgListBean> mImgObserver = new Observer<ImgListBean>() {

        Disposable d;

        @Override
        public void onSubscribe(Disposable d) {
            this.d = d;
        }

        @Override
        public void onNext(ImgListBean imgListBean) {
            hideProgress();
            if (imgListBean != null && imgListBean.getShowapi_res_code() == 0) {
                hideErrorView();
                MLogUtil.i("response:" + new Gson().toJson(imgListBean));
                titles.addAll(imgListBean.getShowapi_res_body().getList());
                adapter.notifyDataSetChanged();
                smartTabLayout.setViewPager(viewPage);
            } else {
                MToastUtil.show(ImageAppActivity.this, "请求失败了");
                onErrorView();
            }
        }

        @Override
        public void onError(Throwable e) {
            hideProgress();
            MLogUtil.i("请求失败了-->e:" + e.toString());
            d.dispose();
            onErrorView();
        }

        @Override
        public void onComplete() {
            hideProgress();
            d.dispose();
        }

    };

    private void hideErrorView() {
        emptyView.setVisibility(View.GONE);
    }

    /**
     * 加载失败
     */
    private void onErrorView() {
        emptyView.setVisibility(View.VISIBLE);
    }


    private void initData() {
        titles = new ArrayList();
        requestData();
    }

    /**
     * 请求网络数据
     */
    private void requestData() {
        showProgress();
        NetWork.getImageApi()
                .getImageList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mImgObserver);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_app);
        ButterKnife.bind(this);
        initView();
        initData();

    }

    private void initView() {

        emptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
            }
        });

        adapter = new MyAdapter(getSupportFragmentManager());
        viewPage.setAdapter(adapter);
        viewPage.setPageMargin(20);
        smartTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        smartTabLayout.setViewPager(viewPage);


    }


    private class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ImgListFragment.newInstance(titles.get(position));
//            return ImgListFragment.newInstance(null);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position).getName();
//            return TITLES[position];
        }

        @Override
        public int getCount() {
            return titles.size();
//            return TITLES.length;
        }
    }

}
