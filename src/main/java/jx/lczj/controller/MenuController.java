package jx.lczj.controller;

import jx.lczj.anotation.Privilege;
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
     * 通过角色查询菜单信息
     * @param role
     * @return
     */
    @RequestMapping("/loadByRole")
    @ResponseBody
    public List<T_menu> loadByRole(String role){
        System.out.println(role);
        return menuService.loadByRole(role);
    }

    /**
     * 通过查询子菜单信息
     * @return
     */
    @RequestMapping("/loadChildren")
    @ResponseBody
    public List<T_menu> loadChildren(){
        System.out.println();
        return menuService.loadChildren();
    }


    /**
     * 更新
     * @return
     */
    @Privilege(value = "菜单管理")
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(String menu, String title, String parents,String url,String method,int sort_no,int visible , String css){
        return menuService.update(menu, title, parents, url, method, sort_no, visible, css);

    }
    /**
     * 添加
     * @return
     */
    @Privilege(value = "菜单管理")
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(String menu, String title, String parents,String url,String method,int sort_no,int visible , String css){
        System.out.println(" menu:"+ menu+" title:"+ title+" parents:"+ parents+" url:"+ url+" method:"+ method+" sort_no:"+ sort_no+" visible:"+ visible+" css:"+ css);

        return menuService.add(menu, title, parents, url, method, sort_no, visible, css);

    }


    /**
     * 删除
     * @param menu
     * @return
     */
    @Privilege(value = "菜单管理")
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(String menu){
        System.out.println(menu);
        return menuService.delete(menu);
    }

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
