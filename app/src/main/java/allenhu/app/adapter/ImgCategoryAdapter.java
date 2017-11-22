package allenhu.app.adapter;

import android.content.Context;

import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

import allenhu.app.R;
import allenhu.app.bean.request.ImgListBean;

/**
 * Author：HM $ on 17/11/21 15:51
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class ImgCategoryAdapter extends CommonAdapter {

    public ImgCategoryAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, Object o) {
        ImgListBean.ShowapiResBodyEntity.ListEntity.Entity entity = (ImgListBean.ShowapiResBodyEntity.ListEntity.Entity) o;
        holder.setText(R.id.id_num, entity.getName());
    }

}
