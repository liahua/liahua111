package com.jt.idea.manage.controller;


import com.jt.idea.manage.service.TbItemCatService;
import com.jt.idea.manage.service.TbItemService;
import com.jt.idea.manage.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/item")
public class TbItemCatController {

    @Autowired
    private TbItemService tbItemService;
    @Autowired
    private TbItemCatService tbItemCatService;



    @RequestMapping(value = "/cat/queryItemName", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String queryItemName(Long itemId) {
        return tbItemService.queryItName(itemId);
    }

    @RequestMapping("/cat/list")
    public @ResponseBody List<EasyUITree> findItemAll(@RequestParam(defaultValue = "0",value = "id") Long parentId) {
        return tbItemCatService.findItemAll(parentId);
    }

}
