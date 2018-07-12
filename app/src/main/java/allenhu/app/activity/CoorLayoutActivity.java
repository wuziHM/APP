package allenhu.app.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bumptech.glide.Glide;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.hlib.util.MToastUtil;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.orhanobut.logger.Logger;
import com.zhy.base.adapter.recyclerview.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import allenhu.app.R;
import allenhu.app.adapter.NineViewAdapter;
import allenhu.app.bean.NineViewBean;
import allenhu.app.bean.request.FindBg;
import allenhu.app.net.retrofit2.NetWork;
import allenhu.app.util.GlideImageLoader;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CoorLayoutActivity extends AppCompatActivity {

    @BindView(R.id.ivImage)
    KenBurnsView ivImage;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;

    @BindView(R.id.recycle)
    EmptyRecyclerView recycle;


    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    private List<FindBg.ImagesEntity> images;
    private List<NineViewBean> beans;
//    private List<ImageInfo> infos;


//    private List<String> list;

    private int position;

    private String[] nineImages = {
            "http://i2.meizitu.net/2018/07/06a01.jpg",
            "http://i2.meizitu.net/2018/07/06a02.jpg",
            "http://i2.meizitu.net/2018/07/06a03.jpg",
            "http://i2.meizitu.net/2018/07/06a04.jpg",
            "http://i2.meizitu.net/2018/07/06a05.jpg",
            "http://i2.meizitu.net/2018/07/06a06.jpg",
            "http://i2.meizitu.net/2018/07/06a07.jpg",
            "http://i2.meizitu.net/2018/07/06a08.jpg",
            "http://i2.meizitu.net/2018/07/06a09.jpg"};


    private String imgURL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512559134864&di=5f611d0878216f50163856b48daddbc3&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F48540923dd54564e38493e2ab9de9c82d1584ff1.jpg";
    private Disposable d;


    public static final String BG_BASE_URL = "http://www.bing.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coor_layout);
        ButterKnife.bind(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initView();
        requestData();

    }

    private void initView() {
        beans = new ArrayList<>();
        List<ImageInfo> infos = new ArrayList<>();
        for (String s : nineImages) {
            ImageInfo info = new ImageInfo();
            info.setBigImageUrl(s);
            info.setThumbnailUrl(s);
            infos.add(info);
        }

        for (int i = 0; i < 3; i++) {
            beans.add(new NineViewBean("2018-01-05 12:33", infos));
        }

        NineGridView.setImageLoader(new GlideImageLoader());
        NineViewAdapter adapter = new NineViewAdapter(this, R.layout.item_nineview_timeline, beans);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setAdapter(adapter);


        swipe.setColorSchemeResources(
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_blue_bright
        );

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (images == null || images.size() == 0) {
                    requestData();
                } else {
                    loadImage();
                    swipe.setRefreshing(false);
                }

//                io.reactivex.Observable.timer(2, TimeUnit.SECONDS)
//                        .subscribeOn(Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Consumer<Long>() {
//                            @Override
//                            public void accept(Long aLong) throws Exception {
//                                swipe.setRefreshing(false);
//                            }
//                        });
            }
        });
        loadImage();

    }

    /**
     * 加载图片
     */
    private void loadImage() {
        String url;
        if (images == null || images.size() == 0) {
            url = imgURL;
//            setSupportActionBar(toolbar);
            toolbarLayout.setTitle("默认图片");
        } else {
            if (position < images.size() - 1) {
                position++;
            } else {
                position = 0;
            }
            url = BG_BASE_URL + images.get(position).getUrl();
            String title = images.get(position).getCopyright();
            Logger.d("title:" + title);
//            setSupportActionBar(toolbar);
            toolbarLayout.setTitle(title);
        }
        Glide.with(this)
                .asBitmap()
                .load(url)
                .thumbnail(0.2f)
                .into(ivImage);
    }


    private void requestData() {
        d = NetWork.getIFindApi().getFindBg("js", 0, 30)
                .subscribeOn(Schedulers.newThread())
                .map(findBg -> {
                    Logger.d(findBg);
                    return findBg.getImages();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(imagesBeans -> {
                    swipe.setRefreshing(false);
                    images = imagesBeans;
                }, throwable -> {
                    swipe.setRefreshing(false);
                    Logger.d("throwable" + throwable.getMessage());
                    MToastUtil.show(CoorLayoutActivity.this, "数据请求失败");
                });
//        d = NetWork.getIFindApi().getFindBg("js", 0, 8)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<FindBg>() {
//                    @Override
//                    public void accept(FindBg imagesBeans) throws Exception {
//                        swipe.setRefreshing(false);
//                        images = imagesBeans.getImages();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        swipe.setRefreshing(false);
//                        Logger.d("throwable-->" + throwable.getMessage());
//                        MToastUtil.show(CoorLayoutActivity.this, "数据请求失败");
//                    }
//                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ivImage.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ivImage.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        d.dispose();
    }
}
