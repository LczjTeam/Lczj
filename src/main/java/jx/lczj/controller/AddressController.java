package jx.lczj.controller;


import jx.lczj.anotation.Privilege;
import jx.lczj.model.T_address;
import jx.lczj.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.Address;
import java.util.List;

/**
 * created by wzj on 2018/7/8
 */

@Controller
@RequestMapping("address")
public class AddressController {

    @Autowired
    AddressService addressService;

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
    @Privilege(value = "收货地址管理")
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(String address, String customer, String consignee,String phone,
    String street, String provincename,String cityname,String countyname,String isdefault){
        return addressService.add(address,customer,consignee,phone,street,provincename,cityname,countyname,isdefault);
    }

    /**
     * 删除信息
     * @param address
     * @return
     */
    @Privilege("收货地址管理")
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(String address){
        return addressService.delete(address);
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
    @Privilege("收货地址管理")
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(String address, String customer, String consignee,String phone,
                          String street, String provincename,String cityname,String countyname,String isdefault){
        return addressService.update(address,customer,consignee,phone,street,provincename,cityname,countyname,isdefault);
    }

    /**
     * 加载
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<T_address> list(){return addressService.loadlist();}
}
