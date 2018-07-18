package jx.lczj.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

public interface RewardDao {

    /**
     * 添加新优惠券记录
     * @param customer
     * @param newcustomer
     * @param status
     * @param ins_time
     * @param prize
     * @return
     */
    @Insert("insert into T_reward(customer,newcustomer,status,ins_time,prize) values(#{0},#{1},#{2},#{3},#{4})")
    boolean add(String customer, String newcustomer, int status, Date ins_time, int prize);

    /**
     * 更新优惠券状态
     * @param customer
     * @param newcustomer
     * @param status
     * @return
     */
    @Update("update T_reward set status=#{2} where customer=#{0} and newcustomer=#{1}")
    boolean update(String customer, String newcustomer, int status);
}
