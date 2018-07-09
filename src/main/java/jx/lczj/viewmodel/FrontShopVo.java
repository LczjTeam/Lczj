package jx.lczj.viewmodel;

import java.util.List;

/**
 * 前端商品
 */
public class FrontShopVo {
    private List<T_goodsHot> t_goodsHots ;

    public List<T_goodsHot> getT_goodsHots() {
        return t_goodsHots;
    }

    public void setT_goodsHots(List<T_goodsHot> t_goodsHots) {
        this.t_goodsHots = t_goodsHots;
    }

    @Override
    public String toString() {
        return "FrontShopVo{" +
                "t_goodsHots=" + t_goodsHots +
                '}';
    }
}
