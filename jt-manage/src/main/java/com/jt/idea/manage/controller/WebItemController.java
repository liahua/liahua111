package com.jt.idea.manage.controller;

import com.jt.idea.common.po.TbItem;
import com.jt.idea.manage.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/item")
public class WebItemController {
    @Autowired
    private TbItemService tbItemService;

    @RequestMapping("/findItemById")
    public TbItem findItemById(Long itemId) {
        return tbItemService.findItemById(itemId);

    }
}
