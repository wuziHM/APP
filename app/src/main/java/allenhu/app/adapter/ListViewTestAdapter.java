package allenhu.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import allenhu.app.R;
import allenhu.app.bean.GoodsSize;
import allenhu.app.util.LogUtil;

/**
 * Author：燕青 $ on 16/4/7 20:53
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class ListViewTestAdapter extends BaseAdapter {
    private List list;
    private Context context;
    private LayoutInflater inflater;

    public ListViewTestAdapter(List list, Context context) {
        this.list = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item1, null);
        TextView t = (TextView) convertView.findViewById(R.id.tv_item1_text);
        t.setText("" + ((GoodsSize) list.get(position)).getColor());
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((GoodsSize) getItem(position)).setColor("黑色");
                ((GoodsSize) list.get(position)).setColor("黑色");
                notifyDataSetChanged();
                LogUtil.e("点了TextView");
            }
        });
        return convertView;
    }
}
