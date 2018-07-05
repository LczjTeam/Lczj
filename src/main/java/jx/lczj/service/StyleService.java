package jx.lczj.service;

import jx.lczj.dao.StyleDao;
import jx.lczj.model.T_style;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by 14260 on 2018/6/10.
 */

@Service
public class StyleService {


    @Resource
    StyleDao styleDao;

    /**
     * 获取镜片样式设计信息
     * @return
     */
    public List<T_style> loadList() {
        return styleDao.loadList();
    }

    /**
     * 添加品牌信息
     * @param style
     * @param name
     * @return
     */
    public boolean add(int style, String name) {
        return styleDao.add(style,name);
    }

    /**
     * 更新品牌信息
     * @param style
     * @param name
     * @return
     */
    @Transactional
    public boolean update(int style, String name) {
        try {
            boolean ok = styleDao.update(style,name);
            return true;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }


    /**
     * 删除品牌信息
     * @param style
     * @return
     */
    @Transactional
    public boolean delete(int style) {
        try {
            styleDao.delete(style);
            return true;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
