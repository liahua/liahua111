package com.jt.idea.web.controller;

import com.jt.idea.common.po.TbUser;
import com.jt.idea.common.vo.SysResult;
import com.jt.idea.web.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private TbUserService tbUserService;

    @RequestMapping("/{modul}")
    public String forward(@PathVariable String modul) {
        return modul;
    }

    @RequestMapping("/doRegister")
    @ResponseBody
    public SysResult saveUser(TbUser tbUser) {
        try {
            tbUserService.saveUser(tbUser);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SysResult.build(201, "新增用户失败");
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public SysResult doLogin(TbUser tbUser, HttpServletResponse resp){
        try {
            String token = tbUserService.findUser(tbUser);
            if(!StringUtils.isEmpty(token)){
                setCookie(resp, token);
                return SysResult.oK();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "登陆失败");
    }

    public void setCookie(HttpServletResponse resp, String token) {
        Cookie cookie = new Cookie("JT_TICKET", token);
        cookie.setPath("/");
        cookie.setMaxAge(7*24*3600);
        resp.addCookie(cookie);
    }

    @RequestMapping("/logout")
    public String logout(){
        System.out.println("UserController.logout");
        return "redirect:/index.html";
    }

}
