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

    @Insert("INSERT INTO T_ORDERDETAIL (\"order\" ,MYWEAR ,ADDRESS , SURE_TIME , STATE ) VALUES ( #{0} , #{1} , #{2} , #{3} , #{4} ) ")
    boolean addOrder(String order, String customer, String address, Date date, int state);

    @Insert("INSERT INTO T_ORDERDETAIL (\"order\" ,MYWEAR) VALUES ( #{0} , #{1} ) ")
    boolean addOrderDetail(String order, String mywear);

    @Select("select * from T_ORDER WHERE \"order\" = #{0} ")
    T_order loadById(String order);

    @Select("select * from T_ORDERDETAIL WHERE \"order\" = #{0} ")
    List<T_orderdetail> loadDetialByOrder(String order);
}
