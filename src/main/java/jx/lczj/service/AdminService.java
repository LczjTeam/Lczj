package jx.lczj.service;

import jx.lczj.dao.AdminDao;
import jx.lczj.dao.MenuDao;
import jx.lczj.model.T_admin;
import jx.lczj.model.T_menu;
import jx.lczj.viewmodel.AdminVo;
import jx.lczj.viewmodel.MenuVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.List;

/**
 * Created by 14260 on 2018/6/10.
 */

@Service
public class AdminService {


    @Resource
    AdminDao adminDao;
    @Resource
    MenuDao menuDao;


    /**
     * 获取用户信息
     * @return
     */
    public List<T_admin> loadList() {
        return adminDao.loadList();
    }

    /**
     * 管理员登录
     * @param admin
     * @param pwd
     * @param session
     * @return
     */
    @Transactional
    public AdminVo login(String admin, String pwd, HttpSession session) {
        try {
            T_admin t_admin = adminDao.loadById(admin);
            //用户不存在或者无效
            if (t_admin == null || !t_admin.getIsvalid().equals("1")) {
                return new AdminVo();
            }
            AdminVo avo = new AdminVo();
            avo.setT_admin(t_admin);
            //核对密码
            if (t_admin.getPwd().equals(pwd)) {
                //获取菜单（权限）信息
                List<MenuVo> mvos = new ArrayList<MenuVo>();
                List<T_menu> t_menus = menuDao.loadByAdminCode(admin);
                List<T_menu> roots = menuDao.loadRoot();
                for (T_menu t : roots) {

                    List<T_menu> tms = new ArrayList<T_menu>();
                    for (T_menu ts : t_menus) {
                        if (ts.getParents().equals(t.getMenu())) {
                            tms.add(ts);
                        }
                    }
                    if (tms.size() != 0) {
                        MenuVo mvo = new MenuVo();
                        mvo.setMenu(t);
                        mvo.setMenus(tms);
                        mvos.add(mvo);
                    }
                }
                avo.setMenuVos(mvos);
                session.setAttribute("admin", avo);
            }
            return avo;
        }catch (Exception e){
            return  new AdminVo();
        }
    }


    /**
     *添加
     * @param admin
     * @param name
     * @param isvalid
     * @return
     */
    public boolean add(String admin, String name,String isvalid) {
        return adminDao.add(admin,name,isvalid);
    }


    /**
     * 更新管理员信息
     * @param admin
     * @param isvalid
     * @param name
     * @param session
     * @return
     */
    @Transactional
    public boolean update1(String admin, String name,String isvalid, HttpSession session) {
        try {
            boolean ok = adminDao.update1(admin,name,isvalid);
            return true;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }

    /**
     * 删除角色信息
     * @param admin
     * @return
     */
    @Transactional
    public boolean delete(String admin) {
        try {
            //删除用户的角色信息
            adminDao.deleteRoleDiv(admin);
            //删除用户信息
            System.out.println("admin:"+admin);
            adminDao.delete(admin);
            return true;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean resetPwd(String admin) {
        return adminDao.resetPwd(admin);
    }

    @Transactional
    public boolean update(String admin, String name, String pwd, HttpSession session) {

        try {
            boolean ok = adminDao.update(admin,name,pwd);
            if(ok){
                try {
                    AdminVo adminVo = (AdminVo) session.getAttribute("admin");
                    T_admin t_admin = new T_admin();
                    t_admin.setAdmin(admin);
                    t_admin.setName(name);
                    t_admin.setPwd(pwd);
                    adminVo.setT_admin(t_admin);
                    session.setAttribute("admin",adminVo);
                }catch (Exception e){
                    throw  new RuntimeException(e.getMessage());
                }
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public boolean userRoleUpdate(String admin, String roles) {

        try {
            String[] ss = roles.split(",");
            ;
            boolean ok = adminDao.deleteRoleDiv(admin);
            for (String role: ss) {
                System.out.println(role);
                boolean ok1 = adminDao.addRoleDiv(admin,role);
            }
            return true;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }

    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    public boolean exit(HttpSession session) {

        try {
            session.removeAttribute("admin");
            return true;
        }catch (Exception e){
            return  false;
        }
    }
}
