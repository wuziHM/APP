package allenhu.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hlib.util.MLogUtil;
import com.zhy.base.adapter.recyclerview.EmptyRecyclerView;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import allenhu.app.R;
import allenhu.app.activity.ImageCateActivity;
import allenhu.app.adapter.ImgCategoryAdapter;
import allenhu.app.bean.request.ImgListBean;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 图片列表的界面
 */
public class ImgListFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String PARAM_ID = "param1";
    @BindView(R.id.recycle)
    EmptyRecyclerView recycle;

    private ImgListBean.ShowapiResBodyEntity.ListEntity listEntity;

    private ImgCategoryAdapter adapter;

    public ImgListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 请求的id
     * @return A new instance of fragment ImgListFragment.
     */
    public static ImgListFragment newInstance(ImgListBean.ShowapiResBodyEntity.ListEntity param1) {
        ImgListFragment fragment = new ImgListFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARAM_ID, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listEntity = (ImgListBean.ShowapiResBodyEntity.ListEntity) getArguments().getSerializable(PARAM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_img_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    private void initView() {
        adapter = new ImgCategoryAdapter(getContext(), R.layout.item_recycler, listEntity.getList(), ImgCategoryAdapter.TYPE_CATEGORY);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {

                ImgListBean.ShowapiResBodyEntity.ListEntity.Entity entity = (ImgListBean.ShowapiResBodyEntity.ListEntity.Entity) o;
                MLogUtil.i("entity.getId:" + entity.getId());
                ImageCateActivity.toImageCateActivity(getContext(), entity.getId());
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        recycle.setLayoutManager(new LinearLayoutManager(getContext()));
//        recycle.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

        recycle.setAdapter(adapter);
    }
}
