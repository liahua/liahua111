package com.jt.idea.web.service;

import com.jt.idea.common.po.TbCart;

import java.util.List;

public interface CartService {


    void deleteCart(TbCart tbCart);

    void updateCartNum(TbCart tbCart);

    void saveCart(TbCart tbCart);

    List<TbCart> findCartListById(Long userId);
}
