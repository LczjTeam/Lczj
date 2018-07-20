package jx.lczj.service;

import jx.lczj.dao.ShopsDao;
import jx.lczj.model.T_shops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopsServices {

    @Autowired
    ShopsDao shopsDao;

    public List<T_shops> list() {
        return shopsDao.list();
    }

    public boolean add(String shop, String name, String address, String phone, float pos_x, float pos_y, int orders) {
        return shopsDao.add(shop,name,address,phone,pos_x,pos_y,orders);
    }

    public boolean update(String shop, String name, String address, String phone, float pos_x, float pos_y, int orders) {
        return shopsDao.update(shop,name,address,phone,pos_x,pos_y,orders);
    }

    public boolean delete(String shop) {
        return shopsDao.delete(shop);
    }

    public T_shops loadById(String shop) {
        return shopsDao.loadById(shop);
    }
}
