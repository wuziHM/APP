package allenhu.app.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.util.List;

/**
 * Author：燕青 $ on 2016/10/13  13:54
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
@DatabaseTable(tableName = "db_province")
public class ProvinceBean {

    /**
     * key : 110000
     * value : 北京市
     * citys : [{"key":"110100","value":"市辖区","citys":[{"key":"110101","value":"东城区","citys":null},{"key":"110102","value":"西城区","citys":null},{"key":"110105","value":"朝阳区","citys":null},{"key":"110106","value":"丰台区","citys":null},{"key":"110107","value":"石景山区","citys":null},{"key":"110108","value":"海淀区","citys":null},{"key":"110109","value":"门头沟区","citys":null},{"key":"110111","value":"房山区","citys":null},{"key":"110112","value":"通州区","citys":null},{"key":"110113","value":"顺义区","citys":null},{"key":"110114","value":"昌平区","citys":null},{"key":"110115","value":"大兴区","citys":null},{"key":"110116","value":"怀柔区","citys":null},{"key":"110117","value":"平谷区","citys":null}]},{"key":"110200","value":"县","citys":[{"key":"110228","value":"密云县","citys":null},{"key":"110229","value":"延庆县","citys":null}]}]
     */

    @DatabaseField(generatedId = true,columnName = "province_id", canBeNull = false)
    private int key;

    @DatabaseField(columnName = "province_name")
    private String value;
    /**
     * key : 110100
     * value : 市辖区
     * citys : [{"key":"110101","value":"东城区","citys":null},{"key":"110102","value":"西城区","citys":null},{"key":"110105","value":"朝阳区","citys":null},{"key":"110106","value":"丰台区","citys":null},{"key":"110107","value":"石景山区","citys":null},{"key":"110108","value":"海淀区","citys":null},{"key":"110109","value":"门头沟区","citys":null},{"key":"110111","value":"房山区","citys":null},{"key":"110112","value":"通州区","citys":null},{"key":"110113","value":"顺义区","citys":null},{"key":"110114","value":"昌平区","citys":null},{"key":"110115","value":"大兴区","citys":null},{"key":"110116","value":"怀柔区","citys":null},{"key":"110117","value":"平谷区","citys":null}]
     */
    @ForeignCollectionField
    private List<CityBean> citys;

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

    public List<CityBean> getCitys() {
        return citys;
    }

    public void setCitys(List<CityBean> citys) {
        this.citys = citys;
    }

}
