package jx.lczj.service;

import jx.lczj.dao.EfficacyDao;
import jx.lczj.model.T_efficacy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by 14260 on 2018/6/10.
 */

@Service
public class EfficacyService {


    @Resource
    EfficacyDao efficacyDao;

    /**
     * 获取功能管理信息
     * @return
     */
    public List<T_efficacy> loadList() {
        return efficacyDao.loadList();
    }

    /**
     * 添加功能管理信息
     * @param efficacy
     * @param name
     * @return
     */
    public boolean add(int efficacy, String name) {
        return efficacyDao.add(efficacy,name);
    }


    /**
     * 更新功能管理信息
     * @param efficacy
     * @param name
     * @return
     */
    @Transactional
    public boolean update(int efficacy, String name) {
        try {
            boolean ok = efficacyDao.update(efficacy,name);
            return true;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }


    /**
     * 删除功能管理信息
     * @param efficacy
     * @return
     */
    @Transactional
    public boolean delete(int efficacy) {
        try {
            efficacyDao.delete(efficacy);
            return true;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
