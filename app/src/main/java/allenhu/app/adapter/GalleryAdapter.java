package allenhu.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import allenhu.app.R;
import allenhu.app.bean.ImageInfo;

/**
 * Created by AllenHu on 2016/2/15.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private List<ImageInfo> imgList;
    private LayoutInflater mInflater;
    private OnItemClickLitener onItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.onItemClickLitener = onItemClickLitener;
    }

    public GalleryAdapter(Context context, List<ImageInfo> imgList) {
        mInflater = LayoutInflater.from(context);
        this.imgList = imgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_gallery, null);
        ViewHolder holder = new ViewHolder(view);
        holder.mImg = (ImageView) view.findViewById(R.id.id_index_gallery_item_image);
        holder.mTxt = (TextView) view.findViewById(R.id.id_index_gallery_item_text);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mImg.setImageResource(imgList.get(position).getResouce());
        holder.mTxt.setText(imgList.get(position).getContext());
        if(onItemClickLitener!=null){
            holder.mImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickLitener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        ImageView mImg;
        TextView mTxt;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

}
