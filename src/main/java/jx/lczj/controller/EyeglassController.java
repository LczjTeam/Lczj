package jx.lczj.controller;

import jx.lczj.anotation.Privilege;
import jx.lczj.service.EyeglassService;
import jx.lczj.viewmodel.EyeglassVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("eyeglass")
public class EyeglassController {

    @Autowired
    EyeglassService eyeglassService;

    /**
     * 获取所有镜片信息
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<EyeglassVo> list(){return eyeglassService.loadlist();};

    /**
     * 添加
     * @param fileName
     * @param request
     * @param session
     * @return
     */
    @Privilege("镜片管理")
    @RequestMapping("/add")
    @ResponseBody
    public EyeglassVo add(String[] fileName, HttpServletRequest request, HttpSession session, MultipartFile detailfile){return eyeglassService.add(fileName,request,session,detailfile);}

    /**
     * 通过编号获取镜片信息
     * @param code
     * @return
     */
    @RequestMapping("/loadById")
    @ResponseBody
    public EyeglassVo loadById(String code){return eyeglassService.loadById(code);};

    /**
     * 编辑
     * @param fileName
     * @param request
     * @param session
     * @return
     */
    @Privilege("镜片管理")
    @RequestMapping("/update")
    @ResponseBody
    public EyeglassVo update(String[] fileName, HttpServletRequest request, HttpSession session, MultipartFile detailfile ){return eyeglassService.update(fileName,request,session,detailfile);};


    /**
     * 删除附件
     * @param code
     * @param request
     * @return
     */
    @Privilege("镜片管理")
    @RequestMapping("/deleteAttach")
    @ResponseBody
    public boolean deleteAttach(String code,HttpServletRequest request){return eyeglassService.deleteAttach(code,request);};


    /**
     * 删除
     * @param code
     * @param request
     * @return
     */
    @Privilege("镜片管理")
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(String code,HttpServletRequest request){return eyeglassService.delete(code,request);};

    /**
     * 根据品牌、功能、价格对镜片进行筛选
     * @param brand
     * @param efficacy
     * @param low
     * @param high
     * @return
     */
    @RequestMapping("/eyeglasslist")
    @ResponseBody
    public List<EyeglassVo> eyeglasslist(String[] brand,String efficacy,String low,String high){
        int[] brands = new int[brand.length];
        for(int i=0;i<brands.length;i++){
            brands[i] = Integer.parseInt(brand[i]);
        }
        int efficacys = Integer.parseInt(efficacy);
        int lows = Integer.parseInt(low);
        int highs = Integer.parseInt(high);
        System.out.println(brand.length+efficacy+low+high);
        return eyeglassService.eyeglasslist(brands,efficacys,lows,highs);};

}
