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

    public List<T_role> loadList() {
        return roleDao.loadList();
    }

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

    public boolean update(String role, String name) {
        return roleDao.update(role,name);
    }

    public boolean add(String role, String name) {
        return roleDao.insert(role,name);
    }
}
