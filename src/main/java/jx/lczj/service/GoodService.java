package jx.lczj.service;

import jx.lczj.dao.GoodDao;
import jx.lczj.model.T_goods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodService {

    @Resource
    GoodDao goodDao;

    /**
     * 商品信息
     * @return
     */
    public List<T_goods> loadlist() {
        return goodDao.loadlist();
    }
}
