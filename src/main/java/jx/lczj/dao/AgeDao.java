package jx.lczj.dao;

import jx.lczj.model.T_agesection;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AgeDao {

    @Select("select * from T_agesection")
    List<T_agesection> loadlist();

    @Insert("insert into T_agesection values(#{0},#{1},#{2},#{3})")
    boolean add(int agesection, String name, int minage, int maxage);

    @Update("update T_agesection set name = #{1},minage = #{2},maxage = #{3} where agesection = #{0}")
    boolean update(int agesection,String name, int minage, int maxage);

    @Delete("delete from T_agesection where agesection = #{0}")
    boolean delete(int agesection);
}
