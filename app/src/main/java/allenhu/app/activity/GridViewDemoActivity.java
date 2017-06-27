package allenhu.app.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import allenhu.app.R;
import allenhu.app.adapter.DividerItemDecoration;
import allenhu.app.adapter.GalleryAdapter;
import allenhu.app.base.BaseActivity;
import allenhu.app.bean.ImageInfo;
import allenhu.app.view.MyRecyclerView;

public class GridViewDemoActivity extends BaseActivity {

    private ImageView image;
    private MyRecyclerView recycle;
    private List<ImageInfo> list;
    private int[] imgs = {R.mipmap.looker1, R.mipmap.looker2, R.mipmap.looker3, R.mipmap.looker4,
            R.mipmap.looker5, R.mipmap.looker6, R.mipmap.looker7, R.mipmap.looker8};


    @Override
    protected int getLayoutId() {
        return R.layout.activity_gird_view_demo;
    }

    @Override
    protected void onMCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recycle = (MyRecyclerView) findViewById(R.id.re_gallery);
        image = (ImageView) findViewById(R.id.id_content);
        initData();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycle.setLayoutManager(manager);
        GalleryAdapter adapter = new GalleryAdapter(this, list);
        adapter.setOnItemClickLitener(new GalleryAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                view.setSelected(true);
                image.setImageResource(imgs[position]);
            }
        });
        recycle.setOnItemScrollChangeListener(new MyRecyclerView.OnItemScrollChangeListener() {
            @Override
            public void onChange(View view, int position) {
                image.setImageResource(imgs[position]);
            }
        });
        recycle.setAdapter(adapter);
        recycle.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));

    }

    private void initData() {
        list = new ArrayList<>();

        for (int i = 0; i < imgs.length; i++) {
            ImageInfo info = new ImageInfo();
            info.setResouce(imgs[i]);
            info.setContext("looker_" + i);
            list.add(info);
        }
    }
}
