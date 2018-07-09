package jx.lczj.service;

import jx.lczj.dao.*;
import jx.lczj.model.T_attachment;
import jx.lczj.model.T_category;
import jx.lczj.model.T_goods;
import jx.lczj.viewmodel.T_goodsHot;
import jx.lczj.viewmodel.FrontShopVo;
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

    public List<T_goodsHot> listHot() {
        try {
            List<T_goods> list = goodDao.listHot();
            //保存眼镜图片，获取第一张图片
            List<T_attachment> t_attachments;
            //保存类型，获取当前第一个类型
            List<T_category> t_categorylist;
            List<T_goodsHot> goodsHots= new ArrayList<T_goodsHot>();
            for (T_goods t : list) {
                T_goodsHot gh= new T_goodsHot();
                gh.setName(t.getName());
                gh.setPrice(t.getPrice());
                t_categorylist = categoryDao.loadByGoods(t.getGoods());
                gh.setT_categories(t_categorylist.get(0));
                t_attachments = goodDao.loadAttachmentByGood(t.getGoods());
                gh.setT_attachments(t_attachments.get(0));
                goodsHots.add(gh);
            }
            return goodsHots;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }

    /**
     * 获取眼镜类型前三条记录
     * @return
     */
    public List<T_category> loadcategor() {

        return categoryDao.loadList1();
    }

    /**
     * 获取各类眼镜的商品
     * @return
     */


    public List<FrontShopVo> loadShop() {

        List<T_category> t_categories = categoryDao.loadList1();
        System.out.println(t_categories);
        FrontShopVo frontShopVos  ;
        List<FrontShopVo> frontShopVoList = new ArrayList<FrontShopVo>();
        for (int i = 0;i<t_categories.size();i++){
            frontShopVos = new FrontShopVo();
            List<T_goods> list = goodDao.listShop(t_categories.get(i).getCategory());
            List<T_goodsHot>t_goodsHots = new ArrayList<T_goodsHot>(); ;
            //保存眼镜图片，获取第一张图片
            List<T_attachment> t_attachments;
            for (T_goods t : list) {
                T_goodsHot gh= new T_goodsHot();
                gh.setName(t.getName());
                gh.setPrice(t.getPrice());
                //获取多种场景
                gh.setT_categoryList(categoryDao.loadByGoods(t.getGoods()));
                //获取第一张图片
                t_attachments = goodDao.loadAttachmentByGood(t.getGoods());
                gh.setT_attachments(t_attachments.get(0));
                t_goodsHots.add(gh);
            }
            frontShopVos.setT_goodsHots(t_goodsHots);
            frontShopVoList.add(frontShopVos);
        }
        return frontShopVoList;
    }
}
