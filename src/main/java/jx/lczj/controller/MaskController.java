package jx.lczj.controller;

import com.sun.deploy.panel.ITreeNode;
import jdk.nashorn.internal.ir.ReturnNode;
import jx.lczj.anotation.Privilege;
import jx.lczj.model.T_mask;
import jx.lczj.service.MaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("mask")
public class MaskController {

    @Autowired
    MaskService maskService;

    @RequestMapping("/list")
    @ResponseBody
    public List<T_mask> list(){
        return maskService.loadList();
    }

    /**
     * 添加膜层信息
     * @param name
     * @return
     */
    @Privilege(value = "膜层管理")
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(int mask,String name){
        return maskService.add(mask,name);
    }
    @Privilege(value = "膜层管理")
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(String mask){return maskService.delete(mask);}

    @Privilege(value = "膜层管理")
    @RequestMapping("/update")
     @ResponseBody
    public boolean update(int mask,String name){return maskService.update(mask,name);}


}
