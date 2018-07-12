package allenhu.app.adapter;

import android.content.Context;

import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

import allenhu.app.R;
import allenhu.app.bean.NineViewBean;

/**
 * Author：HM $ on 17/11/21 15:51
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class NineViewAdapter extends CommonAdapter<NineViewBean> {

//    int type;

    public NineViewAdapter(Context context, int layoutId, List<NineViewBean> datas) {
        super(context, layoutId, datas);
//        this.type = type;
    }

    @Override
    public void convert(ViewHolder holder, NineViewBean o) {
        NineGridView gridView = holder.getView(R.id.nine_view);
        gridView.setAdapter(new NineGridViewClickAdapter(mContext, o.getInfos()));
//        gridView.setAdapter(new NineGridViewAdapter() {
//            @Override
//            protected void onImageItemClick(Context context, NineGridView nineGridView, int index, List<ImageInfo> imageInfo) {
//                super.onImageItemClick(context, nineGridView, index, imageInfo);
//            }
//
//            @Override
//            protected ImageView generateImageView(Context context) {
//                return super.generateImageView(context);
//            }
//
//            @Override
//            public List<ImageInfo> getImageInfo() {
//                return super.getImageInfo();
//            }
//
//            @Override
//            public void setImageInfoList(List<ImageInfo> imageInfo) {
//                super.setImageInfoList(imageInfo);
//            }
//        });
    }

}
