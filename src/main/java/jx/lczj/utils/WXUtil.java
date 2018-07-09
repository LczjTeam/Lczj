package jx.lczj.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.web.bind.annotation.RequestParam;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.List;
import java.util.Map;

/**
 * Created by lsh
 * AES-128-CBC 加密方式
 * 注：
 * AES-128-CBC可以自己定义“密钥”和“偏移量“。
 * AES-128是jdk自动生成的“密钥”。
 */
public class WXUtil {


    public  static JSONObject getOpenidAndSessionKey(String code) {//接收用户传过来的code，required=false表明如果这个参数没有传过来也可以。
        // String code=getcode;
        //接收从客户端获取的code
        String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

        //这三个参数就是之后要填上自己的值。
        //替换appid，appsecret，和code
        String requestUrl = WX_URL.replace("APPID", "wx2e2a553c12f23b71").//填写自己的appid
                replace("SECRET", "888c67988659dd316650582c84c114f4").replace("JSCODE", code).//填写自己的appsecret，
                replace("authorization_code", "authorization_code");

        //调用get方法发起get请求，并把返回值赋值给returnvalue
        String  returnvalue=GET(requestUrl);
        System.out.println(requestUrl);//打印发起请求的url
        System.out.println(returnvalue);//打印调用GET方法返回值
        //定义一个json对象。
        JSONObject convertvalue=new JSONObject();

        //将得到的字符串转换为json
        convertvalue=(JSONObject) JSON.parse(returnvalue);

        System.out.println("return openid is ："+(String)convertvalue.get("openid")); //打印得到的openid
        System.out.println("return sessionkey is ："+(String)convertvalue.get("session_key"));//打印得到的sessionkey，
        //把openid和sessionkey分别赋值给openid和sessionkey
        String openid=(String) convertvalue.get("openid");
        String sessionkey=(String) convertvalue.get("session_key");//定义两个变量存储得到的openid和session_key.

        return convertvalue;
    }

    //发起get请求的方法。
    public  static String GET(String url) {
        String result = "";
        BufferedReader in = null;
        InputStream is = null;
        InputStreamReader isr = null;
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.connect();
            Map<String, List<String>> map = conn.getHeaderFields();
            is = conn.getInputStream();
            isr = new InputStreamReader(is);
            in = new BufferedReader(isr);
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            // 异常记录
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (is != null) {
                    is.close();
                }
                if (isr != null) {
                    isr.close();
                }
            } catch (Exception e2) {
                // 异常记录
            }
        }
        return result;
    }
}