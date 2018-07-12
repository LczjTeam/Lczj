package jx.lczj.dao;

import jx.lczj.model.T_customer;
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
     * @param phone
     * @return
     */
    @Select("select * from T_CUSTOMER WHERE PHONE = #{0}")
    public T_customer loadByVip(String phone);

    /**
     * 重置密码
     * @param phone
     * @return
     */
    @Update("update T_CUSTOMER SET PWD = '123456' WHERE phone = #{0} ")
    boolean resetPwd(String phone);


    @Select("select * from T_CUSTOMER WHERE PHONE = #{0}")
    T_customer loadByPhone(String phone);


    @Insert("insert into T_CUSTOMER(VIP,NAME,PHONE,SEX,PWD,BIRTHDAY,FACE) VALUES (#{0},#{1},#{2},#{3},#{4},#{5},#{6})")
    boolean add(String vip, String name, String phone, String sex, String pwd, String birthday, String face);

    /**
     * 编辑更新
     * @param name
     * @param phone
     * @param sex
     * @param pwd
     * @param birthday
     * @param face
     * @return
     */
    @Update("update T_CUSTOMER set NAME= #{0} , SEX = #{2} , PWD = #{3} , BIRTHDAY = #{4} , FACE = #{5} where PHONE = #{1}")
    boolean update(String name, String phone,String sex,String pwd,String birthday,String face);
}
