package jx.lczj.dao;

import jx.lczj.model.T_agesection;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AgeDao {
    /**
     * 加载年龄段
     * @return
     */
    @Select("select * from T_agesection")
    List<T_agesection> loadlist();

    /**
     * 插入年龄段
     * @param agesection
     * @param name
     * @param minage
     * @param maxage
     * @return
     */
    @Insert("insert into T_agesection values(#{0},#{1},#{2},#{3})")
    boolean add(int agesection, String name, int minage, int maxage);

    /**
     * 更新年龄段
     * @param agesection
     * @param name
     * @param minage
     * @param maxage
     * @return
     */
    @Update("update T_agesection set name = #{1},minage = #{2},maxage = #{3} where agesection = #{0}")
    boolean update(int agesection,String name, int minage, int maxage);

    /**
     * 根据Id删除年龄段
     * @param agesection
     * @return
     */
    @Delete("delete from T_agesection where agesection = #{0}")
    boolean delete(int agesection);

    /**
     * 通过Id查找年龄段
     * @param goods
     * @return
     */
    @Select("select * from T_agesection where agesection in  (select agesection  from T_SUITABLEAGE WHERE GOODS = #{0} ) " )
    List<T_agesection> loadByGood(String goods);
}
