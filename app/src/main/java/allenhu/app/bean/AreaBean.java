package allenhu.app.bean;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Author：燕青 $ on 2016/10/13  13:57
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
@DatabaseTable(tableName = "db_area")
public class AreaBean {

    @DatabaseField(generatedId = true, columnName = "area_id", canBeNull = false)
    private int key;

    @DatabaseField(columnName = "area_name")
    private String value;

    @DatabaseField(canBeNull = true, foreign = true, columnName = "parent_id", foreignAutoRefresh = true)
    private CityBean cityBean;

    private Object obj;

    public int getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = Integer.parseInt(key);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

}
