package jx.lczj.controller;

import jx.lczj.service.MywearService;
import jx.lczj.viewmodel.MywearVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 14260 on 2018/7/9.
 */


@Controller
@RequestMapping("mywear")
public class MywearController {


    @Autowired
    MywearService mywearService;

    /**
     * 添加
     * @param request
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(HttpServletRequest request) {
        return  mywearService.add(request);
    }


    /**
     * 通过试戴编号数组删除
     * @param request
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(HttpServletRequest request) {
        return  mywearService.delete(request);
    }


    /**
     * 通过试戴编号获取试戴详情
     * @param request
     * @return
     */
    @RequestMapping("/loadById")
    @ResponseBody
    public MywearVo loadById(HttpServletRequest request) {
        return  mywearService.loadById(request);
    }

    /**
     * 通过会员编号获取试戴信息
     * @param request
     * @return
     */
    @RequestMapping("/listByCustomer")
    @ResponseBody
    public List<MywearVo> listByCustomer(HttpServletRequest request) {
        return  mywearService.listByCustomer(request);
    }


    /**
     * 更新修改
     * @param request
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(HttpServletRequest request) {
        return  mywearService.update(request);
    }
}
