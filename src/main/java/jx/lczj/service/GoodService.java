package jx.lczj.service;

import jx.lczj.dao.*;
import jx.lczj.model.T_goods;
import jx.lczj.viewmodel.GoodsVo;
import jx.lczj.viewmodel.NewsVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class GoodService  {

    @Resource
    GoodDao goodDao;

    @Resource
    BrandDao brandDao ;

    @Resource
    CategoryDao categoryDao;


    @Resource
    FaceDao faceDao;


    @Resource
    AgeDao ageDao;


    @Resource
    OccasionDao occasionDao ;



    @Resource
    ColorDao colorDao ;

    /**
     * 商品信息
     * @return
     * @return
     */
    public List<T_goods> loadlist() {
        return goodDao.loadlist();
    }


    @Transactional
    public String upload(HttpServletRequest request) {
        String ss = "";
        try {
            System.out.println("收到图片!");
            int counter = 0;

            MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> files = Murequest.getFileMap();//得到文件map对象
            String path = request.getSession().getServletContext().getRealPath("goods");
            File dir = new File(path);
            System.out.println(path);
            if (!dir.exists())//目录不存在则创建
                dir.mkdirs();
            for (MultipartFile file : files.values()) {
                String ctimes = System.currentTimeMillis() + "";
                counter++;
                String fileName = file.getOriginalFilename().split("\\.")[0];
                String code = fileName + ctimes;
                fileName = fileName + ctimes + ".png";
                System.out.println(fileName);
                File tagetFile = new File(path+"/" + fileName);//创建文件对象

                boolean ok =  goodDao.addAttach(code,fileName,fileName,"p");


                if (!tagetFile.exists()) {//文件名不存在 则新建文件，并将文件复制到新建文件中
                    tagetFile.createNewFile();
                }
                file.transferTo(tagetFile);

                if(counter==1){
                    ss  = code;
                }else{
                    ss+=";"+code;
                }



            }

            System.out.println("接收完毕");
            return ss;
        }catch (Exception e){

            throw  new RuntimeException(e.getMessage());
        }
    }


    @Transactional
    public boolean deleteAttach(String code, HttpServletRequest request) {

        try{


            boolean ok = goodDao.deleteAttachDiv(code);
            boolean ok1 = goodDao.deleteAttach(code);

            String path = request.getSession().getServletContext().getRealPath("goods");
            File file = new File(path+"/"+code+".png");
            System.out.println(path+"/"+code+".png");
            if (file.exists())//目录不存在则创建
                file.delete();

            return true;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }

    }


    /**
     * 添加
     * @param fileName
     * @param request
     * @param session
     * @return
     */
    @Transactional
    public GoodsVo add(String[] fileName, HttpServletRequest request, HttpSession session) {

        try {
            String goods = request.getParameter("good");
            System.out.println("goods:" + goods);

            int category = Integer.parseInt(request.getParameter("category"));
            System.out.println("category:" + category);

            int brand = Integer.parseInt(request.getParameter("brand"));
            System.out.println("brand:" + brand);

            String name = request.getParameter("good_name");
            System.out.println("name:" + name);

            String models = request.getParameter("models");
            System.out.println("models:" + models);

            int width = Integer.parseInt(request.getParameter("width"));
            System.out.println("width:" + width);

            int height = Integer.parseInt(request.getParameter("height"));
            System.out.println("height:" + height);

            int space = Integer.parseInt(request.getParameter("space"));
            System.out.println("space:" + space);

            int length = Integer.parseInt(request.getParameter("length"));
            System.out.println("length:" + length);

            int max_width = Integer.parseInt(request.getParameter("max_width"));
            System.out.println("max_width:" + max_width);

            int suitable_sex = Integer.parseInt(request.getParameter("suitable_sex"));
            System.out.println("suitable_sex:" + suitable_sex);

            int price = Integer.parseInt(request.getParameter("price"));
            System.out.println("price:" + price);


            boolean ok1 = goodDao.add(goods,
                    category,
                    brand,
                    name,
                    models,
                    width,
                    height,
                    space,
                    length,
                    max_width,
                    suitable_sex,
                    price
            );


            String colors = request.getParameter("colors");
            System.out.println("colors:" + colors);

            String[] cs = colors.split("\\,");
            for (String s : cs) {

                boolean ok = goodDao.addColorDiv(Integer.parseInt(s), goods);
            }


            String agesections = request.getParameter("agesections");
            System.out.println("agesections:" + agesections);

            String[] as = agesections.split("\\,");
            for (String s : as) {
                boolean ok = goodDao.addAgesectionDiv(Integer.parseInt(s), goods);
            }

            String faces = request.getParameter("faces");
            System.out.println("faces:" + faces);
            String[] fs = faces.split("\\,");
            for (String s : fs) {
                boolean ok = goodDao.addFaceDiv(Integer.parseInt(s), goods);
            }


            String occasions = request.getParameter("occasions");
            System.out.println("occasions:" + occasions);

            String[] os = occasions.split("\\,");
            for (String s : os) {
                boolean ok = goodDao.addOccasionDiv(Integer.parseInt(s), goods);
            }

            System.out.println("fileName:" + fileName.length);

            for (int i = 0; i < fileName.length; i++) {
                boolean ok = goodDao.addAttachmentDiv(fileName[i], goods, i);


            }




            GoodsVo gvo = new GoodsVo();

            T_goods t_good = goodDao.loadById(goods);
            gvo.setT_goods(t_good);
            gvo.setT_brand(brandDao.loadById(brand));
            gvo.setT_category(categoryDao.loadById(category));
            gvo.setT_colors(colorDao.loadByGood(goods));
            gvo.setT_agesections(ageDao.loadByGood(goods));
            gvo.setT_faces(faceDao.loadByGood(goods));
            gvo.setT_occasions(occasionDao.loadByGood(goods));
            gvo.setT_attachments(goodDao.loadAttachmentByGood(goods));
            return gvo;

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
