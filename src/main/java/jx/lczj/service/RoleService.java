package jx.lczj.service;

import jx.lczj.dao.RoleDao;
import jx.lczj.model.T_role;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 14260 on 2018/6/11.
 */


@Service
public class RoleService {

    @Resource
    RoleDao roleDao;

    /**
     * 获取角色信息
     * @return
     */
    public List<T_role> loadList() {
        return roleDao.loadList();
    }

    /**
     * 删除角色信息
     * @param role
     * @return
     */
    @Transactional
    public boolean delete(String role) {
        try {
            //删除角色菜单分配
            roleDao.deleteRole_Menu(role);
            //删除角色用户分配
            roleDao.deleteRole_Admin(role);
            //删除角色信息
            roleDao.delete(role);
            return  true;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 更新角色信息
     * @param role
     * @param name
     * @return
     */
    public boolean update(String role, String name) {
        return roleDao.update(role,name);
    }

    /**
     * 添加角色信息
     * @param role
     * @param name
     * @return
     */
    public boolean add(String role, String name) {
        return roleDao.insert(role,name);
    }


    public List<T_role> loadByAdminId(String admin) {
        return roleDao.loadByAdminId(admin);
    }

    @Transactional
    public boolean roleMenuUpdate(String role, String menus) {

        try {
            String[] ss = menus.split(",");
            ;
            boolean ok = roleDao.deleteMenuDiv(role);
            for (String menu: ss) {
                System.out.println(menu);
                boolean ok1 = roleDao.addMenuDiv(role,menu);
            }
            return true;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }
}
