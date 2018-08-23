package com.czxx.campusmanagement.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.czxx.campusmanagement.entity.User;
import com.czxx.campusmanagement.in.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Resource(name = "userService")
	UserService userService;
	
	@RequestMapping(value = "/list")
    public ModelAndView list()
    {
        ModelAndView mv=new ModelAndView();
        List<User>  userList=userService.getUser();
        mv.addObject("userList",userList);
        mv.setViewName("/show");
        return mv;
    }

}
