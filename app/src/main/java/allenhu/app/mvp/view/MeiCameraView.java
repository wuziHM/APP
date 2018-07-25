package allenhu.app.mvp.view;

import com.hlib.app.IBaseView;

import java.util.List;

import allenhu.app.bean.request.MeiCameraBean;

/**
 * Author：HM $ on 18/6/11 17:54
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public interface MeiCameraView extends IBaseView {

    void setData(List<MeiCameraBean> meiCameraViews);

    void finishRequest();

}
