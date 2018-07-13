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
import javax.servlet.http.HttpServletRequest;
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
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(HttpServletRequest request){
        //System.out.println(provincename);
        return addressService.add(request);
    }

    /**
     * 删除信息
     * @param address
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(String address){
        return addressService.delete(address);
    }

    /**
     * 更新
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(HttpServletRequest request){
        return addressService.update(request);
    }

    /**
     * 加载
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<T_address> listByCustomer(String customer){return addressService.listByCustomer(customer);}

    /**
     * 默认修改
     */
    @RequestMapping("/updateDefault")
    @ResponseBody
    public boolean updateDefault(String address,String customer){
        return addressService.updateDefault(address,customer);
    }

    /**
     * 根据id加载信息
     */
    @RequestMapping("/loadByAddress")
    @ResponseBody
    public T_address loadByAddress(String address){
        return addressService.loadByAddress(address);
    }
}
