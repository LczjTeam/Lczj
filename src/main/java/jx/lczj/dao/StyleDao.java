package jx.lczj.dao;

import jx.lczj.model.T_style;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StyleDao {

    @Select("select * from T_style")
    public List<T_style> loadList();

    @Update("update T_STYLE set name= #{1} where style = #{0}")
    boolean update(int style, String name);

    @Delete("delete from T_STYLE WHERE style = #{0}")
    public boolean delete(int style);

    @Insert("insert into T_STYLE(style,name) values(#{0},#{1})")
    public boolean add(int style, String name);

    @Select("select * from T_STYLE WHERE style = #{0}")
    T_style loadById(int style);

}
