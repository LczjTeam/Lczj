package jx.lczj.controller;

import jx.lczj.anotation.Privilege;
import jx.lczj.model.T_role;
import jx.lczj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 14260 on 2018/6/11.
 */

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    RoleService roleService;

    /**
     * 角色信息
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<T_role> list(){
       return roleService.loadList();
    }

    /**
     * 添加
     * @param role
     * @param name
     * @return
     */
    @Privilege(value = "角色管理")
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(String role,String name){
        return roleService.add(role,name);
    }

    /**
     * 更新
     * @param role
     * @param name
     * @return
     */
    @Privilege(value = "角色管理")
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(String role,String name){
        return roleService.update(role,name);
    }


    /**
     * 删除
     * @param role
     * @return
     */
    @Privilege(value = "角色管理")
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(String role){
        return roleService.delete(role);
    }


    /**
     * 获取角色信息
     * @param admin
     * @return
     */
    @RequestMapping("/loadByAdminId")
    @ResponseBody
    public List<T_role>  loadByAdminId(String admin){
        return roleService.loadByAdminId(admin);
    }

    /**
     * 角色授权
     * @param role
     * @param menus
     * @return
     */
    @Privilege(value = "角色授权")
    @RequestMapping("/role_menu_update")
    @ResponseBody
    public boolean roleMenuUpdate(String role,String menus){
        return roleService.roleMenuUpdate(role,menus);
    }


}
