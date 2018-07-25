package allenhu.app.bean.request;

/**
 * Author：HM $ on 18/7/25 10:35
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class MeiCameraBean {

    /**
     * id : 8185
     * date : 2018-07-24T21:31:06
     * img_src : http://wx1.sinaimg.cn/mw690/9d52c073gy1ftl9x1ks4hj20j60pj118.jpg
     * thumb_src : http://wx1.sinaimg.cn/thumb150/9d52c073gy1ftl9x1ks4hj20j60pj118.jpg
     */

    private int id;
    private String date;
    private String img_src;
    private String thumb_src;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public String getThumb_src() {
        return thumb_src;
    }

    public void setThumb_src(String thumb_src) {
        this.thumb_src = thumb_src;
    }
}
