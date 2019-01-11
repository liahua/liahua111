package com.jt.idea.manage.service;

import com.jt.idea.manage.vo.EasyUITree;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TbItemCatService {

    List<EasyUITree> findItemAll(Long parentId);
}
