package jx.lczj.service;

import jx.lczj.dao.*;
import jx.lczj.model.*;
import jx.lczj.viewmodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    /**
     * 获取全部订单
     * @return
     */
    public List<T_order> list() {
        return orderDao.list();
    }

    /**
     * 通过时间查找
     */
    public List<T_order> seachByTime(String start, String end) throws ParseException {

        System.out.println(start+" 00:00:00"+end+" 23:59:59");

        java.util.Date start_time = new java.util.Date();
        java.util.Date end_time = new java.util.Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try{
            start_time = sdf.parse(start);
            end_time = sdf.parse(end);
        }catch (ParseException e){
            e.printStackTrace();
        }

        int i = start_time.compareTo(end_time);
        if (i > 0){
            System.out.println("start_time:"+start_time);
            System.out.println("end_time:"+end_time);
        }
        List<T_order> t_order = orderDao.seachByTime(start_time,end_time);
        return t_order ;
    }
    //通过订单号查找
    public T_order loadById(String order){
        System.out.println("order:"+order);
        return orderDao.listById(order);
    }

    /**
     *更新总价
     * @return
     */
    public boolean update3(String order, String price) {
    return orderDao.update3(order,price);
    }
}
