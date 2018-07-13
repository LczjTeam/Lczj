package jx.lczj.controller;

 import com.alibaba.fastjson.JSON;
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import jx.lczj.utils.AES;
 import jx.lczj.utils.WXPayUtil;
 import jx.lczj.utils.WXUtil;
 import jx.lczj.viewmodel.Result;
 import org.dom4j.DocumentException;
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
    public Result createUnifiedOrder(HttpServletRequest request){
        //商户号
        String mchId = "";
        //支付密钥
        String key = "&key=自己的商户密钥";
        //交易类型
        String tradeType = "JSAPI";
        //随机字符串
        String nonceStr = WXPayUtil.getNonceStr();
        //微信支付完成后给该链接发送消息，判断订单是否完成
        String notifyUrl = "外网能够访问的url,支付成功后的回调";

        //微信用户唯一id
        if(request.getParameter("openid")==null){
            return new Result(500,"支付失败，openid is null");
        }
        String openId = request.getParameter("openid");
        //小程序id
        if(request.getParameter("appid")==null){
            return new Result(500,"支付失败，appid is null");
        }
        String appid  = request.getParameter("appid");
        //商品订单号(保持唯一性)
        String outTradeNo = mchId+WXPayUtil.getNonceStr();
        //支付金额
        if(request.getParameter("totalfee")==null){
            return new Result(500,"支付失败，totalfee is null");
        }
        String fee = request.getParameter("totalfee");
        String totalFee = WXPayUtil.getMoney(fee);
        //发起支付设备ip
        String spbillCreateIp =  WXPayUtil.getIpAddress(request);
        //商品描述
        if(request.getParameter("body")==null){
            return new Result(500,"支付失败，body is null");
        }
        String body = request.getParameter("body");
        //附加数据，商户携带的订单的自定义数据 (原样返回到通知中,这类我们需要系统中订单的id 方便对订单进行处理)

        String attach = request.getParameter("attach")==null ? "":request.getParameter("attach");
        //我们后面需要键值对的形式，所以先装入map
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("appid", appid);
        sParaTemp.put("attach", attach);
        sParaTemp.put("body",  body);
        sParaTemp.put("mch_id", mchId);
        sParaTemp.put("nonce_str", nonceStr);
        sParaTemp.put("notify_url",notifyUrl);
        sParaTemp.put("openid", openId);
        sParaTemp.put("out_trade_no", outTradeNo);
        sParaTemp.put("spbill_create_ip", spbillCreateIp);
        sParaTemp.put("total_fee",totalFee);
        sParaTemp.put("trade_type", tradeType);

        //去掉空值 跟 签名参数(空值不参与签名，所以需要去掉)
        Map<String, String> map = WXPayUtil.paraFilter(sParaTemp);

        //按照 参数=参数值&参数2=参数值2 这样的形式拼接（拼接需要按照ASCII码升序排列）

        String mapStr = WXPayUtil.createLinkString(map);
        //MD5运算生成签名
        String sign = WXPayUtil.sign(mapStr, key, "utf-8").toUpperCase();
        sParaTemp.put("sign", sign);
        /**
         组装成xml参数,此处偷懒使用手动组装，严格代码可封装一个方法，XML标排序需要注意，ASCII码升序排列
         */
        String xml = "<xml>" + "<appid>" + appid + "</appid>"
                + "<attach>" + attach + "</attach>"
                + "<body>" + body + "</body>"
                + "<mch_id>" + mchId + "</mch_id>"
                + "<nonce_str>" + nonceStr + "</nonce_str>"
                + "<notify_url>" + notifyUrl + "</notify_url>"
                + "<openid>" + openId + "</openid>"
                + "<out_trade_no>" + outTradeNo + "</out_trade_no>"
                + "<spbill_create_ip>" + spbillCreateIp + "</spbill_create_ip>"
                + "<total_fee>" + totalFee + "</total_fee>"
                + "<trade_type>" + tradeType + "</trade_type>"
                + "<sign>" + sign + "</sign>"
                + "</xml>";
        //统一下单url，生成预付id
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String result =WXPayUtil.httpRequest(url, "POST", xml);

        Map<String, String> paramMap = new HashMap<String, String>();
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        //得到预支付id
        String prepay_id = "";
        try {
            prepay_id = WXPayUtil.getPayNo(result);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        String packages = "prepay_id="+prepay_id;
        String nonceStr1 = WXPayUtil.getNonceStr();
        //开始第二次签名
        String mapStr1 = "appId="+appid+"&nonceStr=" + nonceStr1 + "&package=prepay_id=" + prepay_id + "&signType=MD5&timeStamp=" + timeStamp;
        String paySign = WXPayUtil.sign(mapStr1, key, "utf-8").toUpperCase();
        //前端所需各项参数拼接
        String finaPackage = "\"appId\":\"" + appid + "\",\"timeStamp\":\"" + timeStamp
                + "\",\"nonceStr\":\"" + nonceStr1 + "\",\"package\":\""
                + packages + "\",\"signType\" : \"MD5" + "\",\"paySign\":\""
                + paySign + "\"";

        return new Result(200,finaPackage);
    }


    @RequestMapping(value = "/payCallback")
    @ResponseBody
    public String payCallback(HttpServletRequest request,HttpServletResponse response) {
        //用于处理结束后返回的xml
        String resXml = "";
        String key = "&key=自己的密钥";
        try {
            InputStream in = request.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int len = 0;
            byte[] b = new byte[1024];
            while((len = in.read(b)) != -1){
                out.write(b, 0, len);
            }
            out.close();
            in.close();
            //将流 转为字符串
            String result = new String(out.toByteArray(), "utf-8");
            Map<String, String> map = WXPayUtil.getNotifyUrl(result);
            String return_code = map.get("return_code").toString().toUpperCase();
            if(return_code.equals("SUCCESS")){
                //进行签名验证，看是否是从微信发送过来的，防止资金被盗
                if(WXPayUtil.verifyWeixinNotify(map, key)){
                    //签名验证成功后按照微信要求返回的xml
                    resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                            + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    return resXml;
                }
            }else{
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[sign check error]]></return_msg>" + "</xml> ";
                return resXml;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                + "<return_msg><![CDATA[xml error]]></return_msg>" + "</xml> ";
        return resXml;
    }

}
