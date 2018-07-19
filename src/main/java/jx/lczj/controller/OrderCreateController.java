package jx.lczj.controller;

import jx.lczj.model.T_order;
import jx.lczj.service.OrderCreateService;
import jx.lczj.viewmodel.OrderCreateVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 14260 on 2018/7/15.
 */

@Controller
@RequestMapping("orders")
public class OrderCreateController {

    @Resource
    OrderCreateService orderCreateService;


    @RequestMapping("/searchByTime")
    @ResponseBody
    public List<T_order> searchByTime(String customer,String time){
        return orderCreateService.searchByTime(customer,time);
    }


    /**
     * 通过编号获取订单信息
     * @param request
     * @return
     */
    @RequestMapping("/loadById")
    @ResponseBody
    public OrderCreateVo loadById(HttpServletRequest request){
        return orderCreateService.loadById(request);
    }


    /**
     * 通过状态获取订单信息
     * @param request
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<OrderCreateVo> loadList(HttpServletRequest request){
        return orderCreateService.loadList(request);
    }


    /**
     * 删除
     * @param request
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(HttpServletRequest request){
        return orderCreateService.delete(request);
    }

}
