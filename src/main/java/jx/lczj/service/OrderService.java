package jx.lczj.service;

import jx.lczj.dao.OrderDao;
import jx.lczj.model.T_order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WuLinZhi on 2018-07-10.
 */
@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    public List<T_order> loadOrders() {
        return orderDao.queryAll();
    }
}
