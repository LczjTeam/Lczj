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
    /**
     * 加载类别信息
     * @return
     */
    @Select("select * from T_category")
    List<T_category> loadList();

    /**
     * 添加类别信息
     * @param category
     * @param name
     * @return
     */
    @Insert("insert into T_category values(#{0},#{1})")
    boolean add(int category, String name);

    /**
     * 更新类别信息
     * @param category
     * @param name
     * @return
     */
    @Update("update T_category set name=#{1} where category=#{0}")
    boolean update(int category, String name);

    /**
     * 删除类别
     * @param category
     */
    @Delete("delete from T_category where category=#{0}")
    void delete(String category);

    /**
     * 根据Id获取类别
     * @param category
     * @return
     */
    @Select("select * from T_category where category=#{0}")
    T_category loadById(int category);

    /**
     * 根据镜框Id获取类别
     * @param goods
     * @return
     */
    @Select("select * from T_category where category in (select category from T_goodscategory where goods = #{0})")
    List<T_category> loadByGoods(String goods);
}
