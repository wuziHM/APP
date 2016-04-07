package allenhu.app.adapter;

/**
 * Author：燕青 $ on 16/4/7 10:14
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import allenhu.app.R;
import allenhu.app.bean.Container;

/**
 * Created by Lijizhou on 2016/2/21.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context context;
    //    private String[] titles;
    private List<Container> containers;

    //建立枚举 2个item 类型
    public enum ITEM_TYPE {
        ITEM1,
        ITEM2
    }

//    public RecyclerViewAdapter(Context context, String[] titles) {
//        this.titles = titles;
//        this.context = context;
//        mLayoutInflater = LayoutInflater.from(context);
//    }

    public RecyclerViewAdapter(Context context, List<Container> containers) {
        this.containers = containers;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载Item View的时候根据不同TYPE加载不同的布局
        if (viewType == ITEM_TYPE.ITEM1.ordinal()) {
            //表示是货物
            return new Item1ViewHolder(mLayoutInflater.inflate(R.layout.item1, parent, false));
        } else {
            //表示具体尺码
            return new Item2ViewHolder(mLayoutInflater.inflate(R.layout.item2, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Item1ViewHolder) {
            ((Item1ViewHolder) holder).mTextView.setText(containers.get(position).getPurchaseBean().getTitle());
            ((Item1ViewHolder) holder).mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else if (holder instanceof Item2ViewHolder) {
            ((Item2ViewHolder) holder).mTextView.setText(containers.get(position).getGoodsSize().getColor());
        }
    }

    //设置ITEM类型，可以自由发挥，这里设置item position单数显示item1 偶数显示item2
    @Override
    public int getItemViewType(int position) {
        //Enum类提供了一个ordinal()方法，返回枚举类型的序数，这里ITEM_TYPE.ITEM1.ordinal()代表0， ITEM_TYPE.ITEM2.ordinal()代表1
        return containers.get(position).getGoodsSize() == null ? ITEM_TYPE.ITEM1.ordinal() : ITEM_TYPE.ITEM2.ordinal();
    }

    @Override
    public int getItemCount() {
        return containers == null ? 0 : containers.size();
    }

    //item1 的ViewHolder
    public static class Item1ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public Item1ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_item1_text);
        }
    }

    //item2 的ViewHolder
    public static class Item2ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public Item2ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_item2_text);
        }
    }
}