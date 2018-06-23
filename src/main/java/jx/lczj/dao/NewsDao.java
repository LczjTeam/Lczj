package jx.lczj.dao;

import jx.lczj.model.T_news;
import jx.lczj.viewmodel.NewsVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by 14260 on 2018/6/23.
 */
public interface NewsDao {

    @Insert("insert into T_NEWS (CODE,TITLE,ISSUE_DATE,AUTHOR,KEYWORD,FILENAME,ITEMS,TOP,PUBLISH,PHOTO)  VALUES (#{0},#{1},TO_DATE(#{2},'yyyy-mm-dd hh24:mi:ss'),#{3},#{4},#{5},#{6},#{7},#{8},#{9})")
    boolean add(String code, String title, String ISSUE_DATE, String AUTHOR, String KEYWORD, String FILENAME, int ITEMS, String TOP, String PUBLISH, String PHOTO);

    @Select("select * from T_NEWS WHERE CODE =#{0}")
    T_news loadByCode(String code);

    @Select("select * from T_NEWS WHERE AUTHOR =#{0} and ITEMS = 1 ")
    List<T_news> listByAdmin(String admin);


    @Delete("delete from T_NEWS WHERE CODE =#{0}")
    boolean delete(String code);

    @Update("update T_NEWS set  TITLE = #{1},ISSUE_DATE = TO_DATE(#{2},'yyyy-mm-dd hh24:mi:ss') ,AUTHOR = #{3} ,KEYWORD =  #{4},FILENAME =  #{5},ITEMS = #{6},TOP = #{7},PUBLISH = #{8} ,PHOTO = #{9}  where code = #{0}")
    boolean update(String code, String title, String ISSUE_DATE, String AUTHOR, String KEYWORD, String FILENAME, int ITEMS, String TOP, String PUBLISH, String PHOTO);
}
