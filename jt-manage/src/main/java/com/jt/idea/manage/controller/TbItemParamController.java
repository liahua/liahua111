package com.jt.idea.manage.controller;

import com.jt.idea.common.vo.EasyUIResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item/param")
public class TbItemParamController {
    @RequestMapping("/list")
    @ResponseBody
    public EasyUIResult list(int page, int rows) {
//TODO 没写完
        return null;
    }
}

