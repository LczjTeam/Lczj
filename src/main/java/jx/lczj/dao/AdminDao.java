package jx.lczj.dao;

import jx.lczj.model.T_admin;
import jx.lczj.model.T_role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by 14260 on 2018/6/10.
 */


public interface AdminDao {

    /**
     * 加载管理员
     * @return
     */
    @Select("select * from T_ADMIN")
    public List<T_admin> loadList();

    /**
     * 通过ID查找管理员
     * @param code
     * @return
     */
    @Select("select * from T_ADMIN where ADMIN = #{0}")
    public T_admin loadById(String code);

    /**
     * 更新管理员信息
     * @param admin
     * @param name
     * @param isvalid
     * @return
     */
    @Update("update T_ADMIN set name= #{1} , isvalid = #{2} where admin = #{0}")
    boolean update1(String admin, String name, String isvalid);

    /**
     * 根据Id删除管理员
     * @param admin
     * @return
     */
    @Delete("delete from T_ADMIN WHERE admin = #{0}")
    public boolean delete(String admin);

    /**
     * 添加管理员
     * @param admin
     * @param name
     * @param isvalid
     * @return
     */
    @Insert("insert into T_admin(admin,name,isvalid) values(#{0},#{1},#{2})")
    public boolean add(String admin,String name,String isvalid);

    /**
     * 通过管理员Id删除管理员角色
     * @param admin
     * @return
     */
    @Delete("delete from T_USER_ROLE WHERE admin = #{0}")
    boolean deleteRoleDiv(String admin);

    /**
     * 重置为密码
     * @param admin
     * @return
     */
    @Update("update T_ADMIN SET PWD = '123456' WHERE admin = #{0} ")
    boolean resetPwd(String admin);

    /**
     * 更改管理员名和密码
     * @param admin
     * @param name
     * @param pwd
     * @return
     */
    @Update("update T_ADMIN set name= #{1} , pwd = #{2} where admin = #{0}")
    boolean update(String admin, String name, String pwd);

    /**
     * 通过Id添加管理员角色
     * @param admin
     * @param role
     * @return
     */
    @Insert("insert into T_USER_ROLE (admin ,role) values (#{0},#{1})")
    boolean addRoleDiv(String admin, String role);
}
