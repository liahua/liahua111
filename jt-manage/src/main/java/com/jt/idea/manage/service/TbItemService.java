package com.jt.idea.manage.service;


import com.jt.idea.common.po.TbItem;
import com.jt.idea.common.po.TbItemDesc;
import com.jt.idea.common.vo.EasyUIResult;

public interface TbItemService {
    EasyUIResult findItemAll(int page, int rows);

    String queryItName(Long itemId);

    void saveItem(TbItem item, String desc);

    void updateItem(TbItem item, String desc);

    void deleteItem(Long[] ids);

    void insockItem(Long[] ids);

    TbItemDesc queryDesc(Long id);

    void reshelfItem(Long[] ids);

    TbItem findItemById(Long itemId);
}
