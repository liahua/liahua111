package com.jt.jtsso.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.jtsso.mapper.TbUserMapper;
import com.jt.jtsso.po.TbUser;
import com.jt.jtsso.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.JedisCluster;

import java.util.Date;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JedisCluster jedisCluster;


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

    /**
     * 1.确认在点击注册时,是否账号已经被注册
     * 2.user信息落地
     * @param tbUser
     */
    @Override
    @Transactional //开启事务
    public void saveUser(TbUser tbUser) {
        //todo 待完成数据一致性查询
        tbUser.setEmail(tbUser.getPhone());
        tbUser.setCreated(new Date());
        tbUser.setUpdated(tbUser.getCreated());
        tbUserMapper.insert(tbUser);
    }

    @Override
    public String findUserByUP(TbUser tbUser) {
        QueryWrapper<TbUser> tbUserQueryWrapper = new QueryWrapper<>(tbUser);
        TbUser userMessage = tbUserMapper.selectOne(tbUserQueryWrapper);
        if (userMessage != null) {
            //加盐userMessage生成token
           String token="JT_TICKET"+System.currentTimeMillis()+tbUser.getUsername();
           token= DigestUtils.md5DigestAsHex(token.getBytes());
            try {
                String userJson = objectMapper.writeValueAsString(tbUser);
                jedisCluster.setex(token, 3600*7*24, userJson);
                return token;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }
        return null;
    }


}
