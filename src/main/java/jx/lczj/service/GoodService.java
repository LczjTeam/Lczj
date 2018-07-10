package jx.lczj.service;

import jx.lczj.dao.*;
import jx.lczj.model.*;
import jx.lczj.viewmodel.GoodsVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GoodService  {

    @Resource
    GoodDao goodDao ;

    @Resource
    BrandDao brandDao ;

    @Resource
    CategoryDao categoryDao ;


    @Resource
    FaceDao faceDao;


    @Resource
    AgeDao ageDao;


    @Resource
    OccasionDao occasionDao ;



    @Resource
    ColorDao colorDao;

    /**
     * 商品信息
     * @return
     * @return
     */
    @Transactional
    public List<GoodsVo> loadlist() {
        try {
            List<T_goods> list = goodDao.loadlist();
            List<GoodsVo> gvos = new ArrayList<GoodsVo>();
            for (T_goods t : list) {
                GoodsVo gvo = new GoodsVo();
                gvo.setT_goods(t);
                gvo.setT_brand(brandDao.loadById(t.getBrand()));
                gvo.setT_categories(categoryDao.loadByGoods(t.getGoods()));
                gvo.setT_colors(colorDao.loadByGood(t.getGoods()));
                gvo.setT_agesections(ageDao.loadByGood(t.getGoods()));
                gvo.setT_faces(faceDao.loadByGood(t.getGoods()));
                gvo.setT_occasions(occasionDao.loadByGood(t.getGoods()));
                gvo.setT_attachments(goodDao.loadAttachmentByGood(t.getGoods()));
                gvos.add(gvo);
            }
            return gvos;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }


    /**
     * 文件上传
     * @param request
     * @return
     */
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


    /**
     * 删除附件
     * @param code
     * @param request
     * @return
     */
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
     * 添加记录
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


            String categorys = request.getParameter("category");
            System.out.println("categorys:" + categorys);
            String[] cts = categorys.split("\\,");
            for (String s : cts) {
                boolean ok = goodDao.addCategoryDiv(Integer.parseInt(s), goods);
            }


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
            gvo.setT_categories(categoryDao.loadByGoods(goods));
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

    /**
     * 通过商品编号删除商品信息
     * @param code
     * @param request
     * @return
     */
    public boolean delete(String code, HttpServletRequest request) {

        try {
            //删除类别分配
            boolean categoryOk = goodDao.deleteCategoryDiv(code);

            //删除颜色分配
            boolean ok = goodDao.deleteColorDiv(code);

            //删除年龄段分配
            boolean ok1 = goodDao.deleteAgeDiv(code);

            //删除脸型分配
            boolean ok2 = goodDao.deleteFaceDiv(code);

            //删除场景分配
            boolean ok3 = goodDao.deleteOccasionDiv(code);

            //删除附件信息
            List<T_attachment> t_attachments = goodDao.loadAttachmentByGood(code);
            for (T_attachment t: t_attachments) {
                boolean ok4 = goodDao.deleteAttachDiv(t.getAttachment());
                boolean ok5 = goodDao.deleteAttach(t.getAttachment());
            }

            String path = request.getSession().getServletContext().getRealPath("goods");

            for (T_attachment t: t_attachments) {
                File ff = new File(path+"/"+t.getPath());
                if(ff.exists()){
                    ff.delete();
                }
            }

            boolean ok6 = goodDao.delete(code);

            return  true;

        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }

    }

    /**
     *通过编号获取商品信息
     * @param code
     * @return
     */
    public GoodsVo loadById(String code) {
        GoodsVo gvo = new GoodsVo();
        T_goods t = goodDao.loadById(code);
        gvo.setT_goods(t);
        gvo.setT_brand(brandDao.loadById(t.getBrand()));
        gvo.setT_categories(categoryDao.loadByGoods(t.getGoods()));
        gvo.setT_colors(colorDao.loadByGood(t.getGoods()));
        gvo.setT_agesections(ageDao.loadByGood(t.getGoods()));
        gvo.setT_faces(faceDao.loadByGood(t.getGoods()));
        gvo.setT_occasions(occasionDao.loadByGood(t.getGoods()));
        gvo.setT_attachments(goodDao.loadAttachmentByGood(t.getGoods()));
        return  gvo;
    }


    /**
     * 更新
     * @param fileName
     * @param request
     * @param session
     * @return
     */
    @Transactional
    public GoodsVo update(String[] fileName, HttpServletRequest request, HttpSession session) {

        try {
            String goods = request.getParameter("good");
            System.out.println("goods:" + goods);

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


            boolean ok1 = goodDao.update(goods,
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

            //删除类别分配
            boolean categoryOk = goodDao.deleteCategoryDiv(goods);
            String categories = request.getParameter("category");
            System.out.println("categories:" + categories);
            String[] cts = categories.split("\\,");
            for (String s : cts) {
                boolean ok = goodDao.addCategoryDiv(Integer.parseInt(s), goods);
            }


            //删除颜色分配
            boolean colorOk = goodDao.deleteColorDiv(goods);
            String colors = request.getParameter("colors");
            System.out.println("colors:" + colors);

            String[] cs = colors.split("\\,");
            for (String s : cs) {

                boolean ok = goodDao.addColorDiv(Integer.parseInt(s), goods);
            }

            //删除年龄段分配
            boolean ageOK = goodDao.deleteAgeDiv(goods);
            String agesections = request.getParameter("agesections");
            System.out.println("agesections:" + agesections);
            //添加年龄段分配
            String[] as = agesections.split("\\,");
            for (String s : as) {
                boolean ok = goodDao.addAgesectionDiv(Integer.parseInt(s), goods);
            }

            //删除脸型分配
            boolean faceOK = goodDao.deleteFaceDiv(goods);
            //脸型分配
            String faces = request.getParameter("faces");
            System.out.println("faces:" + faces);
            String[] fs = faces.split("\\,");
            for (String s : fs) {
                boolean ok = goodDao.addFaceDiv(Integer.parseInt(s), goods);
            }



            //删除场景分配
            boolean occasionOk= goodDao.deleteOccasionDiv(goods);
            //场景分配
            String occasions = request.getParameter("occasions");
            System.out.println("occasions:" + occasions);

            String[] os = occasions.split("\\,");
            for (String s : os) {
                boolean ok = goodDao.addOccasionDiv(Integer.parseInt(s), goods);
            }

            System.out.println("fileName:" + fileName.length);



            //删除附件信息
            List<T_attachment> t_attachments = goodDao.loadAttachmentByGood(goods);
            for (T_attachment t: t_attachments) {
                boolean ok4 = goodDao.deleteAttachDiv(t.getAttachment());
            }
            //附件添加
            for (int i = 0; i < fileName.length; i++) {
                boolean ok = goodDao.addAttachmentDiv(fileName[i], goods, i);
            }

            GoodsVo gvo = new GoodsVo();

            T_goods t_good = goodDao.loadById(goods);
            gvo.setT_goods(t_good);
            gvo.setT_brand(brandDao.loadById(brand));
            gvo.setT_categories(categoryDao.loadByGoods(goods));
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

    public List<GoodsVo> recommend(HttpServletRequest request) {

        try {

            /**
             * 性别
             */
            String sex = request.getParameter("sex");

            /*
            场景
             */
            String occasion = request.getParameter("occasion");

            /**
             *瞳距
             */
            String pupil = request.getParameter("eyesdistance");

            /**
             * 脸型
             */
            String face = request.getParameter("face");

            /**
             * 年龄
             */
            String age = request.getParameter("age");

            List<T_goods> list = goodDao.recomend( sex,occasion,pupil,face,age);
            List<GoodsVo> gvos = new ArrayList<GoodsVo>();
            for (T_goods tg : list) {
                GoodsVo gvo = new GoodsVo();
                gvo.setT_goods(tg);
                gvo.setT_brand(brandDao.loadById(tg.getBrand()));
                gvo.setT_categories(categoryDao.loadByGoods(tg.getGoods()));
                gvo.setT_colors(colorDao.loadByGood(tg.getGoods()));
                gvo.setT_agesections(ageDao.loadByGood(tg.getGoods()));
                gvo.setT_faces(faceDao.loadByGood(tg.getGoods()));
                gvo.setT_occasions(occasionDao.loadByGood(tg.getGoods()));
                gvo.setT_attachments(goodDao.loadAttachmentByGood(tg.getGoods()));
                gvos.add(gvo);
            }
            return gvos;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }

    }
}
