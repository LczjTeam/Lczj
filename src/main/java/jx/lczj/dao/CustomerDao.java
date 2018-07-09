package jx.lczj.dao;

import jx.lczj.model.T_customer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public  interface CustomerDao {


    /**
     *获取会员信息
     * @return
     */
    @Select("select * from T_CUSTOMER")
    public  List<T_customer> loadCustomer();

    /**
     * 通过vip编号查找会员信息
     * @param vip
     * @return
     */
    @Select("select * from T_CUSTOMER WHERE VIP = #{0}")
    public T_customer loadByVip(String vip);

    /**
     * 重置密码
     * @param vip
     * @return
     */
    @Update("update T_CUSTOMER SET PWD = '123456' WHERE VIP = #{0} ")
    boolean resetPwd(String vip);


}
