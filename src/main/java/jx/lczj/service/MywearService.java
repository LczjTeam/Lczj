package jx.lczj.service;

import jx.lczj.dao.*;
import jx.lczj.model.T_eyeglass;
import jx.lczj.model.T_goods;
import jx.lczj.model.T_mywear;
import jx.lczj.model.T_wearglass;
import jx.lczj.viewmodel.EyeglassVo;
import jx.lczj.viewmodel.GoodsVo;
import jx.lczj.viewmodel.MywearVo;
import jx.lczj.viewmodel.WearglassVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 14260 on 2018/7/9.
 */

@Service
public class MywearService {


    @Autowired
    MywearDao mywearDao ;

    @Resource
    GoodDao goodDao;

    @Resource
    EyeglassDao eyeglassDao;

    @Resource
    EfficacyDao efficacyDao;

    @Resource
    MaskDao maskDao;

    @Resource
    StyleDao styleDao;


    @Resource
    BrandDao brandDao ;

    @Resource
    CategoryDao categoryDao  ;


    @Resource
    FaceDao faceDao;

    @Resource
    OccasionDao occasionDao;



    @Resource
    ColorDao colorDao ;


    /**
     * 添加
     * @param request
     * @return
     */
    @Transactional
    public boolean add(HttpServletRequest request) {


        String mywear ;

        mywear = UUID.randomUUID().toString().replace("-","").substring(0,2)+System.currentTimeMillis();
        System.out.println("mywear:"+mywear);


        Integer occasion = null;  //场景
        if(request.getParameter("occasion")!=null){
            occasion = Integer.parseInt(request.getParameter("occasion"));
        }
        System.out.println("occasion:"+occasion);

        String customer = null; //顾客
        if(request.getParameter("customer")!=null){
            customer = request.getParameter("customer");
        }
        System.out.println("customer:"+customer);

        Integer color= null; //颜色
        if(request.getParameter("color")!=null){
            color = Integer.parseInt(request.getParameter("color"));
        }
        System.out.println("color:"+color);


        Integer face= null;//脸型
        if(request.getParameter("face")!=null){
            face = Integer.parseInt(request.getParameter("face"));
        }
        System.out.println("face:"+face);

        Integer age= null;//脸型
        if(request.getParameter("age")!=null){
            age = Integer.parseInt(request.getParameter("age"));
        }
        System.out.println("age:"+age);


        String goods= null;//镜框
        if(request.getParameter("goods")!=null){
            goods = request.getParameter("goods");
        }
        System.out.println("goods:"+goods);


        Integer pupil= -1;//瞳孔
        if(request.getParameter("eyesdistance")!=null){
            pupil = Integer.parseInt(request.getParameter("eyesdistance"));
        }
        System.out.println("pupil:"+pupil);


        String sex= "1";//性别
        if(request.getParameter("sex")!=null){
            sex = request.getParameter("sex");
        }
        System.out.println("sex:"+sex);


        String selfphoto= "";//自拍照
        if(request.getParameter("selfphoto")!=null){
            selfphoto = request.getParameter("selfphoto");
        }
        System.out.println("selfphoto:"+selfphoto);


        String showphoto= "";//试戴照
        if(request.getParameter("showphoto")!=null){
            showphoto = request.getParameter("showphoto");
        }
        System.out.println("showphoto:"+showphoto);


        Integer deal= 0;//是否成交
        Integer iscart= 0;//是否添加购物车
        if(request.getParameter("iscart")!=null){
            iscart = Integer.parseInt(request.getParameter("iscart"));
        }
        System.out.println("iscart:"+iscart);



        //添加试戴基本信息
        boolean ok =  mywearDao.add(
                mywear,
                occasion,
                customer,
                color,
                face,
                goods,
                pupil,
                sex,
                selfphoto,
                showphoto,
                deal,
                iscart,
                age
        );


        //添加左右镜片参数信息

        String left_type = null;
        if(request.getParameter("left_type")!=null){
            left_type = request.getParameter("left_type");
        }
        System.out.println("left_type:"+left_type);


        String left_eyeglass = null;
        if(request.getParameter("left")!=null){
            left_eyeglass = request.getParameter("left");
        }
        System.out.println("left_eyeglass:"+left_eyeglass);


        Integer left_degress = null;
        if(request.getParameter("left_ds")!=null){
            left_degress = Integer.parseInt(request.getParameter("left_ds"));
        }
        System.out.println("left_degress:"+left_degress);


        Integer left_asdegress = null;
        if(request.getParameter("left_sg")!=null){
            left_asdegress = Integer.parseInt(request.getParameter("left_sg"));
        }
        System.out.println("left_asdegress:"+left_asdegress);


        Float left_axis = null;
        if(request.getParameter("left_zw")!=null){
            left_axis = Float.parseFloat(request.getParameter("left_zw"));
        }
        System.out.println("left_axis:"+left_axis);


        String left_sign = "l";


        boolean ok1 = mywearDao.addEyeglass(
                mywear,
                left_eyeglass,
                left_degress,
                left_asdegress,
                left_axis,
                left_sign,
                left_type.equals("远视") ? 1:0

        );


        String right_type = null;
        if(request.getParameter("right_type")!=null){
            right_type  = request.getParameter("right_type");
        }
        System.out.println("right_type :"+right_type );


        String right_eyeglass = null;
        if(request.getParameter("right")!=null){
            right_eyeglass = request.getParameter("right");
        }
        System.out.println("right_eyeglass:"+right_eyeglass);


        Integer right_degress = null;

        if(request.getParameter("right_ds")!=null){
            right_degress = Integer.parseInt(request.getParameter("right_ds"));
        }
        System.out.println("right_degress:"+right_degress);


        Integer right_asdegress = null;
        if(request.getParameter("right_sg")!=null){
            right_asdegress = Integer.parseInt(request.getParameter("right_sg"));
        }
        System.out.println("right_asdegress:"+right_asdegress);


        Float right_axis = null;
        if(request.getParameter("right_zw")!=null){
            right_axis = Float.parseFloat(request.getParameter("right_zw"));
        }
        System.out.println("right_axis:"+right_axis);


        String right_sign = "r";


        boolean ok2 = mywearDao.addEyeglass(
                mywear,
                right_eyeglass,
                right_degress,
                right_asdegress,
                right_axis,
                right_sign,
                right_type.equals("远视") ? 1:0
        );


        return true;
    }

