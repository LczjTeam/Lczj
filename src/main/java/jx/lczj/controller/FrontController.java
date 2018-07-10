package jx.lczj.controller;


import jx.lczj.model.T_category;
import jx.lczj.viewmodel.FrontShopVo;
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
        List<T_goodsHot> t_goods = frontService.listHot();
       // System.out.println(t_goods);
        model.addAttribute("t_goods",t_goods);
        /**
         * 获取各种眼镜类型
         * 太阳眼镜
         *
         */

        List<T_category>  t_categories  = frontService.loadcategor();
       // System.out.println("-----------------开始----------------");
       // System.out.println("获取各种眼镜类型::::"+t_categories);
       // System.out.println("------------------结束---------------");
        model.addAttribute("t_categories",t_categories);
        /**
         * 获取各种眼镜类型的眼镜
         */
        List<FrontShopVo> t_shop = frontService.loadShop();
       /* System.out.println("-----------------开始----------------");
        System.out.println("获取各种眼镜类型的眼镜"+t_shop);
        System.out.println("------------------结束---------------");*/
        model.addAttribute("t_shop",t_shop);


        return "Fronts/index";
    }


}
