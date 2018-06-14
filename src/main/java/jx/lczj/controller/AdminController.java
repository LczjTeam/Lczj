package jx.lczj.controller;

import jx.lczj.model.T_admin;
import jx.lczj.service.AdminService;
import jx.lczj.viewmodel.AdminVo;
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
@RequestMapping("admin")
public class AdminController {


    @Autowired
    AdminService adminService;

    /**
    * 用户信息
    */
    @RequestMapping("/list")
    @ResponseBody
    public List<T_admin> list(){
        return adminService.loadList();
    }


    /**
     * 管理员登录
     * @param admin
     * @param pwd
     * @param session
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public AdminVo Login(String admin, String pwd, String name, HttpSession session){
        //System.out.println(admin+"::"+pwd);
        return adminService.login(admin,pwd,session);
    }


    /**
     * 添加
     * @param admin
     * @param name
     * @param isvalid
     * @return
     */

    @RequestMapping("/add")
    @ResponseBody
    public boolean add(String admin,String name,String isvalid){
        return adminService.add(admin,name,isvalid);
    }


    /**
     * 更新管理员信息及密码
     * @param admin
     * @param name
     * @param session
     * @return
     */
    @RequestMapping("/update1")
    @ResponseBody
    public boolean update1(String admin,String name,String isvalid, HttpSession session){
        System.out.println("admin:"+admin+",name:"+name+",isvalid:"+isvalid);
        return adminService.update1(admin,name,isvalid,session);
    }

    /**
     * 删除
     * @param admin
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(String admin){
        System.out.println("admin:"+admin);
        return adminService.delete(admin);
    }

    /**
     *
     * @param admin
     * @return
     */
    @RequestMapping("/resetpwd")
    @ResponseBody
    public boolean resetPwd(String admin){
        return adminService.resetPwd(admin);
    }


}
