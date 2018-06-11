package jx.lczj.dao;

import jx.lczj.model.T_role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by 14260 on 2018/6/11.
 */
public interface RoleDao {


    @Select("select * from T_ROLE")
    public List<T_role> loadList();
}
