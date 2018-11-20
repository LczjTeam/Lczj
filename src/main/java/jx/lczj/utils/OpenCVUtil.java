package jx.lczj.utils;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2018-05-07.
 */
public class OpenCVUtil {


//    static  String haarcascade_frontalface = "C:\\OpenCVConfig\\haarcascade_frontalface_default.xml";
//    static  String haarcascade_frontalface_alt = "C:\\OpenCVConfig\\haarcascade_frontalface_alt.xml";
//    static  String haarcascade_eye = "C:\\OpenCVConfig\\haarcascade_eye.xml";
//    static  String haarcascade_eye_tree_eyeglasses = "C:\\OpenCVConfig\\haarcascade_eye_tree_eyeglasses.xml";

    static  String haarcascade_frontalface = "C:\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_default.xml";
    static  String haarcascade_frontalface_alt = "C:\\opencv\\build\\share\\OpenCV\\haarcascades\\haarcascade_frontalface_alt.xml";
    static  String haarcascade_eye = "C:\\opencv\\build\\share\\OpenCV\\haarcascades\\haarcascade_eye.xml";
    static  String haarcascade_eye_tree_eyeglasses = "C:\\opencv\\build\\share\\OpenCV\\haarcascades\\haarcascade_eye_tree_eyeglasses.xml";

