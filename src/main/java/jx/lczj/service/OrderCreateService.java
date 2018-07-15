package jx.lczj.service;

import jx.lczj.dao.*;
import jx.lczj.model.*;
import jx.lczj.viewmodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 14260 on 2018/7/15.
 */
@Service
public class OrderCreateService {

    @Autowired
    OrderCreateDao orderCreateDao;

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
    ColorDao colorDao;

    @Resource
    FaceDao faceDao;

    @Resource
    OccasionDao occasionDao;
    @Resource
    AddressDao addressDao;

    /**
     * 创建订单
     * @param request
     * @return
     */
    @Transactional
    public OrderCreateVo add(HttpServletRequest request) {

        try {
            String mywear = request.getParameter("mywear");
            System.out.println("mywear:" + mywear);

            String customer = request.getParameter("customer");
            System.out.println("customer:" + customer);


            List<T_address>  t_addresses = addressDao.loadlist(customer);



            String address = null;
            if(t_addresses==null || t_addresses.size() == 0 ){
                address = null;
            }else{
                address = t_addresses.get(0).getAddress();

            }
            System.out.println("address:" + address);


            String order = UUID.randomUUID().toString().replace("-", "").substring(0, 9) + System.currentTimeMillis();
            System.out.println("order:" + order);

            boolean ok = orderCreateDao.addOrder(order, customer, address, new Date(), 0);

            boolean ok1 = mywearDao.updateOrder(order, mywear);

            //更新左眼参数
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
                    left_sign

            );

            System.out.println("left_sign:l");

            //更新右眼参数
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
                    right_sign
            );



            //获取返订单信息数据
            OrderCreateVo orderCreateVo = new OrderCreateVo();
            orderCreateVo.setT_order(orderCreateDao.loadById(order));
            if(t_addresses!=null && t_addresses.size() != 0 ){
                orderCreateVo.setT_address(t_addresses.get(0));
            }

            List<MywearVo>  orderdetailsVos = new ArrayList<MywearVo>();
            List<T_mywear> t_mywears = orderCreateDao.loadDetialByOrder(order);
            for (T_mywear to: t_mywears) {

                MywearVo mvo = new MywearVo();

                T_mywear t =  mywearDao.loadById(to.getMywear());
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
                orderdetailsVos.add(mvo);
            }
            orderCreateVo.setMywearVos(orderdetailsVos);

            return orderCreateVo;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }
}
