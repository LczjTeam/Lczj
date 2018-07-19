package jx.lczj.controller;

import jx.lczj.utils.ExpressUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 14260 on 2018/7/19.
 */

@Controller
@RequestMapping("express")
public class ExpressController {

   /**
    * 快点查询
    * @param expCode  快递公司编号
    * @param expNo    快递单号
    * @return
    */
    @RequestMapping("/loadByInfo")
    public String loadByInfo(String expCode,String expNo){
        return   ExpressUtil.getOrderTracesByJson(expCode,expNo);
    }

}
