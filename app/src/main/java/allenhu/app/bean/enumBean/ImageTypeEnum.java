package allenhu.app.bean.enumBean;

/**
 * Author：HM $ on 18/6/13 14:00
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public enum ImageTypeEnum {

    MEIZI(0, "妹子图"), BAIDU(1, "百度api");

    private int code;
    private String name;

    ImageTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
