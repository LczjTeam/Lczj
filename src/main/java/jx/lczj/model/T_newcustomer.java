package jx.lczj.model;

import java.io.Serializable;
import java.util.Date;

public class T_newcustomer implements Serializable{
    private String newcustomer;     //新用户
    private String phone;           //电话
    private Date ins_time;        //推荐时间
    private int status;             //状态
    private int prize;              //价格

    public String getNewcustomer() {
        return newcustomer;
    }

    public void setNewcustomer(String newcustomer) {
        this.newcustomer = newcustomer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    @Override
    public String toString() {
        return "T_newcustomer{" +
                "newcustomer='" + newcustomer + '\'' +
                ", phone='" + phone + '\'' +
                ", ins_time=" + ins_time +
                ", status=" + status +
                ", prize=" + prize +
                '}';
    }
}
