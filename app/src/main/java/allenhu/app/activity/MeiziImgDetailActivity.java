package allenhu.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hlib.util.MStringUtil;
import com.hlib.util.MToastUtil;
import com.lsjwzh.widget.recyclerviewpager.FragmentStatePagerAdapter;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.maning.mndialoglibrary.MStatusDialog;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.bean.Image;
import allenhu.app.fragment.ImageDetailFragment;
import allenhu.app.mvp.present.MeiziImgDetailPresent;
import allenhu.app.mvp.view.MeiziImgDetailView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MeiziImgDetailActivity extends BaseActivity implements MeiziImgDetailView {
    @BindView(R.id.rl_black_bg)
    RelativeLayout rlBlackBg;

    @BindView(R.id.list)
    RecyclerViewPager mRecyclerView;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tvNumShow)
    TextView tvNumShow;


    private Animation mAnimaFade;       //alpha 0--->1
    private Animation mAnimaHold;       //alpha 1--->0

//    @BindView(R.id.view_bottom_bg)
//    View viewBottomBg;

//    @BindView(R.id.browser_root)
//    RelativeLayout browserRoot;


//    @BindView(R.id.view_page)
//    ViewPager viewPage;
//    @BindView(R.id.iv_back)
//    ImageView ivBack;
//    @BindView(R.id.tv_text)
//    TextView tvText;


    private MeiziImgDetailPresent detailPresent;
    //    private ImagePagerAdapter adapter;
    private List<String> datas = new ArrayList<>();
    private MeiziImgDetailActivity context;
    private MStatusDialog dialog;
    private RecycleAdapter mAdapter;
    private String title;


    public static void toMeiziImgDetailActivity(Context context, String url, String title) {
        Intent intent = new Intent(context, MeiziImgDetailActivity.class);
        intent.putExtra("data", url);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_browser1);
        ButterKnife.bind(this);
        initView();
        detailPresent = new MeiziImgDetailPresent(this, this);
        detailPresent.getData(getIntent().getStringExtra("data"));


    }

    private void initView() {
        title = getIntent().getStringExtra("title");
        context = MeiziImgDetailActivity.this;
        dialog = new MStatusDialog(this);


        mAnimaFade = AnimationUtils.loadAnimation(this, R.anim.fade);
        mAnimaFade.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tvNumShow.setVisibility(View.VISIBLE);
                tvTitle.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mAnimaHold = AnimationUtils.loadAnimation(this, R.anim.hold);
        mAnimaHold.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tvNumShow.setVisibility(View.GONE);
                tvTitle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false);
        mRecyclerView.setLayoutManager(layout);
        mAdapter = new RecycleAdapter(getSupportFragmentManager());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLongClickable(true);

        mRecyclerView.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
                tvNumShow.setText(String.valueOf((newPosition + 1) + "/" + datas.size()));
//                tvTitle.setText(datas.get(newPosition).getTitle());
            }
        });

//        mRecyclerView.scrollToPosition(position);
    }

//    @OnClick(R.id.iv_back)
//    public void onClick() {
//        finish();
//    }

    @Override
    public void complete() {

    }

    @Override
    public void setImageUrls(List<String> urls) {
        if (MStringUtil.isEmptyList(urls)) {
            MToastUtil.show(MeiziImgDetailActivity.this, "图片链接是空的");
        }
        detailPresent.getImage(urls.get(0));

        datas.clear();
        datas.addAll(urls);
        tvNumShow.setText("1/" + urls.size());
        mAdapter.notifyDataSetChanged();

//        adapter = ImagePagerAdapter.createDetailPageAdapter(this.getSupportFragmentManager(), datas);
//        viewPage.setAdapter(adapter);
//        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                tvText.setText((position + 1) + "/" + datas.size());
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

    }

    @Override
    public void setImage(Image image) {
    }


    private class RecycleAdapter extends FragmentStatePagerAdapter {
        LinkedHashMap<Integer, ImageDetailFragment> mFragmentCache = new LinkedHashMap<>();

        public RecycleAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i, Fragment.SavedState savedState) {
            Logger.d("i:" + i + "      containsKey:" + mFragmentCache.containsKey(i));
            ImageDetailFragment f = mFragmentCache.containsKey(i) ? mFragmentCache.get(i)
                    : ImageDetailFragment.newInstance(datas.get(i));
            if (savedState == null || f.getArguments() == null) {
            } else if (!mFragmentCache.containsKey(i)) {
                f.setInitialSavedState(savedState);
            }
            f.setListener(v -> startAnima());
            mFragmentCache.put(i, f);
            return f;
        }

        @Override
        public void onDestroyItem(int i, Fragment fragment) {
            // onDestroyItem
            while (mFragmentCache.size() > 8) {
                Object[] keys = mFragmentCache.keySet().toArray();
                mFragmentCache.remove(keys[0]);
            }
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }


    private void startAnima() {
        if (tvNumShow.getVisibility() != View.VISIBLE) {
            tvNumShow.startAnimation(mAnimaFade);
            tvTitle.startAnimation(mAnimaFade);
        } else {
            tvNumShow.startAnimation(mAnimaHold);
            tvTitle.startAnimation(mAnimaHold);
        }
    }
}

