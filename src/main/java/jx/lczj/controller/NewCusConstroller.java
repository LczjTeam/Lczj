package jx.lczj.controller;

import jx.lczj.service.NewCusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("newcustomer")
public class NewCusConstroller {

    @Autowired
    NewCusServices newCusServices;

    //添加新用户
    @RequestMapping("add")
    @ResponseBody
    public boolean add(String vip,String phone){
        return newCusServices.add(vip,phone);
    }


    /**
     * 更新新用户状态
     * @param phone
     * @param status
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public boolean update(String phone,int status){
        return newCusServices.update(phone,status);
    }

}
