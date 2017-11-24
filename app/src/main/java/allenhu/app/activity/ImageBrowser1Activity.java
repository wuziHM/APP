package allenhu.app.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lsjwzh.widget.recyclerviewpager.FragmentStatePagerAdapter;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.maning.mndialoglibrary.MStatusDialog;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.bean.request.ShowImgBean;
import allenhu.app.fragment.ImgBrowserFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageBrowser1Activity extends BaseActivity {


//    protected static final String PARAM1 = "data_param";
//    protected static final String PARAM2 = "data_param2";

    @BindView(R.id.rl_black_bg)
    RelativeLayout rlBlackBg;


//    @BindView(R.id.mnGestureView)
//    MNGestureView mnGestureView;

    @BindView(R.id.tvNumShow)
    TextView tvNumShow;

    @BindView(R.id.view_bottom_bg)
    View viewBottomBg;

    @BindView(R.id.browser_root)
    RelativeLayout browserRoot;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.list)
    RecyclerViewPager mRecyclerView;

    private Context context;
    private ArrayList<ShowImgBean.ShowapiResBodyEntity.PagebeanEntity.ContentlistEntity.ListEntity> arrayList;
    private int position;


    private MStatusDialog dialog;


    private Animation mAnimaFade;       //alpha 0--->1
    private Animation mAnimaHold;       //alpha 1--->0
    private AA mAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_browser1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setWindowFullScreen();
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_image_browser);
        ButterKnife.bind(this);
        initIntent();
        initView();

    }

    private void setWindowFullScreen() {
        //设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= 19) {

            // 虚拟导航栏透明
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void initIntent() {


        arrayList = (ArrayList<ShowImgBean.ShowapiResBodyEntity.PagebeanEntity.ContentlistEntity.ListEntity>) getIntent().getExtras().get(PARAM1);
        position = getIntent().getIntExtra(PARAM2, 1);


        tvNumShow.setText(String.valueOf((position + 1) + "/" + arrayList.size()));

        tvTitle.setText(arrayList.get(position).getTitle());

    }

    private void initView() {


        context = ImageBrowser1Activity.this;
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
        mAdapter = new AA(getSupportFragmentManager());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLongClickable(true);

        mRecyclerView.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
                tvNumShow.setText(String.valueOf((newPosition + 1) + "/" + arrayList.size()));
                tvTitle.setText(arrayList.get(newPosition).getTitle());
            }
        });

        mRecyclerView.scrollToPosition(position);

    }

    /**
     * 成功的操作提示
     */
    private void showSuccess() {
        if (dialog == null) {
            dialog = new MStatusDialog(this);
        }
        dialog.show("保存成功", getResources().getDrawable(R.drawable.gank_icon_pull_refresh_success_white));
    }

    private void finishBrowser() {
        tvNumShow.setVisibility(View.GONE);
        rlBlackBg.setAlpha(0);
        finish();
    }

    @OnClick(R.id.list)
    public void onClick() {
    }


    private class AA extends FragmentStatePagerAdapter {
        LinkedHashMap<Integer, ImgBrowserFragment> mFragmentCache = new LinkedHashMap<>();

        public AA(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i, Fragment.SavedState savedState) {

            ImgBrowserFragment f = mFragmentCache.containsKey(i) ? mFragmentCache.get(i)
                    : ImgBrowserFragment.newInstance(arrayList.get(i).getBig(), arrayList.get(i).getTitle(), i);
            if (savedState == null || f.getArguments() == null) {
            } else if (!mFragmentCache.containsKey(i)) {
                f.setInitialSavedState(savedState);
            }
            f.setListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startAnima();
                }
            });
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
            return arrayList.size();
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
