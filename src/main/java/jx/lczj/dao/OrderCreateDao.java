package jx.lczj.dao;

import jx.lczj.model.T_mywear;
import jx.lczj.model.T_order;
import jx.lczj.model.T_orderdetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
    @Insert("INSERT INTO T_ORDER (\"order\" ,CUSTOMER ,ADDRESS , SURE_TIME ,  STATE ,VOUCHER,TOTALFEE) VALUES ( #{0} , #{1} , #{2} , #{3} , #{4} , #{5} , #{6} ) ")
    boolean addOrder(String order, String customer, String address, Date date, int state, int voucher, int totalfee);

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
    @Select("select * from T_MYWEAR WHERE \"order\" = #{0} ")
    List<T_mywear> loadDetialByOrder(String order);


    /**
     * 获取所有订单详情
     * @return
     */
    @Select("select * from T_ORDER WHERE CUSTOMER = #{0}")
    List<T_order> list(String customer);


    /**
     * 获取所有订单详情
     * @return
     */
    @Select("select * from T_ORDER WHERE CUSTOMER = #{0}  AND STATE = #{1}")
    List<T_order> listByState(String customer,int state);

    @Update("update T_ORDER set STATE = #{1}  WHERE \"order\" = #{0} ")
    int updateState(String order, int state);

    @Update("update T_ORDER set ADDRESS = #{1} ,VOUCHER = #{2}  WHERE \"order\" = #{0} ")
    boolean update(String order, String address,int voucher);

    @Delete("DELETE FROM  T_ORDER WHERE \"order\" = #{0} ")
    boolean delete(String order);
}
