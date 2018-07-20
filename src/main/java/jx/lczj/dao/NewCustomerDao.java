package jx.lczj.dao;

import jx.lczj.model.T_newcustomer;
import jx.lczj.model.T_reward;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

public interface NewCustomerDao {

    /**
     *  添加新用户
     * @param newcustomer
     * @param phone
     * @param date
     * @param status
     * @param prize
     * @return
     */
    @Insert("insert into T_newcustomer(newcustomer,phone,ins_time,status,prize) values(#{0},#{1},#{2},#{3},#{4})")
    boolean add(String newcustomer, String phone, Date date, int status, int prize);

    /**
     * 更新新用户状态
     * @param phone
     * @param status
     * @return
     */
    @Update("update T_newcustomer set status=#{1} where phone=#{0}")
    boolean update(String phone, int status);

    /**
     * 根据帐号查找新用户
     * @param phone
     * @return
     */
    @Select("select * from T_newcustomer where phone=#{0}")
    T_newcustomer loadByPhone(String phone);

    /**
     * * 通过用户编号获取新用户信息
     * @param customer
     * @return
     */
    @Select("select * from T_newcustomer where NEWCUSTOMER =#{0}")
    T_newcustomer loadByNewcustomer(String customer);

    /**
     * 通过新用户编号获取奖励推荐
     * @param newcustomer
     * @return
     */
    @Select("select * from T_REWARD where NEWCUSTOMER =#{0}")
    T_reward loadRewardByNewCustomer(String newcustomer);


    /**
     * 更新新用户状态
     * @param newcustomer
     * @param status
     * @return
     */
    @Update("update T_newcustomer set status=#{1} where newcustomer =#{0}")
    boolean updateStatus(String newcustomer, int status);
}
