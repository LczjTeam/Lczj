package jx.lczj.dao;

import jx.lczj.model.T_shops;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ShopsDao {

    /**
     * 获取全部门店
     * @return
     */
    @Select("select * from T_shops")
    List<T_shops> list();
    /**
     * 获取前三家
     */
    @Select("SELECT  *  FROM T_SHOPS WHERE  ROWNUM < 4")
    List<T_shops> list1();
    /**
     * 插入新的门店
     * @param shop
     * @param name
     * @param address
     * @param phone
     * @param pos_x
     * @param pos_y
     * @param orders
     * @return
     */
    @Insert("insert into T_shops(shop,name,address,phone,pos_x,pos_y,orders) values(#{0},#{1},#{2},#{3},#{4},#{5},#{6})")
    boolean add(String shop, String name, String address, String phone, float pos_x, float pos_y, int orders);

    /**
     * 更新门店信息
     * @param shop
     * @param name
     * @param address
     * @param phone
     * @param pos_x
     * @param pos_y
     * @param orders
     * @return
     */
    @Update("update T_shops set name=#{1},address=#{2},phone=#{3},pos_x=#{4},pos_y=#{5},orders=#{6} where shop=#{0}")
    boolean update(String shop, String name, String address, String phone, float pos_x, float pos_y, int orders);

    /**
     * 删除门店
     * @param shop
     * @return
     */
    @Delete("delete from T_shops where shop=#{0}")
    boolean delete(String shop);
}
