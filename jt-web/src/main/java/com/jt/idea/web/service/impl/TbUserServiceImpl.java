package com.jt.idea.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.idea.common.po.TbUser;
import com.jt.idea.common.service.HttpClientService;
import com.jt.idea.common.vo.SysResult;
import com.jt.idea.web.service.TbUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private HttpClientService httpClientService;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void saveUser(TbUser tbUser) {
        if (tbUser != null) {
            String uri="http://sso.jt.com/user/register";
            try {
                SysResult sysResult=postFindUserByUri(tbUser, uri);
                if (sysResult.getStatus()!=200) {
                    throw new RuntimeException();
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
    }

    @Override
    public String findUser(TbUser tbUser) {
        String data =null;
        if (tbUser != null) {
            String uri="http://sso.jt.com/user/dologin";
            try {

                SysResult sysResult=postFindUserByUri(tbUser, uri);
                data = (String) sysResult.getData();
                if (sysResult.getStatus()!=200) {
                    throw new RuntimeException();
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        return data;
    }

    public SysResult postFindUserByUri(TbUser tbUser, String uri) throws IOException {
        String password = tbUser.getPassword();
        String md5Pass = DigestUtils.md5Hex(password);
        tbUser.setPassword(md5Pass);
        String tbUserJson = objectMapper.writeValueAsString(tbUser);
        String sysResultJson=httpClientService.doPost(uri,tbUserJson);
        SysResult sysResult = objectMapper.readValue(sysResultJson, SysResult.class);
        return sysResult;
    }
}
