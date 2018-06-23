package jx.lczj.service;

import jx.lczj.dao.FaceDao;
import jx.lczj.model.T_color;
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
    FaceDao faceDao;

    public List<T_face> loadFace() {
        return faceDao.loadFace();
    }
    @Transactional
    public boolean addFace(HttpServletRequest request,MultipartFile file) {

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

            //保存
            try {
                file.transferTo(targetFile);
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
    //update
    @Transactional
    public boolean updateFace(MultipartFile file, HttpServletRequest request) {
        try {
            System.out.println("开始");

            System.out.println(file == null);
            System.out.println(file.getOriginalFilename().equals(""));
            String editrgb = "";
            String path = request.getSession().getServletContext().getRealPath("face");
            T_face t_face = faceDao.loadByFace(Integer.parseInt(request.getParameter("face_edit_face")));
            //检测是否修改了图片
            if (file!=null && !file.getOriginalFilename().equals("")) {
                //先吧颜色查询出来

                String photo = t_face.getPhoto();
                //删除对应的图片
                String d_photo = path + "/" + photo;
                System.out.println(d_photo);
                File targetFile = new File(d_photo);
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
                editrgb = t_face.getPhoto();
            }
            return faceDao.updateFace(Integer.parseInt(request.getParameter("face_edit_face")), request.getParameter("face_edit_name"), editrgb);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 删除
     * @param file
     * @param request
     * @return
     */
    @Transactional
    public boolean deleteFace(int face, HttpServletRequest request) {
        try {

            System.out.println("开始");
            String path = request.getSession().getServletContext().getRealPath("face");

            //先吧颜色查询出来
            T_face t_face = faceDao.loadByFace(face);
            String photo = t_face.getPhoto();
            //删除对应的图片
            String d_photo = path + "/" + photo;
            System.out.println(d_photo);
            File targetFile = new File(d_photo);
            if (targetFile.exists()) {
                targetFile.delete();
            }
            return faceDao.deleteFace(face);
        } catch (Exception e) {
            System.out.println("FaceService.java.deleteFace");
            throw new RuntimeException(e.getMessage());
        }
    }
}
