package jx.lczj.dao;

import jx.lczj.model.T_new;
import jx.lczj.model.T_role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by 14260 on 2018/6/11.
 */
public interface NewDao {


    @Select("select * from T_NEW")
    public List<T_new> loadList();

    @Delete("delete from T_NEW WHERE code = #{0}")
    public void delete(String code);

    @Update("update from T_NEW SET title = #{1},issue_date = #{2},author = #{3},keyword = #{4},filename = #{5},items = #{6},top = #{7},publish = #{8},photo = #{9} WHERE code = {0}")
    public boolean update(String code,String title,String issue_date,String author,String keyword,String filename,int items,String top,String publish,String photo);

    @Insert("insert into T_NEW(code,title,issue_date,author,keyword,filename,items,top,publish,photo) values(#{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7},#{8},#{9})")
    public boolean add(String code,String title,String issue_date,String author,String keyword,String filename,int items,String top,String publish,String photo);
}
