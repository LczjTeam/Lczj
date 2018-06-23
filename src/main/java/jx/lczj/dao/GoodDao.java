package jx.lczj.dao;

import jx.lczj.model.T_goods;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodDao {

    @Select("select * from T_goods")
    List<T_goods> loadlist();

}
