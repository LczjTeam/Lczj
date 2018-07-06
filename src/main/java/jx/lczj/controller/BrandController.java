package jx.lczj.controller;

import jx.lczj.anotation.Privilege;
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
     * 加载所有品牌信息
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<T_brand> list(){
        return brandService.loadList();
    }

    /**
     * 加载镜片品牌信息
     */
    @RequestMapping("/list1")
    @ResponseBody
    public List<T_brand> list1(){
        return brandService.loadList1();
    }

    /**
     * 加载镜框品牌信息
     */
    @RequestMapping("/list2")
    @ResponseBody
    public List<T_brand> list2(){
        return brandService.loadList2();
    }


    /**
     * 添加品牌信息
     * @param brand
     * @param name
     * @param company
     * @return
     */
    @Privilege(value = "品牌管理")
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(int brand,String name,String company,int type){
        return brandService.add(brand,name,company,type);
    }


    /**
     * 更新品牌信息
     * @param brand
     * @param name
     * @param company
     * @return
     */
    @Privilege(value = "品牌管理")
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(int brand,String name,String company,int type){
        return brandService.update(brand,name,company,type);
    }

    /**
     * 删除品牌信息
     * @param brand
     * @return
     */
    @Privilege(value = "品牌管理")
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(String brand){
        return brandService.delete(brand);
    }


}
