package allenhu.app.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;

import allenhu.app.R;
import allenhu.app.adapter.DividerItemDecoration;
import allenhu.app.adapter.RecyclerViewAdapter;
import allenhu.app.base.BaseActivity;
import allenhu.app.bean.Container;
import allenhu.app.bean.GoodsSize;
import allenhu.app.bean.PurchaseBean;

public class RecycleActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private List<Container> listC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecycleActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(RecycleActivity.this, DividerItemDecoration.VERTICAL_LIST));
        listC = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            PurchaseBean purchaseBean = new PurchaseBean();
            purchaseBean.setId(i);
            purchaseBean.setTitle("这是第 " + i + " 个");
            Container cp = new Container();
            cp.setPurchaseBean(purchaseBean);
            listC.add(cp);
            List list = null;
            for (int j = 0; j < i; j++) {
                list = new ArrayList();
                GoodsSize size = new GoodsSize("红色" + j, "xxxxL" + j, j, j);
                list.add(size);
                Container cg = new Container();
                cg.setGoodsSize(size);
                listC.add(cg);
            }
            purchaseBean.setGoodsSizes(list);
        }
        recyclerView.setAdapter(new RecyclerViewAdapter(this, listC));
    }
}
