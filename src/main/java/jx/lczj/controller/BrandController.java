package jx.lczj.controller;

import jx.lczj.dao.BrandDao;
import jx.lczj.model.T_brand;
import jx.lczj.service.AdminService;
import jx.lczj.service.BrandService;
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
@RequestMapping("brand")
public class BrandController {

    @Autowired
   BrandService brandService;

    /**
     * 加载品牌信息
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<T_brand> list(){
        return brandService.loadList();
    }


    /**
     * 添加品牌信息
     * @param brand
     * @param name
     * @param company
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(int brand,String name,String company){
        return brandService.add(brand,name,company);
    }


    /**
     * 更新品牌信息
     * @param brand
     * @param name
     * @param company
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(int brand,String name,String company){
        return brandService.update(brand,name,company);
    }

    /**
     * 删除品牌信息
     * @param brand
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(String brand){
        return brandService.delete(brand);
    }


}
