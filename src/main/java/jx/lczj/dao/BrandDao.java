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
    /**
     * 加载品牌
     * @return
     */
    @Select("select * from T_BRAND")
    public List<T_brand> loadList();

    /**
     * 编辑品牌信息
     * @param brand
     * @param name
     * @param company
     * @param type
     * @return
     */
    @Update("update T_BRAND set name= #{1} , company = #{2} , type = #{3} , recommend = #{4} where brand = #{0}")
    boolean update(int brand, String name, String company,int type,int recommend);

    /**
     * 根据id删除品牌信息
     * @param brand
     * @return
     */
    @Delete("delete from T_BRAND WHERE brand = #{0}")
    public boolean delete(String brand);

    /**
     * 添加品牌信息
     * @param brand
     * @param name
     * @param company
     * @param type
     * @return
     */
    @Insert("insert into T_BRAND(brand,name,company,type,recommend) values(#{0},#{1},#{2},#{3},#{4})")
    public boolean add(int brand, String name, String company,int type,int recommend);

    /**
     * 根据Id查找品牌
     * @param brand
     * @return
     */
    @Select("select * from T_BRAND WHERE BRAND = #{0}")
    T_brand loadById(int brand);

    /**
     * 获取镜片品牌
     * @return
     */
     @Select("select * from T_BRAND where TYPE = 2 or TYPE = 0 ")
    List<T_brand> loadList1();

    /**
     * 获取境框品牌
     * @return
     */
    @Select("select * from T_BRAND where TYPE = 1 or TYPE = 0 ")
    List<T_brand> loadList2();

    @Select("select * from T_BRAND where (TYPE = 2 or TYPE = 0) and recommend  = 1 ")
    List<T_brand> loadListByRecommend();
}
