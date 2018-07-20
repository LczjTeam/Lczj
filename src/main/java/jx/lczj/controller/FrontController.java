package jx.lczj.controller;


import jx.lczj.model.T_category;
import jx.lczj.model.T_shops;
import jx.lczj.service.FrontService;
import jx.lczj.service.NewsService;
import jx.lczj.viewmodel.GoodsVo;
import jx.lczj.viewmodel.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "Fronts")
public class FrontController {

    @Autowired
    FrontService frontService;

    @Autowired
    NewsService newsService;

    @RequestMapping(value = "/index")
    public String index(Model model, HttpServletRequest request) {

        /**
         * 获取眼镜销量排行前五
         * 放到
         */
        List<GoodsVo> t_goods = frontService.listHotGoods();
        // System.out.println(t_goods);
        model.addAttribute("t_goods",t_goods);

        /**
         * 获取前三类眼镜眼镜类型
         * 太阳眼镜
         *
         */
        List<T_category> categories = frontService.loadCategories_top3();
        // System.out.println("-----------------开始----------------");
        // System.out.println("获取各种眼镜类型::::"+t_categories);
        // System.out.println("------------------结束---------------");
        model.addAttribute("t_categories",categories);


        /**
         * 获取前三种眼镜类型的所有眼镜
         */
        List<List<GoodsVo>> goods = frontService.listGoods(categories);
       /* System.out.println("-----------------开始----------------");
        System.out.println("获取各种眼镜类型的眼镜"+goods);
        System.out.println("------------------结束---------------");*/
        model.addAttribute("t_shop",goods);

        /**
         *获取前三家门店
         */
        List<T_shops> shops = frontService.listShop();
        System.out.println("-----------------开始----------------");
        System.out.println("获取各种眼镜类型的眼镜"+shops);
        System.out.println("------------------结束---------------");
        model.addAttribute("shops",shops);

        return "Fronts/index";
    }

    @RequestMapping(value = "/newgoods")
    public String newgoods(int page,String type,Model model, HttpServletRequest request){

        /**
         * 获取眼镜类型
         */
        List<T_category> categories = frontService.loadCategories();
        System.out.println("-----------------开始----------------");
        System.out.println("获取各种眼镜类型:"+categories);
        System.out.println("------------------结束---------------");
        model.addAttribute("categories",categories);


        /**
         * 获取各种眼镜类型的眼镜
         */
        //        System.out.println(start);
//        System.out.println(length);

        String category = type.equals("1") ? (""+categories.get(0).getCategory()):type;

        int length = 8;
        int start = length*(page-1);
        List<GoodsVo> goodsList = frontService.loadGoodsByCategoryList(category);

        int pagerNum = pagerNum(goodsList.size(),length);
        model.addAttribute("pagerNum",pagerNum);
        model.addAttribute("page",page);
        model.addAttribute("type",type);


        List<GoodsVo> goods = frontService.loadGoodsByCategory_pager(start,length,category);
        int goods_size = goods.size();
        System.out.println(goods_size);
        System.out.println("-----------------开始----------------");
        System.out.println("获取各种眼镜类型的眼镜loadShop2:"+goods);
        System.out.println("------------------结束---------------");
        model.addAttribute("shops",goods);

        return "Fronts/newgoods";
    }

    public int pagerNum(int goodListNum,int length){
        if (goodListNum%length==0){
            return goodListNum/length;
        }else{
            return goodListNum/length+1;
        }
    }

    /**
     * 知识百科
     */
    @RequestMapping("/knowledge")
    public String knowledge(Model model,HttpServletRequest request){

        String c=request.getParameter("c");
       /* System.out.println("----------------");
        System.out.println("c:"+c);
        System.out.println("----------------");*/
        List<NewsVo> newsVos = newsService.listitem(c);
       /* System.out.println("知识百科："+newsVos);*/
        model.addAttribute("news",newsVos);
        return "Fronts/knowledge";
    }

}
