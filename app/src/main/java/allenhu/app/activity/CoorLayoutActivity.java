package allenhu.app.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhy.base.adapter.recyclerview.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import allenhu.app.R;
import allenhu.app.adapter.DividerItemDecoration;
import allenhu.app.adapter.HomeAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CoorLayoutActivity extends AppCompatActivity {

    @BindView(R.id.ivImage)
    ImageView ivImage;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;

    @BindView(R.id.recycle)
    EmptyRecyclerView recycle;

    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    private List<String> list;


    private String imgURL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512559134864&di=5f611d0878216f50163856b48daddbc3&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F48540923dd54564e38493e2ab9de9c82d1584ff1.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coor_layout);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initView();
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
                io.reactivex.Observable.timer(2, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                swipe.setRefreshing(false);
                            }
                        });
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        Glide.with(this)
                .asBitmap()
                .load(imgURL)
                .thumbnail(0.2f)
                .into(ivImage);
    }

}
