package allenhu.app.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import allenhu.app.R;
import allenhu.app.adapter.DividerItemDecoration;
import allenhu.app.adapter.RecyclerViewAdapter;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.bean.Container;
import allenhu.app.bean.GoodsSize;
import allenhu.app.bean.PurchaseBean;
import allenhu.app.impl.CallBackH;

public class RecycleActivity extends BaseActivity implements CallBackH {
    private RecyclerView recyclerView;
    private List<Container> listC;
    private TextView tvCall;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycle;
    }

    @Override
    protected void onMCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {

        tvCall = (TextView) findViewById(R.id.tvCall);

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
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, listC);
        adapter.setCallback(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void test(String s) {
        tvCall.setText(s);
    }
}
