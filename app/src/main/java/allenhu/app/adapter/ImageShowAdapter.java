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
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.hlib.util.MUnitConversionUtil;
import com.ldoublem.thumbUplib.ThumbUpView;
import com.orhanobut.logger.Logger;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

import allenhu.app.R;
import allenhu.app.bean.ImageBean;
import allenhu.app.bean.PicSizeEntity;
import allenhu.app.bean.debean.ILikeType;
import allenhu.app.db.ILikeDao;
import allenhu.app.db.TypeDao;
import allenhu.app.util.MySnackbar;

/**
 * Author：HM $ on 17/11/22 14:26
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class ImageShowAdapter extends CommonAdapter<ImageBean> {

    public static final short WELFARE_TYPE = 1;
    public static final short BAIDU_TYPE = 0;

    private RequestOptions options;
    private int screenWidth;

    private ILikeDao likeDao;
    private TypeDao typeDao;
    private ArrayMap<String, PicSizeEntity> picSizeEntityArrayMap = new ArrayMap<>();
    private int adapterType;

    public ImageShowAdapter(Context context, int layoutId, List datas, int type) {
        super(context, layoutId, datas);
        options = new RequestOptions();
        options.fitCenter();
        options.placeholder(R.mipmap.ic_beauty);
        options.diskCacheStrategy(DiskCacheStrategy.ALL);

        screenWidth = MUnitConversionUtil.getWidth(context);
        likeDao = new ILikeDao(mContext);
        typeDao = new TypeDao(mContext);
        this.adapterType = type;
    }

    public ImageShowAdapter(Context context, int layoutId, List datas) {
        this(context, layoutId, datas, BAIDU_TYPE);
    }

    @Override
    public void convert(final ViewHolder holder, final ImageBean imageBean) {
        holder.setText(R.id.tvShowTime, imageBean.getDate());
        String url = imageBean.getMiddle();
        PicSizeEntity picSizeEntity = picSizeEntityArrayMap.get(imageBean.getMiddle());
        if (picSizeEntity != null && !picSizeEntity.isNull()) {
            int width = picSizeEntity.getPicWidth();
            int height = picSizeEntity.getPicHeight();
            //计算高宽比
            int finalHeight = (screenWidth / 2) * height / width;
            ViewGroup.LayoutParams layoutParams = holder.getView(R.id.rl_root).getLayoutParams();
            layoutParams.height = finalHeight;
        }

        switch (adapterType) {
            case BAIDU_TYPE:
                loadBaiduImage(url, imageBean, holder);
                break;

            case WELFARE_TYPE:
                loadWelfareImage(url, imageBean, holder);
                break;
        }

        boolean isLike = likeDao.isExist(imageBean.getBig());
        if (isLike)
            ((ThumbUpView) holder.getView(R.id.btn_collect2)).setLike();
        else {
            ((ThumbUpView) holder.getView(R.id.btn_collect2)).setUnlike();
        }
        ((ThumbUpView) holder.getView(R.id.btn_collect2)).setOnThumbUp(new ThumbUpView.OnThumbUp() {
            @Override
            public void like(boolean like) {


                Logger.d("image-->" + imageBean.toString());

                if (like) {

                    ILikeType type = typeDao.getTypeAndAdd(imageBean.getTypeName());
                    imageBean.setType(type);
                    boolean insertResult = likeDao.add(imageBean);
                    if (insertResult) {
                        MySnackbar.makeSnackBarBlack(holder.getView(R.id.tvShowTime), "收藏成功");
                    } else {
                        MySnackbar.makeSnackBarRed(holder.getView(R.id.tvShowTime), "收藏失败");
                        ((ThumbUpView) holder.getView(R.id.btn_collect2)).setUnlike();
                    }
                } else {
                    boolean deleteResult = likeDao.delete(imageBean);
                    if (deleteResult) {
                        MySnackbar.makeSnackBarBlack(holder.getView(R.id.tvShowTime), "取消收藏成功");
                    } else {
                        ((ThumbUpView) holder.getView(R.id.btn_collect2)).setLike();
                        MySnackbar.makeSnackBarRed(holder.getView(R.id.tvShowTime), "取消收藏失败");
                    }
                }
            }
        });

    }

    /**
     * 显示妹子图抓的图片
     *
     * @param url
     * @param imageBean
     * @param holder
     */
    private void loadWelfareImage(String url, ImageBean imageBean, ViewHolder holder) {

        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder()
                .addHeader("referer", imageBean.getMiddle())
                .addHeader("user-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36")
                .build()
        );

        Glide.with(mContext)
                .asBitmap()
                .load(glideUrl)
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
                        PicSizeEntity picSizeEntity = picSizeEntityArrayMap.get(imageBean.getMiddle());
                        if (picSizeEntity == null || picSizeEntity.isNull()) {
                            int width = resource.getWidth();
                            int height = resource.getHeight();
                            //计算高宽比
                            int finalHeight = (screenWidth / 2) * height / width;
                            ViewGroup.LayoutParams layoutParams = holder.getView(R.id.rl_root).getLayoutParams();
                            layoutParams.height = finalHeight;
                            picSizeEntityArrayMap.put(imageBean.getMiddle(), new PicSizeEntity(width, height));
                        }
                        return false;
                    }
                })
                .into((ImageView) holder.getView(R.id.image));

    }


    /**
     * 显示百度买的那个图片地址的的图片
     *
     * @param url
     * @param imageBean
     * @param holder
     */
    private void loadBaiduImage(String url, ImageBean imageBean, ViewHolder holder) {
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
                        PicSizeEntity picSizeEntity = picSizeEntityArrayMap.get(imageBean.getMiddle());
                        if (picSizeEntity == null || picSizeEntity.isNull()) {
                            int width = resource.getWidth();
                            int height = resource.getHeight();
                            //计算高宽比
                            int finalHeight = (screenWidth / 2) * height / width;
                            ViewGroup.LayoutParams layoutParams = holder.getView(R.id.rl_root).getLayoutParams();
                            layoutParams.height = finalHeight;
                            picSizeEntityArrayMap.put(imageBean.getMiddle(), new PicSizeEntity(width, height));
                        }
                        return false;
                    }
                })
                .into((ImageView) holder.getView(R.id.image));

    }
}
