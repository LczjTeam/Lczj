package jx.lczj.viewmodel;

import jx.lczj.model.T_address;
import jx.lczj.model.T_customer;
import jx.lczj.model.T_order;

import java.util.List;

/**
 * Created by 14260 on 2018/7/15.
 */
public class OrderCreateVo {

    private T_order t_order;
    private T_address t_address;
    private T_customer t_customer;
    private List<MywearVo> mywearVos;

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

    public T_customer getT_customer() {
        return t_customer;
    }

    public void setT_customer(T_customer t_customer) {
        this.t_customer = t_customer;
    }

    public List<MywearVo> getMywearVos() {
        return mywearVos;
    }

    public void setMywearVos(List<MywearVo> mywearVos) {
        this.mywearVos = mywearVos;
    }

    @Override
    public String toString() {
        return "OrderCreateVo{" +
                "t_order=" + t_order +
                ", t_address=" + t_address +
                ", t_customer=" + t_customer +
                ", mywearVos=" + mywearVos +
                '}';
    }
}
