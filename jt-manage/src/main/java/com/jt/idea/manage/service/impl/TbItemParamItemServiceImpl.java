package com.jt.idea.manage.service.impl;

import com.jt.idea.common.po.TbItemParamItem;
import com.jt.idea.common.po.TbItemParamItemExample;
import com.jt.idea.manage.mapper.TbItemParamItemMapper;
import com.jt.idea.manage.service.TbItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbItemParamItemServiceImpl implements TbItemParamItemService {
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public TbItemParamItem queryItemParamItem(Long itemId) {
        TbItemParamItemExample tbItemParamItemExample = new TbItemParamItemExample();
        tbItemParamItemExample.or().andItemIdEqualTo(itemId);
        List<TbItemParamItem> tbItemParamItems = tbItemParamItemMapper.selectByExampleWithBLOBs(tbItemParamItemExample);
        if(null == tbItemParamItems){
            return null;
        }
        return tbItemParamItems.get(0);
    }
}
