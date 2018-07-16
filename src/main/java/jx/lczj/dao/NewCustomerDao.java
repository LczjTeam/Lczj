package jx.lczj.dao;

import jx.lczj.model.T_newcustomer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

public interface NewCustomerDao {


//    private String newcustomer;     //新用户
//    private String phone;           //电话
//    private String ins_time;        //推荐时间
//    private int status;             //状态
//    private int prize;              //价格

    @Insert("insert into T_newcustomer(newcustomer,phone,ins_time,status,prize) values(#{0},#{1},#{2},#{3},#{4})")
    boolean add(String newcustomer, String phone, Date date, int status, int prize);

    @Update("update T_newcustomer set status=#{1} where phone=#{0}")
    boolean update(String phone, int status);

    @Select("select * from T_newcustomer where phone=#{0}")
    T_newcustomer loadByPhone(String phone);
}
