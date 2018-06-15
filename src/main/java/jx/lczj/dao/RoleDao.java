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


    @Select("select * from T_ROLE")
    public List<T_role> loadList();

    @Delete("delete from T_ROLE_MENU WHERE role = #{0}")
    public void deleteRole_Menu(String role);

    @Delete("delete from T_USER_ROLE WHERE role = #{0}")
    public void deleteRole_Admin(String role);

    @Delete("delete from T_ROLE  WHERE role = #{0}")
    public void delete(String role);

    @Update("update T_ROLE set name = #{1}  WHERE ROLE = #{0}")
    public boolean update(String role, String name);

    @Update("insert into T_ROLE(role,name) values (#{0},#{1}) ")
    public boolean insert(String role, String name);

    @Select("select * from T_ROLE where role in (select role from T_USER_ROLE WHERE admin = #{0})")
    List<T_role> loadByAdminId(String admin);
}
