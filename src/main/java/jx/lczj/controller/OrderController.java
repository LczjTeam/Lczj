package jx.lczj.controller;

import jx.lczj.model.T_order;
import jx.lczj.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by WuLinZhi on 2018-07-10.
 */
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    public List<T_order> loadOrders(){
        return orderService.loadOrders();
    }
}
