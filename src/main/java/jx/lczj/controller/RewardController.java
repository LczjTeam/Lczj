package jx.lczj.controller;

import jx.lczj.service.RewardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("reward")
public class RewardController {

    @Autowired
    RewardServices rewardServices;

    /**
     * 添加新记录
     * @param customer
     * @param newcustomer
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(String customer,String newcustomer){
        return rewardServices.add(customer,newcustomer);
    }


    /**
     * 更新优惠券状态
     * @param customer
     * @param newcustomer
     * @param status
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(String customer,String newcustomer,int status){
        return rewardServices.update(customer,newcustomer,status);
    }
}
