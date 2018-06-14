package jx.lczj.service;


import jx.lczj.dao.MenuDao;
import jx.lczj.model.T_menu;
import jx.lczj.viewmodel.MenuVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    @Resource
    MenuDao menuDao;
    /**
     * 获取菜单信息
     * @return
     */
    public List<MenuVo> loadList() {
        List<MenuVo> menuVo3 = new ArrayList<MenuVo>();
        List<T_menu> allCode = menuDao.allCode();
        List<T_menu> roots = menuDao.loadRoot();
       for(T_menu t : roots){
           List<T_menu> list1 = new ArrayList<T_menu>();
           MenuVo menuVo1 = new MenuVo();
           for (T_menu menu1 : allCode){
               if(Integer.parseInt(menu1.getParents())==t.getSort_no()*10){
                   list1.add(menu1);
               }
           }
           menuVo1.setMenu(t);
           menuVo1.setMenus(list1);
           menuVo3.add(menuVo1);
       }
        return menuVo3;
    }
    /**
     * 通过I的获取信息
     */
    public T_menu loadById(String menu)
    {
        return  menuDao.loadById(menu);
    }
    /**
     * 更新菜单信息
     * @return
     */
    public boolean update(String menu, String title, String parents,String url,String method,int sort_no,int visible , String css) {
        return menuDao.update(menu, title, parents, url, method, sort_no, visible, css);
    }

    /**
     * 获取根节点
     * @return
     */
    public List<T_menu> loadRoots() {
        return menuDao.loadRoot();
    }

    /**
     * 获取全部节点
     *
     * @param
     */
//    public List<T_menu> getAllCode(){
//        return MenuDao.allCode();
//    }
    /**
     * 删除菜单信息
     * @param role
     * @return
     */
    //@Transactional
  /*  public boolean delete(String role) {
        try {
           // 删除角色菜单分配
            MenuDao.deleteRole_Menu(role);
            //删除角色用户分配
            MenuDao.deleteRole_Admin(role);
            //删除角色信息
            MenuDao.delete(role);
            return  true;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }*/



    /**
     * 添加菜单信息
     * @param role
     * @param name
     * @return
     */
   /* public boolean add(String role, String name) {
        return roleDao.insert(role,name);
    }*/
}
