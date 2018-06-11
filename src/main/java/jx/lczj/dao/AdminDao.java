package jx.lczj.dao;

import jx.lczj.model.T_admin;
import org.apache.ibatis.annotations.Select;

/**
 * Created by 14260 on 2018/6/10.
 */


public interface AdminDao {


    @Select("select * from T_ADMIN where ADMIN = #{0}")
    public T_admin loadById(String code);

}
