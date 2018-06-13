package allenhu.app.mvp.view;

import java.util.List;

import allenhu.app.bean.ImageBean;

/**
 * Author：HM $ on 18/6/11 17:54
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public interface ImageListView {


    void showData(List<ImageBean> imageBeans, boolean isRefresh);

    void complete();

}
