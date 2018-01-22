package allenhu.app.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.imagepicker.bean.ImageItem;
import com.zhy.base.adapter.ViewHolder;

import java.io.File;
import java.util.List;

import allenhu.app.R;

/**
 * Author：HM $ on 18/1/19 13:44
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class ImageUpAdapter extends com.zhy.base.adapter.recyclerview.CommonAdapter<ImageItem> {


    private RequestOptions options;

    public ImageUpAdapter(Context context, int layoutId, List<ImageItem> datas) {
        super(context, layoutId, datas);

        options = new RequestOptions();
        options.fitCenter();
        options.placeholder(R.mipmap.ic_beauty);
        options.fitCenter();
        options.diskCacheStrategy(DiskCacheStrategy.ALL);
    }

    @Override
    public void convert(ViewHolder holder, ImageItem o) {

        Glide.with(mContext)
                .asBitmap()
                .load(new File(o.path))
                .apply(options)
                .thumbnail(0.2f)
                .into((ImageView) holder.getView(R.id.iv_image));
    }
}
