package jx.lczj.dao;

import jx.lczj.model.T_order;
import jx.lczj.model.T_orderdetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by 14260 on 2018/7/15.
 */

public interface OrderCreateDao {


    /**
     * 创建订单信息
     * @param order
     * @param customer
     * @param address
     * @param date
     * @param state
     * @return
     */
    @Insert("INSERT INTO T_ORDERDETAIL (\"order\" ,MYWEAR ,ADDRESS , SURE_TIME , STATE ) VALUES ( #{0} , #{1} , #{2} , #{3} , #{4} ) ")
    boolean addOrder(String order, String customer, String address, Date date, int state);

    /**
     * 创建详情信息
     * @param order
     * @param mywear
     * @return
     */
    @Insert("INSERT INTO T_ORDERDETAIL (\"order\" ,MYWEAR) VALUES ( #{0} , #{1} ) ")
    boolean addOrderDetail(String order, String mywear);

    /**
     * 通过order编号获取订单信息
     * @param order
     * @return
     */
    @Select("select * from T_ORDER WHERE \"order\" = #{0} ")
    T_order loadById(String order);

    /**
     * 通过order编号获取订单详情
     * @param order
     * @return
     */
    @Select("select * from T_ORDERDETAIL WHERE \"order\" = #{0} ")
    List<T_orderdetail> loadDetialByOrder(String order);
}
