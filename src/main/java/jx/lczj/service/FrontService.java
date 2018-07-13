package jx.lczj.service;

import jx.lczj.dao.*;
import jx.lczj.model.T_category;
import jx.lczj.model.T_goods;
import jx.lczj.viewmodel.GoodsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FrontService {
    @Resource
    GoodDao goodDao ;

    @Resource
    CategoryDao categoryDao ;

    /**
     * 获取排行前五的眼镜
     * @return
     */
    public List<GoodsVo> listHotGoods() {
        try {
            List<T_goods> list = goodDao.listHot();
            List<GoodsVo> gvos = new ArrayList<GoodsVo>();
            for (T_goods t : list) {
                GoodsVo gvo = new GoodsVo();
                gvo.setT_goods(t);
                gvo.setT_categories(categoryDao.loadByGoods(t.getGoods()));
                gvo.setT_attachments(goodDao.loadAttachmentByGood(t.getGoods()));
                gvos.add(gvo);
            }
            return gvos;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }

    /**
     * 获取全部的眼镜
     * @return
     */
    public List<List<GoodsVo>> listGoods(List<T_category> categories) {
        try {

            List<List<GoodsVo>> gvos = new ArrayList<List<GoodsVo>>();

            for (T_category category : categories){
                List<T_goods> list = goodDao.loadGoodsByCategory(category.getCategory());
                List<GoodsVo> gvos_2 = new ArrayList<GoodsVo>();

                for (T_goods t : list) {
                    GoodsVo gvo = new GoodsVo();
                    gvo.setT_goods(t);
                    gvo.setT_categories(categoryDao.loadByGoods(t.getGoods()));
                    gvo.setT_attachments(goodDao.loadAttachmentByGood(t.getGoods()));
                    gvos_2.add(gvo);
                }
                gvos.add(gvos_2);
            }
            return gvos;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }

    /**
     * 获取全部的类别，前三个
     * @return
     */
    public List<T_category> loadCategories_top3() {
        return categoryDao.loadList1();
    }

    /**
     * 获取全部的类别
     * @return
     */
    public List<T_category> loadCategories() {
        return categoryDao.loadList();
    }

    /**
     * 通过眼镜类别获取相应类别的全部眼镜
     * @return
     */
    public List<GoodsVo> loadGoodsByCategory(String category) {

        try {
            List<T_goods> list = goodDao.loadGoodsByCategory(Integer.parseInt(category));
            List<GoodsVo> gvos = new ArrayList<GoodsVo>();
            for (T_goods t : list) {
                GoodsVo gvo = new GoodsVo();
                gvo.setT_goods(t);
                gvo.setT_categories(categoryDao.loadByGoods(t.getGoods()));
                gvo.setT_attachments(goodDao.loadAttachmentByGood(t.getGoods()));
                gvos.add(gvo);
            }
            return gvos;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }

    }

}
