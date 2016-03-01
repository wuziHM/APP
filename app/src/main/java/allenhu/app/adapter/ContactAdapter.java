package allenhu.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import allenhu.app.R;
import allenhu.app.bean.ContactBean;
import allenhu.app.listener.OnItemClickListener;

/**
 * Created by AllenHu on 2016/3/1.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {


    private Context context;
    private List list;
    private OnItemClickListener onItemClickListener;

    public ContactAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ContactHolder holder = new ContactHolder(LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final ContactHolder holder, int position) {

        ContactBean bean = (ContactBean) list.get(position);

        holder.tvName.setText(bean.getName() + "");
        holder.tvPhone.setText(bean.getPhone() + "");
        holder.ivHeader.setImageBitmap(bean.getHeaderImg());

        // 如果设置了回调，则设置点击事件
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ContactHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvPhone;
        private ImageView ivHeader;

        public ContactHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvPhone = (TextView) itemView.findViewById(R.id.tv_phone);
            ivHeader = (ImageView) itemView.findViewById(R.id.iv_header);
        }
    }
}
