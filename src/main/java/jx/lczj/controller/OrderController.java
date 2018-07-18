package jx.lczj.controller;

import jx.lczj.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     * 更新商品星级，服务星级，快递星级，评语
     * @param order
     * @param goodsstar
     * @param servicestar
     * @param expressstar
     * @param comments
     * @return
     */
    @RequestMapping("/update1")
    @ResponseBody
    public boolean update1(String order,int goodsstar,int servicestar,int expressstar,String comments){
        return orderService.update1(order,goodsstar,servicestar,expressstar,comments);
    }

    /**
     * 更新快递名，运单号
     * @param order
     * @param express
     * @param expressid
     * @return
     */
    @RequestMapping("/update2")
    @ResponseBody
    public boolean update2(String order,String express,String expressid){
        return orderService.update2(order,express,expressid);
    }
}
