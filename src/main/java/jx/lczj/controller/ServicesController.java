package jx.lczj.controller;

import jx.lczj.model.T_services;
import jx.lczj.service.ServicesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("services")
public class ServicesController {

    @Autowired
    ServicesServices servicesServices;

    /**
     * 获取客服中心
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<T_services> list(){
        return servicesServices.list();
    }

    /**
     * 添加客服
     * @param weixin
     * @param company
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(String weixin,String company){
        return servicesServices.add(weixin,company);
    }

    /**
     * 更新客服
     * @param weixin
     * @param company
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(String weixin,String company){
        return servicesServices.update(weixin,company);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(String weixin){
        return servicesServices.delete(weixin);
    }

}
