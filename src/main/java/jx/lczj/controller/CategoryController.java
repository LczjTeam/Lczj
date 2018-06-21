package jx.lczj.controller;

import jx.lczj.dao.CategoryDao;
import jx.lczj.model.T_admin;
import jx.lczj.model.T_category;
import jx.lczj.service.AdminService;
import jx.lczj.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
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
     * @param occasion
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(int category,String name,String occasion){
        return categoryService.add(category,name,occasion);
    }


    /**
     * 更新商品类别信息
     * @param category
     * @param name
     * @param occasion
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(int category,String name,String occasion){
        return categoryService.update(category,name,occasion);
    }

    /**
     * 删除商品类别信息
     * @param category
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(String category){
        return categoryService.delete(category);
    }


}
