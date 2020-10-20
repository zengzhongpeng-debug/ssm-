package com.itheima.ssm.controller;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProdictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;
@Controller
@RequestMapping("/product")
public class ProductController {
    //查询全部产品
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")//表示当前findAll方法只有ADMIN角色可以访问
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = prodictService.findAll();
        mv.addObject("productList",ps);
        mv.setViewName("product-list1");
        return mv;
    }

    //产品添加
    @RequestMapping("save.do")
    public String save(Product pruduct) throws Exception {
        prodictService.save(pruduct);
        return "redirect:findAll.do";
    }

    @Autowired
    private IProdictService prodictService;


}



































