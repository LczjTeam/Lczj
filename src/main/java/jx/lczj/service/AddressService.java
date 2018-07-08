package jx.lczj.service;

import jx.lczj.dao.AddressDao;
import jx.lczj.model.T_address;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.Address;
import java.util.List;


/**
 * created by wzj on 2018/7/8
 */
@Service
public class AddressService {

    @Resource
    AddressDao addressDao;

    /**
     * 添加信息
     * @param address
     * @param customer
     * @param consignee
     * @param phone
     * @param street
     * @param provincename
     * @param cityname
     * @param countyname
     * @param isdefault
     * @return
     */
    public boolean add(String address, String customer, String consignee,String phone,
                       String street, String provincename,String cityname,String countyname,
                       char isdefault){
        return addressDao.add(address,customer,consignee,phone,street,provincename,cityname,countyname,isdefault);
    }

    /**
     * 删除
     * @param address
     * @return
     */
    public boolean delete(String address) {
        return addressDao.delete(address);
    }

    /**
     * 更新
     * @param address
     * @param customer
     * @param consignee
     * @param phone
     * @param street
     * @param provincename
     * @param cityname
     * @param countyname
     * @param isdefault
     * @return
     */
    public boolean update(String address, String customer, String consignee, String phone, String street, String provincename, String cityname, String countyname, char isdefault) {
        return addressDao.update(address,customer,consignee,phone,street,provincename,cityname,countyname,isdefault);
    }


    /**
     * 加载
     * @return
     */
    public List<T_address> loadlist() {
        return addressDao.loadlist();
    }
}
