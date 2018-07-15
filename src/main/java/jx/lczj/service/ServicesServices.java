package jx.lczj.service;

import jx.lczj.dao.ServicesDao;
import jx.lczj.model.T_services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesServices {

    @Autowired
    ServicesDao servicesDao;

    public List<T_services> list() {
        return servicesDao.list();
    }

    public boolean add(String weixin, String company) {
        return servicesDao.add(weixin,company);
    }

    public boolean update(String weixin, String company) {
        return servicesDao.update(weixin,company);
    }

    public boolean delete(String weixin) {
        return servicesDao.delete(weixin);
    }
}
