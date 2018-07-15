package jx.lczj.service;

import jx.lczj.dao.BrandDao;
import jx.lczj.model.T_brand;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by 14260 on 2018/6/10.
 */

@Service
public class BrandService {


    @Resource
    BrandDao brandDao;

    /**
     * 获取品牌信息
     * @return
     */
    public List<T_brand> loadList() {
        return brandDao.loadList();
    }

    /**
     * 添加品牌信息
     * @param brand
     * @param name
     * @param company
     * @return
     */
    public boolean add(int brand, String name, String company,int type,int recommend) {
        return brandDao.add(brand,name,company,type,recommend);
    }

    /**
     * 更新品牌信息
     * @param brand
     * @param name
     * @param company
     * @return
     */
    @Transactional
    public boolean update(int brand, String name, String company,int type,int recommend) {
        try {
            boolean ok = brandDao.update(brand,name,company,type,recommend);
            return true;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }


    /**
     * 删除品牌信息
     * @param brand
     * @return
     */
    @Transactional
    public boolean delete(String brand) {
        try {
            brandDao.delete(brand);
            return true;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 获取镜片信息
     * @return
     */
    public List<T_brand> loadList1() {
        return brandDao.loadList1();
    }

    /**
     * 获取镜框信息
     * @return
     */
    public List<T_brand> loadList2() {
        return brandDao.loadList2();
    }
}
