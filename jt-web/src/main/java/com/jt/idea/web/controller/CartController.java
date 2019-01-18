package com.jt.idea.web.controller;

import com.jt.idea.common.po.TbCart;
import com.jt.idea.common.vo.SysResult;
import com.jt.idea.web.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.CharArrayReader;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @RequestMapping("/show")
    public String findCartListById(Model model) {
        Long userId=7L;
        List<TbCart> cartList=cartService.findCartListById(userId);
        model.addAttribute("cartList", cartList);
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
     *
     * @param tbCart
     * @return
     */
    @RequestMapping("/add/{itemId}")
    public String saveCart(TbCart tbCart) {
        try {
            Long userId=7L;
            tbCart.setUserId(userId);
            cartService.saveCart(tbCart);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return "redirect:/cart/show.html";
    }

    @RequestMapping("/delete/{itemId}")
    public String deleteCart(@PathVariable Long itemId, TbCart tbCart) {
        try {
            Long userId=7L;
            tbCart.setUserId(userId);
            cartService.deleteCart(tbCart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/cart/show.html";
    }

    @RequestMapping("/update/num/{itemId}/{num}")
    @ResponseBody
    public SysResult updateCartNum(@PathVariable Long itemId, TbCart tbCart) {
        try {
            Long userId=7L;
            tbCart.setUserId(userId);
            cartService.updateCartNum(tbCart);
            SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "商品数量修改失败");
    }

}
