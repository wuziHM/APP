package allenhu.app.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import allenhu.app.R;
import allenhu.app.adapter.ListViewTestAdapter;
import allenhu.app.activity.base.BaseActivity;
import allenhu.app.bean.GoodsSize;

public class ListViewActivity extends BaseActivity {

    private ListView l1, l2;
    private Button b1, b2;
    private String[] ss = {"11111", "2222", "3333", "4444"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_list_view;
    }

    @Override
    protected void onMCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        l1 = (ListView) findViewById(R.id.listView1);
        l2 = (ListView) findViewById(R.id.listView1);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, ss);
        l1.setAdapter(adapter);

        b1 = (Button) findViewById(R.id.btn1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ss[1] = "改过之后的";
                adapter.notifyDataSetChanged();
            }
        });

        final List list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            GoodsSize goodsSize = new GoodsSize("红色", "XXXX", 10.2d, 88);
            list.add(goodsSize);
        }
        final ListViewTestAdapter adapter1 = new ListViewTestAdapter(list, ListViewActivity.this);
        l2.setAdapter(adapter1);
        b2 = (Button) findViewById(R.id.btn2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GoodsSize) list.get(2)).setColor("绿色");
                adapter1.notifyDataSetChanged();
            }
        });


    }
}
