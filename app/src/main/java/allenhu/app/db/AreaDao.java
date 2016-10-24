package allenhu.app.db;

import android.content.Context;
import android.view.ViewGroup;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import allenhu.app.bean.AreaBean;
import allenhu.app.util.LogUtil;

public class AreaDao {


    private Context context;
    private Dao<AreaBean, Integer> aDaoOpe;
    private DatabaseHelper helper;

    public AreaDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            aDaoOpe = helper.getDao(AreaBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(AreaBean bean) {
        try {
            aDaoOpe.create(bean);
            LogUtil.e("添加成功" + bean.getKey() + "    " + bean.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
