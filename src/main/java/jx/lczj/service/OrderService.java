package jx.lczj.service;

import jx.lczj.dao.*;
import jx.lczj.model.T_mywear;
import jx.lczj.model.T_order;
import jx.lczj.model.T_orderdetail;
import jx.lczj.model.T_wearglass;
import jx.lczj.viewmodel.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WuLinZhi on 2018-07-10.
 */
@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderdetailDao orderdetailDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private MywearDao mywearDao;
    @Autowired
    private GoodDao goodDao;
    @Autowired
    private EyeglassDao eyeglassDao;

    public List<OrderVo> loadOrders() {
        List<T_orderdetail> orderList = orderdetailDao.queryAll();
        if(orderList==null || orderList.isEmpty()){
            return null;
        }
        List<OrderVo> orderVoList = new ArrayList<OrderVo>();
        for (T_orderdetail orderDetail:orderList) {
            OrderVo orderVo = new OrderVo();

            orderVo.setT_orderdetail(orderDetail);
            T_order order = orderDao.loadByOrder(orderDetail.getOrder());
            orderVo.setT_order(order);
            orderVo.setT_address(addressDao.loadByAddress(order.getAddress()));
            T_mywear mywear = mywearDao.loadById(orderDetail.getMywear());
            orderVo.setT_mywear(mywear);
            orderVo.setT_goods(goodDao.loadById(mywear.getGoods()));
            T_wearglass twl = mywearDao.loadWearglassByMywear(mywear.getMywear(),"l");
            orderVo.setT_wearglass_l(twl);
            orderVo.setT_eyeglass_l(eyeglassDao.loadById(twl.getEyeglass()));
            T_wearglass twr = mywearDao.loadWearglassByMywear(mywear.getMywear(),"r");
            orderVo.setT_wearglass_r(twr);
            orderVo.setT_eyeglass_r(eyeglassDao.loadById(twr.getEyeglass()));
            orderVoList.add(orderVo);
        }
        return orderVoList;
    }
}
