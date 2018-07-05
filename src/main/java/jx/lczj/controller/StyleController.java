package jx.lczj.controller;

import jx.lczj.anotation.Privilege;
import jx.lczj.model.T_style;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import jx.lczj.service.StyleService;
import java.util.List;

@Controller
@RequestMapping("style")

public class StyleController {

    //@Autowired 注释，它可以对类成员变量、方法及构造函数进行标注，
    @Autowired
    StyleService styleService;

    /**
    *样式设计信息
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<T_style> list(){
        return  styleService.loadList();
    }

    /**
     * 添加
     * @param style
     * @param name
     * @return
     */
    @Privilege(value = "设计样式管理")
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(int style ,String name){
        return styleService.add(style,name);
    }

    /**
     * 更新
     * @param style
     * @param name
     * @return
     */
    @Privilege(value = "设计样式管理")
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(int style,String name){
        return styleService.update(style,name);
    }

    /**
     * 删除
     * @param style
     * @return
     */
    @Privilege(value = "设计样式管理")
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(int style){
        return styleService.delete(style);
    }

}
