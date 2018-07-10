package allenhu.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.hlib.util.MToastUtil;
import com.orhanobut.logger.Logger;
import com.zhy.base.adapter.recyclerview.EmptyRecyclerView;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.adapter.ImageShowAdapter;
import allenhu.app.bean.ImageBean;
import allenhu.app.bean.request.ShowImgBean;
import allenhu.app.db.ILikeDao;
import allenhu.app.net.retrofit2.NetWork;
import allenhu.app.util.Constant;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 分类图片缩略图的界面
 */
public class ImageCateActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {


    @BindView(R.id.ivArrow)
    ImageView ivArrow;

    @BindView(R.id.tvRefresh)
    TextView tvRefresh;

    @BindView(R.id.swipe_target)
    EmptyRecyclerView swipeTarget;

    @BindView(R.id.tvLoadMore)
    TextView tvLoadMore;

    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;


    private ImageShowAdapter adapter;

    //是否是从本地数据库去拿数据
    private boolean isFromDB;

    private ArrayList<ImageBean> imgList;

    private String typeId;

    private int likeType;

    //数据库查询工具
    private ILikeDao dao;

    private int page = 1;
    private Disposable disobs;
    private Disposable disRef;

    /**
     * 根据接口的id去服务器请求数据
     *
     * @param id
     */
    public static void toImageCateActivity(Context context, String id) {
        Intent intent = new Intent(context, ImageCateActivity.class);
        intent.putExtra(PARAM1, id);
        context.startActivity(intent);
    }

    /**
     * 根据本地数据库的数据去查询数据
     *
     * @param context
     * @param id
     */
    public static void toImageCateActivity(Context context, int id) {
        Intent intent = new Intent(context, ImageCateActivity.class);
        intent.putExtra(PARAM1, id);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_cate);
        ButterKnife.bind(this);

        if (getIntent().getExtras().get(PARAM1) instanceof Integer) {
            isFromDB = true;
            likeType = getIntent().getIntExtra(PARAM1, 0);
            Logger.d("likeType:" + likeType);
        } else if (getIntent().getExtras().get(PARAM1) instanceof String) {
            isFromDB = false;
            typeId = getIntent().getExtras().getString(PARAM1);

        }

        initView();
    }


    private void initView() {
        dao = new ILikeDao(this);

        imgList = new ArrayList<>();

        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);

        adapter = new ImageShowAdapter(getMContext(), R.layout.item_img_staggered, imgList);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setItemPrefetchEnabled(false);
        swipeTarget.setLayoutManager(staggeredGridLayoutManager);
        swipeTarget.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Intent intent = new Intent(ImageCateActivity.this, ImageBrowser1Activity.class);
                intent.putExtra(PARAM1, imgList);
                intent.putExtra(PARAM2, position);


                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        ImageCateActivity.this,
                        view.findViewById(R.id.image),
                        getString(R.string.transition_wechat_img)
                );

                ActivityCompat.startActivity(ImageCateActivity.this, intent, optionsCompat.toBundle());
//                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        swipeTarget.setAdapter(adapter);
        swipeTarget.setEmptyView(LayoutInflater.from(this).inflate(R.layout.layout_empty, null));

        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(true);
            }
        }, 100);
    }

    @Override
    public void onRefresh() {
        if (isFromDB) {
            page = 1;
            imgList.clear();
            disRef = refreshObs.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(consumer);
        } else {
            dealPage(false);
            getData();
        }
    }


    @Override
    public void onLoadMore() {
        if (isFromDB) {
            page++;
            disobs = loadObs.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(consumer);
        } else {
            dealPage(true);
            getData();
        }
    }

    private void dealPage(boolean isLoadMore) {
        page = isLoadMore ? page + 1 : 1;
    }

    private void getData() {
        showProgress();
        NetWork.getImageApi()
                .getImageShow(typeId, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mImgObserver);
    }

    private void overRefresh() {
        hideProgress();
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);

    }


    /**
     * 加载更多的时候的操作
     */
    Observable loadObs = Observable.create(new ObservableOnSubscribe() {
        @Override
        public void subscribe(ObservableEmitter e) throws Exception {
            e.onNext(dao.getTypeByPage(likeType, page, Constant.PAGE_SIZE));
        }
    });

    /**
     * 数据库刷新的时候的操作
     */
    Observable<List<ImageBean>> refreshObs = Observable.create(new ObservableOnSubscribe() {
        @Override
        public void subscribe(ObservableEmitter e) throws Exception {
            e.onNext(dao.getTypeByPage(likeType, page, Constant.PAGE_SIZE));
        }
    });

    /**
     * 处理
     */
    Consumer consumer = new Consumer<List<ImageBean>>() {
        @Override
        public void accept(List<ImageBean> imageBeans) throws Exception {
            overRefresh();
            if (imageBeans == null || imageBeans.size() < Constant.PAGE_SIZE) {
                swipeToLoadLayout.setLoadMoreEnabled(false);
            } else {
                swipeToLoadLayout.setLoadMoreEnabled(true);
            }
            imgList.addAll(imageBeans);
            adapter.notifyDataSetChanged();
        }
    };


    Observer<ShowImgBean> mImgObserver = new Observer<ShowImgBean>() {

        Disposable d;

        @Override
        public void onSubscribe(Disposable d) {
            this.d = d;
        }

        @Override
        public void onNext(ShowImgBean showImgBean) {

            if (showImgBean != null && showImgBean.getShowapi_res_code() == 0) {
                if (page == 1) {
                    imgList.clear();
                }
                imgList.addAll(ShowImgBean.getListEn(showImgBean));

                int max = showImgBean.getShowapi_res_body().getPagebean().getMaxResult();
                boolean enable = max >= page ? false : true;
                swipeToLoadLayout.setLoadMoreEnabled(enable);
                adapter.notifyDataSetChanged();

            } else {
                MToastUtil.show(ImageCateActivity.this, "数据获取失败");
            }
            overRefresh();
        }

        @Override
        public void onError(Throwable e) {
            overRefresh();
        }

        @Override
        public void onComplete() {
            overRefresh();
            d.dispose();
        }

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disobs != null) {
            disobs.dispose();
        }
        if (disRef != null) {
            disRef.dispose();
        }
    }
}
