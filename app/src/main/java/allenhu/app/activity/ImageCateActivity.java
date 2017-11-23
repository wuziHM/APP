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
import com.zhy.base.adapter.recyclerview.EmptyRecyclerView;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.adapter.ImageShowAdapter;
import allenhu.app.bean.request.ShowImgBean;
import allenhu.app.net.retrofit2.NetWork;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

    private int maxPage;


    private ArrayList<ShowImgBean.ShowapiResBodyEntity.PagebeanEntity.ContentlistEntity.ListEntity> imgList;

    private String typeId;
    private int page = 1;

    public static void toImageCateActivity(Context context, String id) {
        Intent intent = new Intent(context, ImageCateActivity.class);
        intent.putExtra(PARAM1, id);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_cate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();

    }


    private void initView() {

        typeId = getIntent().getExtras().getString(PARAM1);
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
                Intent intent = new Intent(ImageCateActivity.this, ImageBrowserActivity.class);
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
        dealPage(false);
        getData();
    }


    @Override
    public void onLoadMore() {
        dealPage(true);
        getData();
    }

    private void dealPage(boolean isLoadMore) {
        page = isLoadMore ? page + 1 : 1;
    }

    private void getData() {
        showProgress();
        NetWork.getImageApi()
                .getImageShow(typeId, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mImgObserver);
    }

    private void overRefresh() {
        hideProgress();
        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);

    }


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
}
