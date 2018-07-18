package jx.lczj.dao;

import jx.lczj.model.T_order;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by WuLinZhi on 2018-07-10.
 */
public interface OrderDao {

    /**
     * 更新商品星级，服务星级，快递星级，评语
     * @param order
     * @param goodsstar
     * @param servicestar
     * @param expressstar
     * @param comments
     * @return
     */
    @Update("update T_order set goodsstar=#{1},servicestar=#{2},expressstar=#{3},comments=#{4} where \"order\" =#{0}")
    boolean update1(String order, int goodsstar, int servicestar, int expressstar, String comments);

    /**
     * 更新快递名，运单号
     * @param order
     * @param express
     * @param expressid
     * @return
     */
    @Update("update T_order set express=#{1},expressid=#{2} where \"order\" =#{0}")
    boolean update2(String order, String express, String expressid);
}
