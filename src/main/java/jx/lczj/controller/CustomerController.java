package jx.lczj.controller;

import jx.lczj.anotation.Privilege;
import jx.lczj.model.T_customer;
import jx.lczj.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    public T_customer login(String phone,String _name,String sex,String headurl,HttpServletRequest request){
        return customerService.login(phone,_name,sex,headurl,request);
    }


    /**
     * 编辑更新
     * @param name
     * @param phone
     * @param sex
     * @param birthday
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(String name, String phone,String sex,String birthday){
        System.out.println(name+phone+sex+birthday);
        return customerService.update(name,phone,sex,birthday);
    }


    /**
     * 更新密码
     * @param phone
     * @param pwd
     * @return
     */
    @RequestMapping("/updatePwd")
    @ResponseBody
    public boolean updatePwd(String phone,String pwd){
        System.out.println(phone+pwd);
        return customerService.updatePwd(phone,pwd);
    }

    /**
     * 更新头像
     * @param phone
     * @return
     */
    @RequestMapping("/updateFace")
    @ResponseBody
    public T_customer updateFace(MultipartFile file, String phone, HttpServletRequest request){
        System.out.println(phone);
        return customerService.updateFace(file,phone,request);
    }
}

