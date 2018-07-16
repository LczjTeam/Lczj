package jx.lczj.service;

import jx.lczj.dao.NewCustomerDao;
import jx.lczj.model.T_customer;
import jx.lczj.model.T_newcustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class NewCusServices {

    @Autowired
    NewCustomerDao newCustomerDao;


//    private String newcustomer;     //新用户
//    private String phone;           //电话
//    private String ins_time;        //推荐时间
//    private int status;             //状态
//    private int prize;              //价格

    public boolean add(String phone) {

        try {
            boolean ok=false;
            T_newcustomer t_newcustomer = newCustomerDao.loadByPhone(phone);
            if (t_newcustomer == null) {

                String newcustomer = UUID.randomUUID().toString().replace("-", "");
                ok = newCustomerDao.add(newcustomer,phone,new Date(),0,10);

            }
            return ok;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean update(String phone, int status) {
        return newCustomerDao.update(phone,status);
    }
}
