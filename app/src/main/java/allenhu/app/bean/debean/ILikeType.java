package allenhu.app.bean.debean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Author：HM $ on 17/11/29 11:22
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */

@DatabaseTable(tableName = "db_i_type")
public class ILikeType implements Serializable{

    @DatabaseField(columnName = "type_id", generatedId = true, canBeNull = false)
    private int id;

    @DatabaseField(columnName = "type_name")
    private String type_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return type_name;
    }

    public void setName(String name) {
        this.type_name = name;
    }

    @Override
    public String toString() {
        return "type_name:" + type_name + "   id:" + id;
    }
}
