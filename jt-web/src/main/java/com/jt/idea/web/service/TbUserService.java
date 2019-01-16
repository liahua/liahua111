package com.jt.idea.web.service;

import com.jt.idea.common.po.TbUser;

public interface TbUserService {
    void saveUser(TbUser tbUser);

    String findUser(TbUser tbUser);
}
