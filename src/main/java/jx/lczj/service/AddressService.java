package jx.lczj.service;

import jx.lczj.dao.AddressDao;
import jx.lczj.model.T_address;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.Address;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;


/**
 * created by wzj on 2018/7/8
 */
@Service
public class AddressService {

    @Resource
    AddressDao addressDao ;

    /**
     * 添加信息
     * @return
     */
    @Transactional
    public boolean add(HttpServletRequest request){
        /*String address, String customer, String consignee,String phone,
            String street, String provincename,String cityname,String countyname,
            String isdefault*/
        try {
            String address = UUID.randomUUID().toString().replace("-", "").substring(3, 6) + "" + System.currentTimeMillis();


            System.out.println("address:" + address);
            String customer = request.getParameter("customer");
            System.out.println("custonmer:" + customer);
            String consignee = request.getParameter("consignee");
            System.out.println("consignee:" + consignee);
            String phone = request.getParameter("phone");
            System.out.println("phone:" + phone);
            String street = request.getParameter("street");
            System.out.println("street:" + street);
            String provincename = request.getParameter("provincename");
            System.out.println("provincename:" + provincename);
            String cityname = request.getParameter("cityname");
            System.out.println("cityname:" + cityname);
            String countyname = request.getParameter("countyname");
            System.out.println("countyname:" + countyname);
            String isdefault = request.getParameter("isdefault");
            System.out.println("isdefault:" + isdefault);

            return addressDao.add(address, customer, consignee, phone, street, provincename, cityname, countyname, isdefault);
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
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
     * @return
     */
    @Transactional
    public boolean update(HttpServletRequest request) {
        try {

            String address = request.getParameter("address");
            System.out.println("address:" + address);
            String customer = request.getParameter("customer");
            System.out.println("custonmer:" + customer);
            String consignee = request.getParameter("consignee");
            System.out.println("consignee:" + consignee);
            String phone = request.getParameter("phone");
            System.out.println("phone:" + phone);
            String street = request.getParameter("street");
            System.out.println("street:" + street);
            String provincename = request.getParameter("provincename");
            System.out.println("provincename:" + provincename);
            String cityname = request.getParameter("cityname");
            System.out.println("cityname:" + cityname);
            String countyname = request.getParameter("countyname");
            System.out.println("countyname:" + countyname);
            String isdefault = request.getParameter("isdefault");
            System.out.println("isdefault:" + isdefault);

            return addressDao.update(address, customer, consignee, phone, street, provincename, cityname, countyname, isdefault);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 加载
     * @return
     */
    public List<T_address> listByCustomer(String customer) {
        return addressDao.loadlist(customer);
    }

    /**
     * 默认修改
     * @param address
     * @return
     */
    @Transactional
    public boolean updateDefault(String address,String customer) {

        System.out.println("address的值："+address);
        System.out.println("customer的值："+customer);
        try {
            addressDao.updateDefault0(customer);        //将所有记录默认值置零
            addressDao.updateDefault1(address); //将选中记录默认值置一
            return  true;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public T_address loadByAddress(String address) {
        System.out.println("address:"+address);
        return addressDao.loadByAddress(address);
    }

    @Transactional
    public List<T_address> loadDefault(String customer) {
        try {
             return  addressDao.loadDefault(customer);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
