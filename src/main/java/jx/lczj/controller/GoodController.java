package jx.lczj.controller;

import jx.lczj.model.T_goods;
import jx.lczj.service.GoodService;
import jx.lczj.viewmodel.GoodsVo;
import jx.lczj.viewmodel.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("good")
public class GoodController {

    @Autowired
    GoodService goodService;

    /**
     * 商品信息
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<GoodsVo> list(){
        return goodService.loadlist();
    }

    @RequestMapping("/add")
    @ResponseBody
    public GoodsVo add(String[] fileName, HttpServletRequest request, HttpSession session){


        return goodService.add(fileName,request,session);
    }

    /**
     * 上传文件
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String  upload(HttpServletRequest request) {

        return  goodService.upload(request);

    }



    /**
     * 上传文件
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteAttach")
    @ResponseBody
    public boolean  deleteAttach(String code,HttpServletRequest request) {

        return  goodService.deleteAttach(code,request);

    }
}
