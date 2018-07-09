package jx.lczj.controller;

import jx.lczj.model.T_mywear;
import jx.lczj.service.AddressService;
import jx.lczj.service.MywearService;
import jx.lczj.viewmodel.ModelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 14260 on 2018/7/9.
 */


@Controller
@RequestMapping("mywear")
public class MywearController {


    @Autowired
    MywearService mywearService;

    @RequestMapping("/add")
    @ResponseBody
    public boolean add(HttpServletRequest request) {
        return  mywearService.add(request);
    }





}
