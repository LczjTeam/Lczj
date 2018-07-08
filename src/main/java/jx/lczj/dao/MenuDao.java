package jx.lczj.dao;


import javafx.scene.Parent;
import jx.lczj.model.T_menu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by 14260 on 2018/6/10.
 */
public interface MenuDao {
    /**
     * 根据管理员Id获取二级菜单信息
     * @param admin
     * @return
     */
    @Select("select * from T_MENU where PARENTS <> '0'   and MENU in (select DISTINCT menu from T_ROLE_MENU WHERE ROLE IN ( SELECT ROLE FROM T_USER_ROLE WHERE ADMIN = #{0} ))   order by SORT_NO,MENU")
    public List<T_menu> loadByAdminCode(String admin);

    /**
     * 获取一级菜单
     * @return
     */
    @Select("select * from T_MENU where PARENTS = '0' order by SORT_NO")
    public List<T_menu> loadRoot();

    /**
     *获取二级菜单
     * @return
     */
    @Select("SELECT * from  T_MENU WHERE PARENTS <> '0' ORDER BY MENU,SORT_NO ")
    public List<T_menu> allCode();

    /**
     * 根据菜单Id获取菜单信息
     * @param menu
     * @return
     */
    @Select("SELECT * from  T_MENU where MENU =  #{0} ")
    public T_menu loadById(String menu);

    /**
     * 根据菜单Id更新菜单信息
     * @param menu
     * @param title
     * @param parents
     * @param url
     * @param method
     * @param sort_no
     * @param visible
     * @param css
     * @return
     */
    @Update("update T_MENU set title = #{1},parents = #{2},url = #{3},method = #{4},sort_no = #{5},visible = #{6},css = #{7} WHERE Menu = #{0}")
    public boolean update(String menu, String title, String parents,String url,String method,int sort_no,int visible , String css);

    /**
     * 根据菜单Id添加菜单信息
     * @param menu
     * @param title
     * @param parents
     * @param url
     * @param method
     * @param sort_no
     * @param visible
     * @param css
     * @return
     */
    @Insert("insert into T_MENU (menu,title ,parents,url,method,sort_no ,visible ,css ) values (#{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7})")
    boolean add(String menu, String title, String parents, String url, String method, int sort_no, int visible, String css);

    /**
     * 根据父菜单和菜单Id来删除菜单信息
     * @param menu
     * @return
     */
    @Delete("delete from T_MENU where menu = #{0} or parents = #{0}")
    boolean delete(String menu);

    /**
     * 根据菜单 Id来删除角色菜单
     * @param menu
     * @return
     */
    @Delete("delete from T_ROLE_MENU where menu = #{0} ")
    boolean deleteRole_Menu(String menu);

    /**
     * 通过角色Id来获取菜单信息
     * @param role
     * @return
     */
    @Select("SELECT * from  T_MENU where MENU in (select menu from T_ROLE_MENU WHERE ROLE = #{0}) order by MENU,SORT_NO")
    List<T_menu> loadByRole(String role);

    /**
     * 获取子菜单
     * @return
     */
    @Select("SELECT * from  T_MENU where parents <> '0' order by MENU,SORT_NO")
    List<T_menu> loadChildren();
}
