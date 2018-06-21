package jx.lczj.service;

import jx.lczj.dao.ColorDao;
import jx.lczj.model.T_color;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
@Service
public class ColorService {
    @Resource
    ColorDao colorDao;
    /**
     * 添加
     */
    @Transactional
    public boolean addColor(int color,String name,String rgb ){
        return colorDao.addColor(color,name,rgb);
    }

    public List<T_color> loadColors() {

        return colorDao.loadColors();
    }
    @Transactional
    public boolean deleteColor(int color,HttpServletRequest request) {
        try {

            System.out.println("开始");
            String path = request.getSession().getServletContext().getRealPath("colors");

            //先吧颜色查询出来
            T_color t_color = colorDao.loadColor(color);
            String rgb = t_color.getRgb();
            //删除对应的图片
            String d_rgb = path + "/" + rgb;
            System.out.println(d_rgb);
            File targetFile = new File(d_rgb);
            if (targetFile.exists()) {
                targetFile.delete();
            }
            return colorDao.deleteColor(color);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
     public boolean loadByColor(int color) {return colorDao.loadByColor(color);}

     public T_color loadColor(int color) { return colorDao.loadColor(color);}

    @Transactional
    public boolean updataColor(int color,String name,String rgb) { return colorDao.updataColor(color,name,rgb);}
}
