package com.jt.jtsso.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.jtsso.service.TbUserService;
import com.jt.jtsso.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TbUserService tbUserService;

    @RequestMapping("/check/{param}/{type}")
    public JSONPObject register(@PathVariable String param,
                                @PathVariable Integer type,
                                String callback,
                                HttpServletResponse response){
        boolean flag=tbUserService.findCheckUser(param,type);
        return new JSONPObject(callback, SysResult.oK(flag));
    }
}
