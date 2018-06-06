package jx.lczj.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.Random;

/**
 * Created by root on 2018-03-30.
 */

public class SmsUtil {

    public static String smsSend(String phone){
        try {
            String num = getRadomNum();
            //发短信
            SendSmsResponse response = sendSms(num,phone);
            System.out.println("短信接口返回的数据----------------");
            System.out.println("Message=" + response.getMessage());
            if(response.getMessage().equals("OK")){
                return num;
            }else{
                return "-1";
            }

        }catch (Exception ex){
            return "-1";
        }
    }


    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIVykW2BMEDJ2v";
    static final String accessKeySecret = "I0AJU5xwcA9mLFzyAuK0pyxr3pEjtp";

    public static SendSmsResponse sendSms(String num,String phone) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("刘骞");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_130190051");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\""+num+"\"}");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }
    protected static String getRadomNum(){
        Random random = new Random();
        String s ="";
        for(int n = 0 ; n < 6 ; n++){
            s+=random.nextInt(10);
        }
        return s;
    }

}
