package jx.lczj.viewmodel;

import jx.lczj.model.*;

/**
 * Created by WuLinZhi on 2018-07-15.
 */
public class OrderVo {
    T_order t_order; //订单
    T_address t_address; //收货地址
    T_orderdetail t_orderdetail; //订单明细
    T_mywear t_mywear; //我的试戴
    T_wearglass t_wearglass_l; //双眼镜片_左
    T_wearglass t_wearglass_r; //双眼镜片_右
    T_eyeglass t_eyeglass_l; //镜片信息_左
    T_eyeglass t_eyeglass_r; //镜片信息_右
    T_goods t_goods; //镜框信息

    public T_order getT_order() {
        return t_order;
    }

    public void setT_order(T_order t_order) {
        this.t_order = t_order;
    }

    public T_address getT_address() {
        return t_address;
    }

    public void setT_address(T_address t_address) {
        this.t_address = t_address;
    }

    public T_orderdetail getT_orderdetail() {
        return t_orderdetail;
    }

    public void setT_orderdetail(T_orderdetail t_orderdetail) {
        this.t_orderdetail = t_orderdetail;
    }

    public T_mywear getT_mywear() {
        return t_mywear;
    }

    public void setT_mywear(T_mywear t_mywear) {
        this.t_mywear = t_mywear;
    }

    public T_wearglass getT_wearglass_l() {
        return t_wearglass_l;
    }

    public void setT_wearglass_l(T_wearglass t_wearglass_l) {
        this.t_wearglass_l = t_wearglass_l;
    }

    public T_wearglass getT_wearglass_r() {
        return t_wearglass_r;
    }

    public void setT_wearglass_r(T_wearglass t_wearglass_r) {
        this.t_wearglass_r = t_wearglass_r;
    }

    public T_eyeglass getT_eyeglass_l() {
        return t_eyeglass_l;
    }

    public void setT_eyeglass_l(T_eyeglass t_eyeglass_l) {
        this.t_eyeglass_l = t_eyeglass_l;
    }

    public T_eyeglass getT_eyeglass_r() {
        return t_eyeglass_r;
    }

    public void setT_eyeglass_r(T_eyeglass t_eyeglass_r) {
        this.t_eyeglass_r = t_eyeglass_r;
    }

    public T_goods getT_goods() {
        return t_goods;
    }

    public void setT_goods(T_goods t_goods) {
        this.t_goods = t_goods;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "t_order=" + t_order +
                ", t_address=" + t_address +
                ", t_orderdetail=" + t_orderdetail +
                ", t_mywear=" + t_mywear +
                ", t_wearglass_l=" + t_wearglass_l +
                ", t_wearglass_r=" + t_wearglass_r +
                ", t_eyeglass_l=" + t_eyeglass_l +
                ", t_eyeglass_r=" + t_eyeglass_r +
                ", t_goods=" + t_goods +
                '}';
    }
}
