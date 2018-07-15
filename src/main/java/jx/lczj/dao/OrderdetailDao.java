package jx.lczj.dao;

import jx.lczj.model.T_orderdetail;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by WuLinZhi on 2018-07-15.
 */
public interface OrderdetailDao {

    @Select("select * from T_ORDERDETAIL")
    List<T_orderdetail> queryAll();
}
