package jx.lczj.controller;

import jx.lczj.model.T_goods;
import jx.lczj.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("good")
public class GoodController {

    @Autowired
    GoodService goodService;

    /**
     * 商品信息
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<T_goods> list(){
        return goodService.loadlist();
    }

}
