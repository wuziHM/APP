package allenhu.app.mvp.view;

import org.jsoup.nodes.Document;

import java.util.List;

import allenhu.app.bean.Type;

/**
 * Author：HM $ on 18/6/11 15:36
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public interface WelfareView extends IBaseView{

    void showData(Document object);

    void setTypes(List<Type> types);

}
