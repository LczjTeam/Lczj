package jx.lczj.controller;

import jx.lczj.model.T_admin;
import jx.lczj.model.T_occasion;
import jx.lczj.service.OccasionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@RequestMapping("occasion")
public class OccasionController {

        @Autowired
        OccasionService occasionService;

        /**
         * 场景信息
         */
        @RequestMapping("/list")
        @ResponseBody
        public List<T_occasion> list(){
            return occasionService.loadList();
        }


    /**
     * 添加
     * @param occasion
     * @param name
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(String occasion,String name){
        return occasionService.add(occasion,name);
    }

    /**
     * 更新
     * @param occasion
     * @param name
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(String occasion,String name){
        return occasionService.update(occasion,name);
    }


    /**
     * 删除
     * @param occasion
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(String occasion){
        return occasionService.delete(occasion);
    }



}
