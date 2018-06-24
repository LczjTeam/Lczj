package jx.lczj.controller;

import jx.lczj.anotation.Privilege;
import jx.lczj.service.NewsService;
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
@RequestMapping("lczj")
public class LczjController {

    @Autowired
    NewsService newsService;



    /**
     * 添加
     * @return
     */
    @Privilege(value = "乐潮之镜")
    @RequestMapping("/add")
    @ResponseBody
    public NewsVo add(String title, String keyword, String top, String publish, MultipartFile file, String content, HttpServletRequest request, HttpSession session){
        System.out.println(" title:"+ title+" keyword:"+ keyword+" top:"+ top+" publish:"+ publish);

        return newsService.add(0,title, keyword, top, publish, file, content,request,session);
    }
    /**
     * 更新
     * @return
     */
    @Privilege(value = "乐潮之镜")
    @RequestMapping("/update")
    @ResponseBody
    public NewsVo update(String code,String title, String keyword, String top, String publish, MultipartFile file, String content, HttpServletRequest request, HttpSession session){
        System.out.println(" title:"+ title+" keyword:"+ keyword+" top:"+ top+" publish:"+ publish);
        return newsService.update(code,title, keyword, top, publish, file, content,request,session);
    }

    /**
     * 文章列表[乐潮之镜]
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<NewsVo> list1(HttpSession session){

        return newsService.list1(session,0);
    }

    /**
     * 删除二三事
     * @param code
     * @return
     */
    @Privilege(value = "乐潮之镜")
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete1(String code,HttpServletRequest request){
        return newsService.delete(code,request);
    }


    /**
     * 删除二三事
     * @param code
     * @return
     */
    @RequestMapping("/loadById")
    @ResponseBody
    public NewsVo loadById(String code){
        return newsService.loadById(code);
    }

}
