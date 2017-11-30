package allenhu.app.db;

import android.content.Context;

import com.hlib.util.MLogUtil;
import com.j256.ormlite.dao.Dao;
import com.orhanobut.logger.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
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
            MLogUtil.i("add-->i=" + i);
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
            MLogUtil.i("删除：" + i + "");
            if (i == 1) {
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


    /**
     * 分页查询
     * <p>
     * offset Limit offset跳过指定的行数 limit限制获取指定行数 使用示范：
     * mDao.queryBuilder().offset(2).limit(2).query();
     * 可以用来分页 对应SQL：SELECT * FROM `t_person` LIMIT 2 OFFSET 2
     *
     * @param id       图片类型id
     * @param page     页码
     * @param pageSize 每页的条数
     * @return 查询出来的集合
     */
    public List<ImageBean> getTypeByPage(int id, long page, long pageSize) {
        List<ImageBean> likeTypes = new ArrayList<>();
        if (page <= 0) {
            return likeTypes;
        }
        if (page > getMaxPage(id, pageSize)) {
            return likeTypes;
        }
        try {
            likeTypes.addAll(cDaoOpe.queryBuilder().offset((page - 1) * pageSize)
                    .limit(pageSize)
                    .where().eq("type_id", id)
                    .query());
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
    public long getMaxPage(int id, long pageSize) {
        long maxPage = 0;
        try {
            int size = 0;
            List<ImageBean> images = cDaoOpe.queryBuilder().where().eq("type_id", id).query();
            if (images != null) {
                size = images.size();
            }
            Logger.d("所有的数据的条数：" + size);
            maxPage = size % pageSize == 0 ? size / pageSize : (size / pageSize) + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Logger.d("maxPage:" + maxPage);
        return maxPage;
    }


//    public boolean getImgById(int id, long page, long pageSize) {
//        try {
//            List<ImageBean> list = cDaoOpe.queryForEq("big_img", image);
//            if (list != null && list.size() > 0) {
//                return true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}
