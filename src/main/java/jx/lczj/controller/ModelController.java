package jx.lczj.controller;


import jx.lczj.model.T_model;
import jx.lczj.service.ModelService;
import jx.lczj.viewmodel.ModelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("model")
public class ModelController {
    @Autowired
    ModelService modelService;

    @RequestMapping("/add")
    @ResponseBody
    public  ModelVo addModel(String model,String name,String sex,String face,String age,MultipartFile file, HttpServletRequest request){

        System.out.println(" model:"+ model+" name:"+ name+" sex:"+ sex+" face:"+ face+" age:"+ age);

        return modelService.add(model,name,sex,face,age,file, request);

    }

    @RequestMapping("/list")
    @ResponseBody
    public List<ModelVo>loadModel(){
        return modelService.loadModels();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public boolean deleteModel(int model,HttpServletRequest request){
        System.out.println(model);
        return modelService.delete(model, request);
    }


    @RequestMapping("/loadByModel")
    @ResponseBody
    public T_model loadByModel(String model){
        return modelService.loadByModel(Integer.parseInt(model));
    }

    /*
     * 编辑更新
     */
    @RequestMapping("/update")
    @ResponseBody
    public  ModelVo update(String model,String name,String sex,String face,String age,MultipartFile file, HttpServletRequest request){
        System.out.println(" model:"+ model+" name:"+ name+" sex:"+ sex+" face:"+ face+" age:"+ age);
        return  modelService.update(model,name,sex,face,age,file, request);

    }
}
