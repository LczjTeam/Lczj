package jx.lczj.controller;

 import com.alibaba.fastjson.JSON;
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import jx.lczj.service.WxPayService;
 import jx.lczj.utils.AES;
 import jx.lczj.utils.WXPayUtil;
 import jx.lczj.utils.WXUtil;
 import jx.lczj.viewmodel.Result;
 import org.dom4j.DocumentException;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

 import javax.persistence.criteria.Order;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.*;
 import java.security.NoSuchAlgorithmException;
 import java.util.Comparator;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.TreeMap;


/**
 * Created by 14260 on 2018/7/8.
 */

@Controller
@RequestMapping("wx")
public class WXController {


    @Autowired
    WxPayService wxPayService;


    @RequestMapping("/login")
    @ResponseBody
    public JSONObject getOpenid(@RequestParam(value="code",required=false)String code) {//接收用户传过来的code，required=false表明如果这个参数没有传过来也可以。
        return WXUtil.getOpenidAndSessionKey(code);
    }

    @RequestMapping(value = "/decodePhoneNumber")
    @ResponseBody
    public JSONObject decodeUserInfo(@RequestParam(required = true,value = "encryptedData")String encryptedData,
                                     @RequestParam(required = true,value = "iv")String iv,
                                     @RequestParam(required = true,value = "sessionId")String sessionId) {

        System.out.println(encryptedData);
        System.out.println(iv);
        System.out.println(sessionId);

        return  (JSONObject)JSON.parse(AES.wxDecrypt(encryptedData, sessionId, iv));
    }

    @RequestMapping(value = "/createUnifiedOrder")
    @ResponseBody
    public Result createUnifiedOrder(HttpServletRequest request) {

        return wxPayService.createUnifiedOrder(request);

    }


    @RequestMapping(value = "/pay")
    @ResponseBody
    public Result pay(HttpServletRequest request) {

        return wxPayService.pay(request);

    }


    @RequestMapping(value = "/payCallback")
    @ResponseBody
    public String payCallback(HttpServletRequest request) {

        return wxPayService.payCallback(request);
    }

}
