package jx.lczj.controller;

import jx.lczj.anotation.Privilege;
import jx.lczj.model.T_category;
import jx.lczj.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * Created by 14260 on 2018/6/10.
 */


@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * 加载商品类别信息
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<T_category> list(){
        return categoryService.loadList();
    }


    /**
     * 添加商品类别信息
     * @param category
     * @param name
     * @return
     */
    @Privilege(value = "商品类别")
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(int category,String name){
        return categoryService.add(category,name);
    }


    /**
     * 更新商品类别信息
     * @param category
     * @param name
     * @return
     */
    @Privilege(value = "商品类别")
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(int category,String name){
        return categoryService.update(category,name);
    }

    /**
     * 删除商品类别信息
     * @param category
     * @return
     */
    @Privilege(value = "商品类别")
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(String category){
        return categoryService.delete(category);
    }


}
