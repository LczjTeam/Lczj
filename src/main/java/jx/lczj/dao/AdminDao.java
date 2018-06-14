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

    @Select("select * from T_ADMIN")
    public List<T_admin> loadList();

    @Select("select * from T_ADMIN where ADMIN = #{0}")
    public T_admin loadById(String code);

    @Update("update T_ADMIN set name= #{1} , isvalid = #{2} where admin = #{0}")
    boolean update1(String admin, String name, String isvalid);

    @Delete("delete from T_ADMIN WHERE admin = #{0}")
    public boolean delete(String admin);

    @Insert("insert into T_admin(admin,name,isvalid) values(#{0},#{1},#{2})")
    public boolean add(String admin,String name,String isvalid);

    @Delete("delete from T_USER_ROLE WHERE admin = #{0}")
    boolean deleteRoleDiv(String admin);

    @Update("update T_ADMIN SET PWD = '123456' WHERE admin = #{0} ")
    boolean resetPwd(String admin);
}
