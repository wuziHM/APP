package allenhu.app.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.orhanobut.logger.Logger;

import java.sql.SQLException;
import java.util.List;

import allenhu.app.bean.debean.ILikeType;

/**
 * 收藏的图片类型的数据库操作
 * <p>
 * Allen
 */
public class TypeDao {
    private Context context;
    private Dao<ILikeType, Integer> cDaoOpe;
    private DatabaseHelper helper;

    public TypeDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            cDaoOpe = helper.getDao(ILikeType.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ILikeType getTypeByName(String name) {
        try {
            List<ILikeType> types = cDaoOpe.queryForEq("type_name", name);
            if (types == null || types.size() < 1) {
                return null;
            } else {
                return types.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据名字查询一个类型，如果这个类型没有的话就添加一个
     *
     * @param name
     * @return
     */
    public ILikeType getTypeAndAdd(String name) {
        ILikeType type = null;
        if (getTypeByName(name) == null) {
            type = new ILikeType();
            type.setName(name);
            try {
                cDaoOpe.create(type);
                type = getTypeByName(name);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            type = getTypeByName(name);
        }


        Logger.d("likeType--->" + type.toString());
        return type;
    }

}
