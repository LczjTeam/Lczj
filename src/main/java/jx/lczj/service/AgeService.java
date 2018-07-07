package jx.lczj.service;

import jx.lczj.dao.AgeDao;
import jx.lczj.model.T_agesection;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 14260 on 2018/6/10.
 */

@Service
public class AgeService {

    @Resource
    AgeDao ageDao;

    /**
     * 获取年龄段信息
     * @return
     */
    public List<T_agesection> loadlist() {
        return ageDao.loadlist();
    }

    /**
     * 添加年龄段
     * @param agesection
     * @param name
     * @param minage
     * @param maxage
     * @return
     */
    public boolean add(int agesection, String name, int minage, int maxage) {
        return ageDao.add(agesection,name,minage,maxage);
    }

    /**
     * 编辑年龄段
     * @param agesection
     * @param name
     * @param minage
     * @param maxage
     * @return
     */
    public boolean update(int agesection,String name, int minage, int maxage) {
        return ageDao.update(agesection,name,minage,maxage);
    }

    /**
     * 删除年龄段
     * @param agesection
     * @return
     */
    public boolean delete(int agesection) {
        return ageDao.delete(agesection);
    }
}
