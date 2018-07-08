package jx.lczj.dao;

import jx.lczj.model.T_address;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * created by wzj on 2018/7/8
 */
public interface AddressDao {

    /**
     * 加载
     * @return
     */
    @Select("select * from T_ADDRESS")
    public List<T_address> loadlist();

    /**
     * 添加镜片信息
     * @param address
     * @param customer
     * @param consignee
     * @param phone
     * @param street
     * @param provincename
     * @param cityname
     * @param countyname
     * @param isdefault
     * @return
     */
    @Insert("insert into T_ADDRESS(ADDRESS,CUSTOMER,CONSIGNEE,PHONE,STREET,PROVINCENAME,CITYNAME,COUNTYNAME,ISDEFAULT) VALUES(#{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7},#{8})")
    boolean add(String address, String customer, String consignee,String phone,
                String street, String provincename,String cityname,String countyname,char isdefault);

    /**
     * 删除
     * @param address
     * @return
     */
    @Delete("delete from T_ADDRESS WHERE ADDRESS = #{0}")
    boolean delete(String address);

    /**
     * 更新
     * @param address
     * @param customer
     * @param consignee
     * @param phone
     * @param street
     * @param provincename
     * @param cityname
     * @param countyname
     * @param isdefault
     * @return
     */
    @Update("update T_ADDRESS set customer=#{1},consignee = #{2},phone=#{3},street=#{4},provincename=#{5},cityname=#{6},countyname=#{7},isdefault=#{8} where address = #{0}")
    boolean update(String address, String customer, String consignee, String phone, String street, String provincename, String cityname, String countyname, char isdefault);


}
