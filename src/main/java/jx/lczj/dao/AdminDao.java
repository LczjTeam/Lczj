package jx.lczj.dao;

import jx.lczj.model.T_admin;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by 14260 on 2018/6/10.
 */


public interface AdminDao {


    @Select("select * from T_ADMIN where ADMIN = #{0}")
    public T_admin loadById(String code);

    @Update("update T_ADMIN set name= #{1} , pwd = #{2} where admin = #{0}")
    boolean update(String admin, String name, String pwd);
}
