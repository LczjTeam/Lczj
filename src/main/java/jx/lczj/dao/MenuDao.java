package jx.lczj.dao;


import javafx.scene.Parent;
import jx.lczj.model.T_menu;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by 14260 on 2018/6/10.
 */
public interface MenuDao {

    @Select("select * from T_MENU where PARENTS <> '0'   and MENU in (select DISTINCT menu from T_ROLE_MENU WHERE ROLE IN ( SELECT ROLE FROM T_USER_ROLE WHERE ADMIN = #{0} ))   order by MENU,SORT_NO")
    public List<T_menu> loadByAdminCode(String admin);

    @Select("select * from T_MENU where PARENTS = '0' order by SORT_NO")
    public List<T_menu> loadRoot();

    @Select("SELECT * from  T_MENU ORDER BY MENU,PARENTS")
    public List<T_menu> allCode();

    @Select("SELECT * from  T_MENU where MENU =  #{0} ")
    public T_menu loadById(String menu);

    @Update("update T_MENU set title = #{1},parents = #{2},url = #{3},method = #{4},sort_no = #{5},visible = #{6},css = #{7} WHERE Menu = #{0}")
    public boolean update(String menu, String title, String parents,String url,String method,int sort_no,int visible , String css);
}
