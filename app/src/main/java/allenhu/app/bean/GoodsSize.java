package allenhu.app.bean;

/**
 * Author：燕青 $ on 16/4/7 10:53
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class GoodsSize {
    private String color;
    private String size;
    private double price;
    private int count;

    public GoodsSize() {
    }

    public GoodsSize(String color, String size, double price, int count) {
        this.color = color;
        this.size = size;
        this.price = price;
        this.count = count;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
