package jx.lczj.service;

import jx.lczj.dao.MaskDao;
import jx.lczj.model.T_mask;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MaskService {

    @Resource
    MaskDao maskDao;

    /**
     * 获取膜层信息
     * @return
     */
    public List<T_mask> loadList() {
        return maskDao.loadList();
    }

    /**
     * 添加膜层信息
     * @param mask
     * @param name
     * @return
     */
    public boolean add(int mask, String name) {
        return maskDao.add(mask,name);
    }

    /**
     * 删除膜层信息
     * @param mask
     * @return
     */
    public boolean delete(String mask) {
        return maskDao.delete(mask);
    }

    /**
     * 更新
     * @param mask
     * @param name
     * @return
     */
    public boolean update(int mask, String name) {
        return maskDao.update(mask,name);
    }
}
