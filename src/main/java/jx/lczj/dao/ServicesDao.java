package jx.lczj.dao;

import jx.lczj.model.T_services;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ServicesDao {

    @Select("select * from T_services")
    List<T_services> list();

    @Insert("insert into T_services(weixin,company) values(#{0},#{1})")
    boolean add(String weixin, String company);

    @Update("update T_services set company=#{1} where weixin=#{0}")
    boolean update(String weixin, String company);

    @Delete("delete from T_services where weixin=#{0}")
    boolean delete(String weixin);
}
