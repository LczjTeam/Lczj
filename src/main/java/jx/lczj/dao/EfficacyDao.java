package jx.lczj.dao;

import jx.lczj.model.T_efficacy;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by 14260 on 2018/6/10.
 */


public interface EfficacyDao {
    /**
     * 加载功能信息
     * @return
     */
    @Select("select * from T_efficacy")
    List<T_efficacy> loadList();

    /**
     * 添加功能信息
     * @param efficacy
     * @param name
     * @return
     */
    @Insert("insert into T_efficacy values(#{0},#{1})")
    boolean add(int efficacy, String name);

    /**
     * 更新功能信息
     * @param efficacy
     * @param name
     * @return
     */
    @Update("update T_efficacy set name=#{1} where efficacy=#{0}")
    boolean update(int efficacy, String name);

    /**
     * 删除功能信息
     * @param efficacy
     */
    @Delete("delete from T_efficacy where efficacy=#{0}")
    void delete(int efficacy);

    /**
     * 根据Id获取功能信息
     * @param efficacy
     * @return
     */
    @Select("select * from T_efficacy where efficacy=#{0}")
    T_efficacy loadById(int efficacy);

}
