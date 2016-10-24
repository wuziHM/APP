package allenhu.app.db;

import java.sql.SQLException;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import allenhu.app.bean.ProvinceBean;

public class ProvinceDao {
    private Dao<ProvinceBean, Integer> pDaoOpe;
    private DatabaseHelper helper;

    @SuppressWarnings("unchecked")
    public ProvinceDao(Context context) {
        try {
            helper = DatabaseHelper.getHelper(context);
            pDaoOpe = helper.getDao(ProvinceBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(ProvinceBean bean){
        try {
            pDaoOpe.create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
