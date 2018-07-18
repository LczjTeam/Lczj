package jx.lczj.service;

import jx.lczj.dao.*;
import jx.lczj.model.T_mywear;
import jx.lczj.model.T_order;
import jx.lczj.model.T_orderdetail;
import jx.lczj.model.T_wearglass;
import jx.lczj.viewmodel.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WuLinZhi on 2018-07-10.
 */
@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    /**
     * 更新商品星级，服务星级，快递星级，评语
     * @param order
     * @param goodsstar
     * @param servicestar
     * @param expressstar
     * @param comments
     * @return
     */
    public boolean update1(String order, int goodsstar, int servicestar, int expressstar, String comments) {
        return orderDao.update1(order,goodsstar,servicestar,expressstar,comments);
    }

    /**
     * 更新快递名，运单号
     * @param order
     * @param express
     * @param expressid
     * @return
     */
    public boolean update2(String order, String express, String expressid) {
        return orderDao.update2(order,express,expressid);
    }
}
