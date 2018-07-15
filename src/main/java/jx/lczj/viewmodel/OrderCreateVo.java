package jx.lczj.viewmodel;

import jx.lczj.model.T_address;
import jx.lczj.model.T_order;

import java.util.List;

/**
 * Created by 14260 on 2018/7/15.
 */
public class OrderCreateVo {

    private T_order t_order;
    private T_address t_address;
    private List<MywearVo> mywearVos;

    @Override
    public String toString() {
        return "OrderCreateVo{" +
                "t_order=" + t_order +
                ", t_address=" + t_address +
                ", mywearVos=" + mywearVos +
                '}';
    }

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

    public List<MywearVo> getMywearVos() {
        return mywearVos;
    }

    public void setMywearVos(List<MywearVo> mywearVos) {
        this.mywearVos = mywearVos;
    }
}
