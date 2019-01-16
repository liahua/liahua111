package com.jt.jtsso.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.jtsso.po.TbUser;
import com.jt.jtsso.service.TbUserService;
import com.jt.jtsso.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TbUserService tbUserService;
    @Autowired
    private JedisCluster jedisCluster;

    @RequestMapping("/check/{param}/{type}")
    public JSONPObject register(@PathVariable String param,
                                @PathVariable Integer type,
                                String callback,
                                HttpServletResponse response) {
        boolean flag = tbUserService.findCheckUser(param, type);
        return new JSONPObject(callback, SysResult.oK(flag));
    }

    @RequestMapping(value = "/register")
    public SysResult register(@RequestBody TbUser tbUser) {
        try {
            tbUserService.saveUser(tbUser);

            return SysResult.oK();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "新增用户失败");
    }

    @RequestMapping("/dologin")
    public SysResult findUserByUP(@RequestBody TbUser tbUser) {
        try {
            String token = tbUserService.findUserByUP(tbUser);
            if (!StringUtils.isEmpty(token)) {
                return SysResult.oK(token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "登陆失败");
    }

    private HttpServletResponse setCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("JT_TICKET", token);
        cookie.setMaxAge(3600 * 24 * 7);
        cookie.setPath("/");//表示当前网站根目录
        response.addCookie(cookie);
        return response;
    }

    @RequestMapping("/query/{ticket}")
    public JSONPObject query(String callback, @PathVariable String ticket) {
        String userJson = jedisCluster.get(ticket);
        return new JSONPObject(callback, SysResult.oK(userJson));
    }


}
