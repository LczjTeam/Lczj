package jx.lczj.dao;

import jx.lczj.model.T_model;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by 14260 on 2018/6/24.
 */
public interface ModelDao {

    @Insert("insert into T_MODEL (MODEL ,NAME ,AGE,SEX ,FACE ,PHOTO) VALUES (#{0}, #{1}, #{2}, #{3}, #{4}, #{5})")
    boolean add(int model, String name, int age, String sex, int face, String fileName);

    @Select("select * from T_MODEL")
    List<T_model> loadList();

    @Select("select * from T_MODEL WHERE MODEL = #{0}")
    T_model loadById(int MODEL);

    @Delete("delete from T_MODEL WHERE MODEL = #{0}")
    boolean delete(int model);

    @Update("update T_MODEL set  NAME  = #{1},AGE = #{2},SEX = #{3} ,FACE = #{4} ,PHOTO = #{5} where MODEL = #{0}")
    boolean update(int i, String name, int age, String sex, int face, String fileName);
}
