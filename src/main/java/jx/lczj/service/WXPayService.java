package jx.lczj.service;

import jx.lczj.dao.AddressDao;
import jx.lczj.dao.MywearDao;
import jx.lczj.dao.OrderCreateDao;
import jx.lczj.model.T_address;
import jx.lczj.utils.WXPayUtil;
import jx.lczj.viewmodel.Result;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by 14260 on 2018/7/16.
 */

@Service
public class WxPayService {

    @Autowired
    OrderCreateDao orderCreateDao;

    @Autowired
    MywearDao   mywearDao ;

    @Resource
    AddressDao addressDao;

    /**
     * 统一下单
     * @param request
     * @return
     */
    @Transactional
    public Result createUnifiedOrder(HttpServletRequest request) {

        try {


            //商户号
            String mchId = "1509121291";
            //支付密钥
            String key = "&key=JMODELLECHAOZHIJINGqiuxiaoyu1234";
            //交易类型
            String tradeType = "JSAPI";
            //随机字符串
            String nonceStr = WXPayUtil.getNonceStr();

            System.out.println(nonceStr);
            //微信支付完成后给该链接发送消息，判断订单是否完成
            String notifyUrl = "http://jx-lczj.nat300.top/Lczj/wx/payCallback";

            //微信用户唯一id
            if (request.getParameter("openid") == null) {
                return new Result(500, "支付失败，openid is null");
            }
            String openId = request.getParameter("openid").toString();
            System.out.println(openId);
            //小程序id
            String appid = "wx2e2a553c12f23b71";

            //商品订单号(保持唯一性)
            String outTradeNo = mchId + WXPayUtil.getNonceStr().substring(5, 15);
            //支付金额
            if(request.getParameter("totalfee")==null){
                return new Result(500,"支付失败，totalfee is null");
            }
            String fee = "0.01";
            String totalFee = WXPayUtil.getMoney(fee);
            //发起支付设备ip
            String spbillCreateIp = WXPayUtil.getIpAddress(request);
            //商品描述
            if(request.getParameter("body")==null){
                return new Result(500,"支付失败，body is null");
            }
            String body = request.getParameter("body");


            //数据库出来
            //试戴编号
            if (request.getParameter("mywear") == null) {
                return new Result(500, "支付失败，mywear is null");
            }
            String mywear = request.getParameter("mywear");
            System.out.println("mywear:" + mywear);

            //会员编号
            if (request.getParameter("customer") == null) {
                return new Result(500, "支付失败，customer is null");
            }
            String customer = request.getParameter("customer");
            System.out.println("customer:" + customer);

            //地址编号
            if (request.getParameter("address") == null) {
                return new Result(500, "支付失败，address is null");
            }
            String address = request.getParameter("address");
            System.out.println("address:" + address);


            //订单编号
            String order = outTradeNo;
            System.out.println("order:" + order);

            boolean ok = orderCreateDao.addOrder(order, customer, address, new Date(), 0);
            boolean ok1 = mywearDao.updateOrder(order, mywear);


            //我们后面需要键值对的形式，所以先装入map
            Map<String, String> sParaTemp = new HashMap<String, String>();
            sParaTemp.put("appid", appid);
            sParaTemp.put("body", body);
            sParaTemp.put("mch_id", mchId);
            sParaTemp.put("nonce_str", nonceStr);
            sParaTemp.put("notify_url", notifyUrl);
            sParaTemp.put("openid", openId);
            sParaTemp.put("out_trade_no", outTradeNo);
            sParaTemp.put("spbill_create_ip", spbillCreateIp);
            sParaTemp.put("total_fee", totalFee);
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
            String xml = "<xml>"
                    + "<appid>" + appid + "</appid>"
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
            System.out.println(xml);

            String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
            String result = WXPayUtil.httpRequest(url, "POST", xml);

            System.out.println(result);

            String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
            //得到预支付id
            String prepay_id = WXPayUtil.getPayNo(result);
            String packages = "prepay_id=" + prepay_id;
            String nonceStr1 = WXPayUtil.getNonceStr();
            //开始第二次签名
            String mapStr1 = "appId=" + appid + "&nonceStr=" + nonceStr1 + "&package=prepay_id=" + prepay_id + "&signType=MD5&timeStamp=" + timeStamp;
            String paySign = WXPayUtil.sign(mapStr1, key, "utf-8").toUpperCase();
            //前端所需各项参数拼接
            String finaPackage = "\"appId\":\"" + appid + "\",\"timeStamp\":\"" + timeStamp
                    + "\",\"nonceStr\":\"" + nonceStr1 + "\",\"package\":\""
                    + packages + "\",\"signType\":\"MD5" + "\",\"paySign\":\""
                    + paySign + "\"";

            return new Result(200, finaPackage);
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
         
    }


    /**
     * 支付回调函数
     * @param request
     * @return
     */
    public String payCallback(HttpServletRequest request) {


        //用于处理结束后返回的xml
        String resXml = "";
        String key = "&key=JMODELLECHAOZHIJINGqiuxiaoyu1234";
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
            String order = map.get("out_trade_no").toString();

/*
            System.out.println(result);*/
            System.out.println("回调："+order);
            System.out.println("回调1："+return_code.equals("SUCCESS"));
            if(return_code.equals("SUCCESS")){
                //进行签名验证，看是否是从微信发送过来的，防止资金被盗
                System.out.println("回调2："+WXPayUtil.verifyWeixinNotify(map, key));

                if(WXPayUtil.verifyWeixinNotify(map, key)){

                    int n =  orderCreateDao.updateState(order , 1);
                    System.out.println(n);
                    if(n!=1){
                        resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                                + "<return_msg><![CDATA[sign check error]]></return_msg>" + "</xml> ";
                        return resXml;
                    }

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
