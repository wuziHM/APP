package allenhu.app.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

import allenhu.app.R;
import allenhu.app.bean.request.FindItemBean;

/**
 * Created by AllenHu on 2016/2/16.
 */

/**
 * Created by Jay on 2015/10/16 0016.
 */
public class TopicAdapter extends CommonAdapter<FindItemBean> {


    public TopicAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }


    @Override
    public void convert(ViewHolder holder, FindItemBean findItemBean) {

        Glide.with(mContext).load(findItemBean.getCover()).into((ImageView) holder.getView(R.id.iv_image));
        holder.setText(R.id.tv_type, findItemBean.getTitle());

    }
}
