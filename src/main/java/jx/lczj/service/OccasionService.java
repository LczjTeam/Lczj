package jx.lczj.service;


import jx.lczj.dao.OccasionDao;
import jx.lczj.model.T_occasion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Service
public class OccasionService {
    @Resource
    OccasionDao occasionDao;

    /**
     * 添加
     */
    @Transactional
    public boolean addocassion(int occasion, String name, String photo) {
        return occasionDao.insert(occasion,name,photo);
    }

    /**
     * 获取场景信息
     * @return
     */
    public List<T_occasion> loadList() {
        return occasionDao.loadList();
    }

    /**
     * 删除场景信息
     * @param occasion
     * @return
     */
    @Transactional
    public boolean delete(int occasion, HttpServletRequest request) {
        try {

            System.out.println("开始");
            String path = request.getSession().getServletContext().getRealPath("occasion");

            //先吧颜色查询出来
            T_occasion t_occasion = occasionDao.loadById(occasion);
            int file = t_occasion.getOccasion();
            //删除对应的图片
            String d_file1 = path + "/" + file+ "_0.png";
            String d_file2 = path + "/" + file+ "_1.png";;
            System.out.println(d_file1);
            File targetFile1 = new File(d_file1);
            if (targetFile1.exists()) {
                targetFile1.delete();
            }
            System.out.println(d_file2);
            File targetFile2 = new File(d_file2);
            if (targetFile2.exists()) {
                targetFile2.delete();
            }
            return occasionDao.delete(occasion);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 通过id获取场景信息
     * @param occasion
     * @return
     */
    public T_occasion loadByoccasion(int occasion) {
        return occasionDao.loadById(occasion);
    }


    /**
     * 更新
     * @param file1
     * @param file2
     * @param request
     * @return
     */
    @Transactional
    public boolean update(MultipartFile file1,MultipartFile file2, HttpServletRequest request) {
        try {
            System.out.println("开始");
            //System.out.println(request.getParameter("occasion_edit_occasion")+ request.getParameter("occasion_edit_name")+request.getParameter("occasion_edit_photo"));
            System.out.println(file1.getName());
            System.out.println(file1.getOriginalFilename().equals(""));
            System.out.println(file2.getName());
            System.out.println(file2.getOriginalFilename().equals(""));


            String path = request.getSession().getServletContext().getRealPath("occasion");
            //检测是否修改了图片
            if (file1!=null && !file1.getOriginalFilename().equals("")) {
                //删除对应的图片
                String d_file1 = path + "/" + file1;
                System.out.println(d_file1);
                File targetFile1 = new File(d_file1);
                if (targetFile1.exists()) {
                    targetFile1.delete();
                    System.out.println("已删除图片1。");
                }


               /* String ctimes = System.currentTimeMillis() + "";*/

                System.out.println("开始，保存");
                String fileName1 =request.getParameter("occasion_edit_occasion") + "_0.png";
                System.out.println(fileName1);
                System.out.println(path);
                File editFile1 = new File(path, fileName1);
                if (!editFile1.exists()) {
                    editFile1.mkdirs();
                    System.out.println("目录创建");
                }

                //保存
                try {
                    file1.transferTo(editFile1);
                    System.out.println("文件以保存1。");
                } catch (Exception e) {
                    System.out.println("文件保存1出错。");
                    e.printStackTrace();
                    return false;
                }

            }
            if (file2!=null && !file2.getOriginalFilename().equals("")) {
                //删除对应的图片
                String d_file2 = path + "/" + file2;
                System.out.println(d_file2);
                File targetFile2 = new File(d_file2);
                if (targetFile2.exists()) {
                    targetFile2.delete();
                    System.out.println("已删除图片2。");
                }

                String ctimes = System.currentTimeMillis() + "";

                System.out.println("开始，保存");
                String fileName2 =request.getParameter("occasion_edit_occasion") + "_1.png";
                System.out.println(fileName2);
                System.out.println(path);
                File editFile1 = new File(path, fileName2);
                if (!editFile1.exists()) {
                    editFile1.mkdirs();
                }

                //保存
                try {
                    file2.transferTo(editFile1);
                    System.out.println("文件以保存2。");
                } catch (Exception e) {
                   /* System.out.println("文件保存2出错。");*/
                    e.printStackTrace();
                    return false;
                }

            }
            System.out.println(Integer.parseInt(request.getParameter("occasion_edit_occasion")) + request.getParameter("occasion_edit_name") + request.getParameter("occasion_edit_photo"));
            return occasionDao.update(Integer.parseInt(request.getParameter("occasion_edit_occasion")), request.getParameter("occasion_edit_name"), request.getParameter("occasion_edit_photo"));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 添加场景
     * @param file1
     * @param request
     * @return
     */
    @Transactional
    public boolean add(MultipartFile file1,MultipartFile file2, HttpServletRequest request) {

        try {
            String ctimes = System.currentTimeMillis() + "";
            System.out.println("开始");
            String path = request.getSession().getServletContext().getRealPath("occasion");

            String file1Name = request.getParameter("occasion_add_occasion") + "_0.png";
            String file2Name = request.getParameter("occasion_add_occasion") + "_1.png";
            System.out.println(file1Name);
            System.out.println(file2Name);
            System.out.println(path);
            File targetFile1 = new File(path, file1Name);
            File targetFile2 = new File(path, file2Name);
            if (!targetFile1.exists()) {
                targetFile1.mkdirs();
            }
            if (!targetFile2.exists()) {
                targetFile2.mkdirs();
            }

            //保存
            try {
                file1.transferTo(targetFile1);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            try {
                file2.transferTo(targetFile2);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

            System.out.println(Integer.parseInt(request.getParameter("occasion_add_occasion")) + request.getParameter("occasion_add_name") + request.getParameter("occasion_add_photo"));

            return occasionDao.insert(Integer.parseInt(request.getParameter("occasion_add_occasion")), request.getParameter("occasion_add_name"), request.getParameter("occasion_add_photo"));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }



}
