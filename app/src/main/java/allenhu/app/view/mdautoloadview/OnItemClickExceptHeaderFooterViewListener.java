package allenhu.app.view.mdautoloadview;

import android.view.View;
import android.widget.AdapterView;

/**
 * 除去头部底部视图点击事件
 * Created by moguangjian on 15/9/1 10:46.
 */
public interface OnItemClickExceptHeaderFooterViewListener {


    void onItemClick(AdapterView<?> parent, View view, int position, long id);

    /**
     * 点击加载更多项
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    void onClickMore(AdapterView<?> parent, View view, int position, long id);
}