    /**
     * 画出眼睛的位置
     * @param src_url  原图片位置
     * @param dst_url  目标图片位置
     * @return
     */
    public  static String setEyes(String src_url,String dst_url){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println(Core.VERSION);
        Mat src = Imgcodecs.imread(src_url); //,Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE
        //读取图像到矩阵中,取灰度图像
        if(src.empty()){
            System.out.println("NO FILES");
            return  "NO FILES";
        }
        // 检测灰度图会加快运行速度
        Mat dst = src.clone();
        dst.create(src.size(), src.type());
        Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGR2GRAY);
        ArrayList<Rect> answer = faceAndeye_recognition(dst,
                1.2,6,0,10,200);
        if(answer==null){
            System.out.println("NO FACE");
            return  "NO FACE";
        }
        if(answer != null && answer.size()==3){
            Rect re = answer.get(0);
            Imgproc.rectangle(src,
                    new Point(re.x,re.y),
                    new Point(re.x + re.width,re.y + re.height),
                    new Scalar(0,255,0),2);
            re = answer.get(1);
            Imgproc.rectangle(src,
                    new Point(re.x,re.y),
                    new Point(re.x + re.width,re.y + re.height),
                    new Scalar(0,255,0),2);
            Imgcodecs.imwrite(dst_url, src);
            return "OK";
        }
        System.out.println("NO EYES");
        return  "NO EYES";

    }






    /**
     * 寻找眼睛
     * @param image  检测的图片  mat
     * @param scaleFactor  缩放比率 1.3
     * @param minNeighbors 5
     * @param flags  0
     * @param minSize 最小尺寸  10
     * @param maxSize 最大尺寸  200
     * @return  脸和两个眼睛的参数
     * 注意添加： System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
     */
    public static ArrayList<Rect> faceAndeye_recognition(Mat image, double scaleFactor, int minNeighbors, int flags, int minSize, int maxSize)
    {
        //face detect
        CascadeClassifier faceDetector
                = new CascadeClassifier(haarcascade_frontalface_alt);
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
        int count = faceDetections.toArray().length;
        if(count !=1){
             return null;
        }
        //eye detect
        CascadeClassifier eyeDetector = new CascadeClassifier(haarcascade_eye);
        MatOfRect eyeDetections = new MatOfRect();
        ArrayList<Rect> ret = new ArrayList<Rect>();
        if(faceDetections.toArray().length == 1){
            Rect rect = faceDetections.toArray()[0];
            ret.add(rect);
            // 在“人脸矩形”中寻找眼睛
            eyeDetector.detectMultiScale(
                    new Mat(image, new Rect(rect.x, rect.y, rect.width, rect.height)),
                    eyeDetections, scaleFactor,minNeighbors,flags,
                    new Size(minSize,minSize),
                    new Size(maxSize, maxSize));
            int count0 = eyeDetections.toArray().length;
            System.out.println("eyeDetections:"+eyeDetections.toArray());
            if( count0 == 2){
                // 在一张脸中有且只能有两个眼睛
                for(Rect re : eyeDetections.toArray()) {
                    ret.add(new Rect(rect.x + re.x, rect.y + re.y, re.width, re.height));
                }
            }
        }
        return ret;
    }


    /**
     * 戴上眼镜
     * @param x 眼镜左上点X坐标
     * @param y 眼镜左上点Y坐标
     * @param width 眼镜的宽度【scale*width】
     * @param height  眼镜的长度【scale*height】
     * @param src_url 自拍照片的路径（JPG）
     * @param glass_url  眼镜图片路径（PNG）
     * @return
     */
    public static boolean addGlasses(int x,int y,int width ,int height ,String src_url, String glass_url, String dst_url){
        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            // 读取PNG图片
            Mat glass = Imgcodecs.imread(glass_url, Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);//-1
            // 分离R,B,G,Alpha通道
            List<Mat> dst = new ArrayList<Mat>(4);

            Core.split(glass, dst);
            Mat alpha = dst.get(3);
            // 合并R,B,G通道
            List<Mat> tMat = new ArrayList<Mat>(3);
            tMat.add(dst.get(0));
            tMat.add(dst.get(1));
            tMat.add(dst.get(2));
            Mat rgb = new Mat();
            Core.merge(tMat, rgb);
            // 调整眼镜大小


            // 用alpha通道作为mask
            Mat mask = new Mat();
            Imgproc.resize(alpha, mask, new Size(width, height));
            Mat mask_inv = new Mat();
            // 图像取反
            Core.bitwise_not(mask, mask_inv);

            // 原图ROI中提取放帽子的区域
            List<Mat> tMat0 = new ArrayList<Mat>(3);
            tMat0.add(mask_inv);
            tMat0.add(mask_inv);
            tMat0.add(mask_inv);
            Core.merge(tMat0, mask_inv);

            // 浮点数0~1代表图片透明度
            mask_inv.convertTo(alpha, CvType.CV_32F, 1.0 / 255, 0.0);

            //描述眼镜区域
            Mat test_src1 = new Mat(alpha, new Rect(0, 0, width, height));
            Mat test_src = Imgcodecs.imread(src_url);
            Mat test_src2 = new Mat(test_src, new Rect(x, y, width, height));
            Mat test_dst = new Mat();
            test_src2.convertTo(test_src2, CvType.CV_32F);
            Core.multiply(test_src1, test_src2, test_dst);


            // 提取眼镜区域
            Mat rbg_hat_resized = new Mat();
            Mat hat_area = new Mat();
            Imgproc.resize(rgb, rbg_hat_resized, new Size(width, height));
            Core.bitwise_and(rbg_hat_resized, rbg_hat_resized, hat_area, mask);
            test_dst.convertTo(test_dst, CvType.CV_8U);

            // 图像叠加
            Mat finall_res = new Mat();
            Core.add(test_dst, hat_area, finall_res);

            //还原到完整图
            Mat f_src = test_src.clone();
            finall_res.copyTo(new Mat(f_src, new Rect(x, y, width, height)));
            Imgcodecs.imwrite(dst_url, f_src);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return  false;
        }
    }


    /**
     * 检测图片尺寸
     * @param src_url
     * @param targetWidth
     * @param targetHeight
     * @return
     */
    public static boolean checkImageSize(String  src_url,int targetWidth,int targetHeight) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread(src_url);
        int width = src.width();
        int height = src.height();
        System.out.println("当前图片尺寸为：宽-"+width+",高-"+height);
        return width == targetWidth && height == targetHeight;
    }

    /**
     * 检测模特图片是否合格
     * @param url
     * @param request
     * @return
     */
    public static String check(String  url,String  glasses_url, HttpServletRequest request) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String ctimes = System.currentTimeMillis()+"";
        System.out.println("开始");
        String path = request.getSession().getServletContext().getRealPath("models");

        String src_url = path+"\\"+url;
        String dst_url = path+"/withGlasses/"+ctimes+"_withGlasses.jpg";

        File file1 = new File(path+"/withGlasses/");
        if(!file1.exists()){
            file1.mkdirs();
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
            if(ok) {
                return "/models/withGlasses/" + ctimes + "_withGlasses.jpg";
            }else{
                return "-4";
            }
        }else if(eyes==null){
            return "-2";

        }else if(eyes.size()==1 || eyes.size()==2 ){
            return "-1";

        }else{
            return "-3";
        }
    }


}
