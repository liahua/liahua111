package com.jt.idea.web.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.idea.common.po.TbCart;
import com.jt.idea.common.service.HttpClientService;
import com.jt.idea.common.vo.SysResult;
import com.jt.idea.web.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private HttpClientService httpClientService;
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public void deleteCart(TbCart tbCart) {
        String uri = "http://cart.jt.com/cart/deleteCart";
        try {
            String userIdJson = userIdJson = objectMapper.writeValueAsString(tbCart);
            httpClientService.doPost(uri, userIdJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCartNum(TbCart tbCart) {


        String url =
                "http://cart.jt.com/cart/update/num/"
                        + tbCart.getUserId()
                        + "/" + tbCart.getItemId()
                        + "/" + tbCart.getNum();

        httpClientService.doget(url);


    }

    @Override
    public void saveCart(TbCart tbCart) {
        String uri = "http://cart.jt.com/cart/saveCart";
        try {
            String userIdJson = userIdJson = objectMapper.writeValueAsString(tbCart);
            httpClientService.doPost(uri, userIdJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TbCart> findCartListById(Long userId) {
        String uri = "http://cart.jt.com/cart/findCartListById";
        try {
            String userIdJson = objectMapper.writeValueAsString(userId);
            String sysResultJson = httpClientService.doPost(uri, userIdJson);
            SysResult sysResult = objectMapper.readValue(sysResultJson, SysResult.class);
            if (sysResult.getStatus() == 200) {
                return (List<TbCart>) sysResult.getData();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
