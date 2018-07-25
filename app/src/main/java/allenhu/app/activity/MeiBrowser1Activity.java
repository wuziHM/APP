package allenhu.app.activity;

import android.content.Context;
import android.content.Intent;
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
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.bean.request.MeiBrowserBean;
import allenhu.app.fragment.ImgBrowserFragment;
import allenhu.app.mvp.present.MeiBrowserPresenter;
import allenhu.app.mvp.view.MeiBrowserView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeiBrowser1Activity extends BaseActivity implements MeiBrowserView {


    @BindView(R.id.rl_black_bg)
    RelativeLayout rlBlackBg;

    @BindView(R.id.tvNumShow)
    TextView tvNumShow;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.list)
    RecyclerViewPager mRecyclerView;

    private Context context;
//    private int position;

    private MeiBrowserPresenter presenter;

    private MStatusDialog dialog;


    private Animation mAnimaFade;       //alpha 0--->1
    private Animation mAnimaHold;       //alpha 1--->0
    private AA mAdapter;
    private int id;
    private List<String> arrayList = new ArrayList<>();

    public static void toMeiBrowser(Context context, int mid) {
        Intent intent = new Intent(context, MeiBrowser1Activity.class);
        intent.putExtra("id", mid);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setWindowFullScreen();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_browser1);
        ButterKnife.bind(this);
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


    private void initView() {

        presenter = new MeiBrowserPresenter(this);

        context = MeiBrowser1Activity.this;
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

        id = getIntent().getIntExtra("id", 0);
        presenter.getData(id);
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


    @OnClick(R.id.list)
    public void onClick() {
    }

    @Override
    public void setData(MeiBrowserBean bean) {
        String content = bean.getContent();
        content.replace("\"", "");
        String[] contents = content.split(",");
        arrayList = Arrays.asList(contents);
        tvNumShow.setText(1 + "/" + arrayList.size());

        mAdapter = new AA(getSupportFragmentManager());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLongClickable(true);

        mRecyclerView.addOnPageChangedListener((oldPosition, newPosition) -> tvNumShow.setText(String.valueOf((newPosition + 1) + "/" + arrayList.size())));

    }

    @Override
    public void finishRequest() {

    }


    private class AA extends FragmentStatePagerAdapter {
        LinkedHashMap<Integer, ImgBrowserFragment> mFragmentCache = new LinkedHashMap<>();

        public AA(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i, Fragment.SavedState savedState) {

            ImgBrowserFragment f = mFragmentCache.containsKey(i) ? mFragmentCache.get(i)
                    : ImgBrowserFragment.newInstance(arrayList.get(i).replace("\"",""), "", i);
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
