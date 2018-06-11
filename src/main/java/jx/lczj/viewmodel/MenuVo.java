package jx.lczj.viewmodel;

import jx.lczj.model.T_menu;

import java.util.List;

/**
 * Created by 14260 on 2018/6/10.
 */
public class MenuVo {

    private T_menu menu;
    private List<T_menu> menus;

    public T_menu getMenu() {
        return menu;
    }

    public void setMenu(T_menu menu) {
        this.menu = menu;
    }

    public List<T_menu> getMenus() {
        return menus;
    }

    public void setMenus(List<T_menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "MenuVo{" +
                "menu=" + menu +
                ", menus=" + menus +
                '}';
    }
}
