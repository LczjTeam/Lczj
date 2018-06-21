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
    public List<T_category> loadList();

    @Insert("insert into T_category(category,name,occasion) values(#{0},#{1},#{2})")
    public boolean add(int category, String name, String occasion);

    @Update("update T_category set name=#{1},occasion=#{2} where category=#{0}")
    boolean update(int category, String name, String occasion);

    @Delete("delete from T_category where category=#{0}")
    void delete(String category);
}
