package jx.lczj.controller;


import jx.lczj.model.T_agesection;
import jx.lczj.service.AgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 14260 on 2018/6/10.
 */


@Controller
@RequestMapping("age")
public class AgeController {

    @Autowired
    AgeService ageService;

    /**
     * 年龄段信息
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<T_agesection> list(){
        return ageService.loadlist();
    }

    /**
     * 添加年龄段
     * @param agesection
     * @param name
     * @param minage
     * @param maxage
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(int agesection,String name,int minage,int maxage){
        return ageService.add(agesection,name,minage,maxage);
    }

    /**
     * 编辑年龄段
     * @param agesection
     * @param name
     * @param minage
     * @param maxage
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(int agesection,String name,int minage,int maxage){
        return ageService.update(agesection,name,minage,maxage);
    }

    /**
     * 删除年龄段
     * @param agesection
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(int agesection){
        return ageService.delete(agesection);
    }


}
