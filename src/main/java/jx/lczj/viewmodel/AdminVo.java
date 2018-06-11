package jx.lczj.viewmodel;

import jx.lczj.model.T_admin;

import java.util.List;

/**
 * Created by 14260 on 2018/6/10.
 */
public class AdminVo {

    private T_admin t_admin;
    private List<MenuVo> menuVos;

    @Override
    public String toString() {
        return "AdminVo{" +
                "t_admin=" + t_admin +
                ", menuVos=" + menuVos +
                '}';
    }

    public T_admin getT_admin() {
        return t_admin;
    }

    public void setT_admin(T_admin t_admin) {
        this.t_admin = t_admin;
    }

    public List<MenuVo> getMenuVos() {
        return menuVos;
    }

    public void setMenuVos(List<MenuVo> menuVos) {
        this.menuVos = menuVos;
    }
}
