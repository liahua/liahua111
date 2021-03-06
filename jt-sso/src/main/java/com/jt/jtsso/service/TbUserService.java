package com.jt.jtsso.service;

import com.jt.jtsso.po.TbUser;

public interface TbUserService {
    /**
     * 返回结果为true代表数据库中已有该数据
     * 返回结果为false代表数据库中无该数据
     *
     *
     * @param param  待验证参数
     * @param type   1.username 2.phone 3.email
     * @return
     */
    boolean findCheckUser(String param, Integer type);

    /**
     * 无法保证数据一致性
     *
     * @param tbUser
     */
    void saveUser(TbUser tbUser);


    String findUserByUP(TbUser tbUser);
}
