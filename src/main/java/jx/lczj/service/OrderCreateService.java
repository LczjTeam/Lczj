package jx.lczj.service;

import jx.lczj.dao.*;
import jx.lczj.model.*;
import jx.lczj.viewmodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    OccasionDao  occasionDao;
    @Resource
    AddressDao addressDao;


    /**
     * 通过编号获取订单详情
     * @param request
     * @return
     */
    public OrderCreateVo loadById(HttpServletRequest request) {

        String order = request.getParameter("order");
        System.out.println("order:" + order);

        //获取返订单信息数据
        OrderCreateVo orderCreateVo = new OrderCreateVo();
        T_order t_order = orderCreateDao.loadById(order);
        orderCreateVo.setT_order(t_order);

        orderCreateVo.setT_address(addressDao.loadByAddress(t_order.getAddress()));

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

    }

    /**
     * 通过会员编号以及状态获取订单信息
     * @param request
     * @return
     */
    @Transactional
    public List<OrderCreateVo> loadList(HttpServletRequest request) {

        try {
            String state = request.getParameter("state");
            String customer = request.getParameter("customer");
            List<OrderCreateVo> ods = new ArrayList<OrderCreateVo>();
            List<T_order> t_orders = null;
            if (state == null) {
                t_orders = orderCreateDao.list(customer);
            }else {
                t_orders = orderCreateDao.listByState(customer, Integer.parseInt(state));
            }

            for (T_order t_order : t_orders) {
                //获取返订单信息数据
                OrderCreateVo orderCreateVo = new OrderCreateVo();
                orderCreateVo.setT_order(t_order);

                orderCreateVo.setT_address(addressDao.loadByAddress(t_order.getAddress()));

                List<MywearVo> orderdetailsVos = new ArrayList<MywearVo>();
                List<T_mywear> t_mywears = orderCreateDao.loadDetialByOrder(t_order.getOrder());
                for (T_mywear to : t_mywears) {
                    MywearVo mvo = new MywearVo();
                    T_mywear t = mywearDao.loadById(to.getMywear());
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
                ods.add(orderCreateVo);
            }

            return ods;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 删除订单信息
     * @param request
     * @return
     */
    @Transactional
    public boolean delete(HttpServletRequest request) {

        try {

            String order = request.getParameter("order");
            System.out.println("order:" + order);

            List<T_mywear> t_mywears = orderCreateDao.loadDetialByOrder(order);

            for (T_mywear t : t_mywears) {

                boolean ok1 = mywearDao.deleteWearglassByMywear(t.getMywear());
                boolean ok2 = mywearDao.delete(t.getMywear());

                String path = request.getSession().getServletContext().getRealPath("/");

                //物理删除文件信息
                if (!t.getSelfphoto().contains("models")) {
                    File f1 = new File(path + t.getSelfphoto());
                    if (f1.exists()) f1.delete();
                }

                File f2 = new File(path + t.getShowphoto());
                if (f2.exists()) f2.delete();

            }

            boolean ok = orderCreateDao.delete(order);

            return  true;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }

    }

    public List<T_order> searchByTime(String customer, String time) {
        String start = time.substring(0,10);
        String end = time.substring(13,23);
        System.out.println("start:"+start);
        System.out.println("end:"+end);

        Date start_time = new Date();
        Date end_time = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try{
            start_time = sdf.parse(start);
            end_time = sdf.parse(end);
        }catch (ParseException e){
            e.printStackTrace();
        }

        int i = start_time.compareTo(end_time);
        if (i > 0){
            System.out.println("start_time:"+start_time);
            System.out.println("end_time:"+end_time);
        }

        return orderCreateDao.searchByTime(customer,start_time,end_time);
    }
}
