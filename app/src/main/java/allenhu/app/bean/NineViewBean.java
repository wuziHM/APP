package allenhu.app.bean;

import com.lzy.ninegrid.ImageInfo;

import java.util.List;

/**
 * Author：HM $ on 18/7/11 14:20
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class NineViewBean {
    private String time;
    private List<com.lzy.ninegrid.ImageInfo> infos;

    public NineViewBean() {
    }

    public NineViewBean(String time, List<com.lzy.ninegrid.ImageInfo> infos) {
        this.time = time;
        this.infos = infos;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<com.lzy.ninegrid.ImageInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<ImageInfo> infos) {
        this.infos = infos;
    }
}
