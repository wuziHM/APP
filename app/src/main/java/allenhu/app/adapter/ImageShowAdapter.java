package allenhu.app.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.hlib.util.MUnitConversionUtil;
import com.ldoublem.thumbUplib.ThumbUpView;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

import allenhu.app.R;
import allenhu.app.bean.ImageBean;
import allenhu.app.bean.PicSizeEntity;
import allenhu.app.db.ILikeDao;
import allenhu.app.util.MySnackbar;

/**
 * Author：HM $ on 17/11/22 14:26
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class ImageShowAdapter extends CommonAdapter<ImageBean> {


    private RequestOptions options;
    private int screenWidth;

    private ILikeDao likeDao;
    private ArrayMap<String, PicSizeEntity> picSizeEntityArrayMap = new ArrayMap<>();


    public ImageShowAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
        options = new RequestOptions();
        options.fitCenter();
        options.placeholder(R.mipmap.ic_beauty);
        options.diskCacheStrategy(DiskCacheStrategy.ALL);
        screenWidth = MUnitConversionUtil.getWidth(context);

        likeDao = new ILikeDao(mContext);


    }

//    public void setDate(String date) {
//        this.date = date;
//        notifyDataSetChanged();
//    }

    @Override
    public void convert(final ViewHolder holder, final ImageBean o) {
        holder.setText(R.id.tvShowTime, o.getDate());
        String url = o.getMiddle();
        PicSizeEntity picSizeEntity = picSizeEntityArrayMap.get(o.getMiddle());
        if (picSizeEntity != null && !picSizeEntity.isNull()) {
            int width = picSizeEntity.getPicWidth();
            int height = picSizeEntity.getPicHeight();
            //计算高宽比
            int finalHeight = (screenWidth / 2) * height / width;
            ViewGroup.LayoutParams layoutParams = holder.getView(R.id.rl_root).getLayoutParams();
            layoutParams.height = finalHeight;
        }

        Glide.with(mContext)
                .asBitmap()
                .load(url)
                .apply(options)
                .thumbnail(0.2f)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        PicSizeEntity picSizeEntity = picSizeEntityArrayMap.get(o.getMiddle());
                        if (picSizeEntity == null || picSizeEntity.isNull()) {
                            int width = resource.getWidth();
                            int height = resource.getHeight();
                            //计算高宽比
                            int finalHeight = (screenWidth / 2) * height / width;
                            ViewGroup.LayoutParams layoutParams = holder.getView(R.id.rl_root).getLayoutParams();
                            layoutParams.height = finalHeight;
                            picSizeEntityArrayMap.put(o.getMiddle(), new PicSizeEntity(width, height));
                        }
                        return false;
                    }
                })
                .into((ImageView) holder.getView(R.id.image));

        final ThumbUpView upView = holder.getView(R.id.btn_collect2);
        boolean isLike = likeDao.isExist(o.getBig());
        if (isLike)
            upView.Like();
        else {
            upView.UnLike();
        }
        upView.setOnThumbUp(new ThumbUpView.OnThumbUp() {
            @Override
            public void like(boolean like) {
                if (like) {
                    boolean insertResult = likeDao.add(o);
                    if (insertResult) {
                        MySnackbar.makeSnackBarBlack(holder.getView(R.id.tvShowTime), "收藏成功");
                    } else {
                        upView.setUnlike();
                        MySnackbar.makeSnackBarRed(holder.getView(R.id.tvShowTime), "收藏失败");
                    }
                } else {
                    boolean deleteResult = likeDao.delete(o);
                    if (deleteResult) {
                        MySnackbar.makeSnackBarBlack(holder.getView(R.id.tvShowTime), "取消收藏成功");
                    } else {
                        upView.setLike();
                        MySnackbar.makeSnackBarRed(holder.getView(R.id.tvShowTime), "取消收藏失败");
                    }
                }
            }
        });

    }
}
