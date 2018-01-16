package allenhu.app.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.zhy.base.adapter.recyclerview.EmptyRecyclerView;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import allenhu.app.R;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.adapter.ImgCategoryAdapter;
import allenhu.app.bean.debean.ILikeType;
import allenhu.app.db.TypeDao;
import allenhu.app.util.Constant;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CollectActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {

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

    //当前页码
    private long page = 1;


    private TypeDao typeDao;

    private ImgCategoryAdapter adapter;
    private List list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_cate);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {

        typeDao = new TypeDao(this);
        list = new ArrayList();

        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);


        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(true);
            }
        }, 100);

        adapter = new ImgCategoryAdapter(this, R.layout.item_recycler, list, ImgCategoryAdapter.TYPE_FROM_DB);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {

                ILikeType entity = (ILikeType) o;
                ImageCateActivity.toImageCateActivity(CollectActivity.this, entity.getId());
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        swipeTarget.setLayoutManager(new LinearLayoutManager(this));

        swipeTarget.setAdapter(adapter);

    }

    @Override
    public void onRefresh() {
        refeshObs.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Consumer<List<ILikeType>>() {

                    @Override
                    public void accept(List<ILikeType> likeTypes) throws Exception {
                        list.clear();
                        updateUI(likeTypes);
                    }
                });
    }

    /**
     * 更新界面
     *
     * @param likeTypes
     */
    private void updateUI(List<ILikeType> likeTypes) {

        swipeToLoadLayout.setRefreshing(false);
        swipeToLoadLayout.setLoadingMore(false);
        if (likeTypes == null || likeTypes.size() < Constant.PAGE_SIZE) {
            swipeToLoadLayout.setLoadMoreEnabled(false);
        } else {
            swipeToLoadLayout.setLoadMoreEnabled(true);
        }
        list.addAll(likeTypes);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        LoadObs.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Consumer<List<ILikeType>>() {

                    @Override
                    public void accept(List<ILikeType> likeTypes) throws Exception {
                        updateUI(likeTypes);
                    }
                });
    }


    Observable<List<ILikeType>> refeshObs = Observable.create(new ObservableOnSubscribe() {
        @Override
        public void subscribe(ObservableEmitter e) throws Exception {
            page = 1;
            e.onNext(typeDao.getTypeByPage(page, Constant.PAGE_SIZE));
        }
    });


    Observable LoadObs = Observable.create(new ObservableOnSubscribe() {
        @Override
        public void subscribe(ObservableEmitter e) throws Exception {
            page++;
            e.onNext(typeDao.getTypeByPage(page, Constant.PAGE_SIZE));
        }
    });
}
