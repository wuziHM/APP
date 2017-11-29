package allenhu.app.db;

import android.content.Context;

import com.hlib.util.MLogUtil;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import allenhu.app.bean.ImageBean;

public class ILikeDao {
    private Context context;
    private Dao<ImageBean, Integer> cDaoOpe;
    private DatabaseHelper helper;

    public ILikeDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            cDaoOpe = helper.getDao(ImageBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean add(ImageBean bean) {
        try {
            int i = cDaoOpe.create(bean);
            MLogUtil.e("add-->i=" + i);
            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(ImageBean bean) {
        try {
            int i = cDaoOpe.delete(bean);
            MLogUtil.e("删除：" + i + "");
            if(i==1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isExist(String image) {
        try {
            List<ImageBean> list = cDaoOpe.queryForEq("big_img", image);
            if (list != null && list.size() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
