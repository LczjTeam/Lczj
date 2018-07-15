package jx.lczj.viewmodel;

import jx.lczj.model.T_orderdetail;

import java.util.List;

/**
 * Created by 14260 on 2018/7/15.
 */
public class OrderdetailsVo {

    private T_orderdetail t_orderdetail;
    private MywearVo mywearVo;


    @Override
    public String toString() {
        return "OrderdetailsVo{" +
                "t_orderdetail=" + t_orderdetail +
                ", mywearVo=" + mywearVo +
                '}';
    }

    public T_orderdetail getT_orderdetail() {
        return t_orderdetail;
    }

    public void setT_orderdetail(T_orderdetail t_orderdetail) {
        this.t_orderdetail = t_orderdetail;
    }

    public MywearVo getMywearVo() {
        return mywearVo;
    }

    public void setMywearVo(MywearVo mywearVo) {
        this.mywearVo = mywearVo;
    }
}
