package jx.lczj.dao;

import jx.lczj.model.T_occasion;
import jx.lczj.model.T_occasion;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OccasionDao {
    /**
     * 加载场景信息
     * @return
     */
    @Select("select * from T_occasion")
    public List<T_occasion> loadList();

    /**
     * 根据场景Id加载场景信息
     * @param occasion
     * @return
     */
    @Delete("delete from T_occasion  WHERE occasion = #{0}")
    public boolean delete(int occasion);

    /**
     * 根据场景Id更新场景信息
     * @param occasion
     * @param name
     * @param photo
     * @return
     */
    @Update("update T_occasion set name = #{1},photo = #{2}  WHERE occasion = #{0}")
    public boolean update(int occasion, String name, String photo);

    /**
     * 添加场景信息
     * @param occasion
     * @param name
     * @param photo
     * @return
     */
    @Update("insert into T_occasion(occasion,name,photo) values (#{0},#{1},#{2}) ")
    public boolean insert(int occasion, String name, String photo);

    /**
     * 通过场景ID查找场景信息
     * @param occasion
     * @return
     */
    @Select("select * from T_occasion WHERE occasion = #{0}")
    public T_occasion loadByoccasion(int occasion);

    /**
     * 根据镜框Id查找场景信息
     * @param goods
     * @return
     */
    @Select("select * from T_occasion WHERE occasion in (select occasion from T_SUITABLEOCCASION WHERE GOODS = #{0} )")
    List<T_occasion> loadByGood(String goods);
}
