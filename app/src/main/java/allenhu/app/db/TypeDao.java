package allenhu.app.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.orhanobut.logger.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
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
     * 分页查询
     * <p>
     * offset Limit offset跳过指定的行数 limit限制获取指定行数 使用示范：
     * mDao.queryBuilder().offset(2).limit(2).query();
     * 可以用来分页 对应SQL：SELECT * FROM `t_person` LIMIT 2 OFFSET 2
     *
     * @param page     页码
     * @param pageSize 每页的条数
     * @return 查询出来的集合
     */
    public List<ILikeType> getTypeByPage(long page, long pageSize) {

        //因为rxjava的onnext不能是null所以传一个没有值的集合过去
        List<ILikeType> likeTypes = new ArrayList<>();
        if (page <= 0) {
            return likeTypes;
        }
        if (page > getMaxPage(pageSize)) {
            return likeTypes;
        }

        try {
            likeTypes.addAll(cDaoOpe.queryBuilder().offset((page - 1) * pageSize).limit(pageSize).query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Logger.d("分页查询结果 size:" + likeTypes.size());
        return likeTypes;
    }

    /**
     * 获取最大页码
     *
     * @return
     */
    public long getMaxPage(long pageSize) {
        long maxPage = 0;
        try {
            long size = cDaoOpe.countOf();
            Logger.d("所有的数据的条数：" + size);
            maxPage = size % pageSize == 0 ? size / pageSize : (size / pageSize) + 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Logger.d("maxPage:" + maxPage);
        return maxPage;
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


        return type;
    }

}
