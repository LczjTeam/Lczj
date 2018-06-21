package jx.lczj.service;


import jx.lczj.dao.OccasionDao;
import jx.lczj.model.T_occasion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OccasionService {
    @Resource
    OccasionDao occasionDao;

    /**
     * 获取场景信息
     * @return
     */
    public List<T_occasion> loadList() {
        return occasionDao.loadList();
    }

    /**
     * 删除场景信息
     * @param occasion
     * @return
     */
    @Transactional
    public boolean delete(String occasion) {
        try {

            occasionDao.delete(occasion);
            return  true;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 更新场景信息
     * @param occasion
     * @param name
     * @return
     */
    public boolean update(String occasion, String name) {
        return occasionDao.update(occasion,name);
    }

    /**
     * 添加角色信息
     * @param occasion
     * @param name
     * @return
     */
    public boolean add(String occasion, String name) {
        return occasionDao.insert(occasion,name);
    }


}
