package allenhu.app.db;

import java.sql.SQLException;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import allenhu.app.bean.CityBean;

public class CityDao {
    private Context context;
    private Dao<CityBean, Integer> cDaoOpe;
    private DatabaseHelper helper;

    public CityDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            cDaoOpe = helper.getDao(CityBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(CityBean bean){
        try {
            cDaoOpe.create(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
