package jx.lczj.controller;

/**
 * Created by Software1 on 2017/12/27.
 */

import jx.lczj.utils.OpenCVUtil;
import jx.lczj.viewmodel.AdminVo;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;

@Controller
@RequestMapping("files")
public class FileController {


    /**
     * 文件上传
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload(MultipartFile file,String sex,String age,String station,HttpServletRequest request) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String ctimes = System.currentTimeMillis()+"";
        System.out.println("开始");
        String path = request.getSession().getServletContext().getRealPath("upload");

        //String fileName = file.getOriginalFilename();
        String fileName = ctimes+".jpg";
        System.out.println(fileName);
        System.out.println(path);
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }



        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String src_url = path+"\\"+fileName;
        String dst_url = path+"/withGlasses/"+ctimes+"_withGlasses.jpg";
        String glasses_url = "";

        File file1 = new File(dst_url);
        if(!file1.exists()){
            file1.mkdirs();
        }

        System.out.println("age:"+age+"，sex:"+sex);

        //眼镜推荐
        if(age.equals("1")){
            glasses_url = "C:/OpenCVConfig/glasses/40+/glasses_0.png";

        }else if(sex.equals("1")){
            glasses_url = "C:/OpenCVConfig/glasses/male/glasses_0.png";
        }else{
            glasses_url = "C:/OpenCVConfig/glasses/famale/glasses_0.png";
        }
        System.out.println("src_url:"+src_url);
        System.out.println("dst_url:"+dst_url);
        System.out.println("glasses_url:"+glasses_url);

        Mat src = Imgcodecs.imread(src_url);
        Mat dst = src.clone();
        dst.create(src.size(), src.type());
        Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGR2GRAY);


        //人脸检测以及眼睛检测并获取数据
        ArrayList<Rect> eyes = OpenCVUtil.faceAndeye_recognition(dst, 1.3, 5, 0, 10, 250);


        System.out.println("eyes:"+eyes);

        if(eyes!=null && eyes.size() == 3) {
            //获取眼镜的位置参数
            int x = 0;
            int y = 0;
            int height = 0;
            int width = 0;

            Mat glasses = Imgcodecs.imread(glasses_url);
            System.out.println("glasses："+glasses.width()+":"+glasses.height());


            //脸
            Rect face = eyes.get(0);
            System.out.println("face："+face.x+":"+face.y);

            //眼睛
            Rect eye0 = eyes.get(1);
            System.out.println("eye0："+eye0.x+":"+eye0.y);

            Rect eye1 = eyes.get(2);
            System.out.println("eye1："+eye1.x+":"+eye1.y);

            //计算眼镜位置和眼镜尺寸
            if(eye0.x < eye1.x){
                int dis = (int)(0.7*(eye0.x-face.x));
                System.out.println("dis："+dis);
                x = eye0.x-dis;
                y = eye0.y;
                width = eye1.x - eye0.x + eye1.width+2*dis;
                height =  (int)((width*1.00/glasses.width())*glasses.height()) ;//eye0.height > eye1.height ? eye0.height:eye1.height;
            }else{
                int dis = (int)(0.7*(eye1.x-face.x));
                System.out.println("dis："+dis);
                x = eye1.x-dis;
                y =  eye1.y;
                width = eye0.x - eye1.x + eye0.width+dis*2;
                height = (int)((width*1.00/glasses.width())*glasses.height()) ;//eye1.height > eye0.height ? eye1.height:eye0.height;
            }

            System.out.println(height);
            System.out.println(width);


            //戴眼镜
            boolean ok = OpenCVUtil.addGlasses(x, y, width, height, src_url, glasses_url, dst_url);
            return "/upload/withGlasses/" + ctimes + "_withGlasses.jpg";
        }else if(eyes==null){
            return "-2";

        }else if(eyes.size()==1){
            return "-1";

        }else{
            return "-3";
        }
    }


    /**
     * 用户头像
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploaduserhead")
    @ResponseBody
    public boolean userhead(MultipartFile file, HttpSession session, HttpServletRequest request) {

        AdminVo adminVo = (AdminVo)session.getAttribute("admin");
        String  code = adminVo.getT_admin().getAdmin();
        System.out.println(code);

        String path = request.getSession().getServletContext().getRealPath("heads");

        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        System.out.println(path);
        File targetFile = new File(path, code+".png");


        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        if(targetFile.exists()){
            targetFile.delete();
        }

        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


}
