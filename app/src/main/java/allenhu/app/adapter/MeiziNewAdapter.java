package allenhu.app.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.ArrayMap;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.hlib.util.MUnitConversionUtil;
import com.orhanobut.logger.Logger;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

import allenhu.app.R;
import allenhu.app.bean.PicSizeEntity;
import allenhu.app.bean.request.MeiNewBean;
import io.reactivex.annotations.Nullable;

/**
 * Author：HM $ on 17/11/21 15:51
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class MeiziNewAdapter extends CommonAdapter<MeiNewBean> {
    public static final int TYPE_NEW = 0;
    public static final int TYPE_REC = 1;
    int type = TYPE_NEW;


    private ArrayMap<String, PicSizeEntity> picSizeEntityArrayMap = new ArrayMap<>();
    private RequestOptions options;
    private int screenWidth;


    public MeiziNewAdapter(Context context, int layoutId, List<MeiNewBean> datas, int type) {
        super(context, layoutId, datas);
        this.type = type;
        options = new RequestOptions();
        options.fitCenter();
        options.placeholder(R.mipmap.ic_beauty);
        options.diskCacheStrategy(DiskCacheStrategy.ALL);

        screenWidth = MUnitConversionUtil.getWidth(context);

    }

    @Override
    public void convert(ViewHolder holder, MeiNewBean o) {
        holder.setText(R.id.tv_num, o.getImg_num() + "P");
        holder.setText(R.id.tv_des, o.getTitle());
        if (type == TYPE_REC) {
            ((TextView) holder.getView(R.id.tv_num)).setTextSize(10);
            ((TextView) holder.getView(R.id.tv_des)).setTextSize(10);
        }

        String url = type == TYPE_NEW ? o.getThumb_src() : o.getThumb_src_min();

        PicSizeEntity picSizeEntity = picSizeEntityArrayMap.get(url);
        if (picSizeEntity != null && !picSizeEntity.isNull()) {
            int width = picSizeEntity.getPicWidth();
            int height = picSizeEntity.getPicHeight();
            //计算高宽比
            int finalHeight = getFinalHeight(width, height);
            ViewGroup.LayoutParams layoutParams = holder.getView(R.id.rl_root).getLayoutParams();
            layoutParams.height = finalHeight;
            Logger.d("width:" + width + "   height:" + height + "    finalHeight:" + finalHeight + "    screenWidth:" + screenWidth);
        }
        Glide.with(mContext)
                .asBitmap()
                .load(url)
                .apply(options)
                .thumbnail(0.2f)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        PicSizeEntity picSizeEntity = picSizeEntityArrayMap.get(url);
                        if (picSizeEntity == null || picSizeEntity.isNull()) {
                            int width = resource.getWidth();
                            int height = resource.getHeight();
                            //计算高宽比
                            int finalHeight = getFinalHeight(width, height);
                            ViewGroup.LayoutParams layoutParams = holder.getView(R.id.rl_root).getLayoutParams();
                            layoutParams.height = finalHeight;
                            picSizeEntityArrayMap.put(url, new PicSizeEntity(width, height));
                        }
                        return false;
                    }
                })
                .into((ImageView) holder.getView(R.id.iv_image));

    }

    private int getFinalHeight(int width, int height) {
        return type == TYPE_NEW ? (screenWidth) * height / width : (screenWidth / 2) * height / width;
    }

}
