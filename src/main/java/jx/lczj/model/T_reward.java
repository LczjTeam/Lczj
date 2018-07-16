package jx.lczj.model;

import java.io.Serializable;
import java.util.Date;

public class T_reward implements Serializable{
    private String customer;        //vip客户名
    private String newcustomer;     //新用户的id（新用户表中的）
    private int prize;              //优惠券值
    private Date ins_time;          //推荐期限
    private int status;             //状态

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getNewcustomer() {
        return newcustomer;
    }

    public void setNewcustomer(String newcustomer) {
        this.newcustomer = newcustomer;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public Date getIns_time() {
        return ins_time;
    }

    public void setIns_time(Date ins_time) {
        this.ins_time = ins_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "T_reward{" +
                "customer='" + customer + '\'' +
                ", newcustomer='" + newcustomer + '\'' +
                ", prize=" + prize +
                ", ins_time=" + ins_time +
                ", status=" + status +
                '}';
    }
}
