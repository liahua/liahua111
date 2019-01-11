package com.jt.idea.manage.controller;

import com.jt.idea.common.po.TbItem;
import com.jt.idea.common.po.TbItemDesc;
import com.jt.idea.common.vo.EasyUIResult;


import com.jt.idea.common.vo.SysResult;
import com.jt.idea.manage.service.TbItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/item")
public class TbItemController {
    @Autowired
    private TbItemService tbItemService;


    @RequestMapping("/query")
    @ResponseBody
    public EasyUIResult findItem(int page, int rows) {
        return tbItemService.findItemAll(page, rows);
    }

    @RequestMapping("/save")
    @ResponseBody
    public SysResult saveItem(TbItem item, String desc) {

        try {
            tbItemService.saveItem(item, desc);
            return SysResult.oK(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "商品创建失败");
    }

    @RequestMapping("/update")
    @ResponseBody
    public SysResult updateItem(TbItem item, String desc) {
        try {
            tbItemService.updateItem(item, desc);
            return SysResult.oK(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "更新失败");


    }

    @RequestMapping("/delete")
    @ResponseBody
    public SysResult deleteItem(Long... ids) {

        try {
            tbItemService.deleteItem(ids);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SysResult.build(201, "商品删除失败");
    }

    @RequestMapping("/instock")
    @ResponseBody
    public SysResult instock(Long... ids) {
        try {
            tbItemService.insockItem(ids);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SysResult.build(201, "商品下架失败");
    }

    @RequestMapping("/reshelf")
    @ResponseBody
    public SysResult reshelf(Long... ids) {
        try {
            tbItemService.reshelfItem(ids);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SysResult.build(201, "商品上架失败");
    }

    @RequestMapping("/query/item/desc/{id}")
    @ResponseBody
    public SysResult queryDesc(@PathVariable Long id) {

        try {
            TbItemDesc tbItemDesc = tbItemService.queryDesc(id);
            return SysResult.build(200, "查询成功", tbItemDesc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "商品查询失败");
    }


}
