package jx.lczj.dao;

import jx.lczj.model.T_mask;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MaskDao {

    @Select("select * from T_MASK")
    List<T_mask> loadList();

    @Insert("insert into T_mask values(#{0},#{1})")
    boolean add(int mask, String name);

    @Delete("delete T_mask where MASK = #{0}")
    boolean delete(String mask);

    @Update("update T_mask set  NAME = #{1} where MASK = #{0}")
    boolean update(int mask, String name);

    @Select("select * from T_mask where MASK = #{0}")
    T_mask loadById(int mask);
}
