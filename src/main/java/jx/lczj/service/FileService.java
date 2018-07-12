package jx.lczj.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jx.lczj.utils.OpenCVUtil;
import jx.lczj.viewmodel.SrcDstVo;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by 14260 on 2018/6/24.
 */

@Service
public class FileService {


    /**
     * 文件上传
     * @param file
     * @param request
     * @return
     */
    @Transactional
    public SrcDstVo upload(MultipartFile file, String glass_paths, HttpServletRequest request) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String ctimes = System.currentTimeMillis()+"";
        System.out.println("开始");
        String path = request.getSession().getServletContext().getRealPath("upload");

        String path1 = request.getSession().getServletContext().getRealPath("goods");

        //String fileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileName = ctimes+uuid+".jpg";
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

        String src_url = path+"/"+fileName;
        String dst_url = path+"/withGlasses/"+ctimes+uuid+"_withGlasses.jpg";
        String glasses_url = "";

        File file1 = new File(path+"/withGlasses/");
        if(!file1.exists()){
            file1.mkdirs();
        }

        glasses_url = path1+"/"+glass_paths;

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




            return  new SrcDstVo(fileName,("/upload/withGlasses/" + ctimes+uuid + "_withGlasses.jpg") );
        }else if(eyes==null){
            return  new SrcDstVo("-2","");


        }else if(eyes.size()==1){
            return  new SrcDstVo("-1","");


        }else{
            return  new SrcDstVo("-3","");

        }
    }


    /**
     * 眼镜试戴
     * @param url
     * @param gl_src
     * @param request
     * @return
     */
    public String wearGlasses(String root,String url, String gl_src, HttpServletRequest request) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String ctimes = System.currentTimeMillis()+"";
        System.out.println("开始");
        String path = request.getSession().getServletContext().getRealPath(root);

        String path1 = request.getSession().getServletContext().getRealPath("goods");

        String uuid = UUID.randomUUID().toString().replace("-", "");

        String src_url = path+"\\"+url;
        String dst_url = path+"/withGlasses/"+ctimes+uuid+"_withGlasses.jpg";

        File file1 = new File(path+"/withGlasses/");
        if(!file1.exists()){
            file1.mkdirs();
        }


        String  glasses_url =  path1+"/"+gl_src;


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
            return "/"+root+"/withGlasses/" + ctimes+uuid + "_withGlasses.jpg";

        }else if(eyes==null){
            return "-2";

        }else if(eyes.size()==1 || eyes.size()==2 ){
            return "-1";

        }else{
            return "-3";
        }

    }
}
