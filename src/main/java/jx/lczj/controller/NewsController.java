package jx.lczj.controller;

import jx.lczj.anotation.Privilege;
import jx.lczj.model.T_menu;
import jx.lczj.model.T_news;
import jx.lczj.service.AdminService;
import jx.lczj.service.MenuService;
import jx.lczj.service.NewsService;
import jx.lczj.viewmodel.MenuVo;
import jx.lczj.viewmodel.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("news")
public class NewsController {

    @Autowired
    NewsService newsService;



    /**
     * 添加
     * @return
     */
    @Privilege(value = "眼镜二三事")
    @RequestMapping("/add")
    @ResponseBody
    public NewsVo add(String title, String keyword, String top, String publish, MultipartFile file, String content, HttpServletRequest request, HttpSession session){
        System.out.println(" title:"+ title+" keyword:"+ keyword+" top:"+ top+" publish:"+ publish);

        return newsService.add(1,title, keyword, top, publish, file, content,request,session);
    }
    /**
     * 更新
     * @return
     */
    @Privilege(value = "眼镜二三事")
    @RequestMapping("/update")
    @ResponseBody
    public NewsVo update(String code,String title, String keyword, String top, String publish, MultipartFile file, String content, HttpServletRequest request, HttpSession session){
        System.out.println(" title:"+ title+" keyword:"+ keyword+" top:"+ top+" publish:"+ publish);
        return newsService.update(code,1,title, keyword, top, publish, file, content,request,session);
    }




    /**
     * 文章列表[眼镜二三事]获取当前登录用户的
     * @return
     */
    @Privilege
    @RequestMapping("/list1")
    @ResponseBody
    public List<NewsVo> list1(HttpSession session){

        return newsService.list1(session,1);
    }

    /**
     * 删除二三事
     * @param code
     * @return
     */
    @Privilege(value = "眼镜二三事")
    @RequestMapping("/delete1")
    @ResponseBody
    public boolean delete1(String code,HttpServletRequest request){
        return newsService.delete(code,request);
    }
    /**
     * 删除二三事图片
     * @param code
     * @return
     */
    @Privilege(value = "眼镜二三事")
    @RequestMapping("/deletephoto")
    @ResponseBody
    public boolean deletePhoto(String code,HttpServletRequest request){
        return newsService.deletePhoto(code,request);
    }


    /**
     * 通过编号获取二三事
     * @param code
     * @return
     */
    @RequestMapping("/loadById")
    @ResponseBody
    public NewsVo loadById(String code){
        return newsService.loadById(code);
    }


    /**
     * 文章列表[眼镜二三事]
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<NewsVo> list(){
        return newsService.list(1);
    }



}
