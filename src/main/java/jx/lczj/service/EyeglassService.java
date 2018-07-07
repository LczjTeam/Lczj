package jx.lczj.service;


import jx.lczj.dao.*;
import jx.lczj.model.T_attachment;
import jx.lczj.model.T_eyeglass;
import jx.lczj.viewmodel.EyeglassVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class EyeglassService {

    @Resource
    EyeglassDao eyeglassDao;
    @Resource
    BrandDao brandDao;
    @Resource
    CategoryDao categoryDao;
    @Resource
    EfficacyDao efficacyDao;
    @Resource
    MaskDao maskDao;
    @Resource
    StyleDao styleDao;

    /**
     * 添加
     * @param fileName
     * @param request
     * @param session
     * @return
     */
    @Transactional
    public EyeglassVo add(String[] fileName, HttpServletRequest request, HttpSession session) {

        String eyeglass = request.getParameter("eyeglass") ;
        String name = request.getParameter("add_name");
        int category =Integer.parseInt(request.getParameter("category"));
        int efficacy = Integer.parseInt(request.getParameter("efficacy"));
        int brand = Integer.parseInt(request.getParameter("brand"));
        int mask = Integer.parseInt(request.getParameter("mask"));
        int style = Integer.parseInt(request.getParameter("add_style"));
        float refraction = Float.parseFloat(request.getParameter("refraction"));
        int price =Integer.parseInt(request.getParameter("price"));
        System.out.println("eyeglass:"+eyeglass+"" +
                "\n category:"+category+
                "\n efficacy:"+efficacy+
                "\n brand:"+brand+
                "\n mask:"+mask+
                "\n style:"+style+
                "\n refraction:"+refraction+
                "\n price:"+price+
                "\n name:"+name);

        boolean ok = eyeglassDao.add(
                eyeglass,
                category,
                efficacy,
                brand,
                mask,
                style,
                refraction,
                price,
                name
        );

        System.out.println("fileName:" + fileName.length);

        for (int i = 0; i < fileName.length; i++) {
            boolean ok1 = eyeglassDao.addAttachmentDiv(eyeglass,fileName[i],  i);
        }
        EyeglassVo evo = new EyeglassVo();

        evo.setT_eyeglass(eyeglassDao.loadById(eyeglass));
        evo.setT_attachments(eyeglassDao.loadAttachmentByEyeglass(eyeglass));
        evo.setT_brand(brandDao.loadById(brand));
        evo.setT_category(categoryDao.loadById(category));
        evo.setT_efficacy(efficacyDao.loadById(efficacy));
        evo.setT_mask(maskDao.loadById(mask));
        evo.setT_style(styleDao.loadById(style));

        return evo;


    }

    /**
     * 获取眼镜信息表中的所有记录
     * @return
     */
    public List<EyeglassVo> loadlist() {
        try {
            List<T_eyeglass> list = eyeglassDao.loadlist();
            List<EyeglassVo> evos = new ArrayList<EyeglassVo>();
            for (T_eyeglass t : list) {
                EyeglassVo evo = new EyeglassVo();
                evo.setT_eyeglass(t);
                evo.setT_attachments(eyeglassDao.loadAttachmentByEyeglass(t.getEyeglass()));
                evo.setT_brand(brandDao.loadById(t.getBrand()));
                evo.setT_category(categoryDao.loadById(t.getCategory()));
                evo.setT_efficacy(efficacyDao.loadById(t.getEfficacy()));
                evo.setT_mask(maskDao.loadById(t.getMask()));
                evo.setT_style(styleDao.loadById(t.getStyle()));
                evos.add(evo);
            }
            return evos;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }

    /**
     * 通过id获取对应记录的眼镜信息
     * @param code
     * @return
     */
    public EyeglassVo loadById(String code) {
        try {
            EyeglassVo evo = new EyeglassVo();
            T_eyeglass t = eyeglassDao.loadById(code);
            evo.setT_eyeglass(t);
            evo.setT_attachments(eyeglassDao.loadAttachmentByEyeglass(t.getEyeglass()));
            evo.setT_brand(brandDao.loadById(t.getBrand()));
            evo.setT_category(categoryDao.loadById(t.getCategory()));
            evo.setT_efficacy(efficacyDao.loadById(t.getEfficacy()));
            evo.setT_mask(maskDao.loadById(t.getMask()));
            evo.setT_style(styleDao.loadById(t.getStyle()));
            return evo;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }

    /**
     * 更新某条记录的眼镜信息
     * @param fileName
     * @param request
     * @param session
     * @return
     */
    @Transactional
    public EyeglassVo update(String[] fileName, HttpServletRequest request, HttpSession session) {
        String eyeglass = request.getParameter("eyeglass");
        String name = request.getParameter("add_name");
        int category =Integer.parseInt(request.getParameter("category"));
        int efficacy = Integer.parseInt(request.getParameter("efficacy"));
        int brand = Integer.parseInt(request.getParameter("brand"));
        int mask = Integer.parseInt(request.getParameter("mask"));
        int style = Integer.parseInt(request.getParameter("add_style"));
        float refraction = Float.parseFloat(request.getParameter("refraction"));
        int price =Integer.parseInt(request.getParameter("price"));
        System.out.println("eyeglass:"+eyeglass+"" +
                "\n category:"+category+
                "\n efficacy:"+efficacy+
                "\n brand:"+brand+
                "\n mask:"+mask+
                "\n style:"+style+
                "\n refraction:"+refraction+
                "\n price:"+price+
                "\n name:"+name);

        boolean ok = eyeglassDao.update(
                eyeglass,
                category,
                efficacy,
                brand,
                mask,
                style,
                refraction,
                price,
                name
        );

        System.out.println("fileName:" + fileName.length);
        boolean ok4 = eyeglassDao.deleteAttachDiv(eyeglass);
        for (int i = 0; i < fileName.length; i++) {
            boolean ok1 = eyeglassDao.addAttachmentDiv(eyeglass,fileName[i],  i);
        }
        EyeglassVo evo = new EyeglassVo();

        evo.setT_eyeglass(eyeglassDao.loadById(eyeglass));
        evo.setT_attachments(eyeglassDao.loadAttachmentByEyeglass(eyeglass));
        evo.setT_brand(brandDao.loadById(brand));
        evo.setT_category(categoryDao.loadById(category));
        evo.setT_efficacy(efficacyDao.loadById(efficacy));
        evo.setT_mask(maskDao.loadById(mask));
        evo.setT_style(styleDao.loadById(style));

        return evo;

    }

    /**
     * 通过镜片id删除
     * @param code
     * @param request
     * @return
     */
    public boolean delete(String code, HttpServletRequest request) {
        try {


        //删除附件信息
        List<T_attachment> t_attachments = eyeglassDao.loadAttachmentByEyeglass(code);
        boolean ok4 = eyeglassDao.deleteAttachDiv(code);

        for (T_attachment t: t_attachments) {
            boolean ok5 = eyeglassDao.deleteAttach(t.getAttachment());
        }
        String path = request.getSession().getServletContext().getRealPath("goods");

        for (T_attachment t: t_attachments) {
            File ff = new File(path+"/"+t.getPath());
            if(ff.exists()){
                ff.delete();
            }
        }

        boolean ok6 = eyeglassDao.delete(code);

        return  true;
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }
}
