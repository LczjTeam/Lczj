package jx.lczj.model;

/**
 * Created by WuLinZhi on 2018-07-09.
 */
public class T_orderdetail {
    private String order; //订单号
    private String mywear;//我的试戴
    private int goodsstar;//商品星级
    private int servicestar;//服务星级
    private int expressstar;//快递星级
    private String comments;//评语

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getMywear() {
        return mywear;
    }

    public void setMywear(String mywear) {
        this.mywear = mywear;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "T_orderdetail{" +
                "order='" + order + '\'' +
                ", mywear='" + mywear + '\'' +
                ", goodsstar=" + goodsstar +
                ", servicestar=" + servicestar +
                ", expressstar=" + expressstar +
                ", comments='" + comments + '\'' +
                '}';
    }
}
