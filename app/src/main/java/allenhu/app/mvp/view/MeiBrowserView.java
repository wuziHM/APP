package allenhu.app.mvp.view;

import com.hlib.app.IBaseView;

import allenhu.app.bean.request.MeiBrowserBean;

/**
 * Author：HM $ on 18/6/11 17:54
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public interface MeiBrowserView extends IBaseView {

    void setData(MeiBrowserBean browserBean);

    void finishRequest();

}
