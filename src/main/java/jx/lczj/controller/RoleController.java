package jx.lczj.controller;

import jx.lczj.model.T_role;
import jx.lczj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 14260 on 2018/6/11.
 */

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping("/list")
    @ResponseBody
    public List<T_role> list(){
       return roleService.loadList();
    }



}
