package allenhu.app.bean.request;

import java.io.Serializable;

/**
 * Author：HM $ on 18/7/16 11:55
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class FindItemBean implements Serializable{

    /**
     * show : no
     * tagid : 82
     * title : 性感
     * cover : http://wx3.sinaimg.cn/mw690/9d52c073gy1frm1sklskij205y05yglw.jpg
     */

    private String show;
    private String tagid;
    private String title;
    private String cover;

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getTagid() {
        return tagid;
    }

    public void setTagid(String tagid) {
        this.tagid = tagid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
