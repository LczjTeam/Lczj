package jx.lczj.controller;

import jx.lczj.model.T_admin;
import jx.lczj.model.T_occasion;
import jx.lczj.service.OccasionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
     */
 /*   @RequestMapping("/add")
    @ResponseBody
    public boolean add(int occasion,String name,String photo){
        return occasionService.add(occasion,name,photo);
    }*/
    @RequestMapping("/addoccasion")
    @ResponseBody
    public  boolean add(MultipartFile file1,MultipartFile file2, HttpServletRequest request){

        return occasionService.add(file1,file2,request);

    }


    /**
     * 更新
     */

   @RequestMapping("/update")
    @ResponseBody
    public boolean update(MultipartFile file1,MultipartFile file2, HttpServletRequest request){
        return occasionService.update(file1,file2,request);
    }



    /**
     * 删除
     * @param occasion
     * @param request
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(int occasion,HttpServletRequest request){
        System.out.println(occasion);
        return occasionService.delete(occasion, request);
    }

/*
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(String occasion){
        return occasionService.delete(occasion);
    }
*/



}
