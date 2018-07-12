package jx.lczj.dao;

import jx.lczj.model.T_order;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by WuLinZhi on 2018-07-10.
 */
public interface OrderDao {
    @Select("select * from T_ORDER")
    List<T_order> queryAll();
}
