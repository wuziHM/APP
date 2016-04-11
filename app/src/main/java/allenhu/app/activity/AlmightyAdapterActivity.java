package allenhu.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.abslistview.CommonAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import allenhu.app.R;
import allenhu.app.base.BaseActivity;

public class AlmightyAdapterActivity extends BaseActivity {

    private List<String> mDatas = new ArrayList<>(Arrays.asList("MultiItem ListView",
            "RecyclerView",
            "MultiItem RecyclerView", "RecyclerViewWithHeader"));
    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almighty_adapter);

        mListView = ((ListView) AlmightyAdapterActivity.this.findViewById(R.id.id_listview_list));
        mListView.setAdapter(new CommonAdapter<String>(this, R.layout.item_list, mDatas) {
            @Override
            public void convert(ViewHolder holder, String o) {
                holder.setText(R.id.id_item_list_title, o);
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                    default:
                        intent = new Intent(AlmightyAdapterActivity.this, MultiItemListViewActivity.class);
                        break;
                    case 1:
                        intent = new Intent(AlmightyAdapterActivity.this, RecyclerViewActivity.class);
                        break;
                    case 2:
                        intent = new Intent(AlmightyAdapterActivity.this, MultiItemRvActivity.class);
                        break;
                    case 3:
                        intent = new Intent(AlmightyAdapterActivity.this, RvWidthHeaderActivity.class);
                        break;

                }
                if (intent != null)
                    startActivity(intent);
            }
        });
    }


}
