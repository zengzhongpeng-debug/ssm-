package com.itheima.ssm.controller;
import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequestMapping("/orders" )
public class OrdersController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String ordersId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = orderService.findById(ordersId);//findById根据指定的ordersId查询订单学习
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }

    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page",required = true, defaultValue = "1")
                                            Integer page,@RequestParam(name = "size", required = true, defaultValue = "4")
                                                    Integer size ) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = orderService.findAll(page,size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("order-page-list");
        return mv;
    }






/*
    //查询全部订单--未分页
   @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = orderService.findAll();
        mv.addObject("ordersList",ordersList);
        mv.setViewName("orders-list");//要跳转到的页面
        return null;
    }*/

}
