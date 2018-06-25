package jx.lczj.dao;

import jx.lczj.model.T_category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by 14260 on 2018/6/10.
 */


public interface CategoryDao {

    @Select("select * from T_category")
    List<T_category> loadList();

    @Insert("insert into T_category values(#{0},#{1})")
    boolean add(int category, String name);

    @Update("update T_category set name=#{1} where category=#{0}")
    boolean update(int category, String name);

    @Delete("delete from T_category where category=#{0}")
    void delete(String category);

    @Select("select * from T_category where category=#{0}")
    T_category loadById(int category);
}
