package jx.lczj.controller;

import jx.lczj.service.RewardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("reward")
public class RewardController {

//    private String customer;        //vip客户名
//    private String newcustomer;     //新用户的id（新用户表中的）
//    private int prize;              //优惠券值
//    private String ins_time;        //推荐期限
//    private int status;             //状态

    @Autowired
    RewardServices rewardServices;

    @RequestMapping("/add")
    @ResponseBody
    public boolean add(String customer,String newcustomer){
        return rewardServices.add(customer,newcustomer);
    }

    @RequestMapping("/update")
    @ResponseBody
    public boolean update(String customer,String newcustomer,int status){
        return rewardServices.update(customer,newcustomer,status);
    }
}
