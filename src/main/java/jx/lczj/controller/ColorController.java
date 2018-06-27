package jx.lczj.controller;


import jx.lczj.anotation.Privilege;
import jx.lczj.model.T_color;
import jx.lczj.service.ColorService;
import org.opencv.core.Core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("color")
public class ColorController {
    @Autowired
    ColorService colorService;

    @Privilege(value = "颜色管理")
    @RequestMapping("/addColor")
    @ResponseBody
    public  boolean addColor(MultipartFile file, HttpServletRequest request){

        return colorService.add(file,request);

    }

    @RequestMapping("/list")
    @ResponseBody
    public List<T_color>loadColor(){
        return colorService.loadColors();
    }

    @Privilege(value = "颜色管理")
    @RequestMapping("/delete")
    @ResponseBody
    public boolean deleteColor(int color,HttpServletRequest request){
        System.out.println(color);
        return colorService.deleteColor(color, request);
    }


    @RequestMapping("/loadByColor")
    @ResponseBody
    public T_color loadByColor(String color){
        return colorService.loadByColor(Integer.parseInt(color));
    }


    /**
     * 编辑更新
     */
    @Privilege(value = "颜色管理")
    @RequestMapping("/update")
    @ResponseBody
    public boolean updateColor(MultipartFile file,HttpServletRequest request) {
        return colorService.update(file, request);

    }
}
