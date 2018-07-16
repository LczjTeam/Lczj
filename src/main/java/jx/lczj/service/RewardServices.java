package jx.lczj.service;

import jx.lczj.dao.RewardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RewardServices {

    @Autowired
    RewardDao rewardDao;

//    private String customer;        //vip客户名
//    private String newcustomer;     //新用户的id（新用户表中的）
//    private int prize;              //优惠券值
//    private String ins_time;        //推荐期限
//    private int status;             //状态

    public boolean add(String customer, String newcustomer) {
        return rewardDao.add(customer,newcustomer,0,new Date(),10);
    }

    public boolean update(String customer, String newcustomer,int status) {
        return rewardDao.update(customer,newcustomer,status);
    }
}
