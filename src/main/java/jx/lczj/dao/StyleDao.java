package jx.lczj.dao;

import jx.lczj.model.T_style;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StyleDao {
    /**
     * 获取样式信息
     * @return
     */
    @Select("select * from T_style")
    public List<T_style> loadList();

    /**
     * 根据样式Id获取样式信息
     * @param style
     * @param name
     * @return
     */
    @Update("update T_STYLE set name= #{1} where style = #{0}")
    boolean update(int style, String name);

    /**
     * 根据样式Id删除样式信息
     * @param style
     * @return
     */
    @Delete("delete from T_STYLE WHERE style = #{0}")
    public boolean delete(int style);

    /**
     * 添加样式信息
     * @param style
     * @param name
     * @return
     */
    @Insert("insert into T_STYLE(style,name) values(#{0},#{1})")
    public boolean add(int style, String name);

    /**
     * 根据样式Id加载样式信息
     * @param style
     * @return
     */
    @Select("select * from T_STYLE WHERE style = #{0}")
    T_style loadById(int style);

}
