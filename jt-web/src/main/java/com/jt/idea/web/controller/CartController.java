package com.jt.idea.web.controller;

import com.jt.idea.common.po.TbCart;
import com.jt.idea.web.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @RequestMapping("/show")
    public String show() {
        return "cart";
    }

    /**
     * 需求:
     * 1.在无登陆状态时,可以添加到商品到购物车
     * 2.点击结算时,跳转到登陆界面,登陆成功,将原购物车中商品添加到账号下购物车,并跳转到购物车页面
     * 2.在登陆状态时,可以添加到商品到购物车
     *
     *
     *
     * @param itemId
     * @param tbCart
     * @return
     */
    @RequestMapping("/add/{itemId}")
    public String add(@PathVariable Long itemId, TbCart tbCart) {
        try {
            cartService.addItem(tbCart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("tbCart"+tbCart);
        System.out.println("itemId"+itemId);
       return "redirect:/items/"+itemId+".html";
    }
}
