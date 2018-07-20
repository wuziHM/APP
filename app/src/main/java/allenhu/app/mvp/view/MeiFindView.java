package allenhu.app.mvp.view;

import com.hlib.app.IBaseView;

import java.util.ArrayList;
import java.util.List;

import allenhu.app.bean.request.FindItemBean;

/**
 * Author：HM $ on 18/6/11 17:54
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public interface MeiFindView extends IBaseView {
//    void setData(ArrayList<ArrayList<FindItemBean>> list1);
//
//    void setTopics(ArrayList<FindItemBean> returnTopic);
//
//    void setGirls(ArrayList<FindItemBean> returnGirl);
//    void setData(ArrayList<ArrayList<ArrayList<FindItemBean>>> list1);

    void setData(ArrayList<ArrayList<ArrayList<FindItemBean>>> meiNewBeanList);

    void setTopics(List<ArrayList<FindItemBean>> returnTopic);

    /**
     * 解析出来4个item
     *
     * @param returnGirl
     */
    void setGirls(List<ArrayList<FindItemBean>> returnGirl);
}