    /**
     * 通过会员编号获取试戴信息列表
     * @param request
     * @return
     */
    @Transactional
    public List<MywearVo> listByCustomer(HttpServletRequest request) {

        try {
            String customer = null; //顾客
            if (request.getParameter("customer") != null) {
                customer = request.getParameter("customer");
            }

            List<MywearVo> mywearVos = new ArrayList<MywearVo>();

            List<T_mywear> t_mywears = mywearDao.listByCustomer(customer);

            for (T_mywear t : t_mywears) {

                MywearVo mvo = new MywearVo();
                mvo.setT_mywear(t);
                mvo.setT_color(colorDao.loadByColor(t.getColor()));
                mvo.setT_face(faceDao.loadByFace(t.getFace()));
                mvo.setT_occasion(occasionDao.loadById(t.getOccasion()));

                //镜框信息
                GoodsVo gvo = new GoodsVo();
                T_goods tg = goodDao.loadById(t.getGoods());
                gvo.setT_goods(tg);
                gvo.setT_brand(brandDao.loadById(tg.getBrand()));
                gvo.setT_categories(categoryDao.loadByGoods(t.getGoods()));
                gvo.setT_attachments(goodDao.loadAttachmentByGood(t.getGoods()));
                mvo.setGoodsVo(gvo);

                //左眼镜片
                WearglassVo wvol = new WearglassVo();
                T_wearglass twl = mywearDao.loadWearglassByMywear(t.getMywear(), "l");
                wvol.setT_wearglass(twl);

                EyeglassVo evol = new EyeglassVo();
                T_eyeglass tel = eyeglassDao.loadById(twl.getEyeglass());
                evol.setT_eyeglass(tel);
                evol.setT_brand(brandDao.loadById(tel.getBrand()));
                wvol.setEyeglassVo(evol);

                mvo.setLeftEyeglass(wvol);

                //右眼镜片
                WearglassVo wvor = new WearglassVo();
                T_wearglass twr = mywearDao.loadWearglassByMywear(t.getMywear(), "r");
                wvor.setT_wearglass(twr);

                EyeglassVo evor = new EyeglassVo();
                T_eyeglass ter = eyeglassDao.loadById(twr.getEyeglass());
                evor.setT_eyeglass(ter);
                evor.setT_brand(brandDao.loadById(ter.getBrand()));
                wvor.setEyeglassVo(evor);

                mvo.setRightEyeglass(wvor);

                mywearVos.add(mvo);

            }

            return mywearVos;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 通过试戴编号删除试戴信息
     * @param request
     * @return
     */
    @Transactional
    public boolean delete(HttpServletRequest request) {


        try {

            String mywear = "";
            if(request.getParameter("mywear")!=null){
                mywear = request.getParameter("mywear");
            }else{
                return  false;
            }
            System.out.println("mywear:" + mywear);

            T_mywear t_mywear = mywearDao.loadById(mywear);


            //删除镜片信息
            boolean ok1 = mywearDao.deleteWearglassByMywear(t_mywear.getMywear());

            //删除试戴信息
            boolean ok2 = mywearDao.delete(t_mywear.getMywear());


            String path = request.getSession().getServletContext().getRealPath("/");

            //物理删除文件信息
            if( !t_mywear.getSelfphoto().contains("models")) {
                File f1 = new File(path + t_mywear.getSelfphoto());
                if (f1.exists()) f1.delete();
            }

            File f2 = new File(path + t_mywear.getShowphoto());
            if (f2.exists()) f2.delete();

            return true;

        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }

    /**
     * 通过编号获取试戴详情
     * @param request
     * @return
     */
    @Transactional
    public MywearVo loadById(HttpServletRequest request) {
        try {
            String mywear= "";
            if (request.getParameter("mywear") != null) {
                mywear = request.getParameter("mywear");
            }else{
                return  null;
            }
            T_mywear t   = mywearDao.loadById(mywear);

            MywearVo mvo = new MywearVo();
            mvo.setT_mywear(t);
            mvo.setT_color(colorDao.loadByColor(t.getColor()));
            mvo.setT_face(faceDao.loadByFace(t.getFace()));
            mvo.setT_occasion(occasionDao.loadById(t.getOccasion()));

            //镜框信息
            GoodsVo gvo = new GoodsVo();
            T_goods tg = goodDao.loadById(t.getGoods());
            gvo.setT_goods(tg);
            gvo.setT_brand(brandDao.loadById(tg.getBrand()));
            gvo.setT_categories(categoryDao.loadByGoods(t.getGoods()));
            gvo.setT_colors(colorDao.loadByGood(t.getGoods()));
            gvo.setT_attachments(goodDao.loadAttachmentByGood(t.getGoods()));
            mvo.setGoodsVo(gvo);

            //左眼镜片
            WearglassVo wvol = new WearglassVo();
            T_wearglass twl = mywearDao.loadWearglassByMywear(t.getMywear(), "l");
            wvol.setT_wearglass(twl);

            EyeglassVo evol = new EyeglassVo();
            T_eyeglass tel = eyeglassDao.loadById(twl.getEyeglass());
            evol.setT_eyeglass(tel);
            evol.setT_brand(brandDao.loadById(tel.getBrand()));
            evol.setT_attachments(eyeglassDao.loadAttachmentByEyeglass(tel.getEyeglass()));
            evol.setT_efficacy(efficacyDao.loadById(tel.getEfficacy()));
            evol.setT_mask(maskDao.loadById(tel.getMask()));
            evol.setT_style(styleDao.loadById(tel.getStyle()));
            wvol.setEyeglassVo(evol);

            mvo.setLeftEyeglass(wvol);

            //右眼镜片
            WearglassVo wvor = new WearglassVo();
            T_wearglass twr = mywearDao.loadWearglassByMywear(t.getMywear(), "r");
            wvor.setT_wearglass(twr);

            EyeglassVo evor = new EyeglassVo();
            T_eyeglass ter = eyeglassDao.loadById(twl.getEyeglass());
            evor.setT_eyeglass(ter);
            evor.setT_brand(brandDao.loadById(ter.getBrand()));
            evor.setT_attachments(eyeglassDao.loadAttachmentByEyeglass(ter.getEyeglass()));
            evor.setT_efficacy(efficacyDao.loadById(ter.getEfficacy()));
            evor.setT_mask(maskDao.loadById(ter.getMask()));
            evor.setT_style(styleDao.loadById(ter.getStyle()));
            wvor.setEyeglassVo(evor);
            mvo.setRightEyeglass(wvor);

            return  mvo;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 更新
     * @param request
     * @return
     */
    @Transactional
    public MywearVo update(HttpServletRequest request) {

        try {

            //更新颜色
            String mywear = request.getParameter("mywear");
            System.out.println("mywaer:" + mywear);

//            int color = Integer.parseInt(request.getParameter("color"));
//            System.out.println("color:" + color);
//
//            boolean ok = mywearDao.updateColor(mywear,
//                    color
//            );

            //更新左眼参数
            String left_type = null;
            if(request.getParameter("left_type")!=null){
                left_type = request.getParameter("left_type");
            }
            System.out.println("left_type:"+left_type);


            int left_degress = Integer.parseInt(request.getParameter("left_ds"));
            System.out.println("left_degress:" + left_degress);

            int left_asdegress = Integer.parseInt(request.getParameter("left_sg"));
            System.out.println("left_asdegress:" + left_asdegress);

            float left_axis = Float.parseFloat(request.getParameter("left_zw"));
            System.out.println("left_axis:" + left_axis);

            String left_sign = "l";

            boolean ok2 = mywearDao.updateEyeglass(
                    mywear,
                    left_degress,
                    left_asdegress,
                    left_axis,
                    left_sign,
                    left_type.equals("远视") ? 1:0

            );

            System.out.println("left_sign:l");



            //更新右眼参数
            String right_type = null;
            if(request.getParameter("right_type")!=null){
                right_type  = request.getParameter("right_type");
            }
            System.out.println("right_type :"+right_type );

            int right_degress = Integer.parseInt(request.getParameter("right_ds"));
            System.out.println("right_degress:" + right_degress);

            int right_asdegress = Integer.parseInt(request.getParameter("right_sg"));
            System.out.println("right_asdegress:" + right_asdegress);

            float right_axis = Float.parseFloat(request.getParameter("right_zw"));
            System.out.println("right_axis:" + right_axis);


            String right_sign = "r";


            boolean ok3 = mywearDao.updateEyeglass(
                    mywear,
                    right_degress,
                    right_asdegress,
                    right_axis,
                    right_sign,
                    right_type.equals("远视") ? 1:0
            );

            T_mywear t   = mywearDao.loadById(mywear);

            MywearVo mvo = new MywearVo();
            mvo.setT_mywear(t);
            mvo.setT_color(colorDao.loadByColor(t.getColor()));
            mvo.setT_face(faceDao.loadByFace(t.getFace()));
            mvo.setT_occasion(occasionDao.loadById(t.getOccasion()));

            //镜框信息
            GoodsVo gvo = new GoodsVo();
            T_goods tg = goodDao.loadById(t.getGoods());
            gvo.setT_goods(tg);
            gvo.setT_brand(brandDao.loadById(tg.getBrand()));
            gvo.setT_categories(categoryDao.loadByGoods(t.getGoods()));
            gvo.setT_colors(colorDao.loadByGood(t.getGoods()));
            gvo.setT_attachments(goodDao.loadAttachmentByGood(t.getGoods()));
            mvo.setGoodsVo(gvo);

            //左眼镜片
            WearglassVo wvol = new WearglassVo();
            T_wearglass twl = mywearDao.loadWearglassByMywear(t.getMywear(), "l");
            wvol.setT_wearglass(twl);

            EyeglassVo evol = new EyeglassVo();
            T_eyeglass tel = eyeglassDao.loadById(twl.getEyeglass());
            evol.setT_eyeglass(tel);
            evol.setT_brand(brandDao.loadById(tel.getBrand()));
            evol.setT_attachments(eyeglassDao.loadAttachmentByEyeglass(tel.getEyeglass()));
            evol.setT_efficacy(efficacyDao.loadById(tel.getEfficacy()));
            evol.setT_mask(maskDao.loadById(tel.getMask()));
            evol.setT_style(styleDao.loadById(tel.getStyle()));
            wvol.setEyeglassVo(evol);

            mvo.setLeftEyeglass(wvol);

            //右眼镜片
            WearglassVo wvor = new WearglassVo();
            T_wearglass twr = mywearDao.loadWearglassByMywear(t.getMywear(), "r");
            wvor.setT_wearglass(twr);

            EyeglassVo evor = new EyeglassVo();
            T_eyeglass ter = eyeglassDao.loadById(twl.getEyeglass());
            evor.setT_eyeglass(ter);
            evor.setT_brand(brandDao.loadById(ter.getBrand()));
            evor.setT_attachments(eyeglassDao.loadAttachmentByEyeglass(ter.getEyeglass()));
            evor.setT_efficacy(efficacyDao.loadById(ter.getEfficacy()));
            evor.setT_mask(maskDao.loadById(ter.getMask()));
            evor.setT_style(styleDao.loadById(ter.getStyle()));
            wvor.setEyeglassVo(evor);
            mvo.setRightEyeglass(wvor);

            return  mvo;

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
