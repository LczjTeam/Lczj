package jx.lczj.controller;

import jx.lczj.model.T_mywear;
import jx.lczj.service.AddressService;
import jx.lczj.service.MywearService;
import jx.lczj.viewmodel.ModelVo;
import jx.lczj.viewmodel.MywearVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(HttpServletRequest request) {
        return  mywearService.delete(request);
    }


    @RequestMapping("/listByCustomer")
    @ResponseBody
    public List<MywearVo> listByCustomer(HttpServletRequest request) {
        return  mywearService.listByCustomer(request);
    }



}
