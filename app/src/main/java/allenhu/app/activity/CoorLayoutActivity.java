package allenhu.app.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.hlib.util.MToastUtil;
import com.orhanobut.logger.Logger;
import com.zhy.base.adapter.recyclerview.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import allenhu.app.R;
import allenhu.app.adapter.DividerItemDecoration;
import allenhu.app.adapter.HomeAdapter;
import allenhu.app.bean.request.FindBg;
import allenhu.app.net.retrofit2.NetWork;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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


    private List<String> list;

    private int position;


    private String imgURL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512559134864&di=5f611d0878216f50163856b48daddbc3&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F48540923dd54564e38493e2ab9de9c82d1584ff1.jpg";
    private Disposable d;


    public static final String BG_BASE_URL = "http://www.bing.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coor_layout);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initView();
        requestData();

    }

    private void initView() {


        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Random random = new Random();
            list.add("第" + i + "个随机数:" + random.nextInt());
        }

        HomeAdapter adapter = new HomeAdapter(list, this);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
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
                .map(new Function<FindBg, List<FindBg.ImagesEntity>>() {
                    @Override
                    public List<FindBg.ImagesEntity> apply(FindBg findBg) throws Exception {
                        com.orhanobut.logger.Logger.d(findBg);
                        return findBg.getImages();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<FindBg.ImagesEntity>>() {
                    @Override
                    public void accept(List<FindBg.ImagesEntity> imagesBeans) throws Exception {
                        swipe.setRefreshing(false);
                        images = imagesBeans;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        swipe.setRefreshing(false);
                        Logger.d("throwable" + throwable.getMessage());
                        MToastUtil.show(CoorLayoutActivity.this, "数据请求失败");
                    }
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
