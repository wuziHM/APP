package allenhu.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.orhanobut.logger.Logger;
import com.zhy.base.adapter.recyclerview.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import allenhu.app.R;
import allenhu.app.adapter.NineViewAdapter;
import allenhu.app.bean.NineViewBean;
import allenhu.app.bean.request.MeiCameraBean;
import allenhu.app.mvp.present.MeiCameraPresenter;
import allenhu.app.mvp.view.MeiCameraView;
import allenhu.app.util.GlideImageLoader;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class MeiCameraFragment extends BaseFragment implements MeiCameraView {

    @BindView(R.id.swipe_target)
    EmptyRecyclerView recycle;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    private View rootView;
    private List<NineViewBean> beans;
    private NineViewAdapter adapter;

    MeiCameraPresenter presenter;
    private int page = 1;


    public MeiCameraFragment() {
    }

    public static MeiCameraFragment newInstance(String url) {
        MeiCameraFragment fragment = new MeiCameraFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_image_cate, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }

    private void initView() {
        presenter = new MeiCameraPresenter(this);

        beans = new ArrayList<>();
        NineGridView.setImageLoader(new GlideImageLoader());
        adapter = new NineViewAdapter(getContext(), R.layout.item_nineview_timeline, beans);
        recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        recycle.setAdapter(adapter);

        presenter.getCameraData(page);
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenter.getCameraData(page);
            }
        });
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                presenter.getCameraData(page);
            }
        });
    }

    @Override
    public void setData(List<MeiCameraBean> meiCameraBeans) {
        if (meiCameraBeans == null || meiCameraBeans.isEmpty()) {
            Logger.d("setData-->数据是空的");
            return;
        }
        if (page == 1)
            beans.clear();
        beans.addAll(getNineViewBeans(meiCameraBeans));
        adapter.notifyDataSetChanged();
    }


    /**
     * 转换成nineviewbean
     *
     * @param meiCameraBeans
     * @return
     */
    private List<NineViewBean> getNineViewBeans(List<MeiCameraBean> meiCameraBeans) {
        int size = meiCameraBeans.size();
        NineViewBean bean = null;
        List<NineViewBean> nineViewBeans = new ArrayList<>();
        List<ImageInfo> imageInfos = null;

        /*
         * NineViewBean里面有0~9张图片，把返回的图片放到nineViewBeans里面
            前面的NineViewBean都放9张图片，余下的放到最后的NineViewBean里面，
            就是说前面的NineViewBean里都有9张图片，最后如果不足9张的话放到最后一个NineViewBean里面
         */
        for (int i = 0; i < size; i++) {
            if (i % 9 == 0) {
                if (i / 9 >= 1) {
                    bean.setInfos(imageInfos);
                    nineViewBeans.add(bean);
                }
                imageInfos = new ArrayList<>();
                bean = new NineViewBean();
                bean.setTime(meiCameraBeans.get(i).getDate());
            }
            //最后不足9张的时候做处理
            if (i == size - 1) {
                if (imageInfos.size() > 0) {
                    bean.setInfos(imageInfos);
                    nineViewBeans.add(bean);
                }
            }
            ImageInfo info = new ImageInfo();
            info.setBigImageUrl(meiCameraBeans.get(i).getImg_src());
            info.setThumbnailUrl(meiCameraBeans.get(i).getThumb_src());
            imageInfos.add(info);
        }
        return nineViewBeans;
    }

    @Override
    public void finishRequest() {
        swipeToLoadLayout.setLoadingMore(false);
        swipeToLoadLayout.setRefreshing(false);
    }
}
