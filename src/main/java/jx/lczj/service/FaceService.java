package jx.lczj.service;

import jx.lczj.dao.FaceDao;
import jx.lczj.model.T_face;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Service
public class FaceService {
    @Resource
    FaceDao faceDao ;

    /**
     * 获取脸型信息
     * @return
     */
    public List<T_face> loadFace() {
        return faceDao.loadFace();
    }

    /**
     * 添加脸型记录
     * @param request
     * @param file
     * @param file1
     * @return
     */
    @Transactional
    public boolean addFace(HttpServletRequest request,MultipartFile file,MultipartFile file1) {

        try {
            String ctimes = System.currentTimeMillis() + "";
            System.out.println("开始");
            String path = request.getSession().getServletContext().getRealPath("face");

            String fileName = ctimes + ".jpg";
            System.out.println(fileName);
            System.out.println(path);
            File targetFile = new File(path, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }

            File targetFile1 = new File(path,  ctimes + "_1.jpg");
            if (!targetFile1.exists()) {
                targetFile1.mkdirs();
            }
            //保存
            try {
                file.transferTo(targetFile);
                file1.transferTo(targetFile1);
                System.out.println("已在"+path+"路径下："+"保存文件："+fileName);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            String photo = fileName;

            return faceDao.addFace(Integer.parseInt(request.getParameter("face_add_face")), request.getParameter("face_add_name"), photo.trim());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }


    }

    /**
     * 通过face查找数据
     * @param face
     * @return
     */
    public T_face loadByFace(int face) {
        return faceDao.loadByFace(face);
    }


    /**
     * 更新记录
     * @param file
     * @param file1
     * @param request
     * @return
     */
    @Transactional
    public boolean updateFace(MultipartFile file,MultipartFile file1, HttpServletRequest request) {
        try {
            System.out.println("开始---通过face查找信息");
            String path = request.getSession().getServletContext().getRealPath("face");

            //先把颜色查询出来
            T_face t_face = faceDao.loadByFace(Integer.parseInt(request.getParameter("face_edit_face")));
            String photo = t_face.getPhoto();

            String s_photo = path + "/"+ photo.split("\\.")[0] + "_1.jpg";

            System.out.println(photo);
            //删除对应的图片
            String d_photo = path + "/" + photo;
            System.out.println(d_photo);
            File targetFile = new File(d_photo);
            if (targetFile.exists()) {

                targetFile.delete();
                System.out.println("已删除"+path+"路径下文件:"+photo);

            }

            File targetFile1 = new File(s_photo);
            if (targetFile1.exists()) {

                targetFile1.delete();
                System.out.println("已删除"+path+"路径下文件:"+photo+"_1");

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
                File editFile1 = new File(path, ctimes + "_1.jpg");


            //保存
                try {
                    file.transferTo(editFile);
                    file1.transferTo(editFile1);
                    System.out.println("保存新文件已成功,文件名："+fileName+"路径："+path);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                String editrgb = fileName;
            return faceDao.updateFace(Integer.parseInt(request.getParameter("face_edit_face")), request.getParameter("face_edit_name"), editrgb.trim());
        } catch (Exception e) {
            System.out.println("FaceService");
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 删除记录
     * @param request
     * @return
     */
    @Transactional
    public boolean deleteFace(int face, HttpServletRequest request) {
        try {

            System.out.println("开始");
            String path = request.getSession().getServletContext().getRealPath("face");

            //先把颜色查询出来
            T_face t_face = faceDao.loadByFace(face);
            String photo = t_face.getPhoto();
            String s_photo = path + "/"+ photo.split("\\.")[0] + "_1.jpg";

            //删除对应的图片
            String d_photo = path + "/" + photo;
            System.out.println(d_photo);
            File targetFile = new File(d_photo);
            if (targetFile.exists()) {
                targetFile.delete();
            }

            File targetFile1 = new File(s_photo);
            if (targetFile1.exists()) {
                targetFile1.delete();
            }
            return faceDao.deleteFace(face);
        } catch (Exception e) {
            System.out.println("FaceService.java.deleteFace");
            throw new RuntimeException(e.getMessage());
        }
    }
}
