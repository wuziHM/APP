package allenhu.app.bean;

import java.util.List;

/**
 * Author：燕青 $ on 2016/10/13  15:05
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class PBean {

    /**
     * key : 110000
     * value : 北京市
     * children : [{"key":"110100","value":"市辖区","children":[{"key":"110101","value":"东城区","children":null},{"key":"110102","value":"西城区","children":null},{"key":"110105","value":"朝阳区","children":null},{"key":"110106","value":"丰台区","children":null},{"key":"110107","value":"石景山区","children":null},{"key":"110108","value":"海淀区","children":null},{"key":"110109","value":"门头沟区","children":null},{"key":"110111","value":"房山区","children":null},{"key":"110112","value":"通州区","children":null},{"key":"110113","value":"顺义区","children":null},{"key":"110114","value":"昌平区","children":null},{"key":"110115","value":"大兴区","children":null},{"key":"110116","value":"怀柔区","children":null},{"key":"110117","value":"平谷区","children":null}]},{"key":"110200","value":"县","children":[{"key":"110228","value":"密云县","children":null},{"key":"110229","value":"延庆县","children":null}]}]
     */

    private String key;
    private String value;
    /**
     * key : 110100
     * value : 市辖区
     * children : [{"key":"110101","value":"东城区","children":null},{"key":"110102","value":"西城区","children":null},{"key":"110105","value":"朝阳区","children":null},{"key":"110106","value":"丰台区","children":null},{"key":"110107","value":"石景山区","children":null},{"key":"110108","value":"海淀区","children":null},{"key":"110109","value":"门头沟区","children":null},{"key":"110111","value":"房山区","children":null},{"key":"110112","value":"通州区","children":null},{"key":"110113","value":"顺义区","children":null},{"key":"110114","value":"昌平区","children":null},{"key":"110115","value":"大兴区","children":null},{"key":"110116","value":"怀柔区","children":null},{"key":"110117","value":"平谷区","children":null}]
     */

    private List<Children> children;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    public static class Children {
        private String key;
        private String value;
        /**
         * key : 110101
         * value : 东城区
         * children : null
         */

        private List<ChildrenEntity> children;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<ChildrenEntity> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenEntity> children) {
            this.children = children;
        }

        public static class ChildrenEntity {
            private String key;
            private String value;
            private Object children;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public Object getChildren() {
                return children;
            }

            public void setChildren(Object children) {
                this.children = children;
            }
        }
    }
}
