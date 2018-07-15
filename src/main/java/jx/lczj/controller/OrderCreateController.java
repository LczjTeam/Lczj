package jx.lczj.controller;

import jx.lczj.service.OrderCreateService;
import jx.lczj.viewmodel.OrderCreateVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by 14260 on 2018/7/15.
 */

@Controller
@RequestMapping("orders")
public class OrderCreateController {

    @Resource
    OrderCreateService orderCreateService;

    /**
     * 创建订单
     * @param request
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public OrderCreateVo add(HttpServletRequest request){
        return orderCreateService.add(request);
    }

}
