package jx.lczj.controller;

import jx.lczj.service.AdminService;
import jx.lczj.viewmodel.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by 14260 on 2018/6/10.
 */


@Controller
@RequestMapping("admin")
public class AdminController {


    @Autowired
    AdminService adminService;

    /**
     * 管理员登录
     * @param admin
     * @param pwd
     * @param session
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public AdminVo Login(String admin, String pwd, HttpSession session){
        //System.out.println(admin+"::"+pwd);
        return adminService.login(admin,pwd,session);
    }

    /**
     * 更新管理员信息及密码
     * @param admin
     * @param pwd
     * @param name
     * @param session
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(String admin,String name, String pwd, HttpSession session){
        return adminService.update(admin,pwd,name,session);
    }
}
