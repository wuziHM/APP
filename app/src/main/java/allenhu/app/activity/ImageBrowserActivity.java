package allenhu.app.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.maning.mndialoglibrary.MStatusDialog;

import java.util.ArrayList;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.bean.ImageBean;
import allenhu.app.view.ganhuo.MNGestureView;
import allenhu.app.view.ganhuo.ProgressWheel;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageBrowserActivity extends BaseActivity {


//    protected static final String PARAM1 = "data_param";
//    protected static final String PARAM2 = "data_param2";

    @BindView(R.id.rl_black_bg)
    RelativeLayout rlBlackBg;

    @BindView(R.id.viewPagerBrowser)
    ViewPager viewPagerBrowser;

    @BindView(R.id.mnGestureView)
    MNGestureView mnGestureView;

    @BindView(R.id.tvNumShow)
    TextView tvNumShow;

    @BindView(R.id.view_bottom_bg)
    View viewBottomBg;

    @BindView(R.id.browser_root)
    RelativeLayout browserRoot;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    private Context context;
    private ArrayList<ImageBean> arrayList;
    private int position;


    private MStatusDialog dialog;
    private int clickPosition;
    private ImageView currentImageView;


    private Animation mAnimaFade;       //alpha 0--->1
    private Animation mAnimaHold;       //alpha 1--->0


    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_browser;
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


        arrayList = (ArrayList<ImageBean>) getIntent().getExtras().get(PARAM1);
        position = getIntent().getIntExtra(PARAM2, 1);


        tvNumShow.setText(String.valueOf((position + 1) + "/" + arrayList.size()));

        tvTitle.setText(arrayList.get(position).getTitle());
    }

    private void initView() {


        context = ImageBrowserActivity.this;
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

        mnGestureView.setOnSwipeListener(new MNGestureView.OnSwipeListener() {
            @Override
            public void downSwipe() {
                finishBrowser();
            }

            @Override
            public void onSwiping(float deltaY) {
                tvNumShow.setVisibility(View.GONE);

                float mAlpha = 1 - deltaY / 500;
                if (mAlpha < 0.3) {
                    mAlpha = 0.3f;
                }
                if (mAlpha > 1) {
                    mAlpha = 1;
                }
                rlBlackBg.setAlpha(mAlpha);
            }

            @Override
            public void overSwipe() {
                tvNumShow.setVisibility(View.VISIBLE);
                rlBlackBg.setAlpha(1);
            }
        });


        viewPagerBrowser.setAdapter(new MyAdapter());
        viewPagerBrowser.setCurrentItem(position);
        viewPagerBrowser.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvNumShow.setText(String.valueOf((position + 1) + "/" + arrayList.size()));
                tvTitle.setText(arrayList.get(position).getTitle());

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
//        this.overridePendingTransition(0, R.anim.browser_exit_anim);
    }


    private class MyAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        public MyAdapter() {
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View inflate = layoutInflater.inflate(R.layout.mn_image_browser_item_show_image, container, false);
            final PhotoView imageView = (PhotoView) inflate.findViewById(R.id.photoImageView);
            RelativeLayout rl_browser_root = (RelativeLayout) inflate.findViewById(R.id.rl_browser_root);
            final ProgressWheel progressWheel = (ProgressWheel) inflate.findViewById(R.id.progressWheel);
            final RelativeLayout rl_image_placeholder_bg = (RelativeLayout) inflate.findViewById(R.id.rl_image_placeholder_bg);
            final ImageView iv_fail = (ImageView) inflate.findViewById(R.id.iv_fail);
            iv_fail.setVisibility(View.GONE);

            final String url = arrayList.get(position).getBig();
            Glide.with(context)
                    .load(url)
                    .thumbnail(0.2f)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            progressWheel.setVisibility(View.GONE);
                            iv_fail.setVisibility(View.VISIBLE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            progressWheel.setVisibility(View.GONE);
                            rl_image_placeholder_bg.setVisibility(View.GONE);
                            iv_fail.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(imageView);

//            rl_browser_root.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    finishBrowser();
//                }
//            });


            imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
//                    finishBrowser();
                    startAnima();
                }
            });

//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    MLogUtil.e("单击图片");
//                    finishBrowser();
//                }
//            });

            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    clickPosition = position;
                    currentImageView = imageView;
                    //显示隐藏下面的Dialog
//                    showBottomSheet();
                    return false;
                }
            });

            container.addView(inflate);
            return inflate;
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
