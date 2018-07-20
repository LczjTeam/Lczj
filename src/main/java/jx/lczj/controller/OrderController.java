package jx.lczj.controller;

import jx.lczj.model.T_order;
import jx.lczj.service.OrderService;
import jx.lczj.viewmodel.OrderCreateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;

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
     * 获取全部订单
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<T_order> list(){return orderService.list();};

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
    /**
     * 通过时间查找订单号
     */
    @RequestMapping("/seachByTime")
    @ResponseBody
    public List<T_order> seachByTime(String start ,String end) throws ParseException {

        System.out.println(start+end);
        return orderService.seachByTime(start,end);
    };
    /**
     * 通过订单号查找
     */
    @RequestMapping("/seachById")
    @ResponseBody
    public T_order loadById(String order){
        System.out.println(orderService.loadById(order));
        return orderService.loadById(order);}

    /**
     * 更新总价
     */
    @RequestMapping("/update3")
    @ResponseBody
    public boolean update3(String order,String price){
        return orderService.update3(order,price);}
}
