package jx.lczj.model;

import java.sql.Date;

/**
 * Created by WuLinZhi on 2018-07-09.
 */
public class T_order {
    private String order; //订单号
    private String customer;//收货人
    private String address; //收货地址
    private Date sure_time; //下单时间
    private int state; //订单状态
    private String express;//快递名
    private String expressid;//运单号

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getSure_time() {
        return sure_time;
    }

    public void setSure_time(Date sure_time) {
        this.sure_time = sure_time;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getExpressid() {
        return expressid;
    }

    public void setExpressid(String expressid) {
        this.expressid = expressid;
    }

    @Override
    public String toString() {
        return "T_order{" +
                "order='" + order + '\'' +
                ", customer='" + customer + '\'' +
                ", address='" + address + '\'' +
                ", sure_time=" + sure_time +
                ", state=" + state +
                '}';
    }
}
