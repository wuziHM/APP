package allenhu.app.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by AllenHu on 2016/3/1.
 */
public class ContactBean implements Serializable{
    private String name;
    private String phone;
    private Bitmap headerImg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Bitmap getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(Bitmap headerImg) {
        this.headerImg = headerImg;
    }

}
