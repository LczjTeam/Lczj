package jx.lczj.controller;

import jx.lczj.model.T_menu;
import jx.lczj.service.AdminService;
import jx.lczj.service.MenuService;
import jx.lczj.viewmodel.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    MenuService menuService;
    @Autowired
    AdminService adminService;

    /**
     * 获取全部菜单信息
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody

    public List<MenuVo> getallCode() {
        return menuService.loadList();
    }
    /**
     *通过id查询菜单信息
     */
    @RequestMapping("/loadById")
    @ResponseBody
    public T_menu loadById(String menu){
        System.out.println(menu);
        return menuService.loadById(menu);
    }
        /**
         * 添加
         * @param role
         * @param name
         * @return
         */
   /* @RequestMapping("/add")
    @ResponseBody
    public boolean add(String role,String name){
        return menuService.add(role,name);
    }*/

        /**
         * 更新
         * @return
         */
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(String menu, String title, String parents,String url,String method,int sort_no,int visible , String css){
        return menuService.update(menu, title, parents, url, method, sort_no, visible, css);

    }


    /**
         * 删除
         * @param role
         * @return
         *//*
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(String role){
        return menuService.delete(role);
    }*/

    /**
     * 获取根节点
     * @return
     */
    @RequestMapping("/roots")
    @ResponseBody
    public List<T_menu> loadRoots(){
        return menuService.loadRoots();

    }

}
