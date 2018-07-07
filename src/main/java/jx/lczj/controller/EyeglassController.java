package jx.lczj.controller;


import jx.lczj.anotation.Privilege;
import jx.lczj.model.T_eyeglass;
import jx.lczj.service.EyeglassService;
import jx.lczj.viewmodel.EyeglassVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("eyeglass")
public class EyeglassController {

    @Autowired
    EyeglassService eyeglassService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EyeglassVo> list(){return eyeglassService.loadlist();};

    @Privilege("镜片管理")
    @RequestMapping("/add")
    @ResponseBody
    public EyeglassVo add(String[] fileName, HttpServletRequest request, HttpSession session){return eyeglassService.add(fileName,request,session);}

    @RequestMapping("/loadById")
    @ResponseBody
    public EyeglassVo loadById(String code){return eyeglassService.loadById(code);};

    @Privilege("镜片管理")
    @RequestMapping("/update")
    @ResponseBody
    public EyeglassVo update(String[] fileName, HttpServletRequest request, HttpSession session){return eyeglassService.update(fileName,request,session);};

    @Privilege("镜片管理")
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(String code,HttpServletRequest request){return eyeglassService.delete(code,request);};

}
