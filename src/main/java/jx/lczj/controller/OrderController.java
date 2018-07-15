package jx.lczj.controller;

import jx.lczj.model.T_order;
import jx.lczj.service.OrderService;
import jx.lczj.viewmodel.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by WuLinZhi on 2018-07-10.
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/list")
    @ResponseBody
    public List<OrderVo> loadOrders(){
        return orderService.loadOrders();
    }
}
