package com.jt.idea.manage.controller;


import com.jt.idea.common.po.TbItemParamItem;
import com.jt.idea.common.vo.SysResult;
import com.jt.idea.manage.service.TbItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item/param/item")
public class TbItemParamItemController {

    @Autowired
    private TbItemParamItemService tbItemParamItemService;

    @RequestMapping("/query/{itemId}")
    @ResponseBody
    public SysResult queryItemParamItem(@PathVariable Long itemId){
        try {
            TbItemParamItem tbItemParamItem =tbItemParamItemService.queryItemParamItem(itemId);
            return SysResult.build(200, "查询成功", tbItemParamItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "商品查询失败");

    }


}
