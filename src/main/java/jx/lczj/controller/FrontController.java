package jx.lczj.controller;


import jx.lczj.model.T_category;
import jx.lczj.viewmodel.FrontShopVo;
import jx.lczj.viewmodel.GoodsVo;
import jx.lczj.viewmodel.T_goodsHot;
import jx.lczj.service.FrontService;
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
        System.out.println("-----------------开始----------------");
        System.out.println("获取各种眼镜类型的眼镜"+goods);
        System.out.println("------------------结束---------------");
        model.addAttribute("t_shop",goods);

        return "Fronts/index";
    }

    @RequestMapping(value = "/newgoods")
    public String newgoods(String type,Model model, HttpServletRequest request){

        /**
         * 获取眼镜类型
         */
        List<T_category> categories = frontService.loadCategories();
//        System.out.println("-----------------开始----------------");
//        System.out.println("获取各种眼镜类型:"+categories);
//        System.out.println("------------------结束---------------");
        model.addAttribute("categories",categories);


        /**
         * 获取各种眼镜类型的眼镜
         */
        String category =  type.equals("-1")? (""+categories.get(0).getCategory()):type;
        List<GoodsVo> goods = frontService.loadGoodsByCategory(category);
//        System.out.println("-----------------开始----------------");
//        System.out.println("获取各种眼镜类型的眼镜loadShop2:"+goods);
//        System.out.println("------------------结束---------------");
        model.addAttribute("shops",goods);

        return "Fronts/newgoods";
    }


}
