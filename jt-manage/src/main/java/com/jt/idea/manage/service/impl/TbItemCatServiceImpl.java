package com.jt.idea.manage.service.impl;

import com.jt.idea.common.po.TbItemCat;
import com.jt.idea.common.po.TbItemCatExample;

import com.jt.idea.manage.mapper.TbItemCatMapper;
import com.jt.idea.manage.service.TbItemCatService;
import com.jt.idea.manage.vo.EasyUITree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbItemCatServiceImpl implements TbItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;


    @Override
    public List<EasyUITree> findItemAll(Long parentId) {
        ArrayList<EasyUITree> easyUITrees = new ArrayList<>();
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        tbItemCatExample.or().andParentIdEqualTo(parentId);
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(tbItemCatExample);
        for (TbItemCat tbItemCat : tbItemCats) {
            EasyUITree easyUITree = new EasyUITree();
            easyUITree.setId(tbItemCat.getId());
            easyUITree.setText(tbItemCat.getName());
            easyUITree.setState(tbItemCat.getIsParent() ? "closed" : "open");
            easyUITrees.add(easyUITree);
        }
        System.out.println("easyUITrees"+easyUITrees);
        return easyUITrees;
    }


}
