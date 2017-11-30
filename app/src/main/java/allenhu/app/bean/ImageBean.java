package allenhu.app.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

import allenhu.app.bean.debean.ILikeType;

/**
 * Author：HM $ on 17/11/24 16:44
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
@DatabaseTable(tableName = "db_i_like")
public class ImageBean implements Serializable {


    @DatabaseField(columnName = "img_id", generatedId = true, canBeNull = false)
    private int img_id;


    @DatabaseField(columnName = "big_img", canBeNull = false)
    private String big;

    @DatabaseField(columnName = "middle_img")
    private String middle;

    @DatabaseField(columnName = "small_img")
    private String small;

    @DatabaseField(columnName = "date_time")
    private String date;

    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @DatabaseField(canBeNull = false, foreign = true, columnName = "type_id")
    private ILikeType type;


    @DatabaseField(columnName = "title")
    private String title;


    public ILikeType getType() {
        return type;
    }

    public void setType(ILikeType type) {
        this.type = type;
    }

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


    @Override
    public String toString() {
        return "big:" + big + "     middle:" + middle + "    title" + title + "     typeName:" + typeName;
    }
}
