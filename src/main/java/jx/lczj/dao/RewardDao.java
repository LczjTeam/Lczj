package jx.lczj.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

public interface RewardDao {

    @Insert("insert into T_reward(customer,newcustomer,status,ins_time,prize) values(#{0},#{1},#{2},#{3},#{4})")
    boolean add(String customer, String newcustomer, int status, Date ins_time, int prize);

    @Update("update T_reward set status=#{2} where customer=#{0} and newcustomer=#{1}")
    boolean update(String customer, String newcustomer, int status);
}
