package jx.lczj.service;

import jx.lczj.dao.FaceDao;
import jx.lczj.dao.ModelDao;
import jx.lczj.model.T_model;
import jx.lczj.utils.OpenCVUtil;
import jx.lczj.viewmodel.ModelVo;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 14260 on 2018/6/24.
 */

@Service
public class ModelService {

    @Resource
    ModelDao modelDao ;
    @Resource
    FaceDao faceDao;


    /**
     * 添加
     * @param model
     * @param name
     * @param sex
     * @param face
     * @param age
     * @param file
     * @param request
     * @return
     */
    @Transactional
    public ModelVo add(String model,String name,String sex,String face,String age,MultipartFile file, HttpServletRequest request) {
            try {
                ModelVo mv = new ModelVo();

                String path = request.getSession().getServletContext().getRealPath("models");
                String ctimes = System.currentTimeMillis() + "";
                String fileName = ctimes + ".png";
                System.out.println(fileName);
                System.out.println(path);
                File ff = new File(path);
                if (!ff.exists()) {
                    ff.mkdirs();
                }
                //保存
                //保存
                File file1 = new File(path, fileName);
                file.transferTo(file1);

                String vl = check(fileName,request);
                if(vl.equals("-1")){
                    mv.setInfo("-1:未识别到脸");
                    file1.delete();
                    return mv;


                }else if(vl.equals("-2")){
                    mv.setInfo("-2:未识别到眼镜");
                    file1.delete();
                    return mv;

                }else if(vl.equals("-3")){
                    mv.setInfo("-3:检测出错");
                    file1.delete();
                    return mv;
                }else{
                    mv.setInfo(vl);
                }


                boolean ok = modelDao.add(Integer.parseInt(model), name, Integer.parseInt(age), sex, Integer.parseInt(face), fileName);

                T_model m = modelDao.loadById(Integer.parseInt(model));
                mv.setT_face(faceDao.loadByFace(m.getFace()));
                mv.setT_model(m);
                return mv;
            }catch (Exception e){
                throw  new RuntimeException(e.getMessage());
            }
    }


    /**
     * 获取模特信息
     * @return
     */
    @Transactional
    public List<ModelVo> loadModels() {
        try {
            List<ModelVo> mvs = new ArrayList<ModelVo>();
            List<T_model> mds = modelDao.loadList();
            for (T_model m : mds) {
                ModelVo mv = new ModelVo();
                mv.setT_face(faceDao.loadByFace(m.getFace()));
                mv.setT_model(m);
                mvs.add(mv);
            }
            return mvs;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }

    }

    /**
     * 删除
     * @param model
     * @param request
     * @return
     */
    public boolean delete(int model, HttpServletRequest request) {
        try {
            String path = request.getSession().getServletContext().getRealPath("models");
            T_model m = modelDao.loadById(model);
            System.out.println(path);
            File ff = new File(path,m.getPhoto());
            if (!ff.exists()) {
                ff.delete();
            }
            return  modelDao.delete(model);
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }

    }

    /**
     * 通过id获取模特信息
     * @param model
     * @return
     */
    public T_model loadByModel(int model) {
        return  modelDao.loadById(model);
    }


    /**
     * 修改
     * @param model
     * @param name
     * @param sex
     * @param face
     * @param age
     * @param file
     * @param request
     * @return
     */
    @Transactional
    public ModelVo update(String model, String name, String sex, String face, String age, MultipartFile file, HttpServletRequest request) {
        try {
            ModelVo mv = new ModelVo();
            T_model m = modelDao.loadById(Integer.parseInt(model));
            String path = request.getSession().getServletContext().getRealPath("models");
            String ctimes = System.currentTimeMillis() + "";

            String fileName =m.getPhoto();
            //检测是否you图片
            if (file != null && !file.getOriginalFilename().equals("")) {

                fileName = ctimes + ".png";
                File ff = new File(path);
                if (!ff.exists()) {
                    ff.mkdirs();
                }

                //保存
                File file1 = new File(path, fileName);
                file.transferTo(file1);

                String vl = check(fileName,request);
                System.out.println(vl);
                if(vl.equals("-1")){
                    mv.setInfo("-1:未识别到脸");
                    file1.delete();
                    return mv;


                }else if(vl.equals("-2")){
                    mv.setInfo("-2:未识别到眼镜");
                    file1.delete();
                    return mv;

                }else if(vl.equals("-3")){
                    mv.setInfo("-3:检测出错");
                    file1.delete();
                    return mv;
                }else{
                     mv.setInfo(vl);
                }


                File oldf = new File(path, m.getPhoto());
                if (!oldf.exists()) {
                    oldf.delete();
                }

            }
            boolean ok = modelDao.update(Integer.parseInt(model), name, Integer.parseInt(age), sex, Integer.parseInt(face), fileName);

            T_model mm = modelDao.loadById(Integer.parseInt(model));
            mv.setT_face(faceDao.loadByFace(mm.getFace()));
            mv.setT_model(mm);
            return mv;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }


    /**
     * 检测模特图片是否合格
     * @param url
     * @param request
     * @return
     */
    public String check(String  url, HttpServletRequest request) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String ctimes = System.currentTimeMillis()+"";
        System.out.println("开始");
        String path = request.getSession().getServletContext().getRealPath("models");

        String src_url = path+"\\"+url;
        String dst_url = path+"/withGlasses/"+ctimes+"_withGlasses.jpg";
        String glasses_url = "";

        File file1 = new File(path+"/withGlasses/");
        if(!file1.exists()){
            file1.mkdirs();
        }


        glasses_url = "C:/OpenCVConfig/glasses/male/glasses_0.png";

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
            return "/models/withGlasses/" + ctimes + "_withGlasses.jpg";

        }else if(eyes==null){
            return "-2";

        }else if(eyes.size()==1 || eyes.size()==2 ){
            return "-1";

        }else{
            return "-3";
        }
    }


    /**
     * 根据脸形，年龄，性别推荐模特
     * @return
     */
    @Transactional
    public List<ModelVo> modelCommend(String face,String age ,String sex) {
        int faces = Integer.parseInt(face);
        int ages = Integer.parseInt(age);

        try {
            List<ModelVo> mvs = new ArrayList<ModelVo>();
            List<T_model> mds = modelDao.modelCommend(faces,ages,sex);
            for (T_model m : mds) {
                ModelVo mv = new ModelVo();
                mv.setT_face(faceDao.loadByFace(m.getFace()));
                mv.setT_model(m);
                mvs.add(mv);
            }
            return mvs;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }

    }
}
