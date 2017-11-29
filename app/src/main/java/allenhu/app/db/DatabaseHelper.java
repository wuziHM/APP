package allenhu.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hlib.util.MLogUtil;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import allenhu.app.bean.AreaBean;
import allenhu.app.bean.CityBean;
import allenhu.app.bean.ImageBean;
import allenhu.app.bean.ProvinceBean;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TABLE_NAME = "sqlite-test.db";
    private static final int TABLE_VERSION = 7;

    private Map<String, Dao> daos = new HashMap<String, Dao>();

    private DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, TABLE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource)

    {
        MLogUtil.e("onCreate---->TABLE_VERSION:" + TABLE_VERSION);

        try {
            TableUtils.createTable(connectionSource, ProvinceBean.class);
            TableUtils.createTable(connectionSource, CityBean.class);
            TableUtils.createTable(connectionSource, AreaBean.class);
            TableUtils.createTable(connectionSource, ImageBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {

            MLogUtil.e("onUpgrade---->oldVersion:" + oldVersion + "    newVersion" + newVersion);
            TableUtils.dropTable(connectionSource, ProvinceBean.class, true);
            TableUtils.dropTable(connectionSource, CityBean.class, true);
            TableUtils.dropTable(connectionSource, AreaBean.class, true);
            TableUtils.dropTable(connectionSource, ImageBean.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DatabaseHelper instance;

    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }

        return instance;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();

        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }

}
