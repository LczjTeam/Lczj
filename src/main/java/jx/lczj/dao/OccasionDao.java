package jx.lczj.dao;

import jx.lczj.model.T_occasion;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OccasionDao {
    @Select("select * from T_occasion")
    public List<T_occasion> loadList();

    @Delete("delete from T_occasion  WHERE occasion = #{0}")
    public void delete(String occasion);

    @Update("update T_occasion set name = #{1}  WHERE occasion = #{0}")
    public boolean update(String occasion, String name);

    @Update("insert into T_occasion(occasion,name) values (#{0},#{1}) ")
    public boolean insert(String occasion, String name);

}
