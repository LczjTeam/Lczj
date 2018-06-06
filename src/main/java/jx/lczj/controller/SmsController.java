package jx.lczj.controller;

import jx.lczj.utils.SmsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by root on 2018-05-14.
 */

@Controller
@RequestMapping("sms")
public class SmsController {

    @RequestMapping(value = "/icode")
    @ResponseBody
    public String upload(String phone) {
        return SmsUtil.smsSend(phone);
    }


}
