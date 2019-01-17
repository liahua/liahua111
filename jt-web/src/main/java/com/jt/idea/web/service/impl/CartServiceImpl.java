package com.jt.idea.web.service.impl;

import com.jt.idea.common.po.TbCart;
import com.jt.idea.common.service.HttpClientService;
import com.jt.idea.web.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private HttpClientService httpClientService;

    @Override
    public void addItem(TbCart tbCart) {
        String uri = "http://cart.jt.com/cart/add";

    }
}
