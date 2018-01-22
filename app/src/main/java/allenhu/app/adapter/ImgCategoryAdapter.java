package allenhu.app.adapter;

import android.content.Context;

import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

import allenhu.app.R;
import allenhu.app.bean.debean.ILikeType;
import allenhu.app.bean.request.ImgListBean;

/**
 * Author：HM $ on 17/11/21 15:51
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class ImgCategoryAdapter extends CommonAdapter<Object> {


    public static final int TYPE_CATEGORY = -1;
    public static final int TYPE_FROM_DB = -2;

    int type;

    public ImgCategoryAdapter(Context context, int layoutId, List datas, int type) {
        super(context, layoutId, datas);
        this.type = type;
    }

    @Override
    public void convert(ViewHolder holder, Object o) {
        switch (type) {
            case TYPE_CATEGORY:
                ImgListBean.ShowapiResBodyEntity.ListEntity.Entity entity = (ImgListBean.ShowapiResBodyEntity.ListEntity.Entity) o;
                holder.setText(R.id.id_num, entity.getName());
                break;

            case TYPE_FROM_DB:
                ILikeType type = (ILikeType) o;
                holder.setText(R.id.id_num, type.getName());
                break;

        }
    }

}
