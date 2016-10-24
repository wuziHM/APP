package allenhu.app.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.util.List;

/**
 * Author：燕青 $ on 2016/10/13  13:56
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
@DatabaseTable(tableName = "db_city")
public class CityBean {

    @DatabaseField(generatedId = true, columnName = "city_id", canBeNull = false)
    private int key;

    @DatabaseField(columnName = "city_name")
    private String value;
    /**
     * key : 110101
     * value : 东城区
     * areas : null
     */
    @ForeignCollectionField
    private Collection<AreaBean> areas;

    @DatabaseField(canBeNull = true, foreign = true, columnName = "parent_id", foreignAutoRefresh = true)
    private ProvinceBean provinceBean;

    public ProvinceBean getProvinceBean() {
        return provinceBean;
    }

    public void setProvinceBean(ProvinceBean provinceBean) {
        this.provinceBean = provinceBean;
    }

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


    public Collection<AreaBean> getAreas() {
        return areas;
    }

    public void setAreas(List<AreaBean> areas) {
        this.areas = areas;
    }
}
