package allenhu.app.bean;

import java.util.List;

/**
 * Author：燕青 $ on 16/4/7 10:52
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class PurchaseBean {

    private String title;
    private int id;
    private List<GoodsSize> goodsSizes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<GoodsSize> getGoodsSizes() {
        return goodsSizes;
    }

    public void setGoodsSizes(List<GoodsSize> goodsSizes) {
        this.goodsSizes = goodsSizes;
    }
}
