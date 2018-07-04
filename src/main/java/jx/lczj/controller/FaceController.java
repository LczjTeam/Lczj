package jx.lczj.controller;


import jx.lczj.anotation.Privilege;
import jx.lczj.model.T_face;
import jx.lczj.service.FaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("face")
public class FaceController {
    @Autowired
    FaceService faceService;

    @RequestMapping("/list")
    @ResponseBody
    public List<T_face> loadFace(){return faceService.loadFace();}


    /**
     * 添加
     * @param file
     * @return
     */
    @Privilege(value = "脸型管理")
    @RequestMapping("/addFace")
    @ResponseBody
    public boolean addFace(HttpServletRequest request, MultipartFile file, MultipartFile file1){
        return  faceService.addFace(request,file,file1);

    }
    @RequestMapping("/loadByFace")
    @ResponseBody
    public T_face loadByFace(String face){return faceService.loadByFace(Integer.parseInt(face));}

    @Privilege(value = "脸型管理")
    @RequestMapping("/update")
    @ResponseBody
    public boolean updateFace(MultipartFile file ,MultipartFile file1,HttpServletRequest request){return faceService.updateFace(file,file1,request);}

    @Privilege(value = "脸型管理")
    @RequestMapping("/delete")
    @ResponseBody
    public boolean deleteFace(int face ,HttpServletRequest request){
        return faceService.deleteFace(face,request);
    }
}
