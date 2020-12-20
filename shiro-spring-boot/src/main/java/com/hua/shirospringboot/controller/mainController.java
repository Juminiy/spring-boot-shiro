package com.hua.shirospringboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.print.DocFlavor;

@Controller
@RequestMapping
public class mainController {

    @RequestMapping({"/index","/"})
    public String index(Model model){
        model.addAttribute("msg","hello,Shiro!");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "user/add" ;
    }

    @RequestMapping("/user/update")
    public String update(){
        return "user/update" ;
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username,String password,Model model){
        Subject subject = SecurityUtils.getSubject();
        // 前端传值 令牌加密
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try{
            subject.login(token);
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }

        return "index";
    }

    @RequestMapping("/noauth")
    @ResponseBody
    public String unauthorized(){
        return "未经授权无法访问此页面";
    }
}
