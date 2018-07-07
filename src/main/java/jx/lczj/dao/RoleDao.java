package jx.lczj.dao;

import jx.lczj.model.T_role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by 14260 on 2018/6/11.
 */
public interface RoleDao {

    /**
     * 加载角色信息
     * @return
     */
    @Select("select * from T_ROLE")
    public List<T_role> loadList();

    /**
     * 根据角色Id删除角色菜单
     * @param role
     */
    @Delete("delete from T_ROLE_MENU WHERE role = #{0}")
    public void deleteRole_Menu(String role);

    /**
     * 根据角色Id删除用户角色
     * @param role
     */
    @Delete("delete from T_USER_ROLE WHERE role = #{0}")
    public void deleteRole_Admin(String role);

    /**
     * 根据角色Id删除角色
     * @param role
     */
    @Delete("delete from T_ROLE  WHERE role = #{0}")
    public void delete(String role);

    /**
     * 根据角色Id更新角色信息
     * @param role
     * @param name
     * @return
     */
    @Update("update T_ROLE set name = #{1}  WHERE ROLE = #{0}")
    public boolean update(String role, String name);

    /**
     * 添加角色信息
     * @param role
     * @param name
     * @return
     */
    @Update("insert into T_ROLE(role,name) values (#{0},#{1}) ")
    public boolean insert(String role, String name);

    /**
     * 根据管理员id获取角色信息
     * @param admin
     * @return
     */
    @Select("select * from T_ROLE where role in (select role from T_USER_ROLE WHERE admin = #{0})")
    List<T_role> loadByAdminId(String admin);

    /**
     * 删除菜单分配
     * @param role
     * @return
     */
    @Delete("delete from T_ROLE_MENU WHERE ROLE = #{0} ")
    boolean deleteMenuDiv(String role);

    /**
     * 添加菜单分配
     * @param role
     * @param menu
     * @return
     */
    @Update("insert into T_ROLE_MENU(role,menu) values (#{0},#{1}) ")
    boolean addMenuDiv(String role, String menu);
}
