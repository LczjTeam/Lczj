package jx.lczj.controller;

 import com.alibaba.fastjson.JSON;
 import com.alibaba.fastjson.JSONObject;
 import jx.lczj.utils.AES;
 import jx.lczj.utils.WXUtil;
 import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by 14260 on 2018/7/8.
 */

@Controller
@RequestMapping("wx")
public class WXController {


   @ResponseBody
    @RequestMapping("/login")
    public JSONObject getOpenid(@RequestParam(value="code",required=false)String code) {//接收用户传过来的code，required=false表明如果这个参数没有传过来也可以。
        return WXUtil.getOpenidAndSessionKey(code);
    }

    @RequestMapping(value = "/decodePhoneNumber")
    @ResponseBody
    public JSONObject decodeUserInfo(@RequestParam(required = true,value = "encryptedData")String encryptedData,
                                     @RequestParam(required = true,value = "iv")String iv,
                                     @RequestParam(required = true,value = "sessionId")String sessionId) {
        return  (JSONObject)JSON.parse(AES.wxDecrypt(encryptedData, sessionId, iv));
    }
}
