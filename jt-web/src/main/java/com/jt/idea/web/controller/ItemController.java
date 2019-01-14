package com.jt.idea.web.controller;

import com.jt.idea.common.po.TbItem;
import com.jt.idea.web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/{itemId}")
    public String itemsModel(@PathVariable Long itemId, Model model){
        TbItem item=itemService.findItemById(itemId);
        model.addAttribute("item", item);
        return "item";
    }
}

