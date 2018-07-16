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
    private int goodsstar;//商品星级
    private int servicestar;//服务星级
    private int expressstar;//快递星级
    private int voucher;//优惠券
    private String comments;//评语
    private String express;//快递名
    private String expressid;//运单号

    @Override
    public String toString() {
        return "T_order{" +
                "order='" + order + '\'' +
                ", customer='" + customer + '\'' +
                ", address='" + address + '\'' +
                ", sure_time=" + sure_time +
                ", state=" + state +
                ", goodsstar=" + goodsstar +
                ", servicestar=" + servicestar +
                ", expressstar=" + expressstar +
                ", voucher=" + voucher +
                ", comments='" + comments + '\'' +
                ", express='" + express + '\'' +
                ", expressid='" + expressid + '\'' +
                '}';
    }

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


    public int getGoodsstar() {
        return goodsstar;
    }

    public void setGoodsstar(int goodsstar) {
        this.goodsstar = goodsstar;
    }

    public int getServicestar() {
        return servicestar;
    }

    public void setServicestar(int servicestar) {
        this.servicestar = servicestar;
    }

    public int getExpressstar() {
        return expressstar;
    }

    public void setExpressstar(int expressstar) {
        this.expressstar = expressstar;
    }

    public int getVoucher() {
        return voucher;
    }

    public void setVoucher(int voucher) {
        this.voucher = voucher;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
