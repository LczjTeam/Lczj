package jx.lczj.service;

import jx.lczj.dao.CategoryDao;
import jx.lczj.model.T_category;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by 14260 on 2018/6/10.
 */

@Service
public class CategoryService {


    @Resource
    CategoryDao categoryDao;

    /**
     * 获取商品类别信息
     * @return
     */
    public List<T_category> loadList() {
        return categoryDao.loadList();
    }

    /**
     * 添加商品类别信息
     * @param category
     * @param name
     * @return
     */
    public boolean add(int category, String name) {
        return categoryDao.add(category,name);
    }

    /**
     * 更新商品类别信息
     * @param category
     * @param name
     * @return
     */
    @Transactional
    public boolean update(int category, String name) {
        try {
            boolean ok = categoryDao.update(category,name);
            return true;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }


    /**
     * 删除商品类别信息
     * @param category
     * @return
     */
    @Transactional
    public boolean delete(String category) {
        try {
            categoryDao.delete(category);
            return true;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
