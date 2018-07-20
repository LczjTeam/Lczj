package jx.lczj.dao;

import jx.lczj.model.T_model;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 14260 on 2018/6/24.
 */
public interface ModelDao {
    /**
     * 添加模特信息
     * @param model
     * @param name
     * @param age
     * @param sex
     * @param face
     * @param fileName
     * @return
     */
    @Insert("insert into T_MODEL (MODEL ,NAME ,AGE,SEX ,FACE ,PHOTO) VALUES (#{0}, #{1}, #{2}, #{3}, #{4}, #{5})")
    boolean add(int model, String name, int age, String sex, int face, String fileName);

    /**
     * 加载模特信息
     * @return
     */
    @Select("select * from T_MODEL")
    List<T_model> loadList();

    /**
     * 根据模特Id加载模特信息
     * @param MODEL
     * @return
     */
    @Select("select * from T_MODEL WHERE MODEL = #{0}")
    T_model loadById(int MODEL);

    /**
     * 根据模特Id删除模特信息
     * @param model
     * @return
     */
    @Delete("delete from T_MODEL WHERE MODEL = #{0}")
    boolean delete(int model);

    /**
     * 根据模特Id更新模特Id
     * @param i
     * @param name
     * @param age
     * @param sex
     * @param face
     * @param fileName
     * @return
     */
    @Update("update T_MODEL set  NAME  = #{1},AGE = #{2},SEX = #{3} ,FACE = #{4} ,PHOTO = #{5} where MODEL = #{0}")
    boolean update(int i, String name, int age, String sex, int face, String fileName);

    /**
     * 根据脸形，年龄，性别推荐模特
     * @param face
     * @param age
     * @param sex
     * @return
     */
    List<T_model> modelCommend(
            @Param("faces") int face,
            @Param("ages") int age,
            @Param("sexes") String sex);
}
