package jx.lczj.dao;
import jx.lczj.model.T_brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by 25778 on 2018/6/18.
 */
public interface BrandDao {
    @Select("select * from T_BRAND")
    public List<T_brand> loadList();

    @Update("update T_BRAND set name= #{1} , company = #{2} , type = #{3} where brand = #{0}")
    boolean update(int brand, String name, String company,int type);

    @Delete("delete from T_BRAND WHERE brand = #{0}")
    public boolean delete(String brand);

    @Insert("insert into T_BRAND(brand,name,company,type) values(#{0},#{1},#{2},#{3})")
    public boolean add(int brand, String name, String company,int type);

    @Select("select * from T_BRAND WHERE BRAND = #{0}")
    T_brand loadById(int brand);

     @Select("select * from T_BRAND where TYPE = 1 or TYPE = 0 ")
    List<T_brand> loadList1();

    @Select("select * from T_BRAND where TYPE = 2 or TYPE = 0 ")
    List<T_brand> loadList2();
}
