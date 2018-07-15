package jx.lczj.controller;

import jx.lczj.model.T_shops;
import jx.lczj.service.ShopsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("shops")
public class ShopsController {

    @Autowired
    ShopsServices shopsServices;

    /**
     * 获取门店数据
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<T_shops> list(){
        return shopsServices.list();
    }

    /**
     * 添加门店
     * @param shop
     * @param name
     * @param address
     * @param phone
     * @param pos_x
     * @param pos_y
     * @param orders
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(String shop,String name,String address,String phone,float pos_x,float pos_y,int orders){
        return shopsServices.add(shop,name,address,phone,pos_x,pos_y,orders);
    }

    /**
     * 更新门店数据
     * @param shop
     * @param name
     * @param address
     * @param phone
     * @param pos_x
     * @param pos_y
     * @param orders
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(String shop,String name,String address,String phone,float pos_x,float pos_y,int orders){
        return shopsServices.update(shop,name,address,phone,pos_x,pos_y,orders);
    }

    /**
     * 删除门店信息
     * @param shop
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(String shop){
        return shopsServices.delete(shop);
    }
}
