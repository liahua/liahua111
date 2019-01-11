package com.jt.idea.manage.service.impl;


import com.jt.idea.common.po.*;
import com.jt.idea.common.vo.EasyUIResult;
import com.jt.idea.manage.mapper.TbItemCatMapper;
import com.jt.idea.manage.mapper.TbItemDescMapper;
import com.jt.idea.manage.mapper.TbItemMapper;
import com.jt.idea.manage.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author 李志学
 */
@Service
public class TbItemServiceImpl implements TbItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;


    @Override
    public EasyUIResult findItemAll(int page, int rows) {
        int start = (page - 1) * rows;
        TbItemExample tbItemExample = new TbItemExample();
        tbItemExample.setOffset(start);
        tbItemExample.setLimit(rows);
        tbItemExample.setOrderByClause("updated desc");
        List<TbItem> tbItems = tbItemMapper.selectByExample(tbItemExample);
        int count = tbItemMapper.countByExample(null);
        EasyUIResult easyUIResult = new EasyUIResult(count, tbItems);
        return easyUIResult;
    }

    @Override
    public String queryItName(Long itemId) {

        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        tbItemCatExample.or().andIdEqualTo(itemId);
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(tbItemCatExample);
        return tbItemCats.get(0).getName();
    }

    @Override
    public void saveItem(TbItem item, String desc) {
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        byte status=1;
        item.setStatus(status);
        tbItemMapper.insert(item);

        TbItemDesc tbItemDesc = new TbItemDesc();

        tbItemDesc.setItemId(item.getId());
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(item.getCreated());
        tbItemDesc.setUpdated(item.getUpdated());

        tbItemDescMapper.insert(tbItemDesc);

    }

    @Override
    public void updateItem(TbItem item, String desc) {
        item.setUpdate(true);
        tbItemMapper.updateByPrimaryKeySelective(item);
    }

    @Override
    public void deleteItem(Long[] ids) {
        TbItemExample tbItemExample = new TbItemExample();
        tbItemExample.or().andIdIn(Arrays.asList(ids));
        tbItemMapper.deleteByExample(tbItemExample);
        TbItemDescExample tbItemDescExample = new TbItemDescExample();
        tbItemDescExample.or().andItemIdIn(Arrays.asList(ids));
        tbItemDescMapper.deleteByExample(tbItemDescExample);
    }

    @Override
    public void insockItem(Long[] ids) {
        changeStatus((byte) 2, ids);
    }

    @Override
    public TbItemDesc queryDesc(Long id) {
        TbItemDescExample tbItemDescExample = new TbItemDescExample();
        tbItemDescExample.or().andItemIdEqualTo(id);
        List<TbItemDesc> tbItemDescs = tbItemDescMapper.selectByExampleWithBLOBs(tbItemDescExample);
        if(tbItemDescs==null){
            return null;
        }
        return tbItemDescs.get(0);
    }

    @Override
    public void reshelfItem(Long[] ids) {
        changeStatus((byte) 1, ids);
    }

    public void changeStatus(byte status,Long[] ids){
        TbItemExample tbItemExample = new TbItemExample();
        tbItemExample.or().andIdIn(Arrays.asList(ids));
        TbItem tbItem = new TbItem();
        tbItem.setStatus(status);
        tbItem.setUpdate(true);
        tbItemMapper.updateByExampleSelective(tbItem, tbItemExample);
    }


}
