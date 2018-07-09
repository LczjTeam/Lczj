package jx.lczj.controller;

import jx.lczj.anotation.Privilege;
import jx.lczj.model.T_customer;
import jx.lczj.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 14260 on 2018/6/10.
 */


@Controller
@RequestMapping("customer")
public class CustomerController {


    @Autowired
    CustomerService customerService;


    /**
     * 获取所有会员信息
     * @return
     */
    @Privilege(value = "会员查询")
    @RequestMapping("/list")
    @ResponseBody
    public List<T_customer>loadCustomer(){
        return customerService.loadCustomer();
    }


    /**
     * 通过vip编号获得会员信息
     * @param vip
     * @return
     */
    @Privilege(value = "会员查询")
    @RequestMapping("/loadByVip")
    @ResponseBody
    public T_customer loadByVip(String vip){
        return customerService.loadByVip(vip);
    }


    /**
     * 密码重置
     * @return
     */
    @Privilege(value = "会员查询")
    @RequestMapping("/resetpwd")
    @ResponseBody
    public boolean resetPwd(String vip){
        return customerService.resetPwd(vip);
    }


    /**
     * 登录
     */
    @RequestMapping("/login")
    @ResponseBody
    public T_customer login(String phone,String _name,String sex){
        return customerService.login(phone,_name,sex);
    }




}
