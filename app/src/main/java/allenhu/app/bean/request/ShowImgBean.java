package allenhu.app.bean.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import allenhu.app.bean.ImageBean;

/**
 * Author：HM $ on 17/11/22 14:05
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public class ShowImgBean implements Serializable {


    /**
     * 数据转换
     *
     * @param showImgBean
     * @return
     */
    public static ArrayList<ImageBean> getListEn(ShowImgBean showImgBean) {
        ArrayList<ImageBean> arrayList = new ArrayList<>();
        ShowapiResBodyEntity.PagebeanEntity pageBean = showImgBean.getShowapi_res_body().getPagebean();
        for (ShowapiResBodyEntity.PagebeanEntity.ContentlistEntity entity : pageBean.getContentlist()) {
            for (ImageBean e : entity.getList()) {

                e.setTitle(entity.getTitle());

                String date = "";
                if (entity.getCt() != null && entity.getCt().length() > 10) {
                    date = entity.getCt().substring(0, 10);
                }
                e.setDate(date);
                e.setTypeName(entity.getTypeName());
                arrayList.add(e);
            }
        }
        return arrayList;
    }


    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"pagebean":{"allNum":1041,"allPages":53,"contentlist":[{"ct":"2015-10-17 16:41:01.468","itemId":"37070983","list":[{"big":"http: //image.tianjimedia.com/uploadImages/2014/126/14/99DPC7722YXH.jpg","middle":"http: //image.tianjimedia.com/uploadImages/2014/126/14/99DPC7722YXH_680x500.jpg","small":"http: //image.tianjimedia.com/uploadImages/2014/126/14/99DPC7722YXH_113.jpg"},{"big":"http: //image.tianjimedia.com/uploadImages/2014/126/15/SA6XO66KW687.jpg","middle":"http: //image.tianjimedia.com/uploadImages/2014/126/15/SA6XO66KW687_680x500.jpg","small":"http: //image.tianjimedia.com/uploadImages/2014/126/15/SA6XO66KW687_113.jpg"},{"big":"http: //image.tianjimedia.com/uploadImages/2014/126/16/ZDW18D38HD0F.jpg","middle":"http: //image.tianjimedia.com/uploadImages/2014/126/16/ZDW18D38HD0F_680x500.jpg","small":"http: //image.tianjimedia.com/uploadImages/2014/126/16/ZDW18D38HD0F_113.jpg"},{"big":"http: //image.tianjimedia.com/uploadImages/2014/126/17/32CUJ3W6X3B0.jpg","middle":"http: //image.tianjimedia.com/uploadImages/2014/126/17/32CUJ3W6X3B0_680x500.jpg","small":"http: //image.tianjimedia.com/uploadImages/2014/126/17/32CUJ3W6X3B0_113.jpg"},{"big":"http: //image.tianjimedia.com/uploadImages/2014/126/22/S6GV0A927Y51.jpg","middle":"http: //image.tianjimedia.com/uploadImages/2014/126/22/S6GV0A927Y51_680x500.jpg","small":"http: //image.tianjimedia.com/uploadImages/2014/126/22/S6GV0A927Y51_113.jpg"}],"title":"summer: 就是那么一种情绪而已","type":4002,"typeName":"气质"}],"currentPage":1,"maxResult":20},"ret_code":0}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    /**
     * pagebean : {"allNum":1041,"allPages":53,"contentlist":[{"ct":"2015-10-17 16:41:01.468","itemId":"37070983","list":[{"big":"http: //image.tianjimedia.com/uploadImages/2014/126/14/99DPC7722YXH.jpg","middle":"http: //image.tianjimedia.com/uploadImages/2014/126/14/99DPC7722YXH_680x500.jpg","small":"http: //image.tianjimedia.com/uploadImages/2014/126/14/99DPC7722YXH_113.jpg"},{"big":"http: //image.tianjimedia.com/uploadImages/2014/126/15/SA6XO66KW687.jpg","middle":"http: //image.tianjimedia.com/uploadImages/2014/126/15/SA6XO66KW687_680x500.jpg","small":"http: //image.tianjimedia.com/uploadImages/2014/126/15/SA6XO66KW687_113.jpg"},{"big":"http: //image.tianjimedia.com/uploadImages/2014/126/16/ZDW18D38HD0F.jpg","middle":"http: //image.tianjimedia.com/uploadImages/2014/126/16/ZDW18D38HD0F_680x500.jpg","small":"http: //image.tianjimedia.com/uploadImages/2014/126/16/ZDW18D38HD0F_113.jpg"},{"big":"http: //image.tianjimedia.com/uploadImages/2014/126/17/32CUJ3W6X3B0.jpg","middle":"http: //image.tianjimedia.com/uploadImages/2014/126/17/32CUJ3W6X3B0_680x500.jpg","small":"http: //image.tianjimedia.com/uploadImages/2014/126/17/32CUJ3W6X3B0_113.jpg"},{"big":"http: //image.tianjimedia.com/uploadImages/2014/126/22/S6GV0A927Y51.jpg","middle":"http: //image.tianjimedia.com/uploadImages/2014/126/22/S6GV0A927Y51_680x500.jpg","small":"http: //image.tianjimedia.com/uploadImages/2014/126/22/S6GV0A927Y51_113.jpg"}],"title":"summer: 就是那么一种情绪而已","type":4002,"typeName":"气质"}],"currentPage":1,"maxResult":20}
     * ret_code : 0
     */

    private ShowapiResBodyEntity showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyEntity getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyEntity showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyEntity implements Serializable {
        /**
         * allNum : 1041
         * allPages : 53
         * contentlist : [{"ct":"2015-10-17 16:41:01.468","itemId":"37070983","list":[{"big":"http: //image.tianjimedia.com/uploadImages/2014/126/14/99DPC7722YXH.jpg","middle":"http: //image.tianjimedia.com/uploadImages/2014/126/14/99DPC7722YXH_680x500.jpg","small":"http: //image.tianjimedia.com/uploadImages/2014/126/14/99DPC7722YXH_113.jpg"},{"big":"http: //image.tianjimedia.com/uploadImages/2014/126/15/SA6XO66KW687.jpg","middle":"http: //image.tianjimedia.com/uploadImages/2014/126/15/SA6XO66KW687_680x500.jpg","small":"http: //image.tianjimedia.com/uploadImages/2014/126/15/SA6XO66KW687_113.jpg"},{"big":"http: //image.tianjimedia.com/uploadImages/2014/126/16/ZDW18D38HD0F.jpg","middle":"http: //image.tianjimedia.com/uploadImages/2014/126/16/ZDW18D38HD0F_680x500.jpg","small":"http: //image.tianjimedia.com/uploadImages/2014/126/16/ZDW18D38HD0F_113.jpg"},{"big":"http: //image.tianjimedia.com/uploadImages/2014/126/17/32CUJ3W6X3B0.jpg","middle":"http: //image.tianjimedia.com/uploadImages/2014/126/17/32CUJ3W6X3B0_680x500.jpg","small":"http: //image.tianjimedia.com/uploadImages/2014/126/17/32CUJ3W6X3B0_113.jpg"},{"big":"http: //image.tianjimedia.com/uploadImages/2014/126/22/S6GV0A927Y51.jpg","middle":"http: //image.tianjimedia.com/uploadImages/2014/126/22/S6GV0A927Y51_680x500.jpg","small":"http: //image.tianjimedia.com/uploadImages/2014/126/22/S6GV0A927Y51_113.jpg"}],"title":"summer: 就是那么一种情绪而已","type":4002,"typeName":"气质"}]
         * currentPage : 1
         * maxResult : 20
         */

        private PagebeanEntity pagebean;
        private int ret_code;

        public PagebeanEntity getPagebean() {
            return pagebean;
        }

        public void setPagebean(PagebeanEntity pagebean) {
            this.pagebean = pagebean;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public static class PagebeanEntity implements Serializable {
            private int allNum;
            private int allPages;
            private int currentPage;
            private int maxResult;

            /**
             * ct : 2015-10-17 16:41:01.468
             * itemId : 37070983
             * list : [{"big":"http: //image.tianjimedia.com/uploadImages/2014/126/14/99DPC7722YXH.jpg","middle":"http: //image.tianjimedia.com/uploadImages/2014/126/14/99DPC7722YXH_680x500.jpg","small":"http: //image.tianjimedia.com/uploadImages/2014/126/14/99DPC7722YXH_113.jpg"},{"big":"http: //image.tianjimedia.com/uploadImages/2014/126/15/SA6XO66KW687.jpg","middle":"http: //image.tianjimedia.com/uploadImages/2014/126/15/SA6XO66KW687_680x500.jpg","small":"http: //image.tianjimedia.com/uploadImages/2014/126/15/SA6XO66KW687_113.jpg"},{"big":"http: //image.tianjimedia.com/uploadImages/2014/126/16/ZDW18D38HD0F.jpg","middle":"http: //image.tianjimedia.com/uploadImages/2014/126/16/ZDW18D38HD0F_680x500.jpg","small":"http: //image.tianjimedia.com/uploadImages/2014/126/16/ZDW18D38HD0F_113.jpg"},{"big":"http: //image.tianjimedia.com/uploadImages/2014/126/17/32CUJ3W6X3B0.jpg","middle":"http: //image.tianjimedia.com/uploadImages/2014/126/17/32CUJ3W6X3B0_680x500.jpg","small":"http: //image.tianjimedia.com/uploadImages/2014/126/17/32CUJ3W6X3B0_113.jpg"},{"big":"http: //image.tianjimedia.com/uploadImages/2014/126/22/S6GV0A927Y51.jpg","middle":"http: //image.tianjimedia.com/uploadImages/2014/126/22/S6GV0A927Y51_680x500.jpg","small":"http: //image.tianjimedia.com/uploadImages/2014/126/22/S6GV0A927Y51_113.jpg"}]
             * title : summer: 就是那么一种情绪而已
             * type : 4002
             * typeName : 气质
             */

            private List<ContentlistEntity> contentlist;

            public int getAllNum() {
                return allNum;
            }

            public void setAllNum(int allNum) {
                this.allNum = allNum;
            }

            public int getAllPages() {
                return allPages;
            }

            public void setAllPages(int allPages) {
                this.allPages = allPages;
            }

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public int getMaxResult() {
                return maxResult;
            }

            public void setMaxResult(int maxResult) {
                this.maxResult = maxResult;
            }

            public List<ContentlistEntity> getContentlist() {
                return contentlist;
            }

            public void setContentlist(List<ContentlistEntity> contentlist) {
                this.contentlist = contentlist;
            }

            public static class ContentlistEntity implements Serializable {
                private String ct;
                private String itemId;
                private String title;
                private int type;
                private String typeName;
                /**
                 * big : http: //image.tianjimedia.com/uploadImages/2014/126/14/99DPC7722YXH.jpg
                 * middle : http: //image.tianjimedia.com/uploadImages/2014/126/14/99DPC7722YXH_680x500.jpg
                 * small : http: //image.tianjimedia.com/uploadImages/2014/126/14/99DPC7722YXH_113.jpg
                 */

                private List<ImageBean> list;

                public String getCt() {
                    return ct;
                }

                public void setCt(String ct) {
                    this.ct = ct;
                }

                public String getItemId() {
                    return itemId;
                }

                public void setItemId(String itemId) {
                    this.itemId = itemId;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getTypeName() {
                    return typeName;
                }

                public void setTypeName(String typeName) {
                    this.typeName = typeName;
                }

                public List<ImageBean> getList() {
                    return list;
                }

                public void setList(List<ImageBean> list) {
                    this.list = list;
                }

//                public static class ListEntity implements Serializable {
//                    private String big;
//                    private String middle;
//                    private String small;
//                    private String date;
//                    private String title;
//
//                    public String getTitle() {
//                        return title;
//                    }
//
//                    public void setTitle(String title) {
//                        this.title = title;
//                    }
//
//                    public String getDate() {
//                        return date;
//                    }
//
//                    public void setDate(String date) {
//                        this.date = date;
//                    }
//
//                    public String getBig() {
//                        return big;
//                    }
//
//                    public void setBig(String big) {
//                        this.big = big;
//                    }
//
//                    public String getMiddle() {
//                        return middle;
//                    }
//
//                    public void setMiddle(String middle) {
//                        this.middle = middle;
//                    }
//
//                    public String getSmall() {
//                        return small;
//                    }
//
//                    public void setSmall(String small) {
//                        this.small = small;
//                    }
//                }
            }
        }


    }
}
