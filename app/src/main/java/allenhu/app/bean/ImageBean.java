package allenhu.app.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Author：HM $ on 17/11/24 16:44
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
@DatabaseTable(tableName = "db_i_like")
public class ImageBean implements Serializable {

    @DatabaseField(columnName = "big_img", canBeNull = false)
    private String big;

    @DatabaseField(columnName = "middle_img")
    private String middle;

    @DatabaseField(columnName = "small_img")
    private String small;

    @DatabaseField(columnName = "date_time")
    private String date;

    @DatabaseField(columnName = "title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBig() {
        return big;
    }

    public void setBig(String big) {
        this.big = big;
    }

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }
}
