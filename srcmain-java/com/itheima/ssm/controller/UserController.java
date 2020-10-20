package com.itheima.ssm.controller;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.security.mscapi.CPublicKey;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    //查询用户以及用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userid) throws Exception{
        ModelAndView mv = new ModelAndView();
        //1.根据用户id查询用户
        UserInfo userInfo = userService.findById(userid);
        //2.根据用户id查询可以添加的角色
        List<Role> otherRoles = userService.findOtherRoles(userid);

        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }


    //给用户添加信息
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId
            ,@RequestParam(name = "ids", required = true) String[] roleIds) throws Exception {
        userService.addRoleToUser(userId,roleIds);//通过userService调用addRoleToUser

        return "redirect:finAll.do";
    }



























    //查询指定id的用户
    @RequestMapping("/findById.do")
    public  ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    //用户添加
    @RequestMapping("/findAll.do")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }






    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();//调用service查询所有用户并且封装到List集合
        mv.addObject("userList",userList);
        mv.setViewName("user-list");
        return mv;
    }
}