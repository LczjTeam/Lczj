package jx.lczj.controller;

/**
 * Created by Software1 on 2017/12/27.
 */

import jx.lczj.service.FileService;
import jx.lczj.utils.OpenCVUtil;
import jx.lczj.viewmodel.AdminVo;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;

@Controller
@RequestMapping("files")
public class FileController {


    @Autowired
    FileService fileService;
    /**
     * 文件上传
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload(MultipartFile file,String sex,String age,String station,HttpServletRequest request) {

        return fileService.upload(file,sex,age,station,request);
    }


    /**
     * 用户头像
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploaduserhead")
    @ResponseBody
    public boolean userhead(MultipartFile file, HttpSession session, HttpServletRequest request) {

        AdminVo adminVo = (AdminVo)session.getAttribute("admin");
        String  code = adminVo.getT_admin().getAdmin();
        System.out.println(code);

        String path = request.getSession().getServletContext().getRealPath("heads");

        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        System.out.println(path);
        File targetFile = new File(path, code+".png");


        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        if(targetFile.exists()){
            targetFile.delete();
        }

        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }




    @RequestMapping(value = "/wearglasses")
    @ResponseBody
    public String wearGlasses(String url,String glasses,HttpServletRequest request) {

        return fileService.wearGlasses(url,glasses,request);
    }


}
