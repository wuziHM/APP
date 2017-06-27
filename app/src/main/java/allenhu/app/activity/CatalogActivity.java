package allenhu.app.activity;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;

import allenhu.app.R;
import allenhu.app.adapter.ContentAdapter;
import allenhu.app.view.IndexableListView;

/**
 * 右边带索引条的listview
 */
public class CatalogActivity extends Activity {

    private ArrayList mItems;
    private IndexableListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        //初始化数据
        initData();
    }

    private void initData() {
        mItems = new ArrayList();
        mItems.add("12345");
        mItems.add("$$@@@");
        mItems.add("A allen");
        mItems.add("B bill");
        mItems.add("C city");
        mItems.add("D day");
        mItems.add("E easy");
        mItems.add("F fit");
        mItems.add("G grade");
        mItems.add("H hide");
        mItems.add("I important");
        mItems.add("J json");
        mItems.add("K kit");
        mItems.add("L line");
        mItems.add("M mine");
        mItems.add("N nice");
        mItems.add("O offer");
        mItems.add("P perfect");
        mItems.add("Q queen");
        mItems.add("R run");
        mItems.add("S sit");
        mItems.add("T today");
        mItems.add("U user");
        mItems.add("V volley");
        mItems.add("W wonderful");
        mItems.add("X xenon");
        mItems.add("Y yellow");
        mItems.add("Z zero");
        Collections.sort(mItems);

        ContentAdapter adapter = new ContentAdapter(this, android.R.layout.simple_list_item_1, mItems);
        listview = (IndexableListView) findViewById(R.id.listView);
        listview.setAdapter(adapter);
        listview.setFastScrollEnabled(true);
    }
}
