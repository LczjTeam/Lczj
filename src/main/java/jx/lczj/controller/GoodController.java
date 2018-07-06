package jx.lczj.controller;

import jx.lczj.anotation.Privilege;
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
     * 获取所有商品信息
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<GoodsVo> list(){
        return goodService.loadlist();
    }

    /**
     * 添加
     * @param fileName
     * @param request
     * @param session
     * @return
     */
    @Privilege("眼镜管理")
    @RequestMapping("/add")
    @ResponseBody
    public GoodsVo add(String[] fileName, HttpServletRequest request, HttpSession session){


        return goodService.add(fileName,request,session);
    }
    /**
     * 编辑
     * @param fileName
     * @param request
     * @param session
     * @return
     */
    @Privilege("眼镜管理")
    @RequestMapping("/update")
    @ResponseBody
    public GoodsVo update(String[] fileName, HttpServletRequest request, HttpSession session){


        return goodService.update(fileName,request,session);
    }

    /**
     * 上传文件
     * @param request
     * @return
     */
    @Privilege("眼镜管理")
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String  upload(HttpServletRequest request) {

        return  goodService.upload(request);

    }



    /**
     * 删除附件
     * @param request
     * @return
     */
    @Privilege("眼镜管理")
    @RequestMapping(value = "/deleteAttach")
    @ResponseBody
    public boolean  deleteAttach(String code,HttpServletRequest request) {

        return  goodService.deleteAttach(code,request);

    }

    /**
     * 删除
     * @param request
     * @return
     */
    @Privilege("眼镜管理")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public boolean  delete(String code,HttpServletRequest request) {

        return  goodService.delete(code,request);

    }

    /**
     * 通过编号获取镜框信息
     * @param code
     * @return
     */
    @RequestMapping(value = "/loadById")
    @ResponseBody
    public GoodsVo  loadById(String code) {
        return  goodService.loadById(code);

    }
}
