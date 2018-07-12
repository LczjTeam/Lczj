package jx.lczj.controller;

import jx.lczj.anotation.Privilege;
import jx.lczj.model.T_customer;
import jx.lczj.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * @param phone
     * @return
     */
    @Privilege(value = "会员查询")
    @RequestMapping("/loadByPhone")
    @ResponseBody
    public T_customer loadByVip(String phone){
        return customerService.loadByPhone(phone);
    }


    /**
     * 密码重置
     * @return
     */
    @Privilege(value = "会员查询")
    @RequestMapping("/resetpwd")
    @ResponseBody
    public boolean resetPwd(String phone){
        return customerService.resetPwd(phone);
    }


    /**
     * 登录
     */
    @RequestMapping("/login")
    @ResponseBody
    public T_customer login(String phone,String _name,String sex){
        return customerService.login(phone,_name,sex);
    }


    /**
     * 编辑更新
     * @param name
     * @param phone
     * @param sex
     * @param pwd
     * @param birthday
     * @param face
     * @return
     */
    @Privilege(value = "会员查询")
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(String name, String phone,String sex,String pwd,String birthday,String face){
        System.out.println(name+phone+sex+pwd+birthday+face);
        return customerService.update(name,phone,sex,pwd,birthday,face);
    }

}
