package jx.lczj.service;

import jx.lczj.dao.AdminDao;
import jx.lczj.dao.MenuDao;
import jx.lczj.model.T_admin;
import jx.lczj.model.T_menu;
import jx.lczj.viewmodel.AdminVo;
import jx.lczj.viewmodel.MenuVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.awt.*;
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


    public AdminVo login(String admin, String pwd, HttpSession session) {
        T_admin t_admin = adminDao.loadById(admin);
        //用户不存在或者无效
        if(t_admin==null || !t_admin.getIsvalid().equals("1")){
            return new AdminVo();
        }
        AdminVo avo = new AdminVo();
        avo.setT_admin(t_admin);
        //核对密码
        if(t_admin.getPwd().equals(pwd)){
            //获取菜单（权限）信息
            List<MenuVo> mvos = new ArrayList<MenuVo>();
            List<T_menu> t_menus = menuDao.loadByAdminCode(admin);
            List<T_menu> roots = menuDao.loadRoot();
            for (T_menu t: roots) {

                List<T_menu> tms = new ArrayList<T_menu>();
                for ( T_menu ts : t_menus) {
                    if(ts.getParents().equals(t.getMenu())){
                        tms.add(ts);
                    }
                }
                if(tms.size()!=0){
                    MenuVo mvo = new MenuVo();
                    mvo.setMenu(t);
                    mvo.setMenus(tms);
                    mvos.add(mvo);
                }
            }
            avo.setMenuVos(mvos);
            session.setAttribute("admin",avo);
        }
        return avo;

    }
}
