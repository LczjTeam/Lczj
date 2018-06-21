package jx.lczj.controller;


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

    @RequestMapping("/addColor")
    @ResponseBody
    public  boolean addColor(MultipartFile file, HttpServletRequest request){

        String ctimes = System.currentTimeMillis()+"";
        System.out.println("开始");
        String path = request.getSession().getServletContext().getRealPath("colors");

        String fileName = ctimes+".jpg";
        System.out.println(fileName);
        System.out.println(path);
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        String rgb = fileName;
        System.out.println(Integer.parseInt(request.getParameter("color")) + request.getParameter("color_name") + rgb );

        return colorService.addColor(Integer.parseInt(request.getParameter("color")) ,request.getParameter("color_name") ,rgb);
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<T_color>loadColor(){
        return colorService.loadColors();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public boolean deleteColor(int color,HttpServletRequest request){
        System.out.println(color);
        return colorService.deleteColor(color, request);
    }

    @RequestMapping("/loadByColor")
    @ResponseBody
    public boolean loadByColor(int color){return colorService.loadByColor(color);}

    /**
     * 编辑
     */
    @RequestMapping("/loadColor")
    @ResponseBody
    public T_color loadColor(int color){ return colorService.loadColor(color); }
    /**
     * 编辑更新
     */
    @RequestMapping("/updata")
    @ResponseBody
    public boolean updataColor(MultipartFile colorFile,HttpServletRequest request){

        System.out.println("开始");
        String path = request.getSession().getServletContext().getRealPath("colors");
        //先吧颜色查询出来
        T_color t_color = colorService.loadColor(Integer.parseInt(request.getParameter("color_edit")));
        String rgb = t_color.getRgb();
        //删除对应的图片
        String d_rgb = path + "/" + rgb;
        System.out.println(d_rgb);
        File targetFile = new File(d_rgb);
        if (targetFile.exists()) {
            targetFile.delete();
        }

        String ctimes = System.currentTimeMillis()+"";
        System.out.println("开始，保存");
        String fileName = ctimes+".jpg";
        System.out.println(fileName);
        System.out.println(path);
        File editFile = new File(path, fileName);
        if(!editFile.exists()){
            editFile.mkdirs();
        }

        //保存
        try {
            colorFile.transferTo(editFile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        String editrgb = fileName;
        System.out.println(Integer.parseInt(request.getParameter("color_edit")) + request.getParameter("name_edit") + editrgb );

        return colorService.updataColor(Integer.parseInt(request.getParameter("color_edit")) ,request.getParameter("name_edit") ,editrgb);}

}
