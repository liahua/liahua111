package com.jt.jtsso.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.jtsso.mapper.TbUserMapper;
import com.jt.jtsso.po.TbUser;
import com.jt.jtsso.service.TbUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;



    @Override
    public boolean findCheckUser(String param, Integer type) {
        String column = null;
        switch (type) {
            case 1:
                column = "username";
                break;
            case 2:
                column = "phone";
                break;
            case 3:
                column = "email";
                break;
        }
        QueryWrapper<TbUser> tbUserQueryWrapper = new QueryWrapper<>();
        tbUserQueryWrapper.eq(column, param);
        Integer count = tbUserMapper.selectCount(tbUserQueryWrapper);
        System.out.println("count"+count);
        return count == 0 ? false : true;
    }
}
