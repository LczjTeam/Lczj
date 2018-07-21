package jx.lczj.controller;

import com.alibaba.fastjson.JSONObject;
import jx.lczj.service.OrderService;
import jx.lczj.utils.ExpressUtil;
import jx.lczj.viewmodel.ExpressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 14260 on 2018/7/19.
 */

@Controller
@RequestMapping("express")
public class ExpressController {


    @Autowired
    OrderService orderService;

    /**
    * 快点查询
    * @param expCode  快递公司编号
    * @param expNo    快递单号
    * @return
    */
    @RequestMapping("/loadByInfo")
    @ResponseBody
    public String loadByInfo(String expCode,String expNo){
        return   ExpressUtil.getOrderTracesByJson(expCode,expNo);
    }

    /**
     * 快点查询
     * @param order  订单编号
     * @return
     */
    @RequestMapping("/loadByOrder")
    @ResponseBody
    public ExpressVo loadByOrder(String order){
        return   orderService.loadByOrder(order);
    }

}
