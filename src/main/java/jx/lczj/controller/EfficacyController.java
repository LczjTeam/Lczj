package jx.lczj.controller;

import jx.lczj.anotation.Privilege;
import jx.lczj.model.T_efficacy;
import jx.lczj.model.T_efficacy;
import jx.lczj.service.EfficacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 14260 on 2018/6/10.
 */


@Controller
@RequestMapping("efficacy")
public class EfficacyController {

    @Autowired
    EfficacyService efficacyService;

    /**
     * 加载功能管理信息
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<T_efficacy> list(){
        return efficacyService.loadList();
    }


    /**
     * 添加功能管理信息
     * @param efficacy
     * @param name
     * @return
     */
    @Privilege(value = "功能管理")
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(int efficacy,String name){
        return efficacyService.add(efficacy,name);
    }


    /**
     * 更新功能管理信息
     * @param efficacy
     * @param name
     * @return
     */
    @Privilege(value = "功能管理")
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(int efficacy,String name){
        return efficacyService.update(efficacy,name);
    }

    /**
     * 删除功能管理信息
     * @param efficacy
     * @return
     */
    @Privilege(value = "功能管理")
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(int efficacy){
        return efficacyService.delete(efficacy);
    }


}
