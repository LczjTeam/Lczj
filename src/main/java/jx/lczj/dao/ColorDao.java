package jx.lczj.dao;

import jx.lczj.model.T_color;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.omg.IOP.TAG_CODE_SETS;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public  interface  ColorDao {
    /**
     * 添加颜色
     */
    @Insert("insert into T_COLOR VALUES (#{0},#{1},#{2})")
    public  boolean addColor(int color, String name, String rgb);

    /**
     *
     * @return
     */
    @Select("select * from T_COLOR")
    public  List<T_color> loadColors();

    /**
     * 删除某一个颜色
     * @param color
     * @return
     */
    @Delete("delete from T_COLOR where COLOR = #{0}")
    public  boolean deleteColor(int color);

    /**
     * 通过ID查找颜色
     * @param color
     * @return
     */
    @Select("select * from T_COLOR WHERE COLOR = #{0}")
   public T_color loadByColor(int color);



    @Update("update T_COLOR set NAME = #{1}, RGB = #{2} where COLOR = #{0}")
    boolean updateColor(int color, String name, String rgb);

    @Select("select * from T_COLOR WHERE COLOR IN (SELECT COLOR FROM T_GOODSCOLOR WHERE GOODS = #{0})")
    List<T_color> loadByGood(String goods);
}
