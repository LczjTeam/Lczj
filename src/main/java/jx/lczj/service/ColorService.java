package jx.lczj.service;

import jx.lczj.dao.ColorDao;
import jx.lczj.model.T_color;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.enterprise.inject.New;
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
    public boolean addColor(int color, String name, String rgb) {
        return colorDao.addColor(color, name, rgb);
    }

    /**
     * 获取颜色列表
     *
     * @return
     */
    public List<T_color> loadColors() {

        return colorDao.loadColors();
    }

    /**
     * 删除颜色
     *
     * @param color
     * @param request
     * @return
     */
    @Transactional
    public boolean deleteColor(int color, HttpServletRequest request) {
        try {

            System.out.println("开始");
            String path = request.getSession().getServletContext().getRealPath("colors");

            //先吧颜色查询出来
            T_color t_color = colorDao.loadByColor(color);
            String rgb = t_color.getRgb();
            //删除对应的图片
            String d_rgb = path + "/" + rgb;
            System.out.println(d_rgb);
            File targetFile = new File(d_rgb);
            if (targetFile.exists()) {
                targetFile.delete();
            }
            return colorDao.deleteColor(color);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public T_color loadByColor(int color) {
        return colorDao.loadByColor(color);
    }

    /**
     * 添加颜色
     *
     * @param file
     * @param request
     * @return
     */
    @Transactional
    public boolean add(MultipartFile file, HttpServletRequest request) {

        try {
            String ctimes = System.currentTimeMillis() + "";
            System.out.println("开始");
            String path = request.getSession().getServletContext().getRealPath("colors");

            String fileName = ctimes + ".jpg";
            System.out.println(fileName);
            System.out.println(path);
            File targetFile = new File(path, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }

            //保存
            try {
                file.transferTo(targetFile);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            String rgb = fileName;
            System.out.println(Integer.parseInt(request.getParameter("color_add_color")) + request.getParameter("color_add_name") + rgb);

            return colorDao.addColor(Integer.parseInt(request.getParameter("color_add_color")), request.getParameter("color_add_name"), rgb.trim());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    /**
     * 修改颜色信息
     * @param file
     * @param request
     * @return
     */
    @Transactional
    public boolean update(MultipartFile file, HttpServletRequest request) {
        try {
            System.out.println("开始");

            System.out.println(file == null);
            System.out.println(file.getOriginalFilename().equals(""));
            String editrgb = "";
            String path = request.getSession().getServletContext().getRealPath("colors");
            T_color t_color = colorDao.loadByColor(Integer.parseInt(request.getParameter("color_edit_color")));
            //检测是否修改了图片
            if (file!=null && !file.getOriginalFilename().equals("")) {
                //先吧颜色查询出来

                String rgb = t_color.getRgb();
                //删除对应的图片
                String d_rgb = path + "/" + rgb;
                System.out.println(d_rgb);
                File targetFile = new File(d_rgb);
                if (targetFile.exists()) {
                    targetFile.delete();
                }

                String ctimes = System.currentTimeMillis() + "";
                System.out.println("开始，保存");
                String fileName = ctimes + ".jpg";
                System.out.println(fileName);
                System.out.println(path);
                File editFile = new File(path, fileName);
                if (!editFile.exists()) {
                    editFile.mkdirs();
                }

                //保存
                try {
                    file.transferTo(editFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                 editrgb = fileName;

            }else{
                 editrgb = t_color.getRgb();
            }
            System.out.println(Integer.parseInt(request.getParameter("color_edit_color")) + request.getParameter("color_edit_name") + editrgb);
            return colorDao.updateColor(Integer.parseInt(request.getParameter("color_edit_color")), request.getParameter("color_edit_name"), editrgb);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
