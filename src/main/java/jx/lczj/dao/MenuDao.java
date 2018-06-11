package jx.lczj.dao;


import jx.lczj.model.T_menu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by 14260 on 2018/6/10.
 */
public interface MenuDao {

    @Select("select * from T_MENU where PARENTS <> '0'   and menu in (select DISTINCT menu from T_ROLE_MENU WHERE ROLE IN ( SELECT ROLE FROM T_USER_ROLE WHERE ADMIN = #{0} ))   order by MENU,SORT_NO")
    public List<T_menu> loadByAdminCode(String admin);

    @Select("select * from T_MENU where PARENTS = '0' order by SORT_NO")
    public List<T_menu> loadRoot();



}
