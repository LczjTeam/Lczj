package jx.lczj.service;

import jx.lczj.dao.MywearDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by 14260 on 2018/7/9.
 */

@Service
public class MywearService {


    @Autowired
    MywearDao  mywearDao;

    @Transactional
    public boolean add(HttpServletRequest request) {


        String mywear ;

        mywear = UUID.randomUUID().toString().replace("-","");

        Integer occasion = null;  //场景
        if(request.getParameter("occasion")!=null){
            occasion = Integer.parseInt(request.getParameter("occasion"));
        }

        String customer = null; //顾客
        if(request.getParameter("customer")!=null){
            customer = request.getParameter("occasion");
        }

        Integer color= null; //颜色
        if(request.getParameter("color")!=null){
            color = Integer.parseInt(request.getParameter("color"));
        }


        Integer face= null;//脸型
        if(request.getParameter("face")!=null){
            face = Integer.parseInt(request.getParameter("face"));
        }

        String goods= null;//镜框
        if(request.getParameter("goods")!=null){
            goods = request.getParameter("goods");
        }
        Integer pupil= -1;//瞳孔
        if(request.getParameter("eyesdistance")!=null){
            pupil = Integer.parseInt(request.getParameter("eyesdistance"));
        }

        String sex= "1";//性别
        if(request.getParameter("sex")!=null){
            sex = request.getParameter("sex");
        }
        String selfphoto= "";//自拍照
        if(request.getParameter("selfphoto")!=null){
            selfphoto = request.getParameter("selfphoto");
        }
        String showphoto= "";//试戴照
        if(request.getParameter("showphoto")!=null){
            selfphoto = request.getParameter("showphoto");
        }
        Integer deal= 0;//是否成交
        Integer iscart= 0;//是否添加购物车
        if(request.getParameter("iscart")!=null){
            iscart = Integer.parseInt(request.getParameter("iscart"));
        }


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
                iscart
        );


        //添加左右镜片参数信息

        String left_eyeglass = null;
        Integer left_degress = null;
        if(request.getParameter("left_ds")!=null){
            left_degress = Integer.parseInt(request.getParameter("left_ds"));
        }


        Integer left_asdegress = null;
        if(request.getParameter("left_sg")!=null){
            left_asdegress = Integer.parseInt(request.getParameter("left_sg"));
        }

        Integer left_axis = null;
        if(request.getParameter("left_zw")!=null){
            left_axis = Integer.parseInt(request.getParameter("left_zw"));
        }

        String left_sign = "l";


        boolean ok1 = mywearDao.addEyeglass(
                mywear,
                left_eyeglass,
                left_degress,
                left_asdegress,
                left_axis,
                left_sign

        );


        String right_eyeglass = null;
        Integer right_degress = null;

        if(request.getParameter("right_zw")!=null){
            right_degress = Integer.parseInt(request.getParameter("right_zw"));
        }
        Integer right_asdegress = null;
        if(request.getParameter("right_sg")!=null){
            right_asdegress = Integer.parseInt(request.getParameter("right_sg"));
        }


        Integer right_axis = null;
        if(request.getParameter("right_zw")!=null){
            right_axis = Integer.parseInt(request.getParameter("right_zw"));
        }

        String right_sign = "r";


        boolean ok2 = mywearDao.addEyeglass(
                mywear,
                right_eyeglass,
                right_degress,
                right_asdegress,
                right_axis,
                right_sign
        );


        return true;
    }
}
